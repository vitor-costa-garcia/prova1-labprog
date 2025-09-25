package com.example.serving_web_content.services;

import com.example.serving_web_content.repository.ContactRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.serving_web_content.models.Contact;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ResponseEntity<Contact> adicionar(Contact contato) {
        int id = contato.getId();
        String nome = contato.getNome();
        String telefone = contato.getTelefone();
        String email = contato.getEmail();
        String endereco = contato.getEndereco();
        String data_nascimento = contato.getDataNascimento();

        if (nome == null || nome.isEmpty()) { // Validação de nome
            return ResponseEntity.badRequest().build();
        } else if (telefone == null || telefone.isEmpty()) { // Validação de telefone
            return ResponseEntity.badRequest().build();
        } else if (email == null || email.isEmpty() || !email.contains("@")) { // Validação de email
            return ResponseEntity.badRequest().build();
        }

        Contact c = new Contact(id, nome, telefone, email, endereco, data_nascimento);
        contactRepository.save(c);

        return ResponseEntity.status(HttpStatus.CREATED).body(contato);
    }

    public ResponseEntity<String> remover(int index) {
        contactRepository.deleteById((long) index);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Contact> atualizar(Long index, @Valid Contact contato) {
        int id = contato.getId();
        String nome = contato.getNome();
        String telefone = contato.getTelefone();
        String email = contato.getEmail();
        String endereco = contato.getEndereco();
        String dataNascimento = contato.getDataNascimento();

        // Validações
        if (nome == null || nome.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else if (telefone == null || telefone.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else if (email == null || email.isEmpty() || !email.contains("@")) {
            return ResponseEntity.badRequest().build();
        }

        // Busca o contato existente
        return contactRepository.findById((long) index).map(contact -> {
            contact.setId(id);
            contact.setNome(nome);
            contact.setTelefone(telefone);
            contact.setEmail(email);
            contact.setEndereco(endereco);
            contact.setDataNascimento(dataNascimento);

            Contact atualizado = contactRepository.save(contact);
            return ResponseEntity.ok(atualizado);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public List<Contact> listar() {
        List<Contact> contatos = contactRepository.findAll();
        return contatos;
    }

    public Contact listarPorId(int id) {
        return contactRepository.findById((long) id).orElse(null);
    }
}