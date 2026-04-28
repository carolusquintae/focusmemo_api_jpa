package com.api.focusmemo.service.impl;

import com.api.focusmemo.dto.ProgresoTarjetaDTO;
import com.api.focusmemo.exception.ResourceNotFoundException;
import com.api.focusmemo.model.ProgresoTarjeta;
import com.api.focusmemo.model.Tarjeta;
import com.api.focusmemo.model.Usuario;
import com.api.focusmemo.repository.ProgresoTarjetaRepository;
import com.api.focusmemo.repository.TarjetaRepository;
import com.api.focusmemo.repository.UsuarioRepository;
import com.api.focusmemo.service.ProgresoTarjetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProgresoTarjetaServiceImpl implements ProgresoTarjetaService {

    private final ProgresoTarjetaRepository repo;
    private final UsuarioRepository usuarioRepo;
    private final TarjetaRepository tarjetaRepo;

    @Override
    @Transactional
    public ProgresoTarjetaDTO upsert(ProgresoTarjetaDTO dto) {
        ProgresoTarjeta progreso = repo
                                    .buscarPorIds(dto.idUsuario(), dto.idTarjeta())
                                    .orElse(null);

        if (progreso == null) {
            Usuario user = usuarioRepo
                                .findById(dto.idUsuario())
                                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
            Tarjeta card = tarjetaRepo
                                .findById(dto.idTarjeta())
                                .orElseThrow(() -> new ResourceNotFoundException("Tarjeta no encontrada"));
            
            progreso = ProgresoTarjeta.builder()
                            .usuario(user)
                            .tarjeta(card)
                            .build();
        }

        progreso.setRepeticion(dto.repeticion());
        progreso.setIntervalo(dto.intervalo());
        progreso.setEaseFactor(dto.easeFactor());
        progreso.setProximaRevision(dto.proximaRevision());
        progreso.setUltimaRevision(dto.ultimaRevision());

        return mapToDTO(repo.save(progreso));
    }

    @Override
    @Transactional(readOnly = true)
    public ProgresoTarjetaDTO buscarPorUsuarioYTarjeta(Long idUsuario, Long idTarjeta) {
        ProgresoTarjeta p = repo
                             .buscarPorIds(idUsuario, idTarjeta)
                             .orElseThrow(() -> new ResourceNotFoundException("Progreso no encontrado"));
        return mapToDTO(p);
    }

    private ProgresoTarjetaDTO mapToDTO(ProgresoTarjeta p) {
        return ProgresoTarjetaDTO.builder()
                    .idProgreso(p.getIdProgreso())
                    .idUsuario(p.getUsuario().getIdUsuario())
                    .idTarjeta(p.getTarjeta().getIdTarjeta())
                    .repeticion(p.getRepeticion())
                    .intervalo(p.getIntervalo())
                    .easeFactor(p.getEaseFactor())
                    .proximaRevision(p.getProximaRevision())
                    .ultimaRevision(p.getUltimaRevision())
                    .build();
    }
}