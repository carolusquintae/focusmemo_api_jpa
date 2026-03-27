package com.api.focusmemo.service;
import com.api.focusmemo.dto.SesionEstudioDTO;
import java.util.List;
public interface SesionEstudioService {
    SesionEstudioDTO guardar(SesionEstudioDTO dto);
    List<SesionEstudioDTO> listarPorUsuario(Long idUsuario);
}
