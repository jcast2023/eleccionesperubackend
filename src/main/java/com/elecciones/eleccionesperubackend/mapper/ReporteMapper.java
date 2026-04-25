package com.elecciones.eleccionesperubackend.mapper;

import com.elecciones.eleccionesperubackend.dto.ReporteDTO;
import com.elecciones.eleccionesperubackend.model.Reporte;
import org.springframework.stereotype.Component;

@Component
public class ReporteMapper {

    public Reporte toEntity(ReporteDTO dto) {
        Reporte entity = new Reporte();
        entity.setNombre(dto.getNombre());
        entity.setEmail(dto.getEmail());
        entity.setRelacion(dto.getRelacion());
        entity.setTipoReporte(dto.getTipoReporte());
        entity.setPartido(dto.getPartido());
        entity.setSeccion(dto.getSeccion());
        entity.setDescripcion(dto.getDescripcion());
        return entity;
    }

    public ReporteDTO toDto(Reporte entity) {
        ReporteDTO dto = new ReporteDTO();
        dto.setNombre(entity.getNombre());
        dto.setEmail(entity.getEmail());
        dto.setRelacion(entity.getRelacion());
        dto.setTipoReporte(entity.getTipoReporte());
        dto.setPartido(entity.getPartido());
        dto.setSeccion(entity.getSeccion());
        dto.setDescripcion(entity.getDescripcion());
        return dto;
    }
}
