package com.example.serving_web_content.services;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.example.serving_web_content.models.Contact;

@Service
public class ContactService {
    private final List<Contact> contatos = new ArrayList<>();
    public List<Contact> listar() {
        return contatos;
    }
    public void adicionar(Contact contato) {
        contatos.add(contato);
    }
    public void remover(int index) {
        contatos.remove(index);
    }
    public void atualizar(int index, Contact contato){
        contatos.set(index, contato);
    }
}