package com.api.focusmemo.repository;

import com.api.focusmemo.model.Mazo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MazoRepository extends JpaRepository<Mazo, Long> {
    List<Mazo> findByUsuarioIdUsuario(Long idUsuario);
}
