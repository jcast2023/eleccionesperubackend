package com.elecciones.eleccionesperubackend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ContactoDTO {

    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$",
            message = "Nombre solo letras"
    )
    private String nombre;

    @NotBlank(message = "Correo obligatorio")
    @Email(message = "Correo inválido")
    private String email;

    @NotBlank(message = "Asunto obligatorio")
    @Pattern(
            regexp = ".*[a-zA-ZáéíóúÁÉÍÓÚñÑ].*",
            message = "Asunto debe contener letras"
    )
    private String asunto;

    @NotBlank(message = "Mensaje obligatorio")
    @Size(min = 10, message = "Mensaje mínimo 10 caracteres")
    private String mensaje;
}

