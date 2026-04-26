package com.elecciones.eleccionesperubackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "contacto_mensajes")
@Data
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Opcional, pero si escriben algo debe ser solo letras
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$",
            message = "El nombre solo puede contener letras"
    )
    @Size(max = 100, message = "Máximo 100 caracteres")
    @Column(length = 100)
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Correo electrónico inválido")
    @Size(max = 150, message = "Máximo 150 caracteres")
    @Column(nullable = false, length = 150)
    private String email;

    @NotBlank(message = "El asunto es obligatorio")
    @Size(min = 3, max = 150, message = "El asunto debe tener entre 3 y 150 caracteres")
    @Pattern(
            regexp = ".*[a-zA-ZáéíóúÁÉÍÓÚñÑ].*",
            message = "El asunto debe contener letras"
    )
    @Column(nullable = false, length = 150)
    private String asunto;

    @NotBlank(message = "El mensaje es obligatorio")
    @Size(min = 10, max = 1000, message = "El mensaje debe tener entre 10 y 1000 caracteres")
    @Column(nullable = false, length = 1000)
    private String mensaje;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();
}

