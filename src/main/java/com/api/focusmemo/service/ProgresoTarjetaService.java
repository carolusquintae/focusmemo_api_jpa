package com.api.focusmemo.service;

import com.api.focusmemo.dto.ProgresoTarjetaDTO;

public interface ProgresoTarjetaService {
    ProgresoTarjetaDTO upsert(ProgresoTarjetaDTO dto);
    ProgresoTarjetaDTO buscarPorUsuarioYTarjeta(Long idUsuario, Long idTarjeta);
}
