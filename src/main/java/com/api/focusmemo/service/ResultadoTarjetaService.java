package com.api.focusmemo.service;

import com.api.focusmemo.dto.ResultadoTarjetaDTO;
import java.util.List;

public interface ResultadoTarjetaService {
    ResultadoTarjetaDTO guardar(ResultadoTarjetaDTO dto);
    List<ResultadoTarjetaDTO> listarPorUsuario(Long idUsuario);
}
