package com.fintrack.fintrack.dto.categoria;

import com.fintrack.fintrack.model.Categoria;
import com.fintrack.fintrack.model.CategoriaTipo;

public record CategoriaOutput(
    Long id,
    String nome,
    CategoriaTipo tipo,
    Boolean defaultCategory
) {
    public CategoriaOutput(Categoria categoria) {
       this(
               categoria.getId(),
               categoria.getNome(),
               categoria.getTipo(),
               categoria.getDefaultCategory()
       );
    }
}
