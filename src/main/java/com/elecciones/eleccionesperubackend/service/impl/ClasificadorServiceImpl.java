package com.elecciones.eleccionesperubackend.service.impl;

import com.elecciones.eleccionesperubackend.model.Propuesta;
import com.elecciones.eleccionesperubackend.repository.ReglaRepository;
import com.elecciones.eleccionesperubackend.service.ClasificadorService;
import org.springframework.stereotype.Service;

@Service
public class ClasificadorServiceImpl implements ClasificadorService {

    private final ReglaRepository reglaRepository;

    public ClasificadorServiceImpl(ReglaRepository reglaRepository) {
        this.reglaRepository = reglaRepository;
    }

    @Override
    public void clasificarPropuesta(Propuesta propuesta) {

        String texto = propuesta.getDescripcion().toLowerCase();

        reglaRepository.findAll().forEach(r -> {
            if (texto.contains(r.getPalabraClave().toLowerCase())) {
                propuesta.setCategoria(r.getCategoria());
                propuesta.setValor(r.getValor());
            }
        });

        if (propuesta.getCategoria() == null) {
            propuesta.setCategoria("General");
            propuesta.setValor(3);
        }
    }
}
