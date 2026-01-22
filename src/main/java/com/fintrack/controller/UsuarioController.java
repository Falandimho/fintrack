package com.fintrack.controller;

import com.fintrack.dto.usuario.UsuarioCadastroInput;
import com.fintrack.dto.usuario.UsuarioPerfilOutput;
import com.fintrack.dto.usuario.UsuarioSaldoOutput;
import com.fintrack.model.Usuario;
import com.fintrack.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/user")
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarUsuario(@RequestBody UsuarioCadastroInput usuario) {
        Usuario usuario1 = usuarioService.cadastrarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioPerfilOutput buscarUsuarioPorEmail(@PathVariable("email") String email) {
        return usuarioService.getUsuarioPerfil(email);
    }

    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioSaldoOutput buscarUsuarioPorSaldo(@PathVariable("email") String email) {
        return usuarioService.getUsuarioSaldo(email);
    }

}
