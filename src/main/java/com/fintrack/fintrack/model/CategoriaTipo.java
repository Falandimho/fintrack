package com.fintrack.fintrack.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CategoriaTipo {
    DESPESA,
    RECEITA;

    @JsonValue
    public String toValue() {
        return name();
    }

    @JsonCreator
    public static CategoriaTipo fromString(String value) {
        if (value == null) return null;
        try {
            return CategoriaTipo.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Valor inválido para CategoriaTipo: " + value);
        }
    }
}
