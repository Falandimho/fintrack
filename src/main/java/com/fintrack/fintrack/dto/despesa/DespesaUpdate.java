package com.fintrack.fintrack.dto.despesa;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DespesaUpdate(
        Long id,
        String titulo,
        String descricao,
        Long idCategoria,
        BigDecimal valor,
        LocalDate dataDespesa
) {
}
