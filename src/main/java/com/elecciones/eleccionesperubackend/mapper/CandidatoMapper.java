package com.elecciones.eleccionesperubackend.mapper;

import com.elecciones.eleccionesperubackend.dto.CandidatoDTO;
import com.elecciones.eleccionesperubackend.model.Candidato;
import org.springframework.stereotype.Component;

@Component
public class CandidatoMapper {

    public CandidatoDTO toDTO(Candidato c) {
        // Calculamos los contadores filtrando la lista de denuncias del modelo
        long sentencias = 0;
        long investigaciones = 0;

        if (c.getDenuncias() != null) {
            sentencias = c.getDenuncias().stream()
                    .filter(d -> "SENTENCIA".equalsIgnoreCase(d.getTipo()))
                    .count();

            investigaciones = c.getDenuncias().stream()
                    .filter(d -> "INVESTIGACION".equalsIgnoreCase(d.getTipo()))
                    .count();
        }

        return new CandidatoDTO(
                c.getId(),
                c.getNombre(),
                c.getPartido(),
                c.getFotoUrl(),
                c.getLogoPartidoUrl(),
                sentencias,      // Campo: sentenciasCount
                investigaciones  // Campo: investigacionesCount
        );
    }
}