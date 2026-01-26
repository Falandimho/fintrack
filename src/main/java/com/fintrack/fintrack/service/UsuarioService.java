package com.fintrack.fintrack.service;

import com.fintrack.fintrack.dto.usuario.UsuarioCadastroInput;
import com.fintrack.fintrack.dto.usuario.UsuarioPerfilOutput;
import com.fintrack.fintrack.dto.usuario.UsuarioSaldoOutput;
import com.fintrack.fintrack.model.Usuario;
import com.fintrack.fintrack.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario cadastrarUsuario(UsuarioCadastroInput usuario) {
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
}
