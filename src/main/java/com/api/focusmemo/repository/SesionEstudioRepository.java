package com.api.focusmemo.repository;

import com.api.focusmemo.model.SesionEstudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SesionEstudioRepository extends JpaRepository<SesionEstudio, Long> {
    List<SesionEstudio> findByUsuarioIdUsuario(Long idUsuario);
}
