package com.elecciones.eleccionesperubackend.dto;

import java.util.Date;

public record DenunciaDTO(
        String descripcion,
        String estado,
        int gravedad,
        String fuenteUrl,
        Date fecha,
        String tipo,
        String fuente
) {}

