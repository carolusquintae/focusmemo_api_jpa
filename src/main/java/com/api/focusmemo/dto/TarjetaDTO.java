package com.api.focusmemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record TarjetaDTO(
    Long idTarjeta,
    
    @NotNull
    Long idMazo,
    
    @NotBlank
    String pregunta,
    
    @NotBlank
    String respuesta,
    
    boolean activa
) {}