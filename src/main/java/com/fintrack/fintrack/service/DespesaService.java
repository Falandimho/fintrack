package com.fintrack.fintrack.service;

import com.fintrack.fintrack.dto.despesa.DespesaInput;
import com.fintrack.fintrack.dto.despesa.DespesaOutput;
import com.fintrack.fintrack.dto.despesa.DespesaUpdate;
import com.fintrack.fintrack.model.Categoria;
import com.fintrack.fintrack.model.CategoriaTipo;
import com.fintrack.fintrack.model.Despesa;
import com.fintrack.fintrack.model.Usuario;
import com.fintrack.fintrack.repository.CategoriaRepository;
import com.fintrack.fintrack.repository.DespesaRepository;
import com.fintrack.fintrack.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public DespesaOutput cadastroDespesa(DespesaInput despesaInput) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(despesaInput.usuarioId());
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(despesaInput.categoriaId());

        if (optionalUsuario.isEmpty() || optionalCategoria.isEmpty()) {
            throw new RuntimeException("Bota aí algum dos dois");
        }

        BigDecimal despesaValor = despesaInput.valor();
        String email = optionalUsuario.get().getEmail();

        Despesa despesa = new Despesa();
        BeanUtils.copyProperties(despesaInput, despesa);
        despesa.setCategoria(optionalCategoria.get());
        despesa.setUsuario(optionalUsuario.get());

        BigDecimal saldoAtual = usuarioService.atualizarSaldoUsuario(email, despesaValor, CategoriaTipo.DESPESA);
        return new DespesaOutput(despesaRepository.save(despesa), saldoAtual);
    }

    public List<DespesaOutput> getDespesas(String emailUsuario) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(emailUsuario);

        if (optionalUsuario.isEmpty()) {
            throw new RuntimeException("Esse usuário não existe");
        }

        List<Despesa> despesas = despesaRepository.findAllByUsuarioId(optionalUsuario.get().getId());
        if (despesas.isEmpty()) {
            throw new RuntimeException("Nenhuma despesa encontrada");
        }

        return despesas.stream().map(DespesaOutput::new).toList();
    }

    @Transactional
    public DespesaOutput atualizarDespesa(DespesaUpdate despesaUpdate) {
        Optional<Despesa> despesaOptional = despesaRepository.findById(despesaUpdate.id());
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(despesaUpdate.idCategoria());

        if (despesaOptional.isEmpty()) {
            throw new RuntimeException("Nenhuma despesa encontrada");
        }
        Usuario usuario = despesaOptional.get().getUsuario();

        if (categoriaOptional.isEmpty()) {
            throw new RuntimeException("Nenhuma categoria encontrada");
        }

        BigDecimal valor = despesaUpdate.valor().subtract(despesaOptional.get().getValor());

        Despesa despesa = despesaOptional.get();
        BeanUtils.copyProperties(despesaUpdate, despesa, "id");
        despesa.setCategoria(categoriaOptional.get());

        BigDecimal saldoAtual = usuarioService.atualizarSaldoUsuario(usuario.getEmail(), valor, CategoriaTipo.DESPESA);

        return new DespesaOutput(despesaRepository.save(despesa), saldoAtual);
    }

    public void deletarDespesa(Long idDespesa) {
        Optional<Despesa> despesaOptional = despesaRepository.findById(idDespesa);

        if (despesaOptional.isEmpty()) {
            throw new RuntimeException("Nenhum despesa encontrada");
        }

        BigDecimal valor = despesaOptional.get().getValor();
        Usuario usuario = despesaOptional.get().getUsuario();

        usuarioService.atualizarSaldoUsuario(usuario.getEmail(), valor, CategoriaTipo.RECEITA);
        despesaRepository.deleteById(idDespesa);
    }
}
