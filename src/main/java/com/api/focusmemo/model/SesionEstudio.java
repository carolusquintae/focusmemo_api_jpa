package com.api.focusmemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "sesion_estudio")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SesionEstudio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sesion")
    private Long idSesion;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    @Builder.Default
    @Column(name = "tarjetas_estudiadas")
    private Integer tarjetasEstudiadas = 0;

    @Builder.Default
    @Column(name = "aciertos")
    private Integer aciertos = 0;

    @Builder.Default
    @Column(name = "errores")
    private Integer errores = 0;
}
