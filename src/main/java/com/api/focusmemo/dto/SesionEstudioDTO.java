package com.api.focusmemo.dto;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record SesionEstudioDTO(
    Long idSesion,
    Long idUsuario,
    LocalDateTime fechaInicio,
    LocalDateTime fechaFin,
    Integer tarjetasEstudiadas,
    Integer aciertos,
    Integer errores
) {}
