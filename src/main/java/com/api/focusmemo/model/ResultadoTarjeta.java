package com.api.focusmemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "resultado_tarjeta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultadoTarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resultado")
    private Long idResultado;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tarjeta", nullable = false)
    private Tarjeta tarjeta;

    @NotNull
    @Column(name = "calidad_respuesta", nullable = false)
    private Integer calidadRespuesta;

    @Column(
        name = "fecha",
        insertable = false,
        updatable = false,
        columnDefinition = "datetime default CURRENT_TIMESTAMP"
    )
    private LocalDateTime fecha;
}
