package com.example.serving_web_content.repository;

import com.example.serving_web_content.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    List<Usuario> findById(Integer id);
    List<Usuario> findByEmail(String email);
    List<Usuario> findByNome(String nome);

    List<Usuario> findByEmailAndNome(String email, String nome);

    List<Usuario> findByEmailContainingIgnoreCaseAndNomeContainingIgnoreCase(String email, String nome);
}
