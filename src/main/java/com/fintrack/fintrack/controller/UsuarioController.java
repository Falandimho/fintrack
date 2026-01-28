package com.fintrack.fintrack.controller;

import com.fintrack.fintrack.dto.usuario.UsuarioInput;
import com.fintrack.fintrack.dto.usuario.UsuarioPerfilOutput;
import com.fintrack.fintrack.dto.usuario.UsuarioSaldoOutput;
import com.fintrack.fintrack.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarUsuario(@RequestBody UsuarioInput usuario) {
        usuarioService.cadastrarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioPerfilOutput buscarUsuarioPorEmail(@PathVariable("email") String email) {
        return usuarioService.getUsuarioPerfil(email);
    }

    @GetMapping("/saldo/{email}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioSaldoOutput buscarUsuarioPorSaldo(@PathVariable("email") String email) {
        return usuarioService.getUsuarioSaldo(email);
    }

}
