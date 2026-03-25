package com.api.focusmemo.repository;

import com.api.focusmemo.model.Suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuscripcionRepository extends JpaRepository<Suscripcion, Long> {
    List<Suscripcion> findByUsuarioIdUsuario(Long idUsuario);
}
