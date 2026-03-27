package com.api.focusmemo.controller;

import com.api.focusmemo.dto.PartidaMinijuegoDTO;
import com.api.focusmemo.service.PartidaMinijuegoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/partidas")
@RequiredArgsConstructor
public class PartidaMinijuegoController {

    private final PartidaMinijuegoService service;

    @PostMapping
    public ResponseEntity<PartidaMinijuegoDTO> guardar(@RequestBody PartidaMinijuegoDTO dto) {
        return ResponseEntity.ok(service.guardar(dto));
    }

    @GetMapping("/usuario/{uId}")
    public ResponseEntity<List<PartidaMinijuegoDTO>> listar(@PathVariable Long uId) {
        return ResponseEntity.ok(service.listarPorUsuario(uId));
    }
}
