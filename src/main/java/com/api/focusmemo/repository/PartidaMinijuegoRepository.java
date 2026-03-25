package com.api.focusmemo.repository;

import com.api.focusmemo.model.PartidaMinijuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidaMinijuegoRepository extends JpaRepository<PartidaMinijuego, Long> {
    List<PartidaMinijuego> findByUsuarioIdUsuario(Long idUsuario);
}
