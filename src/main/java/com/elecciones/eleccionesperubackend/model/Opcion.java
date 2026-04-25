package com.elecciones.eleccionesperubackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "opciones")
public class Opcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;
    private int valor; // -2 a +2 o 1 a 5

    @ManyToOne
    @JoinColumn(name = "pregunta_id")
    private Pregunta pregunta;
}
