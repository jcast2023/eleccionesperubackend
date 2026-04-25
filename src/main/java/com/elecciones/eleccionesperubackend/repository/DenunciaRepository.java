package com.elecciones.eleccionesperubackend.repository;

import com.elecciones.eleccionesperubackend.model.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {

    List<Denuncia> findByCandidatoId(Long candidatoId);
}
