package com.api.focusmemo.service;

import com.api.focusmemo.dto.PartidaMinijuegoDTO;
import java.util.List;

public interface PartidaMinijuegoService {
    PartidaMinijuegoDTO guardar(PartidaMinijuegoDTO dto);
    List<PartidaMinijuegoDTO> listarPorUsuario(Long idUsuario);
}
