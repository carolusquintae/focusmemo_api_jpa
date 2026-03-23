package com.api.focusmemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "partida_minijuego")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartidaMinijuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partida")
    private Long idPartida;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Builder.Default
    @Column(name = "puntuacion")
    private Integer puntuacion = 0;

    @Builder.Default
    @Column(name = "aciertos")
    private Integer aciertos = 0;

    @Builder.Default
    @Column(name = "errores")
    private Integer errores = 0;

    @Column(
        name = "fecha",
        insertable = false,
        updatable = false,
        columnDefinition = "datetime default CURRENT_TIMESTAMP"
    )
    private LocalDateTime fecha;
}
