package com.example.serving_web_content.controller;

import com.example.serving_web_content.dto.LoginData;
import com.example.serving_web_content.models.Usuario;
import com.example.serving_web_content.repository.UsuarioRepository;
import com.example.serving_web_content.services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.serving_web_content.repository.UsuarioRepository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class MainController {
    private final UsuarioService usuarioService;
    public MainController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String index(Model model) {
        LoginData loginData = new LoginData();
        model.addAttribute("loginData", loginData);
        return "login";
    }

    @PostMapping("/logar")
    public String logar(@Valid @ModelAttribute LoginData loginData,
                            BindingResult erros,
                            RedirectAttributes ra,
                            Model model,
                            HttpSession session) {
        List<Usuario> usuarioLoggado = usuarioService.verificarLogin(loginData);
        System.out.println(usuarioLoggado);
        System.out.println(loginData.getEmail());
        System.out.println(loginData.getSenha());
        if(usuarioLoggado.size() == 1){
            session.setAttribute("loggedUserData", usuarioLoggado.getFirst());
            session.setAttribute("loggedUserId", usuarioLoggado.getFirst().getId());
            session.setAttribute("loggedUserUsername", usuarioLoggado.getFirst().getSenha());
            session.setAttribute("loggedUserEmail", usuarioLoggado.getFirst().getEmail());
            return "redirect:/home";
        } else {
            ra.addFlashAttribute("error", 1);
            ra.addFlashAttribute("msg", "Não foram encontrados usuários com essa combinação de email e senha.");
            return "redirect:/";
        }
    };

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    };

    @GetMapping("/home")
    public String homeRedirect() {
        LocalDate dataAtual = LocalDate.now();

        String yearMonth = dataAtual.format(DateTimeFormatter.ofPattern("yyyy-MM"));

        return "redirect:/home/" + yearMonth;
    }


    @GetMapping("/home/{yearMonth}")
    public String home(Model model, @PathVariable String yearMonth, HttpSession session) {
        Object loggedUserData = session.getAttribute("loggedUserData");
        if(loggedUserData == null){
            return "redirect:/";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        //Transformo a data do url para objeto LocalDate
        LocalDate ym = YearMonth.parse(yearMonth, formatter).atDay(1);

        //Data atual vinda do URL, mes seguinte e mes anterior para usar nos botoes de navegação
        String yearMonthCurrent = ym.format(formatter);
        String yearMonthNext = ym.plusMonths(1).format(formatter);
        String yearMonthLast = ym.minusMonths(1).format(formatter);

        model.addAttribute("yearMonth", yearMonthCurrent);
        model.addAttribute("yearMonthNext", yearMonthNext);
        model.addAttribute("yearMonthLast", yearMonthLast);

        return "main";
    }

}
