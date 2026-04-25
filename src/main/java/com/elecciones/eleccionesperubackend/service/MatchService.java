package com.elecciones.eleccionesperubackend.service;

import com.elecciones.eleccionesperubackend.dto.ComparacionCompletaDTO;
import com.elecciones.eleccionesperubackend.dto.ComparacionDTO;
import com.elecciones.eleccionesperubackend.dto.MatchResultDTO;

import java.util.List;
import java.util.Map;

public interface MatchService {
    List<MatchResultDTO> calcularMatch(Map<String, Integer> respuestasUsuario);
    ComparacionCompletaDTO compararCandidatosCompleto(Long c1, Long c2);

}
