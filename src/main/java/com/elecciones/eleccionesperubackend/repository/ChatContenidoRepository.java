package com.elecciones.eleccionesperubackend.repository;

import com.elecciones.eleccionesperubackend.model.ChatContenido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatContenidoRepository extends JpaRepository<ChatContenido, Long> {

    List<ChatContenido> findByCandidatoIdAndCategoria(Long candidatoId, String categoria);

}


