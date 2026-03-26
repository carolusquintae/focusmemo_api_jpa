package com.api.focusmemo.dto;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record SuscripcionDTO(
    Long idSuscripcion,
    Long idUsuario,
    String tipoPlan,
    LocalDateTime fechaInicio,
    LocalDateTime fechaFin,
    String estado
) {}