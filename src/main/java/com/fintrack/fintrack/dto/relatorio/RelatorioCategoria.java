package com.fintrack.fintrack.dto.relatorio;

import com.fintrack.fintrack.model.CategoriaTipo;

import java.math.BigDecimal;

public record RelatorioCategoria(
    Long categoriaId,
    String categoriaNome,
    CategoriaTipo tipo,
    BigDecimal totalValor,
    Long quantidadeTransacoes,
    BigDecimal valorMedio
) {
    // Construtor adicional para compatibilidade com JPQL que retorna BigDecimal (SUM) e Double (AVG)
    public RelatorioCategoria(Long categoriaId, String categoriaNome, CategoriaTipo tipo,
                              BigDecimal totalValor, Long quantidadeTransacoes, Double valorMedio) {
        this(categoriaId, categoriaNome, tipo,
                totalValor != null ? totalValor : BigDecimal.ZERO,
                quantidadeTransacoes,
                valorMedio != null ? BigDecimal.valueOf(valorMedio) : BigDecimal.ZERO);
    }

}
