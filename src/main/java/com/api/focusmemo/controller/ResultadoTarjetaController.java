package com.api.focusmemo.controller;

import com.api.focusmemo.dto.ResultadoTarjetaDTO;
import com.api.focusmemo.service.ResultadoTarjetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resultados")
@RequiredArgsConstructor
public class ResultadoTarjetaController {

    private final ResultadoTarjetaService service;

    @PostMapping
    public ResponseEntity<ResultadoTarjetaDTO> guardar(@RequestBody ResultadoTarjetaDTO dto) {
        return ResponseEntity.ok(service.guardar(dto));
    }

    @GetMapping("/usuario/{uId}")
    public ResponseEntity<List<ResultadoTarjetaDTO>> listar(@PathVariable Long uId) {
        return ResponseEntity.ok(service.listarPorUsuario(uId));
    }
}
