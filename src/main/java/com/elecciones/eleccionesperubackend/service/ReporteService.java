package com.elecciones.eleccionesperubackend.service;

import com.elecciones.eleccionesperubackend.dto.ReporteDTO;

import java.util.List;

public interface ReporteService {
    ReporteDTO guardarReporte(ReporteDTO reporteDTO);
    List<ReporteDTO> obtenerTodos();
}
