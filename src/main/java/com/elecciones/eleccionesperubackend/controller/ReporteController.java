package com.elecciones.eleccionesperubackend.controller;

import com.elecciones.eleccionesperubackend.dto.ReporteDTO;
import com.elecciones.eleccionesperubackend.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "http://localhost:5173")
public class ReporteController {

    @Autowired
    private ReporteService service;

    @PostMapping
    public ResponseEntity<ReporteDTO> crearReporte(@RequestBody ReporteDTO dto) {
        return ResponseEntity.ok(service.guardarReporte(dto));
    }

    @GetMapping
    public ResponseEntity<List<ReporteDTO>> listarReportes() {
        return ResponseEntity.ok(service.obtenerTodos());
    }
}
