package com.elecciones.eleccionesperubackend.repository;

import com.elecciones.eleccionesperubackend.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

    // Para la lista principal (Cards)
    @Query("SELECT DISTINCT c FROM Candidato c LEFT JOIN FETCH c.denuncias")
    List<Candidato> findAllWithDenuncias();

    // Para el detalle (Resumen) - Corregido para traer AMBAS listas
    @Query("""
        SELECT DISTINCT c FROM Candidato c
        LEFT JOIN FETCH c.propuestas
        LEFT JOIN FETCH c.denuncias
        WHERE c.id = :id
    """)
    Optional<Candidato> findByIdWithPropuestas(@Param("id") Long id);
}