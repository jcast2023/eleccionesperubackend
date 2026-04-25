package com.elecciones.eleccionesperubackend.dto;

import lombok.Data;

@Data
public class ChatRequest {
    private String pregunta;
    private Long candidatoId; // 🔥 clave
    private String categoria;
}

