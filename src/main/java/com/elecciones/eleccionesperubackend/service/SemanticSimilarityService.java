package com.elecciones.eleccionesperubackend.service;

public interface SemanticSimilarityService {
    double calculateSimilarity(String text1, String text2);
}
