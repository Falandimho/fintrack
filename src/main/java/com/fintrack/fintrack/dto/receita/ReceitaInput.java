package com.fintrack.fintrack.dto.receita;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReceitaInput(
        String titulo,
        String descricao,
        Long categoriaId,
        Long usuarioId,
        BigDecimal valor,
        LocalDate dataReceita
) {
}
