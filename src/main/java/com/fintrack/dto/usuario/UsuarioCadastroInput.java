package com.fintrack.dto.usuario;

public record UsuarioCadastroInput(
        String nome,
        String email,
        String senha
) {

}
