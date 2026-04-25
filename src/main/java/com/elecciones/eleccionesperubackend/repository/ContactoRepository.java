package com.elecciones.eleccionesperubackend.repository;

import com.elecciones.eleccionesperubackend.model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {
}
