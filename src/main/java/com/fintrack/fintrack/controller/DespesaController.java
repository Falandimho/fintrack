package com.fintrack.fintrack.controller;

import com.fintrack.fintrack.dto.despesa.DespesaInput;
import com.fintrack.fintrack.dto.despesa.DespesaOutput;
import com.fintrack.fintrack.dto.despesa.DespesaUpdate;
import com.fintrack.fintrack.service.DespesaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/despesa")
public class DespesaController {
    private final DespesaService despesaService;
    public DespesaController(DespesaService despesaService) {
        this.despesaService = despesaService;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarDespesa(@RequestBody DespesaInput despesaInput) {
        despesaService.cadastroDespesa( despesaInput );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{emailUsuario}")
    public ResponseEntity<List<DespesaOutput>> listarDespesas(@PathVariable String emailUsuario) {
        return ResponseEntity.ok().body(despesaService.getDespesas(emailUsuario));
    }

    @PutMapping
    public ResponseEntity<DespesaOutput> atualizarDespesa(@RequestBody DespesaUpdate despesaUpdate) {
        despesaService.atualizarDespesa(despesaUpdate);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public void removerDespesa(@PathVariable Long id) {
        despesaService.deletarDespesa(id);
    }
}
