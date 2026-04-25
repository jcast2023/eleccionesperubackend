package com.elecciones.eleccionesperubackend.controller;

import com.elecciones.eleccionesperubackend.dto.ContactoDTO;
import com.elecciones.eleccionesperubackend.service.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacto")
@CrossOrigin(origins = "http://localhost:5173") // URL de tu React
public class ContactoController {

    @Autowired
    private ContactoService service;

    @PostMapping
    public ResponseEntity<ContactoDTO> crearMensaje(@RequestBody ContactoDTO dto) {
        return ResponseEntity.ok(service.guardarMensaje(dto));
    }

    @GetMapping
    public ResponseEntity<List<ContactoDTO>> listarMensajes() {
        return ResponseEntity.ok(service.obtenerTodos());
    }
}