package com.fintrack.fintrack.dto.usuario;

public record UsuarioCadastroInput(
        String nome,
        String email,
        String senha
) {

}
