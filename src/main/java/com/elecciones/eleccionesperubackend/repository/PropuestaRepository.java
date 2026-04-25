package com.elecciones.eleccionesperubackend.repository;

import com.elecciones.eleccionesperubackend.model.Propuesta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropuestaRepository extends JpaRepository<Propuesta, Long> {
    List<Propuesta> findByCandidatoId(Long candidatoId);

}
