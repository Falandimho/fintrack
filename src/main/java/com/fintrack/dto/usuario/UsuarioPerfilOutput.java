package com.fintrack.dto.usuario;

import com.fintrack.model.Usuario;

public record UsuarioPerfilOutput(
        Long id,
        String nome,
        String email
) {
    public UsuarioPerfilOutput(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}
