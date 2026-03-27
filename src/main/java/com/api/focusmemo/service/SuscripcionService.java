package com.api.focusmemo.service;
import com.api.focusmemo.dto.SuscripcionDTO;
import java.util.List;
public interface SuscripcionService {
    SuscripcionDTO guardar(SuscripcionDTO dto);
    List<SuscripcionDTO> listarPorUsuario(Long idUsuario);
}
