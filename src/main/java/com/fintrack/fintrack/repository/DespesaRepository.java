package com.fintrack.fintrack.repository;

import com.fintrack.fintrack.dto.relatorio.RelatorioCategoria;
import com.fintrack.fintrack.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa,Long> {
    List<Despesa> findAllByUsuarioId(Long idUsuario);

    @Query("""
        SELECT new com.fintrack.fintrack.dto.relatorio.RelatorioCategoria(
            c.id,
            c.nome,
            c.tipo,
            SUM(d.valor),
            COUNT(d.id),
            AVG(d.valor)
        )
        FROM Despesa d
        JOIN d.categoria c
        WHERE d.usuario.id = :usuarioId
        AND c.id = :categoriaId
        AND d.dataDespesa BETWEEN :dataInicio AND :dataFim
        GROUP BY c.id, c.nome, c.tipo
    """)
    RelatorioCategoria gerarRelatorioPorCategoria(
            @Param("usuarioId") Long usuarioId,
            @Param("categoriaId") Long categoriaId,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim
            );

    List<Despesa> findAllByUsuarioIdAndDataDespesaBetween(Long id, LocalDate inicio, LocalDate fim);
}
