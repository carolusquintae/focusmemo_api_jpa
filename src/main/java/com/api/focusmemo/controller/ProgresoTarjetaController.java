package com.api.focusmemo.controller;

import com.api.focusmemo.dto.ProgresoTarjetaDTO;
import com.api.focusmemo.service.ProgresoTarjetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/progresos")
@RequiredArgsConstructor
public class ProgresoTarjetaController {

    private final ProgresoTarjetaService service;

    @PutMapping
    public ResponseEntity<ProgresoTarjetaDTO> upsert(@RequestBody ProgresoTarjetaDTO dto) {
        return ResponseEntity.ok(service.upsert(dto));
    }

    @GetMapping("/usuario/{uId}/tarjeta/{tId}")
    public ResponseEntity<ProgresoTarjetaDTO> buscar(@PathVariable Long uId, @PathVariable Long tId) {
        return ResponseEntity.ok(service.buscarPorUsuarioYTarjeta(uId, tId));
    }
}
