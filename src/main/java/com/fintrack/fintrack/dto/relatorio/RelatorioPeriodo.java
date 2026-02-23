package com.fintrack.fintrack.dto.relatorio;

import java.math.BigDecimal;

public record RelatorioPeriodo(
        BigDecimal valorGanho,
        BigDecimal valorGasto,
        BigDecimal valorLiquido,
        Long quantidadeDespesa,
        Long quantidadeReceita
) {

}
