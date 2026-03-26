package com.api.focusmemo.dto;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record ProgresoTarjetaDTO(
    Long idProgreso,
    Long idUsuario,
    Long idTarjeta,
    Integer repeticion,
    Integer intervalo,
    Double easeFactor,
    LocalDate proximaRevision,
    LocalDate ultimaRevision
) {}
