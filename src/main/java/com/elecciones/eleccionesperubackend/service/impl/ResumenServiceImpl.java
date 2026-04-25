package com.elecciones.eleccionesperubackend.service.impl;

import com.elecciones.eleccionesperubackend.dto.DenunciaDTO;
import com.elecciones.eleccionesperubackend.dto.ResumenDTO;
import com.elecciones.eleccionesperubackend.model.Candidato;
import com.elecciones.eleccionesperubackend.model.Propuesta;
import com.elecciones.eleccionesperubackend.repository.CandidatoRepository;
import com.elecciones.eleccionesperubackend.service.ResumenService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResumenServiceImpl implements ResumenService {

    private final CandidatoRepository candidatoRepository;

    public ResumenServiceImpl(CandidatoRepository candidatoRepository) {
        this.candidatoRepository = candidatoRepository;
    }

    @Override
    public ResumenDTO obtenerResumenPorCandidato(Long id) {
        Candidato c = candidatoRepository
                .findByIdWithPropuestas(id)
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));

        // Funciona igual con Set
        List<String> propuestas = c.getPropuestas() == null ? new ArrayList<>() :
                c.getPropuestas().stream()
                        .map(Propuesta::getDescripcion)
                        .toList();

        List<DenunciaDTO> denuncias = c.getDenuncias() == null ? new ArrayList<>() :
                c.getDenuncias().stream()
                        .map(d -> new DenunciaDTO(
                                d.getDescripcion(),
                                d.getEstado() != null ? d.getEstado().toString() : "PENDIENTE",
                                d.getGravedad(),
                                d.getFuenteUrl(),
                                d.getFecha(),
                                d.getTipo(),
                                d.getFuente()
                        ))
                        .toList();

        return new ResumenDTO(
                c.getNombre(),
                c.getPartido(),
                c.getResumenLargo(),
                propuestas,
                c.getPerfilLargo(),
                c.getPdfUrl(),
                c.getFotoUrl(),
                denuncias,
                c.getLogoPartidoUrl()
        );
    }


    // 🔥 puedes mejorar esto luego con IA
    private String generarResumen(Candidato c) {
        return "El candidato " + c.getNombre() +
                " propone iniciativas enfocadas en " +
                c.getPartido() + ", priorizando desarrollo social y económico.";
    }

    private String generarPerfil(Candidato c) {
        return "Candidato " + c.getNombre() +
                " del partido " + c.getPartido() +
                ". Información basada en su hoja de vida y trayectoria política.";
    }



}
