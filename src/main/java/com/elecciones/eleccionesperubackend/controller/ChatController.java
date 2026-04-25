package com.elecciones.eleccionesperubackend.controller;

import com.elecciones.eleccionesperubackend.dto.ChatRequest;
import com.elecciones.eleccionesperubackend.dto.ChatResponse;
import com.elecciones.eleccionesperubackend.service.ChatService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*") // 🔥 importante para el frontend
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    // 🔥 ENDPOINT PRINCIPAL
    @PostMapping
    public ChatResponse responder(@RequestBody ChatRequest request) {

        String respuesta = chatService.responder(request);

        return new ChatResponse(respuesta);
    }
}

