package com.elecciones.eleccionesperubackend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ReporteDTO {

    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$",
            message = "Nombre solo letras"
    )
    private String nombre;

    @NotBlank(message = "Correo obligatorio")
    @Email(message = "Correo inválido")
    private String email;

    @NotBlank(message = "Seleccione una relación")
    private String relacion;

    @NotBlank(message = "Seleccione tipo de reporte")
    private String tipoReporte;

    private String partido;

    @Pattern(
            regexp = "^(|.*[a-zA-ZáéíóúÁÉÍÓÚñÑ].*)$",
            message = "La sección debe contener letras"
    )
    private String seccion;


    @NotBlank(message = "Descripción obligatoria")
    @Size(min = 15, message = "Mínimo 15 caracteres")
    private String descripcion;
}
