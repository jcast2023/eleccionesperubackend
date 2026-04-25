package com.elecciones.eleccionesperubackend.service.impl;

import com.elecciones.eleccionesperubackend.service.SemanticSimilarityService;
import com.elecciones.eleccionesperubackend.service.TextSimilarityService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TextSimilarityServiceImpl implements TextSimilarityService {

    private final SemanticSimilarityService semanticSimilarityService;

    public TextSimilarityServiceImpl(SemanticSimilarityService semanticSimilarityService) {
        this.semanticSimilarityService = semanticSimilarityService;
    }

    @Override
    public double calcularSimilitud(String texto1, String texto2) {
        // Delega completamente en el servicio semántico
        return semanticSimilarityService.calculateSimilarity(texto1, texto2);
    }

    // Los siguientes métodos (tokenización, palabras clave, extracción de fragmentos)
    // se mantienen para usarlos en extractFragmentForCategory
    private static final Set<String> STOP_WORDS = Set.of(
            "de", "la", "que", "el", "en", "y", "a", "los", "del", "se", "las", "por", "un", "para", "con",
            "no", "una", "su", "al", "lo", "como", "más", "pero", "sus", "le", "ya", "o", "este", "entre",
            "si", "porque", "esta", "desde", "ser", "tiene", "fue", "sobre", "también", "hay",
            "ante", "bajo", "cabe", "contra", "sin", "tras", "durante", "mediante", "vs", "frente"
    );

    private static final Map<String, List<String>> KEYWORDS_BY_CATEGORY = Map.of(
            "Seguridad", List.of("seguridad", "delincuencia", "policía", "crimen", "violencia", "orden", "ciudadano", "mano dura"),
            "Corrupción", List.of("corrupción", "anticorrupción", "transparencia", "soborno", "ética", "lucha contra la corrupción", "limpia"),
            "Economía", List.of("economía", "crecimiento", "empleo", "inflación", "pyme", "inversión", "mercado", "trabajo", "libre mercado"),
            "Educación", List.of("educación", "escuela", "universidad", "enseñanza", "alumno", "docente", "currículo", "colegio"),
            "Salud", List.of("salud", "hospital", "atención médica", "seguro", "enfermedad", "prevención", "sanidad", "clínica"),
            "Infraestructura", List.of("infraestructura", "obra", "carretera", "puente", "transporte", "vivienda", "urbanismo", "construcción"),
            "Medio Ambiente", List.of("medio ambiente", "ambiente", "ecología", "sostenible", "cambio climático", "recursos naturales", "agua", "bosque", "verde"),
            "Estado", List.of("estado", "gobierno", "administración pública", "reforma del estado", "descentralización", "burocracia", "modernización"),
            "Social", List.of("social", "pobreza", "desigualdad", "inclusión", "vulnerable", "programa social", "bienestar", "ayuda"),
            "Libertad", List.of("libertad", "derechos", "libertad de expresión", "libertad económica", "autonomía", "individual", "libre")
    );

    @Override
    public String extractFragmentForCategory(String texto, String categoria) {
        if (texto == null || texto.isBlank()) return "";

        List<String> terms = KEYWORDS_BY_CATEGORY.getOrDefault(categoria, List.of(categoria.toLowerCase()));

        // Dividir en párrafos (asumiendo separación por dos saltos de línea)
        String[] parrafos = texto.split("\n\n");
        String mejorParrafo = "";
        int maxScore = 0;

        for (String parrafo : parrafos) {
            String lower = parrafo.toLowerCase();
            int score = 0;
            for (String term : terms) {
                if (lower.contains(term)) {
                    score++;
                }
            }
            if (score > maxScore) {
                maxScore = score;
                mejorParrafo = parrafo.trim();
            }
        }

        // Si no se encontró ningún párrafo relevante, tomar el primer párrafo (o un fragmento)
        if (mejorParrafo.isEmpty() && parrafos.length > 0) {
            mejorParrafo = parrafos[0].trim();
        }

        // Si aún está vacío, tomar los primeros 300 caracteres
        if (mejorParrafo.isEmpty() && !texto.isEmpty()) {
            mejorParrafo = texto.substring(0, Math.min(300, texto.length()));
        }

        return mejorParrafo;
    }
}