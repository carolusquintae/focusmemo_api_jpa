package com.api.focusmemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tarjeta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarjeta")
    private Long idTarjeta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mazo", nullable = false)
    private Mazo mazo;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String pregunta;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String respuesta;

    @Column(
        name = "fecha_creacion",
        insertable = false,
        updatable = false,
        columnDefinition = "datetime default CURRENT_TIMESTAMP"
    )
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private boolean activa;

    @OneToMany(mappedBy = "tarjeta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgresoTarjeta> progresos;

    @OneToMany(mappedBy = "tarjeta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResultadoTarjeta> resultados;
}
