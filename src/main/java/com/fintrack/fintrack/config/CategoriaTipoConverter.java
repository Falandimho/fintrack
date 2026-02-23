package com.fintrack.fintrack.config;

import com.fintrack.fintrack.model.CategoriaTipo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoriaTipoConverter implements Converter<String, CategoriaTipo> {

    @Override
    public CategoriaTipo convert(String source){
        if(source == null) return null;
        try {
            return CategoriaTipo.valueOf(source.trim().toUpperCase());
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
