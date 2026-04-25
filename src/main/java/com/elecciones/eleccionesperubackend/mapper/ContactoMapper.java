package com.elecciones.eleccionesperubackend.mapper;

import com.elecciones.eleccionesperubackend.dto.ContactoDTO;
import com.elecciones.eleccionesperubackend.model.Contacto;
import org.springframework.stereotype.Component;

@Component
public class ContactoMapper {
    public Contacto toEntity(ContactoDTO dto) {
        Contacto entity = new Contacto();
        entity.setNombre(dto.getNombre());
        entity.setEmail(dto.getEmail());
        entity.setAsunto(dto.getAsunto());
        entity.setMensaje(dto.getMensaje());
        return entity;
    }

    public ContactoDTO toDto(Contacto entity) {
        ContactoDTO dto = new ContactoDTO();
        dto.setNombre(entity.getNombre());
        dto.setEmail(entity.getEmail());
        dto.setAsunto(entity.getAsunto());
        dto.setMensaje(entity.getMensaje());
        return dto;
    }
}