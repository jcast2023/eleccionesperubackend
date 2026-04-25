package com.elecciones.eleccionesperubackend.service.impl;

import com.elecciones.eleccionesperubackend.dto.ReporteDTO;
import com.elecciones.eleccionesperubackend.mapper.ReporteMapper;
import com.elecciones.eleccionesperubackend.model.Reporte;
import com.elecciones.eleccionesperubackend.repository.ReporteRepository;
import com.elecciones.eleccionesperubackend.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteServiceImpl implements ReporteService {
    @Autowired
    private ReporteRepository repository;
    @Autowired
    private ReporteMapper mapper;

    @Override
    public ReporteDTO guardarReporte(ReporteDTO dto) {
        Reporte entidad = mapper.toEntity(dto);
        Reporte guardado = repository.save(entidad);
        return mapper.toDto(guardado);
    }

    @Override
    public List<ReporteDTO> obtenerTodos() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
