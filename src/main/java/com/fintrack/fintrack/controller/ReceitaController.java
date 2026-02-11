package com.fintrack.fintrack.controller;

import com.fintrack.fintrack.dto.receita.ReceitaInput;
import com.fintrack.fintrack.dto.receita.ReceitaOutput;
import com.fintrack.fintrack.dto.receita.ReceitaUpdate;
import com.fintrack.fintrack.service.ReceitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receita")
public class ReceitaController {
    private final ReceitaService receitaService;
    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @PostMapping
    public ResponseEntity<ReceitaOutput> cadastrarReceita(@RequestBody ReceitaInput receitaInput){
        return ResponseEntity.status(HttpStatus.CREATED).body(receitaService.cadastroReceita(receitaInput));
    }

    @GetMapping("/{emailUsuario}")
    public ResponseEntity<List<ReceitaOutput>> listarReceita(@PathVariable String emailUsuario){
        return ResponseEntity.ok().body(receitaService.getReceita(emailUsuario));
    }

    @PutMapping
    public ResponseEntity<ReceitaOutput> atualizarReceita(@RequestBody ReceitaUpdate receitaUpdate){
        ReceitaOutput receitaOutput = receitaService.atualizarReceita(receitaUpdate);
        return ResponseEntity.ok(receitaOutput);
    }

    @DeleteMapping("/{id}")
    public void deleteReceita(@PathVariable Long id){
        receitaService.deletarReceita(id);
    }
}
