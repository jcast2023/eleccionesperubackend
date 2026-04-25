package com.elecciones.eleccionesperubackend.controller;

import com.elecciones.eleccionesperubackend.dto.PreguntaDTO;
import com.elecciones.eleccionesperubackend.model.Pregunta;
import com.elecciones.eleccionesperubackend.repository.PreguntaRepository;
import com.elecciones.eleccionesperubackend.service.PreguntaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;@RestController
@RequestMapping("/api/preguntas")
public class PreguntaController {

    private final PreguntaRepository preguntaRepository;

    public PreguntaController(PreguntaRepository preguntaRepository) {
        this.preguntaRepository = preguntaRepository;
    }

    @GetMapping
    public List<Pregunta> listar() {
        return preguntaRepository.findAll();
    }
}
