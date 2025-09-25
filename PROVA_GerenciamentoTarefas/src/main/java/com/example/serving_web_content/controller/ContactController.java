package com.example.serving_web_content.controller;

import com.example.serving_web_content.models.Contact;
import com.example.serving_web_content.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contatos")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("contatos", contactService.listar());
        return "contatos/lista";
    }

    @GetMapping("/novo")
    public String abrirCadastro(Model model) {
        model.addAttribute("contato", new Contact());
        return "contatos/form";
    }
    @PostMapping
    public String cadastrar(@Valid @ModelAttribute Contact contato,
                            BindingResult erros,
                            RedirectAttributes ra) {

        if (erros.hasErrors()) {
            return "contatos/form";
        }
        contactService.adicionar(contato);
        ra.addFlashAttribute("msg", "Contato cadastrado com sucesso!");
        return "redirect:/contatos";
    }

    @GetMapping("/editar/{id}")
    public String abrirEdicao(@PathVariable Long id, Model model) {
        model.addAttribute("contato", contactService.listarPorId(Math.toIntExact(id)));
        return "contatos/form";
    }
    @PutMapping("/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute Contact contato,
                            BindingResult erros,
                            RedirectAttributes ra) {
        if (erros.hasErrors()) {
            return "contatos/form";
        }
        contactService.atualizar(id, contato);
        ra.addFlashAttribute("msg", "Cliente atualizado!");
        return "redirect:/clientes";
    }

    @DeleteMapping("/{id}")
    public String excluir(@PathVariable Long id,
                          RedirectAttributes ra) {
        contactService.remover(Math.toIntExact(id));
        ra.addFlashAttribute("msg", "Cliente excluído!");
        return "redirect:/clientes";
    }
}