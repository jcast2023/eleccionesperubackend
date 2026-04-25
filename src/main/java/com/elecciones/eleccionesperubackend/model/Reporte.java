package com.elecciones.eleccionesperubackend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "reportes_error")
@Data
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String relacion;

    @Column(name = "tipo_reporte")
    private String tipoReporte;

    @Column(name = "partido_relacionado")
    private String partido;

    @Column(name = "seccion_relacionada")
    private String seccion;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();
}
