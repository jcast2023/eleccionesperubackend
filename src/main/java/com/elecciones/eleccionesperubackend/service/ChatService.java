package com.elecciones.eleccionesperubackend.service;

import com.elecciones.eleccionesperubackend.dto.ChatRequest;

public interface ChatService {
    String responder(ChatRequest request);
}
