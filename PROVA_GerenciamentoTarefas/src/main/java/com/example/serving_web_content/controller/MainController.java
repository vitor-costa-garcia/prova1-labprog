package com.example.serving_web_content.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Controller
public class MainController {

    @GetMapping
    public String index(Model model) {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String homeRedirect() {
        LocalDate dataAtual = LocalDate.now();

        String yearMonth = dataAtual.format(DateTimeFormatter.ofPattern("yyyy-MM"));

        return "redirect:/home/" + yearMonth;
    }


    @GetMapping("/home/{yearMonth}")
    public String home(Model model, @PathVariable String yearMonth) {
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
