package com.fintrack.fintrack.service;

import com.fintrack.fintrack.dto.despesa.DespesaInput;
import com.fintrack.fintrack.dto.despesa.DespesaOutput;
import com.fintrack.fintrack.dto.despesa.DespesaUpdate;
import com.fintrack.fintrack.model.Categoria;
import com.fintrack.fintrack.model.Despesa;
import com.fintrack.fintrack.model.Usuario;
import com.fintrack.fintrack.repository.CategoriaRepository;
import com.fintrack.fintrack.repository.DespesaRepository;
import com.fintrack.fintrack.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public DespesaOutput cadastroDespesa(DespesaInput despesaInput) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(despesaInput.usuarioId());
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(despesaInput.categoriaId());

        if (optionalUsuario.isEmpty() || optionalCategoria.isEmpty()) {
            throw new RuntimeException("Bota aí algum dos dois");
        }

        Despesa despesa = new Despesa();
        BeanUtils.copyProperties(despesaInput, despesa);
        despesa.setCategoria(optionalCategoria.get());
        despesa.setUsuario(optionalUsuario.get());
        return new DespesaOutput(despesaRepository.save(despesa));
    }

    public List<DespesaOutput> getDespesas(String emailUsuario) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(emailUsuario);

        if (optionalUsuario.isEmpty()) {
            throw new RuntimeException("Esse usuário não existe");
        }

        Optional<List<Despesa>> optionalDespesas = Optional.ofNullable(despesaRepository.findAllByUsuarioId(optionalUsuario.get().getId()));
        if (optionalDespesas.isEmpty()) {
            throw new RuntimeException("Nenhuma despesa encontrada");
        }

        return optionalDespesas.get().stream().map(DespesaOutput::new).toList();
    }

    public DespesaOutput atualizarDespesa(DespesaUpdate despesaUpdate) {
        Optional<Despesa> despesaOptional = despesaRepository.findById(despesaUpdate.id());
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(despesaUpdate.idCategoria());

        if (despesaOptional.isEmpty()) {
            throw new RuntimeException("Nenhum despesa encontrada");
        }

        if (categoriaOptional.isEmpty()) {
            throw new RuntimeException("Nenhuma categoria encontrada");
        }

        Despesa despesa = despesaOptional.get();
        BeanUtils.copyProperties(despesaUpdate, despesa);
        despesa.setCategoria(categoriaOptional.get());

        return new DespesaOutput(despesaRepository.save(despesa));
    }

    public void  deletarDespesa(Long idDespesa) {
        Optional<Despesa> despesaOptional = despesaRepository.findById(idDespesa);

        if (despesaOptional.isEmpty()) {
            throw new RuntimeException("Nenhum despesa encontrada");
        }

        despesaRepository.deleteById(idDespesa);
    }
}
