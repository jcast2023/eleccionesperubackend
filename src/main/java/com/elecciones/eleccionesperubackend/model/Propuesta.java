package com.elecciones.eleccionesperubackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "propuestas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Propuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    private String categoria;

    private int valor;

    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;

}
