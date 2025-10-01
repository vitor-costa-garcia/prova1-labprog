package com.example.serving_web_content.services;

import com.example.serving_web_content.models.Tarefa;
import com.example.serving_web_content.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.example.serving_web_content.models.Usuario;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
    private final UsuarioRepository UsuarioRepository;

    public UsuarioService(UsuarioRepository UsuarioRepository) {
        this.UsuarioRepository = UsuarioRepository;
    }

    public ResponseEntity<Usuario> adicionar(Usuario usuario) {
        Integer idUsuario = usuario.getId();
        String nome = usuario.getNome();
        String email = usuario.getEmail();

        List<Usuario> usersAvailable = UsuarioRepository.findAll();

        //Validações
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome inválido.");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        for (Usuario u :  usersAvailable) {
            if (u.getNome().equals(nome)) {
                throw new IllegalArgumentException("O nome fornecido já foi usado.");
            }
            if(u.getEmail().equals(email)) {
                throw new IllegalArgumentException("O email fornecido já foi usado.");
            }
        }

        Usuario c = new Usuario(idUsuario,
                nome,
                email);
        UsuarioRepository.save(c);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @Transactional
    public ResponseEntity<String> remover(Integer idUsuario) {
        UsuarioRepository.deleteById((long) idUsuario);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Usuario> atualizar(Long index, @Valid Usuario usuario) {
        Integer idUsuario = usuario.getId();
        String nome = usuario.getNome();
        String email = usuario.getEmail();

        List<Usuario> usersAvailable = UsuarioRepository.findAll();

        /* Validações */
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome inválido.");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        for (Usuario u :  usersAvailable) {
            if (u.getNome().equals(nome) && !u.getId().equals(idUsuario)) {
                throw new IllegalArgumentException("O nome fornecido já foi usado.");
            }
            if(u.getEmail().equals(email) && !u.getId().equals(idUsuario)) {
                throw new IllegalArgumentException("O email fornecido já foi usado.");
            }
        }

        // Busca o tarefa existente
        return UsuarioRepository.findById((long) index).map(Usuario -> {
            Usuario.setId(idUsuario);
            Usuario.setNome(nome);
            Usuario.setEmail(email);

            Usuario atualizado = UsuarioRepository.save(Usuario);
            return ResponseEntity.ok(atualizado);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public List<Usuario> listar() {
        return UsuarioRepository.findAll();
    }

    public Usuario listarPorId(Integer id) {
        return UsuarioRepository.findById((long) id).orElse(null);
    }

    public List<Usuario> listarPorUsuario(Usuario usuario) {
        return UsuarioRepository.findByEmailContainingIgnoreCaseAndNomeContainingIgnoreCase(
                usuario.getEmail() != null ? usuario.getEmail() : "",
                usuario.getNome() != null ? usuario.getNome() : ""
        );
    }



}