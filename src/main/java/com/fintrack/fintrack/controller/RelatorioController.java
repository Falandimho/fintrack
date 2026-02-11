package com.fintrack.fintrack.controller;

import com.fintrack.fintrack.dto.relatorio.RelatorioCategoria;
import com.fintrack.fintrack.model.CategoriaTipo;
import com.fintrack.fintrack.service.RelatorioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {
    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping
    public ResponseEntity<RelatorioCategoria> getRelatorioCategoria(
            @RequestParam String emailUsuario,
            @RequestParam Long categoriaId,
            @RequestParam CategoriaTipo categoriaTipo,
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim
    ){
        try {
            System.out.println("=== DEBUG RELATORIO ===");
            System.out.println("Email: " + emailUsuario);
            System.out.println("CategoriaId: " + categoriaId);
            System.out.println("Tipo: " + categoriaTipo);
            System.out.println("Data Inicio: " + dataInicio);
            System.out.println("Data Fim: " + dataFim);

            RelatorioCategoria relatorio = relatorioService.gerarRelatorioPorCategoria(
                emailUsuario, categoriaId, categoriaTipo, dataInicio, dataFim
            );

            System.out.println("Relatorio gerado: " + relatorio);
            System.out.println("======================");

            return ResponseEntity.ok(relatorio);
        } catch (Exception e) {
            System.err.println("ERRO ao gerar relatório: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}