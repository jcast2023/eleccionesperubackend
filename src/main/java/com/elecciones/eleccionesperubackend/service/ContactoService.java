package com.elecciones.eleccionesperubackend.service;

import com.elecciones.eleccionesperubackend.dto.ContactoDTO;

import java.util.List;

public interface ContactoService {
    ContactoDTO guardarMensaje(ContactoDTO contactoDTO);
    List<ContactoDTO> obtenerTodos();
}
