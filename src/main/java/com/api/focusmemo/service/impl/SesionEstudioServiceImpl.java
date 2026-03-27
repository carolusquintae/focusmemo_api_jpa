package com.api.focusmemo.service.impl;

import com.api.focusmemo.dto.SesionEstudioDTO;
import com.api.focusmemo.model.SesionEstudio;
import com.api.focusmemo.model.Usuario;
import com.api.focusmemo.repository.SesionEstudioRepository;
import com.api.focusmemo.repository.UsuarioRepository;
import com.api.focusmemo.service.SesionEstudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SesionEstudioServiceImpl implements SesionEstudioService {
    
    private final SesionEstudioRepository repo;
    private final UsuarioRepository userRepo;
    
    @Override
    @Transactional
    public SesionEstudioDTO guardar(SesionEstudioDTO dto) {
        Usuario user = userRepo.findById(dto.idUsuario()).orElseThrow();
        SesionEstudio s = SesionEstudio.builder()
                                .usuario(user)
                                .fechaInicio(dto.fechaInicio())
                                .fechaFin(dto.fechaFin())
                                .tarjetasEstudiadas(dto.tarjetasEstudiadas())
                                .aciertos(dto.aciertos())
                                .errores(dto.errores())
                                .build();
        
        return mapToDTO(repo.save(s));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<SesionEstudioDTO> listarPorUsuario(Long idUsuario) {
        return repo
                 .findByUsuarioIdUsuario(idUsuario)
                 .stream().map(se -> this.mapToDTO(se))
                 .collect(Collectors.toList());
    }
    
    private SesionEstudioDTO mapToDTO(SesionEstudio s) {
        return SesionEstudioDTO.builder()
                    .idSesion(s.getIdSesion())
                    .idUsuario(s.getUsuario()
                    .getIdUsuario())
                    .fechaInicio(s.getFechaInicio())
                    .fechaFin(s.getFechaFin())
                    .tarjetasEstudiadas(s.getTarjetasEstudiadas())
                    .aciertos(s.getAciertos())
                    .errores(s.getErrores())
                    .build();
    }
}
