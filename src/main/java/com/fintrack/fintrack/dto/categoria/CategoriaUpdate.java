package com.fintrack.fintrack.dto.categoria;

import com.fintrack.fintrack.model.CategoriaTipo;

public record CategoriaUpdate(
        Long id,
        String nome,
        CategoriaTipo tipo
) {

}
