package com.example.serving_web_content.controller;

import com.example.serving_web_content.models.Usuario;
import com.example.serving_web_content.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        usuarioService.adicionar(usuario);
        ra.addFlashAttribute("msg", "Usuario cadastrado com sucesso!");
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
        usuarioService.atualizar(id, usuario);
        ra.addFlashAttribute("msg", "Usuario atualizado com sucesso!");
        return "redirect:/usuarios";
    }

    @DeleteMapping("/{idUsuario}")
    public String excluir(@PathVariable("idUsuario") Long idUsuario, RedirectAttributes ra) {
        usuarioService.remover(idUsuario.intValue());
        ra.addFlashAttribute("msg", "Usuário excluído com sucesso!");
        return "redirect:/usuarios";
    }
}