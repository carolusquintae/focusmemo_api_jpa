package com.api.focusmemo.service;

import com.api.focusmemo.dto.PasswordRequestDTO;
import com.api.focusmemo.dto.UsuarioDTO;
import com.api.focusmemo.dto.UsuarioRegistroDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioDTO registrarUsuario(UsuarioRegistroDTO registroDTO);
    List<UsuarioDTO> listarTodos();
    UsuarioDTO buscarPorId(Long id);
    UsuarioDTO actualizar(Long id, UsuarioDTO usuarioDTO);
    UsuarioDTO login(UsuarioRegistroDTO loginDTO);
    void eliminar(Long id);
    void cambiarPassword(Long id, PasswordRequestDTO passwordRequestDTO);
}
