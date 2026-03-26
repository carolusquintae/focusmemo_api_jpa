package com.api.focusmemo.dto;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record ResultadoTarjetaDTO(
    Long idResultado,
    Long idUsuario,
    Long idTarjeta,
    Integer calidadRespuesta,
    LocalDateTime fecha
) {}