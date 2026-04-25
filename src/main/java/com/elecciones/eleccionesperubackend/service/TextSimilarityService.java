package com.elecciones.eleccionesperubackend.service;

public interface TextSimilarityService {
    double calcularSimilitud(String texto1, String texto2);

    String extractFragmentForCategory(String texto, String categoria);
}
