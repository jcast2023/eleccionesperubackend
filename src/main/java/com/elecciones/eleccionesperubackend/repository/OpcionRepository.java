package com.elecciones.eleccionesperubackend.repository;

import com.elecciones.eleccionesperubackend.model.Opcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpcionRepository extends JpaRepository<Opcion, Long> {}

