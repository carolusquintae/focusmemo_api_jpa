package com.api.focusmemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "progreso_tarjeta", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_usuario", "id_tarjeta"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgresoTarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_progreso")
    private Long idProgreso;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tarjeta", nullable = false)
    private Tarjeta tarjeta;

    @Builder.Default
    @Column(name = "repeticion")
    private Integer repeticion = 0;

    @Builder.Default
    @Column(name = "intervalo")
    private Integer intervalo = 1;

    @Builder.Default
    @Column(name = "ease_factor")
    private Double easeFactor = 2.5;

    @Column(name = "proxima_revision")
    private LocalDate proximaRevision;

    @Column(name = "ultima_revision")
    private LocalDate ultimaRevision;
}
