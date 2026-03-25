package com.api.focusmemo.repository;

import com.api.focusmemo.model.ResultadoTarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultadoTarjetaRepository extends JpaRepository<ResultadoTarjeta, Long> {
    List<ResultadoTarjeta> findByUsuarioIdUsuario(Long idUsuario);
}
