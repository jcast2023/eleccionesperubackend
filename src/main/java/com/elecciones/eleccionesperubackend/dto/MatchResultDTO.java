package com.elecciones.eleccionesperubackend.dto;

import java.util.List;

public record MatchResultDTO(
        String candidato,
        double porcentaje,
        String mensaje,
        List<String> coincidencias,
        List<String> diferencias,
        String explicacion,
        List<DenunciaDTO> denuncias,
        String riesgo,
        int totalDenuncias
) {}




