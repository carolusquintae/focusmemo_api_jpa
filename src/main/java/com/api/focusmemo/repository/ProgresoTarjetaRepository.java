package com.api.focusmemo.repository;

import com.api.focusmemo.model.ProgresoTarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgresoTarjetaRepository extends JpaRepository<ProgresoTarjeta, Long> {
    Optional<ProgresoTarjeta> findByIdUsuarioAndIdTarjeta(Long idUsuario, Long idTarjeta);
}
