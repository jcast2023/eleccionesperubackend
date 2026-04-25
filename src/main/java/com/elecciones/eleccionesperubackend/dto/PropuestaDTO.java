package com.elecciones.eleccionesperubackend.dto;

public record PropuestaDTO(
        Long id,
        String titulo,
        String descripcion,
        String categoria,
        int valor
) {}
