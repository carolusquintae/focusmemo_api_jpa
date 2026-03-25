package com.api.focusmemo.repository;

import com.api.focusmemo.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
    List<Tarjeta> findByMazoIdMazo(Long idMazo);
}
