package com.fintrack.fintrack.service;

import com.fintrack.fintrack.dto.categoria.CategoriaInput;
import com.fintrack.fintrack.dto.categoria.CategoriaOutput;
import com.fintrack.fintrack.dto.categoria.CategoriaUpdate;
import com.fintrack.fintrack.model.Categoria;
import com.fintrack.fintrack.model.Usuario;
import com.fintrack.fintrack.repository.CategoriaRepository;
import com.fintrack.fintrack.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void cadastroCategoria(CategoriaInput categoriaInput) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(categoriaInput.usuarioId());
        if (optionalUsuario.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        Categoria categoria = new Categoria();
        BeanUtils.copyProperties(categoriaInput, categoria);
        categoria.setUsuario(optionalUsuario.get());
        new CategoriaOutput(categoriaRepository.save(categoria));
    }

    public List<CategoriaOutput> listCategorias(String emailUsuario) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(emailUsuario);
        if (optionalUsuario.isEmpty()) {
            throw new RuntimeException("Usuario não encontrado");
        }

        List<Categoria> categorias = categoriaRepository.findAllByDefaultCategoryTrueOrUsuario_Id(optionalUsuario.get().getId());
        if (categorias.isEmpty()) {
            throw new RuntimeException("Nenhuma categoria encontrada");
        }

        return categorias.stream().map(CategoriaOutput::new).toList();
    }

    public CategoriaOutput editarCategoria(CategoriaUpdate categoriaUpdate) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(categoriaUpdate.id());
        if (optionalCategoria.isEmpty()) {
            throw new IllegalArgumentException("Nenhuma categoria encontrada");
        }

        Categoria categoria = optionalCategoria.get();
        BeanUtils.copyProperties(categoriaUpdate, categoria, "id");
        return new CategoriaOutput(categoriaRepository.save(categoria));
    }

    public void deletarCategoria(Long id) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (optionalCategoria.isEmpty()) {
            throw new IllegalArgumentException("Nenhuma categoria encontrada");
        }

        categoriaRepository.deleteById(id);
    }
}
