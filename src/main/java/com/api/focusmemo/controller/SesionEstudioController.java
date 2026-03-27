package com.api.focusmemo.controller;

import com.api.focusmemo.dto.SesionEstudioDTO;
import com.api.focusmemo.service.SesionEstudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sesiones")
@RequiredArgsConstructor
public class SesionEstudioController {
    
    private final SesionEstudioService service;
    
    @PostMapping
    public ResponseEntity<SesionEstudioDTO> guardar(@RequestBody SesionEstudioDTO dto) {
        return ResponseEntity.ok(service.guardar(dto));
    }
    
    @GetMapping("/usuario/{uId}")
    public ResponseEntity<List<SesionEstudioDTO>> listar(@PathVariable Long uId) {
        return ResponseEntity.ok(service.listarPorUsuario(uId));
    }
}
