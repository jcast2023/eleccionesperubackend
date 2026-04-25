package com.elecciones.eleccionesperubackend.dto;

import java.util.List;

public record ComparacionCompletaDTO(
        String candidato1,
        String candidato2,
        List<ComparacionDTO> radar,
        List<String> coincidencias,
        List<String> diferencias,
        double porcentajeCoincidencia
) {}

