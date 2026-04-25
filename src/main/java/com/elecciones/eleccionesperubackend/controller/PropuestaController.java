package com.elecciones.eleccionesperubackend.controller;

import com.elecciones.eleccionesperubackend.model.Propuesta;
import com.elecciones.eleccionesperubackend.service.PropuestaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/propuestas")
@CrossOrigin(origins = "http://localhost:5173")
public class PropuestaController {

    private final PropuestaService propuestaService;

    public PropuestaController(PropuestaService propuestaService) {
        this.propuestaService = propuestaService;
    }

    @PostMapping
    public Propuesta guardar(@RequestBody Propuesta propuesta) {
        return propuestaService.guardar(propuesta);
    }
}
