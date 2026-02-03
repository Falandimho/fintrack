package com.fintrack.fintrack.dto.categoria;

import com.fintrack.fintrack.model.CategoriaTipo;

public record CategoriaInput(
        String nome,
        CategoriaTipo tipo,
        Long usuarioId
) {
}
