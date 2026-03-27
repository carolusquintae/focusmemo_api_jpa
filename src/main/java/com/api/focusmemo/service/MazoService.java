package com.api.focusmemo.service;

import com.api.focusmemo.dto.MazoDTO;
import java.util.List;

public interface MazoService {
    MazoDTO crear(MazoDTO mazoDTO);
    List<MazoDTO> listarTodos();
    List<MazoDTO> listarPorUsuario(Long idUsuario);
    MazoDTO buscarPorId(Long id);
    MazoDTO actualizar(Long id, MazoDTO mazoDTO);
    void eliminar(Long id);
}
