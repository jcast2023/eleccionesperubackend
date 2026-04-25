package com.elecciones.eleccionesperubackend.service.impl;

import com.elecciones.eleccionesperubackend.dto.ContactoDTO;
import com.elecciones.eleccionesperubackend.mapper.ContactoMapper;
import com.elecciones.eleccionesperubackend.model.Contacto;
import com.elecciones.eleccionesperubackend.repository.ContactoRepository;
import com.elecciones.eleccionesperubackend.service.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


// Service Impl
@Service
public class ContactoServiceImpl implements ContactoService {
    @Autowired
    private ContactoRepository repository;
    @Autowired
    private ContactoMapper mapper;

    @Override
    public ContactoDTO guardarMensaje(ContactoDTO dto) {
        Contacto entidad = mapper.toEntity(dto);
        Contacto guardado = repository.save(entidad);
        return mapper.toDto(guardado);
    }

    @Override
    public List<ContactoDTO> obtenerTodos() {
        return repository.findAll() // Trae todo de MySQL
                .stream()           // Convierte a flujo de datos
                .map(mapper::toDto) // Transforma cada Entidad en DTO
                .collect(Collectors.toList()); // Lo agrupa en una Lista
    }
}
