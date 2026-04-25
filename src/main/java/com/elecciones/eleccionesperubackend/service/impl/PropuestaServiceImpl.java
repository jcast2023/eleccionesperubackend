package com.elecciones.eleccionesperubackend.service.impl;

import com.elecciones.eleccionesperubackend.model.Propuesta;
import com.elecciones.eleccionesperubackend.repository.PropuestaRepository;
import com.elecciones.eleccionesperubackend.service.ClasificadorService;
import com.elecciones.eleccionesperubackend.service.PropuestaService;
import org.springframework.stereotype.Service;

@Service
public class PropuestaServiceImpl implements PropuestaService {

    private final PropuestaRepository propuestaRepository;
    private final ClasificadorService clasificadorService;

    public PropuestaServiceImpl(PropuestaRepository propuestaRepository,
                                ClasificadorService clasificadorService) {
        this.propuestaRepository = propuestaRepository;
        this.clasificadorService = clasificadorService;
    }

    @Override
    public Propuesta guardar(Propuesta propuesta) {
        clasificadorService.clasificarPropuesta(propuesta);
        return propuestaRepository.save(propuesta);
    }
}
