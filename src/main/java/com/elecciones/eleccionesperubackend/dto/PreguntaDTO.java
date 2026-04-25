package com.elecciones.eleccionesperubackend.dto;

public record PreguntaDTO(
        Long id,
        String texto,
        String categoria,
        Double peso
) {}
