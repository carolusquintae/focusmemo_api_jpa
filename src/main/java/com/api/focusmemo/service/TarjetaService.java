package com.api.focusmemo.service;

import com.api.focusmemo.dto.TarjetaDTO;
import java.util.List;

public interface TarjetaService {
    TarjetaDTO crear(TarjetaDTO tarjetaDTO);
    List<TarjetaDTO> listarPorMazo(Long idMazo);
    TarjetaDTO buscarPorId(Long id);
    TarjetaDTO actualizar(Long id, TarjetaDTO tarjetaDTO);
    void eliminar(Long id);
}
