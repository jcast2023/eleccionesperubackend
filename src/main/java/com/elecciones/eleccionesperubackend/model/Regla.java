package com.elecciones.eleccionesperubackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reglas")
@Getter
@Setter
public class Regla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String palabraClave;
    private String categoria;
    private int valor;
}

