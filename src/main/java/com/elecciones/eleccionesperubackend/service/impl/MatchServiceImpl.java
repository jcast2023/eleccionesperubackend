package com.elecciones.eleccionesperubackend.service.impl;

import com.elecciones.eleccionesperubackend.dto.*;
import com.elecciones.eleccionesperubackend.model.*;
import com.elecciones.eleccionesperubackend.repository.CandidatoRepository;
import com.elecciones.eleccionesperubackend.repository.PreguntaRepository;
import com.elecciones.eleccionesperubackend.service.MatchService;
import com.elecciones.eleccionesperubackend.service.TextSimilarityService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchServiceImpl implements MatchService {

    private final CandidatoRepository candidatoRepository;
    private final PreguntaRepository preguntaRepository;
    private final TextSimilarityService textSimilarityService;

    // 🔥 PONDERACIÓN POR CATEGORÍAS (NIVEL TESIS)
    private final Map<String, Double> pesos = Map.of(
            "Seguridad", 1.3,
            "Corrupción", 1.3,
            "Educación", 1.2,
            "Salud", 1.2,
            "Economía", 1.1,
            "Infraestructura", 1.0,
            "Medio Ambiente", 1.0,
            "Estado", 1.0,
            "Social", 1.0,
            "Libertad", 1.0
    );

    public MatchServiceImpl(CandidatoRepository candidatoRepository,
                            PreguntaRepository preguntaRepository,
                            TextSimilarityService textSimilarityService) {
        this.candidatoRepository = candidatoRepository;
        this.preguntaRepository = preguntaRepository;
        this.textSimilarityService = textSimilarityService;
    }

    @Override
    public List<MatchResultDTO> calcularMatch(Map<String, Integer> respuestasUsuario) {

        Map<String, Integer> respuestasPorCategoria = convertirRespuestas(respuestasUsuario);

        List<Candidato> candidatos = candidatoRepository.findAllWithDenuncias();

        return candidatos.stream()
                .map(c -> {

                    double puntaje = calcularPuntaje(c, respuestasPorCategoria);

                    double penalizacion = 0;
                    List<DenunciaDTO> denunciasDTO = new ArrayList<>();

                    int condenas = 0;
                    int investigaciones = 0;

                    // 🔥 PENALIZACIÓN INTELIGENTE
                    if (c.getDenuncias() != null) {
                        for (Denuncia d : c.getDenuncias()) {

                            double factor = d.getGravedad() * 0.02;

                            switch (d.getEstado()) {
                                case EN_CURSO -> {
                                    penalizacion += factor * (1 + d.getGravedad() / 5.0);
                                    investigaciones++;
                                }
                                case CONDENA -> {
                                    penalizacion += factor * 2 * (1 + d.getGravedad() / 5.0);
                                    condenas++;
                                }
                                case ARCHIVADA -> penalizacion += factor * 0.5;
                            }

                            denunciasDTO.add(new DenunciaDTO(
                                    d.getDescripcion(),
                                    d.getEstado().name(),
                                    d.getGravedad(),
                                    d.getFuenteUrl(),
                                    d.getFecha(),
                                    d.getTipo(),
                                    d.getFuente()
                            ));
                        }
                    }

                    // 🔥 LIMITE DE PENALIZACIÓN
                    penalizacion = Math.min(penalizacion, 0.4);

                    puntaje -= penalizacion;
                    if (puntaje < 0) puntaje = 0;

                    // 🔥 COINCIDENCIAS
                    Set<String> coincidenciasSet = new HashSet<>();
                    Set<String> diferenciasSet = new HashSet<>();

                    if (c.getPropuestas() != null) {
                        for (Propuesta p : c.getPropuestas()) {

                            Integer valorUsuario = respuestasPorCategoria.get(p.getCategoria());

                            if (valorUsuario != null) {

                                double diferencia = Math.abs(valorUsuario - p.getValor());

                                if (diferencia <= 1) {
                                    coincidenciasSet.add(p.getCategoria());
                                } else {
                                    diferenciasSet.add(p.getCategoria());
                                }
                            }
                        }
                    }

                    List<String> coincidencias = new ArrayList<>(coincidenciasSet);
                    List<String> diferencias = new ArrayList<>(diferenciasSet);

                    // 🔥 REDONDEO PROFESIONAL
                    double porcentaje = Math.round(puntaje * 1000) / 10.0;

                    // 🔥 RIESGO
                    String riesgo;
                    if (condenas > 0) riesgo = "ALTO";
                    else if (investigaciones > 0) riesgo = "MEDIO";
                    else riesgo = "BAJO";

                    // 🔥 MENSAJE OPTIMIZADO
                    String mensaje;
                    if (porcentaje >= 80)
                        mensaje = "Alta afinidad política con " + c.getNombre();
                    else if (porcentaje >= 60)
                        mensaje = "Buena afinidad con " + c.getNombre();
                    else if (porcentaje >= 40)
                        mensaje = "Afinidad moderada con " + c.getNombre();
                    else
                        mensaje = "Baja coincidencia con " + c.getNombre();

                    // 🔥 ALERTA POR RIESGO
                    if (!denunciasDTO.isEmpty()) {
                        if (riesgo.equals("ALTO")) {
                            mensaje += " ❌ Presenta antecedentes con sentencia.";
                        } else if (riesgo.equals("MEDIO")) {
                            mensaje += " ⚠ Tiene investigaciones en curso.";
                        }
                    }

                    String explicacion = generarExplicacion(coincidencias, diferencias);

                    return new MatchResultDTO(
                            c.getNombre(),
                            porcentaje,
                            mensaje,
                            coincidencias,
                            diferencias,
                            explicacion,
                            denunciasDTO,
                            riesgo,
                            denunciasDTO.size()
                    );

                })
                .sorted(Comparator.comparingDouble(MatchResultDTO::porcentaje).reversed())
                .limit(3)
                .toList();

    }

    // 🔥 EXPLICACIÓN
    private String generarExplicacion(List<String> coincidencias, List<String> diferencias) {

        StringBuilder sb = new StringBuilder();

        if (!coincidencias.isEmpty()) {
            sb.append("Coincides en temas clave como ");
            sb.append(String.join(", ", coincidencias));
            sb.append(". ");
        }

        if (!diferencias.isEmpty()) {
            sb.append("Existen diferencias en ");
            sb.append(String.join(", ", diferencias));
            sb.append(", lo que reduce afinidad.");
        }

        return sb.toString();
    }

    // 🔥 CONVERSIÓN DE RESPUESTAS (PROMEDIO POR CATEGORÍA)
    private Map<String, Integer> convertirRespuestas(Map<String, Integer> respuestasUsuario) {

        Map<String, List<Integer>> agrupado = new HashMap<>();

        for (Map.Entry<String, Integer> entry : respuestasUsuario.entrySet()) {

            Long preguntaId = Long.parseLong(entry.getKey());
            Integer valor = entry.getValue();

            Pregunta pregunta = preguntaRepository.findById(preguntaId).orElse(null);

            if (pregunta != null) {
                agrupado
                        .computeIfAbsent(pregunta.getCategoria(), k -> new ArrayList<>())
                        .add(valor);
            }
        }

        Map<String, Integer> resultado = new HashMap<>();

        for (Map.Entry<String, List<Integer>> entry : agrupado.entrySet()) {

            double promedio = entry.getValue()
                    .stream()
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElse(0);

            resultado.put(entry.getKey(), (int) Math.round(promedio));
        }

        return resultado;
    }

    // 🔥 CÁLCULO DE AFINIDAD CON PESOS
    private double calcularPuntaje(Candidato c, Map<String, Integer> usuario) {

        if (c.getPropuestas() == null || c.getPropuestas().isEmpty()) return 0;

        double suma = 0;
        double total = 0;

        for (Propuesta p : c.getPropuestas()) {

            Integer valorUsuario = usuario.get(p.getCategoria());

            if (valorUsuario != null) {

                double diferencia = Math.abs(valorUsuario - p.getValor());

                double peso = pesos.getOrDefault(p.getCategoria(), 1.0);

                suma += (5 - diferencia) * peso;
                total += 5 * peso;
            }
        }

        return total == 0 ? 0 : suma / total;
    }

    @Override
    public ComparacionCompletaDTO compararCandidatosCompleto(Long id1, Long id2) {
        Candidato c1 = candidatoRepository.findByIdWithPropuestas(id1)
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado con id: " + id1));
        Candidato c2 = candidatoRepository.findByIdWithPropuestas(id2)
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado con id: " + id2));

        // 🔥 NUEVO CÁLCULO BASADO EN TEXTO (resumen_largo)
        double similitudTexto = textSimilarityService.calcularSimilitud(
                c1.getResumenLargo(),
                c2.getResumenLargo()
        );
        // Convertir a porcentaje con un decimal
        double porcentajeCoincidencia = Math.round(similitudTexto * 1000) / 10.0;

        // Construir el radar con las propuestas (aunque sean numéricas, no influyen en el %)
        Map<String, Integer> mapa1 = new HashMap<>();
        Map<String, Integer> mapa2 = new HashMap<>();

        for (Propuesta p : c1.getPropuestas()) {
            mapa1.put(p.getCategoria(), p.getValor());
        }
        for (Propuesta p : c2.getPropuestas()) {
            mapa2.put(p.getCategoria(), p.getValor());
        }

        List<String> ordenCategorias = List.of(
                "Seguridad", "Corrupción", "Economía", "Educación",
                "Salud", "Infraestructura", "Medio Ambiente",
                "Estado", "Social", "Libertad"
        );

        List<ComparacionDTO> radar = new ArrayList<>();
        List<String> coincidencias = new ArrayList<>();
        List<String> diferencias = new ArrayList<>();

        for (String cat : ordenCategorias) {
            // Extraer fragmentos relevantes para la categoría
            String frag1 = textSimilarityService.extractFragmentForCategory(c1.getResumenLargo(), cat);
            String frag2 = textSimilarityService.extractFragmentForCategory(c2.getResumenLargo(), cat);

            // Calcular similitud entre los fragmentos (valor entre 0 y 1)
            double sim = textSimilarityService.calcularSimilitud(frag1, frag2);

            // Escalar la similitud a un valor entre 0 y 5 para el radar
            int valorEscalado = (int) Math.round(sim * 5);

            // Agregar el punto al radar (mismo valor para ambos candidatos, refleja grado de coincidencia)
            radar.add(new ComparacionDTO(cat, valorEscalado, valorEscalado));

            // Decidir si es coincidencia o diferencia según umbral (puedes ajustarlo)
            // Decidir si es coincidencia o diferencia según umbral ajustado
            if (sim >= 0.25) {
                coincidencias.add(cat);
            } else {
                diferencias.add(cat);
            }
        }

        return new ComparacionCompletaDTO(
                c1.getNombre(),
                c2.getNombre(),
                radar,
                coincidencias,
                diferencias,
                porcentajeCoincidencia   // ✅ ahora refleja la similitud real del plan de gobierno
        );
    }
}
