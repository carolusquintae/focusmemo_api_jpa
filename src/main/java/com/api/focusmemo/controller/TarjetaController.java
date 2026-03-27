package com.api.focusmemo.controller;

import com.api.focusmemo.dto.TarjetaDTO;
import com.api.focusmemo.service.TarjetaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tarjetas")
@RequiredArgsConstructor
public class TarjetaController {

    private final TarjetaService tarjetaService;

    @PostMapping
    public ResponseEntity<TarjetaDTO> crear(@Valid @RequestBody TarjetaDTO tarjetaDTO) {
        TarjetaDTO creado = tarjetaService.crear(tarjetaDTO);
        URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(creado.idTarjeta())
                            .toUri();
        return ResponseEntity.created(location).body(creado);
    }

    @GetMapping("/mazo/{idMazo}")
    public ResponseEntity<List<TarjetaDTO>> listarPorMazo(@PathVariable Long idMazo) {
        return ResponseEntity.ok(tarjetaService.listarPorMazo(idMazo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarjetaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tarjetaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarjetaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody TarjetaDTO tarjetaDTO) {
        return ResponseEntity.ok(tarjetaService.actualizar(id, tarjetaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tarjetaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
