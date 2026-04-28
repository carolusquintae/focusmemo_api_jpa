package com.api.focusmemo.repository;

import com.api.focusmemo.model.ProgresoTarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgresoTarjetaRepository extends JpaRepository<ProgresoTarjeta, Long> {
    String jpql = """
        SELECT p FROM ProgresoTarjeta p
        WHERE
            p.usuario.idUsuario = :idUsuario AND
            p.tarjeta.idTarjeta = :idTarjeta
    """;
    
    @Query(jpql)
    Optional<ProgresoTarjeta> buscarPorIds(@Param("idUsuario") Long idUsuario, @Param("idTarjeta") Long idTarjeta);
}
