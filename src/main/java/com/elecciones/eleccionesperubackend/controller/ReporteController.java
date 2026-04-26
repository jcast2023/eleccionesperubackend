package com.elecciones.eleccionesperubackend.controller;

import com.elecciones.eleccionesperubackend.dto.ReporteDTO;
import com.elecciones.eleccionesperubackend.service.ReporteService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "http://localhost:5173")
public class ReporteController {

    @Autowired
    private ReporteService service;

    @PostMapping
    public ResponseEntity<?> guardar(
            @Valid @RequestBody ReporteDTO dto,
            BindingResult result) {

        if (result.hasErrors()) {

            Map<String, String> errores = new HashMap<>();

            result.getFieldErrors().forEach(error -> {
                errores.put(
                        error.getField(),
                        error.getDefaultMessage()
                );
            });

            return ResponseEntity.badRequest().body(errores);
        }

        return ResponseEntity.ok(
                service.guardarReporte(dto)
        );
    }

    @GetMapping
    public ResponseEntity<List<ReporteDTO>> listar() {
        return ResponseEntity.ok(
                service.obtenerTodos()
        );
    }
}
