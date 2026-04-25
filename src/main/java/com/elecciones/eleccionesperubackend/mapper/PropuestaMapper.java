package com.elecciones.eleccionesperubackend.mapper;

import com.elecciones.eleccionesperubackend.dto.PropuestaDTO;
import com.elecciones.eleccionesperubackend.model.Propuesta;
import org.springframework.stereotype.Component;

@Component
public class PropuestaMapper {

    public PropuestaDTO toDTO(Propuesta p) {
        return new PropuestaDTO(
                p.getId(),
                p.getTitulo(),
                p.getDescripcion(),
                p.getCategoria(),
                p.getValor()
        );
    }
}
