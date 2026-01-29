package com.fintrack.fintrack.dto.receita;

import com.fintrack.fintrack.model.Receita;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReceitaOutput(
        Long id,
        String titulo,
        String descricao,
        String categoria,
        BigDecimal valor,
        LocalDate dataReceita
) {
    public ReceitaOutput(Receita receita) {
        this(
                receita.getId(),
                receita.getTitulo(),
                receita.getDescricao(),
                String.valueOf(receita.getCategoria().getNome()),
                receita.getValor(),
                receita.getDataReceita()
        );
    }
}
