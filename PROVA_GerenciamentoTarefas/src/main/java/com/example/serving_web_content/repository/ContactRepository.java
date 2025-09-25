package com.example.serving_web_content.repository;

import com.example.serving_web_content.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long>{
    List<Contact> findByNome(String nome);
    List<Contact> findByTelefone(String telefone);
    List<Contact> findByEmail(String email);
}
