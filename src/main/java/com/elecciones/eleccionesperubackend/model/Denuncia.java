package com.elecciones.eleccionesperubackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "denuncias")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Denuncia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;

    private String descripcion;
    public enum EstadoDenuncia {
        EN_CURSO,
        CONDENA,
        ARCHIVADA
    }

    @Enumerated(EnumType.STRING)
    private EstadoDenuncia estado;
    private int gravedad;

    private String tipo;   // INVESTIGACION | SENTENCIA
    private String fuente; // Voto Informado / La República


    private String fuenteUrl;
    private Date fecha;
}