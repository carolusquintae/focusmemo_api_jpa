package com.api.focusmemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "mazo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mazo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mazo")
    private Long idMazo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Size(max = 20)
    @Column(length = 20)
    private String color;

    @Column(
        name = "fecha_creacion",
        insertable = false,
        updatable = false,
        columnDefinition = "datetime default CURRENT_TIMESTAMP"
    )
    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "mazo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarjeta> tarjetas;
}
