package com.elecciones.eleccionesperubackend.repository;

import com.elecciones.eleccionesperubackend.model.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {
}
