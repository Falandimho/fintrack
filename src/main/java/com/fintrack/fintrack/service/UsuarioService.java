package com.fintrack.fintrack.service;

import com.fintrack.fintrack.dto.usuario.UsuarioInput;
import com.fintrack.fintrack.dto.usuario.UsuarioPerfilOutput;
import com.fintrack.fintrack.dto.usuario.UsuarioSaldoOutput;
import com.fintrack.fintrack.model.CategoriaTipo;
import com.fintrack.fintrack.model.Usuario;
import com.fintrack.fintrack.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario cadastrarUsuario(UsuarioInput usuario) {
        if (usuarioRepository.findByEmail(usuario.email()).isPresent()) {
            throw new IllegalStateException("Email ja em uso");
        }
        String senhaCriptografada = passwordEncoder.encode(usuario.senha());

        Usuario usuarioCadastro = new Usuario();
        BeanUtils.copyProperties(usuario, usuarioCadastro);
        usuarioCadastro.setSenha(senhaCriptografada);

        return usuarioRepository.save(usuarioCadastro);
    }

    public UsuarioPerfilOutput getUsuarioPerfil(String email) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);

        if (optionalUsuario.isPresent()) {
            return new UsuarioPerfilOutput(optionalUsuario.get());
        } else {
            throw new RuntimeException("Usuario nao encontrado");
        }
    }

    public UsuarioSaldoOutput getUsuarioSaldo(String email) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);

        if (optionalUsuario.isPresent()) {
            return new UsuarioSaldoOutput(optionalUsuario.get());
        }else{
            throw new RuntimeException("Usuario nao encontrado");
        }
    }

    public BigDecimal atualizarSaldoUsuario(String email, BigDecimal valor, CategoriaTipo tipo) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);
        if (optionalUsuario.isEmpty()) {
            throw new RuntimeException("Usuario nao encontrado");
        }
        BigDecimal saldoAtual = optionalUsuario.get().getSaldoAtual();

        if (tipo == CategoriaTipo.RECEITA) {
            optionalUsuario.get().setSaldoAtual(saldoAtual.add(valor));
        }else {
            optionalUsuario.get().setSaldoAtual(saldoAtual.subtract(valor));
        }
        usuarioRepository.save(optionalUsuario.get());

        return optionalUsuario.get().getSaldoAtual();
    }


}
