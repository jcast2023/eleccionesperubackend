package com.elecciones.eleccionesperubackend.mapper;

import com.elecciones.eleccionesperubackend.dto.OpcionDTO;
import com.elecciones.eleccionesperubackend.dto.PreguntaDTO;
import com.elecciones.eleccionesperubackend.model.Pregunta;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PreguntaMapper {

    public PreguntaDTO toDTO(Pregunta p) {
        List<OpcionDTO> opciones = p.getOpciones() == null ? List.of() :
                p.getOpciones().stream()
                        .map(o -> new OpcionDTO(o.getId(), o.getTexto(), o.getValor()))
                        .toList();

        return new PreguntaDTO(
                p.getId(),
                p.getTexto(),
                p.getCategoria(),
                p.getPeso(),
                opciones
        );
    }
}
