package com.example.serving_web_content.controller;

import com.example.serving_web_content.services.ContactService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import com.example.serving_web_content.models.Contact;

@RestController
@RequestMapping("/contatos")
public class ContactController {

    @Autowired
    private ContactService contactService;

    /*
    @Autowired
    private ContactController(ContactService contactService) {
        this.contactService = contactService;
    }
    */

    @GetMapping("/")
    public String contatos(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping
    public List<Contact> getContatos(){
        return contactService.listar();
    }

    @GetMapping("/{id}")
    public Contact getContato(@PathVariable int id){
        return contactService.listar().get(id);
    }

    @PostMapping
    public Contact addContato(@RequestBody Contact contato){
        contactService.adicionar(contato);
        return contato;
    }

    @PutMapping("/{id}")
    public Contact updateContato(@RequestBody Contact contato, @PathVariable int id){
        contactService.atualizar(id,  contato);
        return contato;
    }

    @DeleteMapping("/{id}")
    public void deleteContato(@PathVariable int id){
        contactService.remover(id);
    }
}
