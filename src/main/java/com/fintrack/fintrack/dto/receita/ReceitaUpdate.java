package com.fintrack.fintrack.dto.receita;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReceitaUpdate(
        Long id,
        String titulo,
        String descricao,
        Long categoriaId,
        BigDecimal valor,
        LocalDate dataReceita
) {
}
