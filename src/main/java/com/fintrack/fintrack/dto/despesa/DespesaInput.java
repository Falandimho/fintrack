package com.fintrack.fintrack.dto.despesa;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DespesaInput(
        String titulo,
        String descricao,
        Long categoriaId,
        Long usuarioId,
        BigDecimal valor,
        LocalDate dataDespesa
) {

}

