package com.api.focusmemo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UsuarioDTO(
    Long idUsuario,
    
    @NotBlank
    @Size(max = 100)
    String nombre,
    
    @NotBlank
    @Email
    @Size(max = 150)
    String email,
    
    boolean premium
) {}