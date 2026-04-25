package com.elecciones.eleccionesperubackend.repository;


import com.elecciones.eleccionesperubackend.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {
}
