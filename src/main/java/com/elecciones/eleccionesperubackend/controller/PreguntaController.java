package com.elecciones.eleccionesperubackend.controller;
import com.elecciones.eleccionesperubackend.dto.OpcionDTO;
import com.elecciones.eleccionesperubackend.dto.PreguntaDTO;
import com.elecciones.eleccionesperubackend.repository.PreguntaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preguntas")
@CrossOrigin(origins = "${FRONTEND_URL:http://localhost:5173}")
public class PreguntaController {

    private final PreguntaRepository preguntaRepository;

    public PreguntaController(PreguntaRepository preguntaRepository) {
        this.preguntaRepository = preguntaRepository;
    }

    @GetMapping
    @Transactional
    public List<PreguntaDTO> listar() {
        return preguntaRepository.findAll()
                .stream()
                .map(p -> new PreguntaDTO(
                        p.getId(),
                        p.getTexto(),
                        p.getCategoria(),
                        p.getPeso(),
                        p.getOpciones().stream()
                                .map(o -> new OpcionDTO(
                                        o.getId(),
                                        o.getTexto(),
                                        o.getValor()
                                ))
                                .toList()
                ))
                .toList();
    }
}
