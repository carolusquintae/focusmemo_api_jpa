package com.api.focusmemo.controller;

import com.api.focusmemo.dto.SuscripcionDTO;
import com.api.focusmemo.service.SuscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/suscripciones")
@RequiredArgsConstructor
public class SuscripcionController {
    
    private final SuscripcionService service;
    
    @PostMapping
    public ResponseEntity<SuscripcionDTO> guardar(@RequestBody SuscripcionDTO dto) {
        return ResponseEntity.ok(service.guardar(dto));
    }
    
    @GetMapping("/usuario/{uId}")
    public ResponseEntity<List<SuscripcionDTO>> listar(@PathVariable Long uId) {
        return ResponseEntity.ok(service.listarPorUsuario(uId));
    }
    
    @DeleteMapping("/suscripciones/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        service.borrar(id);
        return ResponseEntity.noContent().build();
    }
}
