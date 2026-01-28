package com.fintrack.fintrack.service;

import com.fintrack.fintrack.dto.receita.ReceitaInput;
import com.fintrack.fintrack.dto.receita.ReceitaOutput;
import com.fintrack.fintrack.dto.receita.ReceitaUpdate;
import com.fintrack.fintrack.model.Categoria;
import com.fintrack.fintrack.model.Receita;
import com.fintrack.fintrack.model.Usuario;
import com.fintrack.fintrack.repository.CategoriaRepository;
import com.fintrack.fintrack.repository.ReceitaRepository;
import com.fintrack.fintrack.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceitaService {
    @Autowired
    private ReceitaRepository receitaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public ReceitaOutput cadastroReceita(ReceitaInput receitaInput){
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(receitaInput.usuarioId());
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(receitaInput.categoriaId());

        if (optionalUsuario.isEmpty() || optionalCategoria.isEmpty()){
            throw new RuntimeException("Bota aí algum dos dois");
        }
        Receita receita = new Receita();
        BeanUtils.copyProperties(receitaInput,receita);
        receita.setUsuario(optionalUsuario.get());
        receita.setCategoria(optionalCategoria.get());
        return new ReceitaOutput(receitaRepository.save(receita));
    }

    public List<ReceitaOutput> getReceita(String emailUsuario){
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(emailUsuario);

        if (optionalUsuario.isEmpty()){
            throw new RuntimeException("Esse usuario não existe");
        }

        List<Receita> receitas = receitaRepository.findAllByUsuarioId(optionalUsuario.get().getId());
        if (receitas.isEmpty()){
            throw new RuntimeException("Nenhum receita encontrada");
        }

        return receitas.stream().map(ReceitaOutput::new).toList();
    }

    public ReceitaOutput atualizarReceita(ReceitaUpdate receitaUpdate){
        Optional<Receita> optionalReceita = receitaRepository.findById(receitaUpdate.id());
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(receitaUpdate.categoriaId());

        if (optionalReceita.isEmpty()){
            throw new RuntimeException("Nenhum receita encontrada");
        }

        if (optionalCategoria.isEmpty()){
            throw new RuntimeException("Nenhum categoria encontrada");
        }

        Receita receita = new Receita();
        BeanUtils.copyProperties(receitaUpdate,receita);
        receita.setCategoria(optionalCategoria.get());

        return new ReceitaOutput(receitaRepository.save(receita));
    }

    public void deletarReceita(Long id){

        Optional<Receita> optionalReceita = receitaRepository.findById(id);
        if (optionalReceita.isEmpty()){
            throw new RuntimeException("Nenhum receita encontrada");
        }

        receitaRepository.deleteById(id);
    }
}
