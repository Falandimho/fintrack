package com.fintrack.fintrack.repository;

import com.fintrack.fintrack.dto.relatorio.RelatorioCategoria;
import com.fintrack.fintrack.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    List<Receita> findAllByUsuarioId(Long id);
    List<Receita> findAllByUsuarioIdAndDataReceitaBetween(Long id, LocalDate inicio, LocalDate fim);

    @Query("""
        SELECT new com.fintrack.fintrack.dto.relatorio.RelatorioCategoria(
            c.id,
            c.nome,
            c.tipo,
            SUM(r.valor),
            COUNT(r.id),
            AVG(r.valor)
        )
        FROM Receita r
        JOIN r.categoria c
        WHERE r.usuario.id = :usuarioId
        AND c.id = :categoriaId
        AND r.dataReceita BETWEEN :dataInicio AND :dataFim
        GROUP BY c.id, c.nome, c.tipo
    """)
    RelatorioCategoria gerarRelatorioPorCategoria(
            @Param("usuarioId") Long usuarioId,
            @Param("categoriaId") Long categoriaId,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim")LocalDate dataFim
    );
}

