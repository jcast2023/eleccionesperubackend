package com.elecciones.eleccionesperubackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "candidatos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String partido;
    private String fotoUrl;
    private String biografia;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.Set<Denuncia> denuncias;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.Set<Propuesta> propuestas;
    @Column(name = "pdf_url")
    private String pdfUrl;
    private String logoPartidoUrl;
    @Column(columnDefinition = "TEXT")
    private String resumenLargo;

    @Column(columnDefinition = "TEXT")
    private String perfilLargo;

}
