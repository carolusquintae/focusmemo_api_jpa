package com.api.focusmemo.service.impl;

import com.api.focusmemo.dto.SuscripcionDTO;
import com.api.focusmemo.model.Suscripcion;
import com.api.focusmemo.model.Usuario;
import com.api.focusmemo.repository.SuscripcionRepository;
import com.api.focusmemo.repository.UsuarioRepository;
import com.api.focusmemo.service.SuscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SuscripcionServiceImpl implements SuscripcionService {
    
    private final SuscripcionRepository repo;
    private final UsuarioRepository userRepo;
    
    @Override
    @Transactional
    public SuscripcionDTO guardar(SuscripcionDTO dto) {
        Usuario user = userRepo.findById(dto.idUsuario()).orElseThrow();
        Suscripcion s = Suscripcion.builder()
                            .usuario(user)
                            .tipoPlan(dto.tipoPlan())
                            .fechaInicio(dto.fechaInicio())
                            .fechaFin(dto.fechaFin())
                            .estado(dto.estado())
                            .build();
        return mapToDTO(repo.save(s));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<SuscripcionDTO> listarPorUsuario(Long idUsuario) {
        return repo
                .findByUsuarioIdUsuario(idUsuario)
            .stream().map(s -> this.mapToDTO(s))
            .collect(Collectors.toList());
    }
    
    private SuscripcionDTO mapToDTO(Suscripcion s) {
        return SuscripcionDTO.builder()
                    .idSuscripcion(s.getIdSuscripcion())
                    .idUsuario(s.getUsuario().getIdUsuario())
                    .tipoPlan(s.getTipoPlan())
                    .fechaInicio(s.getFechaInicio())
                    .fechaFin(s.getFechaFin())
                    .estado(s.getEstado())
                    .build();
    }
}
