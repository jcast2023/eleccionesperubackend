package com.elecciones.eleccionesperubackend.service.impl;

import com.elecciones.eleccionesperubackend.dto.ChatRequest;
import com.elecciones.eleccionesperubackend.model.Candidato;
import com.elecciones.eleccionesperubackend.repository.CandidatoRepository;
import com.elecciones.eleccionesperubackend.service.ChatService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    private final CandidatoRepository candidatoRepo;

    public ChatServiceImpl(CandidatoRepository candidatoRepo) {
        this.candidatoRepo = candidatoRepo;
    }

    @Override
    public String responder(ChatRequest req) {
        Candidato c = candidatoRepo.findById(req.getCandidatoId()).orElse(null);
        if (c == null) {
            return "❌ Candidato no encontrado.";
        }

        boolean esPerfil = esPreguntaPerfil(req.getPregunta());
        String categoriaDetectada = detectarCategoria(req.getPregunta());

        String categoriaFinal = (req.getCategoria() != null && !req.getCategoria().isEmpty())
                ? req.getCategoria().toLowerCase().trim()
                : (categoriaDetectada != null ? categoriaDetectada : "general");

        String textoFuente = esPerfil ? c.getPerfilLargo() : c.getResumenLargo();
        if (textoFuente == null || textoFuente.isEmpty()) {
            return "❌ No hay información suficiente sobre este candidato.";
        }

        String contenidoFiltrado = filtrarPorCategoria(textoFuente, categoriaFinal);

        if (contenidoFiltrado.isBlank()) {
            return "⚠️ No se encontró información específica sobre \"" + categoriaFinal +
                    "\" en el plan de gobierno de este candidato.\n\nIntenta con otra categoría.";
        }

        String respuestaNatural = formatearRespuestaNatural(contenidoFiltrado);

        return "📊 Categoría detectada: " + categoriaFinal + "\n\n" +
                "🧠 Respuesta:\n\n" +
                respuestaNatural + "\n\n" +
                "📌 Interpretación:\n" +
                generarConclusion(categoriaFinal);
    }

    private String filtrarPorCategoria(String texto, String categoria) {
        if (categoria == null || categoria.isEmpty()) return "";

        List<String> keywords = getKeywordsForCategory(categoria);

        String textoLimpio = limpiarTexto(texto);

        String[] bloques = textoLimpio.split("(?<=\\.)|(?<=•)");

        List<Map.Entry<Integer, String>> scored = Arrays.stream(bloques)
                .map(String::trim)
                .filter(b -> !b.isBlank() && b.length() > 30)
                .map(bloque -> {
                    String lower = bloque.toLowerCase();
                    int score = (int) keywords.stream().filter(lower::contains).count();

                    // Penalizaciones específicas por categoría
                    if ("economia".equals(categoria)) {
                        if (lower.contains("orientación estratégica") ||
                                lower.contains("combina medidas programáticas") ||
                                lower.contains("contenido recuperado") ||
                                lower.contains("plan se organiza")) {
                            score = -100;
                        }
                    } else if ("corrupcion".equals(categoria)) {
                        if (lower.contains("plan señala") || lower.contains("nivel de detalle")) {
                            score = -100;
                        }
                    }

                    return Map.entry(score, bloque);
                })
                .filter(e -> e.getKey() >= 1)
                .sorted((a, b) -> b.getKey().compareTo(a.getKey()))
                .collect(Collectors.toList());

        // Para corrupción tomamos solo el mejor bloque
        if ("corrupcion".equals(categoria)) {
            return scored.isEmpty() ? "" : scored.get(0).getValue();
        }

        // Para otras categorías tomamos hasta 2 bloques relevantes
        List<String> mejores = scored.stream()
                .limit(2)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        return String.join(". ", mejores);
    }

    private String limpiarTexto(String texto) {
        return texto
                .replaceAll("(?i)propuestas principales|cómo planean implementarlo|nivel de detalle del plan", "")
                .replaceAll("(?i)el plan señala mecanismos institucionales específicos para algunas medidas", "")
                .replaceAll("(?i)el plan combina lineamientos generales", "")
                .replaceAll("(?i)el plan se organiza en tres ejes estratégicos", "")
                .replaceAll("(?i)el contenido recuperado presenta", "")
                .replaceAll("[¿?:()]", " ")   // eliminamos paréntesis también
                .replaceAll("•|▪|●|–", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }

    private List<String> getKeywordsForCategory(String cat) {
        return switch (cat.toLowerCase()) {
            case "corrupcion" -> List.of("central de lucha", "ccc", "corrup", "código el servicio público",
                    "sanciones por mal servicio", "flagrancia", "patrimonios");
            case "economia" -> List.of("econom", "pyme", "inversion", "reactivar", "empleo", "formaliz",
                    "techo propio", "bono", "ayuda estatal");
            case "salud" -> List.of("salud", "hospital", "atención primaria", "farmacia");
            case "educacion" -> List.of("educ", "escuela", "colegio");
            case "seguridad" -> List.of("seguridad", "pacificación", "delincuen", "delito");
            case "estado" -> List.of("buen gobierno", "gobierno digital");
            default -> List.of(cat);
        };
    }

    private String formatearRespuestaNatural(String texto) {
        if (texto == null || texto.isBlank()) return "";

        String limpio = texto.replaceAll("\\s+", " ").trim();

        List<String> oraciones = Arrays.stream(limpio.split("\\.(?=\\s|$)"))
                .map(String::trim)
                .filter(s -> !s.isBlank() && s.length() > 25)
                .collect(Collectors.toList());

        if (oraciones.isEmpty()) {
            return "En este aspecto, el candidato propone " + limpio + ".";
        }

        StringBuilder sb = new StringBuilder("En este aspecto, el candidato propone ");

        for (int i = 0; i < oraciones.size(); i++) {
            String oracion = oraciones.get(i);
            if (oracion.endsWith(".")) oracion = oracion.substring(0, oracion.length() - 1);

            if (i == 0) {
                oracion = Character.toLowerCase(oracion.charAt(0)) + oracion.substring(1);
                sb.append(oracion);
            } else {
                sb.append(i == oraciones.size() - 1 ? " y " : ", ")
                        .append(oracion.toLowerCase());
            }
        }
        sb.append(".");
        return sb.toString();
    }

    // Detector y Conclusion sin cambios importantes
    private String detectarCategoria(String pregunta) {
        if (pregunta == null) return null;
        String p = pregunta.toLowerCase().trim();

        if (p.contains("corrup") || p.contains("soborno") || p.contains("transparen")) return "corrupcion";
        if (p.contains("seguridad") || p.contains("delito") || p.contains("delincuen")) return "seguridad";
        if (p.contains("educ")) return "educacion";
        if (p.contains("salud")) return "salud";
        if (p.contains("econom") || p.contains("pyme") || p.contains("empleo") || p.contains("inversion") || p.contains("bono") || p.contains("techo"))
            return "economia";
        if (p.contains("gobierno") || p.contains("estado")) return "estado";

        return null;
    }

    private boolean esPreguntaPerfil(String pregunta) {
        if (pregunta == null) return false;
        String p = pregunta.toLowerCase();
        return p.contains("quien es") || p.contains("perfil") || p.contains("biografia") ||
                p.contains("edad") || p.contains("estudios") || p.contains("experiencia");
    }

    private String generarConclusion(String categoria) {
        return switch (categoria.toLowerCase()) {
            case "corrupcion" -> "Plantea acciones concretas para mejorar la transparencia y combatir la corrupción en el Estado.";
            case "economia" -> "El candidato prioriza el crecimiento económico y el apoyo a las PYMEs.";
            case "salud" -> "Se enfoca en mejorar el acceso y calidad de los servicios de salud.";
            case "educacion" -> "Busca fortalecer el sistema educativo.";
            case "seguridad" -> "Propone medidas para reducir la delincuencia y fortalecer la seguridad ciudadana.";
            case "estado" -> "Busca modernizar la gestión pública y mejorar la transparencia.";
            default -> "Incluye propuestas relevantes en este sector.";
        };
    }
}