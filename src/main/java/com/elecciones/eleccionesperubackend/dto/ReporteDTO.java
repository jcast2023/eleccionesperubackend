package com.elecciones.eleccionesperubackend.dto;

import lombok.Data;

@Data
public class ReporteDTO {
    private String nombre;
    private String email;
    private String relacion;
    private String tipoReporte;
    private String partido;
    private String seccion;
    private String descripcion;
}
