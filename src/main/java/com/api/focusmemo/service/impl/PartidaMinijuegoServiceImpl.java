package com.api.focusmemo.service.impl;

import com.api.focusmemo.dto.PartidaMinijuegoDTO;
import com.api.focusmemo.model.PartidaMinijuego;
import com.api.focusmemo.model.Usuario;
import com.api.focusmemo.repository.PartidaMinijuegoRepository;
import com.api.focusmemo.repository.UsuarioRepository;
import com.api.focusmemo.service.PartidaMinijuegoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartidaMinijuegoServiceImpl implements PartidaMinijuegoService {

    private final PartidaMinijuegoRepository repo;
    private final UsuarioRepository userRepo;

    @Override
    @Transactional
    public PartidaMinijuegoDTO guardar(PartidaMinijuegoDTO dto) {
        Usuario user = userRepo.findById(dto.idUsuario()).orElseThrow();
        
        PartidaMinijuego p = PartidaMinijuego.builder()
                                .usuario(user)
                                .puntuacion(dto.puntuacion())
                                .aciertos(dto.aciertos())
                                .errores(dto.errores())
                                .build();
        
        return mapToDTO(repo.save(p));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartidaMinijuegoDTO> listarPorUsuario(Long idUsuario) {
        return repo
                .findByUsuarioIdUsuario(idUsuario)
                .stream()
                .map(pm -> this.mapToDTO(pm))
                .collect(Collectors.toList());
    }

    private PartidaMinijuegoDTO mapToDTO(PartidaMinijuego p) {
        return PartidaMinijuegoDTO.builder()
                    .idPartida(p.getIdPartida())
                    .idUsuario(p.getUsuario().getIdUsuario())
                    .puntuacion(p.getPuntuacion())
                    .aciertos(p.getAciertos())
                    .errores(p.getErrores())
                    .fecha(p.getFecha())
                    .build();
    }
}
