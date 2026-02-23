package com.fintrack.fintrack.repository;

import com.fintrack.fintrack.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findAllByDefaultCategoryTrueOrUsuario_Id(Long idUsuario);
}
