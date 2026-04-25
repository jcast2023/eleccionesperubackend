package com.elecciones.eleccionesperubackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "contacto_mensajes")
@Data
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String asunto;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
}
