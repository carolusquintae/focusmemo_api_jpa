package com.api.focusmemo.controller;

import com.api.focusmemo.dto.PasswordRequestDTO;
import com.api.focusmemo.dto.UsuarioDTO;
import com.api.focusmemo.dto.UsuarioRegistroDTO;
import com.api.focusmemo.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> registrar(@Valid @RequestBody UsuarioRegistroDTO registroDTO) {
        UsuarioDTO creado = usuarioService.registrarUsuario(registroDTO);
        URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(creado.idUsuario())
                            .toUri();
        return ResponseEntity.created(location).body(creado);
    }
    
    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody UsuarioRegistroDTO loginDTO) {
        return ResponseEntity.ok(usuarioService.login(loginDTO));
    }
    
    @PostMapping("/{id}/password")
    public ResponseEntity<Void> cambiarPassword(@PathVariable Long id, @Valid @RequestBody PasswordRequestDTO passwordRequestDTO) {
        usuarioService.cambiarPassword(id, passwordRequestDTO);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.actualizar(id, usuarioDTO));
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
