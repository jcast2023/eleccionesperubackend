package com.elecciones.eleccionesperubackend.service;

import com.elecciones.eleccionesperubackend.dto.PreguntaDTO;
import com.elecciones.eleccionesperubackend.model.Pregunta;

import java.util.List;

public interface PreguntaService {

    List<PreguntaDTO> listar();

    Pregunta guardar(Pregunta pregunta);
}
