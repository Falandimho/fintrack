package com.fintrack.fintrack.repository;

import com.fintrack.fintrack.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    List<Receita> findAllByUsuarioId(Long id);
}
