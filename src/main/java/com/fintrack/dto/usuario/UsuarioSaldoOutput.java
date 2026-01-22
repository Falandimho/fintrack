package com.fintrack.dto.usuario;

import com.fintrack.model.Usuario;

import java.math.BigDecimal;

public record UsuarioSaldoOutput(
        Long id,
        BigDecimal saldoAtual
) {
   public UsuarioSaldoOutput(Usuario usuario) {
       this(
               usuario.getId(),
               usuario.getSaldoAtual()
       );
   }
}
