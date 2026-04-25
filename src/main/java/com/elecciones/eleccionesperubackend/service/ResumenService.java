package com.elecciones.eleccionesperubackend.service;

import com.elecciones.eleccionesperubackend.dto.ResumenDTO;

public interface ResumenService {
    ResumenDTO obtenerResumenPorCandidato(Long id);
}
