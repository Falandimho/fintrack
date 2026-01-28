package com.fintrack.fintrack.dto.despesa;

import com.fintrack.fintrack.model.Despesa;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DespesaOutput(
        Long id,
        String titulo,
        String descricao,
        String categoria,
        BigDecimal valor,
        LocalDate dataDespesa
) {
    public DespesaOutput(Despesa despesa){
        this(
                despesa.getId(),
                despesa.getTitulo(),
                despesa.getDescricao(),
                String.valueOf(despesa.getCategoria().getNome()),
                despesa.getValor(),
                despesa.getDataDespesa()
        );
    }
}
