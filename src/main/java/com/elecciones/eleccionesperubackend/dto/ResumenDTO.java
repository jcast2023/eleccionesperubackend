package com.elecciones.eleccionesperubackend.dto;

import java.util.List;

public record ResumenDTO(
        String nombre,
        String partido,
        String resumen,
        List<String> propuestas,
        String perfil,
        String pdfUrl,
        String fotoUrl,
        List<DenunciaDTO> denuncias,
        String logoPartidoUrl
) {}

