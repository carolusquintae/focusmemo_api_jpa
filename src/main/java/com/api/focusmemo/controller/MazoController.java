package com.api.focusmemo.controller;

import com.api.focusmemo.dto.MazoDTO;
import com.api.focusmemo.service.MazoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/mazos")
@RequiredArgsConstructor
public class MazoController {

    private final MazoService mazoService;

    @PostMapping
    public ResponseEntity<MazoDTO> crear(@Valid @RequestBody MazoDTO mazoDTO) {
        MazoDTO creado = mazoService.crear(mazoDTO);
        URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(creado.idMazo())
                            .toUri();
        return ResponseEntity.created(location).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<MazoDTO>> listar() {
        return ResponseEntity.ok(mazoService.listarTodos());
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<MazoDTO>> listarPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(mazoService.listarPorUsuario(idUsuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MazoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mazoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MazoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody MazoDTO mazoDTO) {
        return ResponseEntity.ok(mazoService.actualizar(id, mazoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        mazoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
