package com.api.focusmemo.service.impl;

import com.api.focusmemo.dto.UsuarioDTO;
import com.api.focusmemo.dto.UsuarioRegistroDTO;
import com.api.focusmemo.exception.BusinessConflictException;
import com.api.focusmemo.exception.ResourceNotFoundException;
import com.api.focusmemo.model.Usuario;
import com.api.focusmemo.repository.UsuarioRepository;
import com.api.focusmemo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UsuarioDTO registrarUsuario(UsuarioRegistroDTO registroDTO) {
        if (usuarioRepository.findByEmail(registroDTO.email()).isPresent()) {
            throw new BusinessConflictException("El email ya está registrado");
        }

        Usuario usuario = Usuario.builder()
                             .nombre(registroDTO.nombre())
                             .email(registroDTO.email())
                             .passwordHash(passwordEncoder.encode(registroDTO.password()))
                             .build();

        usuario = usuarioRepository.save(usuario);
        return mapToDTO(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository
                    .findAll()
                    .stream()
                    .map(u -> this.mapToDTO(u))
                    .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository
                            .findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
        return mapToDTO(usuario);
    }

    @Override
    @Transactional
    public UsuarioDTO actualizar(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository
                            .findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));

        boolean emailUsado = !usuario.getEmail().equals(usuarioDTO.email())
                             && usuarioRepository.findByEmail(usuarioDTO.email()).isPresent();
        
        if (emailUsado) {
            throw new BusinessConflictException("El nuevo email ya está en uso por otro usuario");
        }

        usuario.setNombre(usuarioDTO.nombre());
        usuario.setEmail(usuarioDTO.email());
        usuario = usuarioRepository.save(usuario);
        return mapToDTO(usuario);
    }
    
    @Override
    public UsuarioDTO login(UsuarioRegistroDTO loginDTO) {
        Usuario usuario = usuarioRepository
                            .findByEmail(loginDTO.email())
                            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        if (!passwordEncoder.matches(loginDTO.password(), usuario.getPasswordHash())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        
        return mapToDTO(usuario);
    }
    
    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO mapToDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                    .idUsuario(usuario.getIdUsuario())
                    .nombre(usuario.getNombre())
                    .email(usuario.getEmail())
                    .premium(usuario.isPremium())
                    .build();
    }
}
