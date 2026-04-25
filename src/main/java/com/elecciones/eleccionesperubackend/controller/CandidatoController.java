package com.elecciones.eleccionesperubackend.controller;

import com.elecciones.eleccionesperubackend.dto.*;
import com.elecciones.eleccionesperubackend.mapper.CandidatoMapper;
import com.elecciones.eleccionesperubackend.model.Candidato;
import com.elecciones.eleccionesperubackend.repository.CandidatoRepository;
import com.elecciones.eleccionesperubackend.service.MatchService;
import com.elecciones.eleccionesperubackend.service.ResumenService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class CandidatoController {

    private final CandidatoRepository candidatoRepository;
    private final MatchService matchService;
    private final CandidatoMapper candidatoMapper;
    private final ResumenService resumenService;


    public CandidatoController(CandidatoRepository candidatoRepository,
                               MatchService matchService,
                               CandidatoMapper candidatoMapper,
                               ResumenService resumenService) {
        this.candidatoRepository = candidatoRepository;
        this.matchService = matchService;
        this.candidatoMapper = candidatoMapper;
        this.resumenService = resumenService;
    }



    @GetMapping("/candidatos")
    public List<CandidatoDTO> listar() {
        return candidatoRepository.findAll()
                .stream()
                .map(candidatoMapper::toDTO)
                .toList();
    }



    @PostMapping("/match")
    public List<MatchResultDTO> calcularMatch(@RequestBody Map<String, Integer> respuestasUsuario) {
        return matchService.calcularMatch(respuestasUsuario);
    }

    @GetMapping("/comparar-full")
    public ComparacionCompletaDTO compararFull(
            @RequestParam Long c1,
            @RequestParam Long c2) {

        return matchService.compararCandidatosCompleto(c1, c2);
    }

    @GetMapping("/resumen/{id}")
    public ResumenDTO obtenerResumen(@PathVariable Long id) {

        return resumenService.obtenerResumenPorCandidato(id);
    }


}
