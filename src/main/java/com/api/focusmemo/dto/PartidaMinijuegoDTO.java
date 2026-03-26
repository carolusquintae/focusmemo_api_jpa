package com.api.focusmemo.dto;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record PartidaMinijuegoDTO(
    Long idPartida,
    Long idUsuario,
    Integer puntuacion,
    Integer aciertos,
    Integer errores,
    LocalDateTime fecha
) {}