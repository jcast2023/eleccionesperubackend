package com.elecciones.eleccionesperubackend.dto;

public record CandidatoDTO(
        Long id,
        String nombre,
        String partido,
        String fotoUrl,
        String logoPartidoUrl,
        long sentenciasCount,      // Calculado en el Service
        long investigacionesCount
) {}

