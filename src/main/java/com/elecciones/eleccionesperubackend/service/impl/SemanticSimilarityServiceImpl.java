package com.elecciones.eleccionesperubackend.service.impl;

import com.elecciones.eleccionesperubackend.service.SemanticSimilarityService;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
// Importa la clase correcta
import dev.langchain4j.model.embedding.onnx.allminilml6v2q.AllMiniLmL6V2QuantizedEmbeddingModel;
import org.springframework.stereotype.Service;

@Service
public class SemanticSimilarityServiceImpl implements SemanticSimilarityService {

    private final EmbeddingModel embeddingModel;

    public SemanticSimilarityServiceImpl() {
        this.embeddingModel = new AllMiniLmL6V2QuantizedEmbeddingModel();
    }

    @Override
    public double calculateSimilarity(String text1, String text2) {
        if (text1 == null || text2 == null || text1.isBlank() || text2.isBlank()) {
            return 0.0;
        }

        // Generar embeddings
        Embedding embedding1 = embeddingModel.embed(text1).content();
        Embedding embedding2 = embeddingModel.embed(text2).content();

        float[] vector1 = embedding1.vector();
        float[] vector2 = embedding2.vector();

        return cosineSimilarity(vector1, vector2);
    }

    private double cosineSimilarity(float[] a, float[] b) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        for (int i = 0; i < a.length; i++) {
            dotProduct += a[i] * b[i];
            normA += a[i] * a[i];
            normB += b[i] * b[i];
        }

        if (normA == 0.0 || normB == 0.0) {
            return 0.0;
        }

        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}