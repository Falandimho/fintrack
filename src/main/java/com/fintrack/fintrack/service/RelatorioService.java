package com.fintrack.fintrack.service;

import com.fintrack.fintrack.dto.relatorio.RelatorioCategoria;
import com.fintrack.fintrack.model.CategoriaTipo;
import com.fintrack.fintrack.model.Usuario;
import com.fintrack.fintrack.repository.DespesaRepository;
import com.fintrack.fintrack.repository.ReceitaRepository;
import com.fintrack.fintrack.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RelatorioService {
    private final UsuarioRepository usuarioRepository;
    private final DespesaRepository despesaRepository;
    private final ReceitaRepository receitaRepository;

    public RelatorioService(UsuarioRepository usuarioRepository,
                            DespesaRepository despesaRepository,
                            ReceitaRepository receitaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.despesaRepository = despesaRepository;
        this.receitaRepository = receitaRepository;
    }

    public RelatorioCategoria gerarRelatorioPorCategoria(
            String email,
            Long categoriaId,
            CategoriaTipo categoriaTipo,
            LocalDate dataInicio,
            LocalDate dataFim
    ) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        if (usuarioOptional.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado com email: " + email);
        }

        var usuario = usuarioOptional.get();

        RelatorioCategoria relatorio = null;

        if(categoriaTipo == CategoriaTipo.DESPESA){
            relatorio = despesaRepository.gerarRelatorioPorCategoria(
                   usuario.getId(),
                   categoriaId,
                   dataInicio,
                   dataFim
           );
        } else if (categoriaTipo == CategoriaTipo.RECEITA) {
            relatorio = receitaRepository.gerarRelatorioPorCategoria(
                    usuario.getId(),
                    categoriaId,
                    dataInicio,
                    dataFim
            );
        } else {
            throw new RuntimeException("Tipo de categoria inválido: " + categoriaTipo);
        }

        if (relatorio == null) {
            throw new RuntimeException("Nenhum dado encontrado para categoria ID " + categoriaId
                + " no período de " + dataInicio + " a " + dataFim);
        }

        return relatorio;
    }
}
