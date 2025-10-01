package com.example.serving_web_content.controller;

import com.example.serving_web_content.models.Tarefa;
import com.example.serving_web_content.models.Usuario;
import com.example.serving_web_content.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @GetMapping
    public String listar(Model model, @ModelAttribute Usuario usuario) {
        model.addAttribute("usuariosNoFilter", usuarioService.listar());
        model.addAttribute("usuarioFilter", usuario); // reuse the one from form

        if ((usuario.getNome() == null || usuario.getNome().isBlank()) &&
                (usuario.getEmail() == null || usuario.getEmail().isBlank())) {
            model.addAttribute("usuariosLista", usuarioService.listar());
        } else {
            model.addAttribute("usuariosLista", usuarioService.listarPorUsuario(usuario));
        }

        return "usuarios/lista";
    }

    @GetMapping("/novo")
    public String abrirCadastro(Model model) {
        Usuario formUsuario = new Usuario();
        formUsuario.setId(null);
        System.out.print(formUsuario.getId());
        model.addAttribute("usuario", formUsuario);
        return "usuarios/form";
    }

    @PostMapping
    public String cadastrar(@Valid @ModelAttribute Usuario usuario,
                            BindingResult erros,
                            RedirectAttributes ra) {

        if (erros.hasErrors()) {
            return "usuarios/form";
        }
        try {
            usuarioService.adicionar(usuario);
        } catch(IllegalArgumentException inputInvalid) {
            ra.addFlashAttribute("msg", "Não foi possível cadastrar o usuário.\nErro: " + inputInvalid.getMessage());
            ra.addFlashAttribute("error", 1);
            return "redirect:/usuarios";
        }
        ;
        ra.addFlashAttribute("msg", "Usuário cadastrado com sucesso!");
        ra.addFlashAttribute("error", 0);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String abrirEdicao(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", usuarioService.listarPorId(Math.toIntExact(id)));
        return "usuarios/form";
    }

    @PutMapping("/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute Usuario usuario,
                            BindingResult erros,
                            RedirectAttributes ra) {
        if (erros.hasErrors()) {
            return "usuarios/form";
        }
        try {
            usuarioService.atualizar(id, usuario);
        } catch(IllegalArgumentException inputInvalid) {
            ra.addFlashAttribute("msg", "Não foi possível atualizar o usuário.\nErro: " + inputInvalid.getMessage());
            ra.addFlashAttribute("error", 1);
            return "redirect:/usuarios";
        }
        ;
        ra.addFlashAttribute("msg", "Usuário atualizado com sucesso!");
        ra.addFlashAttribute("error", 0);
        return "redirect:/usuarios";
    }

    @DeleteMapping("/{idUsuario}")
    public String excluir(@PathVariable("idUsuario") Integer idUsuario, RedirectAttributes ra) {
        List<Usuario> usuariosAvailable = usuarioService.listar();
        List<Integer> usuariosIds = new ArrayList<>();
        for (Usuario u : usuariosAvailable) {
            usuariosIds.add(u.getId());
        }
        if (usuariosIds.contains(idUsuario)) {
            usuarioService.remover(idUsuario);
            ra.addFlashAttribute("msg", "Usuário excluído com sucesso!");
            ra.addFlashAttribute("error", 0);
        } else {
            ra.addFlashAttribute("msg", "Não foi possível excluir o usuário: Usuário inexistente.");
            ra.addFlashAttribute("error", 1);
        }
        return "redirect:/usuarios";
    }
}