package com.elecciones.eleccionesperubackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ChatContenido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long candidatoId;
    private String categoria;

    @Column(columnDefinition = "TEXT")
    private String contenido;
}


