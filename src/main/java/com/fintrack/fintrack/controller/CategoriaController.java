package com.fintrack.fintrack.controller;

import com.fintrack.fintrack.dto.categoria.CategoriaInput;
import com.fintrack.fintrack.dto.categoria.CategoriaOutput;
import com.fintrack.fintrack.dto.categoria.CategoriaUpdate;
import com.fintrack.fintrack.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Void> cadastroCategoria(@RequestBody CategoriaInput categoriaInput) {
        categoriaService.cadastroCategoria(categoriaInput);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{emailUsuario}")
    public ResponseEntity<List<CategoriaOutput>> listarCategoria(@PathVariable String emailUsuario) {
        return ResponseEntity.ok().body(categoriaService.listCategorias(emailUsuario));
    }

    @PutMapping
    public ResponseEntity<CategoriaOutput> atualizarCategoria(@RequestBody CategoriaUpdate categoriaUpdate) {
        CategoriaOutput categoriaOutput = categoriaService.editarCategoria(categoriaUpdate);
        return ResponseEntity.ok().body(categoriaOutput);
    }

    @DeleteMapping("/{id}")
    public void deletarCategoria(@PathVariable Long id) {
        categoriaService.deletarCategoria(id);
    }
}
