package com.elecciones.eleccionesperubackend.service.impl;



import com.elecciones.eleccionesperubackend.dto.PreguntaDTO;
import com.elecciones.eleccionesperubackend.mapper.PreguntaMapper;
import com.elecciones.eleccionesperubackend.model.Pregunta;
import com.elecciones.eleccionesperubackend.repository.PreguntaRepository;
import com.elecciones.eleccionesperubackend.service.PreguntaService;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.stream;

@Service
public class PreguntaServiceImpl implements PreguntaService {

    private final PreguntaRepository preguntaRepository;
    private final PreguntaMapper preguntaMapper;


    public PreguntaServiceImpl(PreguntaRepository preguntaRepository,
                               PreguntaMapper preguntaMapper) {
        this.preguntaRepository = preguntaRepository;
        this.preguntaMapper = preguntaMapper;
    }


    @Override
    public List<PreguntaDTO> listar() {
        return preguntaRepository.findAll()
                .stream()
                .map(preguntaMapper::toDTO)
                .toList();
    }


    @Override
    public Pregunta guardar(Pregunta pregunta) {
        return preguntaRepository.save(pregunta);
    }
}

