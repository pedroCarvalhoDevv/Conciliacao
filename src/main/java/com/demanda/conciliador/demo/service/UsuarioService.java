package com.demanda.conciliador.demo.service;

import com.demanda.conciliador.demo.model.Usuario;
import com.demanda.conciliador.demo.model.dto.UsuarioDTO;
import com.demanda.conciliador.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDTO validarLogin(String email, String senha) {

        Optional<Usuario> usuarioOpt =
                usuarioRepository.findByEmailAndSenha(email, senha);

        if (usuarioOpt.isEmpty()) {
            return null;
        }

        Usuario usuario = usuarioOpt.get();

        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                null
        );
    }

    public boolean emailJaExiste(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public void salvar(UsuarioDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());

        usuarioRepository.save(usuario);
    }
}
