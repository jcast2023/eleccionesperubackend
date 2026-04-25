package com.elecciones.eleccionesperubackend.dto;

import lombok.Data;

@Data
public class ContactoDTO {
    private String nombre;
    private String email;
    private String asunto;
    private String mensaje;
}
