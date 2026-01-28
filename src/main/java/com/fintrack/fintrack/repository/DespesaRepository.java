package com.fintrack.fintrack.repository;

import com.fintrack.fintrack.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DespesaRepository extends JpaRepository<Despesa,Long> {

    List<Despesa> findAllByUsuarioId(Long idUsuario);
}
