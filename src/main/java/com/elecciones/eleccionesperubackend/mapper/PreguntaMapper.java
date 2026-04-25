package com.elecciones.eleccionesperubackend.mapper;

import com.elecciones.eleccionesperubackend.dto.PreguntaDTO;
import com.elecciones.eleccionesperubackend.model.Pregunta;
import org.springframework.stereotype.Component;

@Component
public class PreguntaMapper {

    public PreguntaDTO toDTO(Pregunta p) {
        return new PreguntaDTO(
                p.getId(),
                p.getTexto(),
                p.getCategoria(),
                p.getPeso()
        );
    }
}

