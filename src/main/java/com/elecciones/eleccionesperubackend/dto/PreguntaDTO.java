package com.elecciones.eleccionesperubackend.dto;

import java.util.List;

public record PreguntaDTO(
        Long id,
        String texto,
        String categoria,
        Double peso,
        List<OpcionDTO> opciones
) {}
