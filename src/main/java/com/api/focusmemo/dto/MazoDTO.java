package com.api.focusmemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record MazoDTO(
    Long idMazo,
    String descripcion,
    
    @NotNull
    Long idUsuario,
    
    @NotBlank
    @Size(max = 150)
    String nombre,
    
    @Size(max = 20)
    String color
) {}