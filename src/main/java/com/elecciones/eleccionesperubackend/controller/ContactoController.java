package com.elecciones.eleccionesperubackend.controller;

import com.elecciones.eleccionesperubackend.dto.ContactoDTO;
import com.elecciones.eleccionesperubackend.service.ContactoService;

import jakarta.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contacto")
@CrossOrigin(origins = "http://localhost:5173")
public class ContactoController {

    @Autowired
    private ContactoService service;

    @PostMapping
    public ResponseEntity<?> crearMensaje(
            @Valid @RequestBody ContactoDTO dto,
            BindingResult result) {

        if (result.hasErrors()) {

            Map<String, String> errores = new HashMap<>();

            result.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });

            return ResponseEntity.badRequest().body(errores);
        }

        return ResponseEntity.ok(service.guardarMensaje(dto));
    }

    @GetMapping
    public ResponseEntity<List<ContactoDTO>> listarMensajes() {
        return ResponseEntity.ok(service.obtenerTodos());
    }
}
