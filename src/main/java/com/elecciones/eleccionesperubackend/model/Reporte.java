package com.elecciones.eleccionesperubackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "reportes_error")
@Data
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Opcional
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$",
            message = "El nombre solo puede contener letras"
    )
    @Size(max = 100, message = "Máximo 100 caracteres")
    @Column(length = 100)
    private String nombre;

    // Obligatorio
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Correo inválido")
    @Size(max = 150, message = "Máximo 150 caracteres")
    @Column(nullable = false, length = 150)
    private String email;

    // Obligatorio
    @NotBlank(message = "Seleccione su relación con el problema")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String relacion;

    // Obligatorio
    @NotBlank(message = "Seleccione el tipo de reporte")
    @Size(max = 100)
    @Column(name = "tipo_reporte", nullable = false, length = 100)
    private String tipoReporte;

    // Opcional
    @Size(max = 100, message = "Máximo 100 caracteres")
    @Column(name = "partido_relacionado", length = 100)
    private String partido;

    // Opcional
    @Size(max = 100, message = "Máximo 100 caracteres")
    @Column(name = "seccion_relacionada", length = 100)
    private String seccion;

    // Obligatorio
    @NotBlank(message = "La descripción es obligatoria")
    @Size(
            min = 15,
            max = 2000,
            message = "La descripción debe tener entre 15 y 2000 caracteres"
    )
    @Column(columnDefinition = "TEXT", nullable = false)
    private String descripcion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();
}
