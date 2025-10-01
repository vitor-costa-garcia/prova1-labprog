package com.example.serving_web_content.controller;

import com.example.serving_web_content.dto.TarefaFiltro;
import com.example.serving_web_content.dto.TarefaPos;
import com.example.serving_web_content.dto.TarefaTam;
import com.example.serving_web_content.models.Tarefa;
import com.example.serving_web_content.models.Usuario;
import com.example.serving_web_content.repository.TarefaRepository;
import com.example.serving_web_content.services.TarefaService;
import com.example.serving_web_content.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/tarefas")
public class TarefaController {
    private final TarefaService tarefaService;
    private final TarefaRepository tarefaRepository;
    private final UsuarioService usuarioService;

    @Autowired
    public TarefaController(TarefaService tarefaService, TarefaRepository tarefaRepository, UsuarioService usuarioService) {
        this.tarefaService = tarefaService;
        this.tarefaRepository = tarefaRepository;
        this.usuarioService = usuarioService;
    }
    @GetMapping
    public String listar(Model model, @ModelAttribute TarefaFiltro tarefa) {
        model.addAttribute("tarefasNoFilter", tarefaService.listar());
        List<String> cores = List.of("Amarelo", "Laranja", "Vermelho", "Verde", "Azul", "Roxo");
        model.addAttribute("cores", cores);
        model.addAttribute("tarefaFilter", tarefa);
        model.addAttribute("tarefas", tarefaService.listarTarefasFiltradas(tarefa));
        List<String> situacao = List.of("Em andamento", "Concluído");
        model.addAttribute("situacao", situacao);
        model.addAttribute("usuariosList", usuarioService.listar());
        return "tarefas/lista";
    }

    @GetMapping("/{idUsuario}/{dataInicio}")
    public String verDia(@PathVariable Integer idUsuario, @PathVariable String dataInicio, Model model, @ModelAttribute TarefaFiltro tarefa) {
        tarefa.setIdUsuario(idUsuario);
        tarefa.setDataInicio(dataInicio);
        List<Tarefa> tarefasFiltradas = tarefaService.listarTarefasFiltradas(tarefa);

        List<List<String>> cores = List.of(
                List.of("#fff17b", "#fff289"),//amarelo
                List.of("#ffb06e", "#ffb97e"),//laranja
                List.of("#ff6a6a", "#ff7b7b"),//vermelho
                List.of("#82ff7b", "#95ff8f"),//verde
                List.of("#80ddff", "#8fe5ff"),//azul
                List.of("#ff7bf8", "#ff89fa") //roxo
        );
        model.addAttribute("tarefaFilter", tarefa);
        model.addAttribute("tarefas", tarefasFiltradas);
        model.addAttribute("cores", cores);
        model.addAttribute("tarefasNoFilter", tarefaService.listarTarefasUsuarioData(idUsuario, dataInicio));
        return "tarefas/diacalendario";
    }

/*  MAPEAR AS CORES PARA CRIAR OS POSTITS COM AS CORES CORRETAS
    | CODIGO_COR | COR      | COR_DRAG | COR_BODY |
    | 0          | AMARELO  | #fff17b  | #fff289  |
    | 1          | LARANJA  | #ffb06e  | #ffb97e  |
    | 2          | VERMELHO | #ff6a6a  | #ff7b7b  |
    | 3          | VERDE    | #82ff7b  | #95ff8f  |
    | 4          | AZUL     | #80ddff  | #8fe5ff  |
    | 5          | ROXO     | #ff7bf8  | #ff89fa  |
*/

    @GetMapping("/novo")
    public String abrirCadastroSemData(Model model) {
        LocalDate dataAtual = LocalDate.now();

        String yearMonthDay = dataAtual.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return "redirect:/tarefas/novo/" + yearMonthDay;
    }

    @GetMapping("/novo/{data}")
    public String abrirCadastro(@PathVariable String data, Model model) {
        Tarefa formTarefa = new Tarefa();
        formTarefa.setId(null);
        System.out.print(formTarefa.getId());
        formTarefa.setDataInicio(data);
        model.addAttribute("tarefa", formTarefa);
        model.addAttribute("usuarios", usuarioService.listar());
        return "tarefas/form";
    }

    @PostMapping
    public String cadastrar(@Valid @ModelAttribute Tarefa tarefa,
                            BindingResult erros,
                            RedirectAttributes ra,
                            Model model) {

        if (erros.hasErrors()) {
            model.addAttribute("usuarios", usuarioService.listar());
            return "tarefas/form";
        }
        try {
            tarefaService.adicionar(tarefa);
        } catch(IllegalArgumentException inputInvalid) {
            ra.addFlashAttribute("msg", "Não foi possível cadastrar a tarefa.\nErro: " + inputInvalid.getMessage());
            ra.addFlashAttribute("error", 1);
            return "redirect:/tarefas";
        }
        ;
        ra.addFlashAttribute("msg", "Tarefa cadastrada com sucesso!");
        ra.addFlashAttribute("error", 0);
        return "redirect:/tarefas";
    }

    @PostMapping("/atualizar_pos")
    public ResponseEntity<?> updatePosition(@RequestBody TarefaPos dto) {
        Tarefa tarefaMovida = tarefaService.listarPorId(dto.getId());
        if (tarefaMovida.getId() != null) {
            tarefaMovida.setPosx(dto.getX());
            tarefaMovida.setPosy(dto.getY());
            tarefaService.atualizar(tarefaMovida.getId(), tarefaMovida);
            return ResponseEntity.ok(Map.of("status", "success"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("status", "task not found"));
        }
    }

    @PostMapping("/atualizar_tamanho")
    public ResponseEntity<?> updateSize(@RequestBody TarefaTam dto) {
        Tarefa tarefaRedim = tarefaService.listarPorId(dto.getIdTarefa());
        if (tarefaRedim.getId() != null) {
            tarefaRedim.setComprimento(dto.getComprimento());
            tarefaRedim.setAltura(dto.getAltura());
            tarefaService.atualizar(dto.getIdTarefa(), tarefaRedim);
            return ResponseEntity.ok(Map.of("status", "success"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("status", "task not found"));
        }
    }

    @GetMapping("/editar/{id}")
    public String abrirEdicao(@PathVariable Integer id, Model model) {
        model.addAttribute("tarefa", tarefaService.listarPorId(id));
        model.addAttribute("usuarios", usuarioService.listar());
        return "tarefas/form";
    }
    @PutMapping("/{id}")
    public String atualizar(@PathVariable Integer id,
                            @Valid @ModelAttribute Tarefa tarefa,
                            BindingResult erros,
                            RedirectAttributes ra,
                            Model model) {
        if (erros.hasErrors()) {
            model.addAttribute("usuarios", usuarioService.listar());
            return "tarefas/form";
        }
        try {
            tarefaService.atualizar(id, tarefa);
        } catch(IllegalArgumentException inputInvalid) {
            ra.addFlashAttribute("msg", "Não foi possível atualizar a tarefa.\nErro: " + inputInvalid.getMessage());
            ra.addFlashAttribute("error", 1);
            return "redirect:/tarefas";
        }
        ;
        ra.addFlashAttribute("msg", "Tarefa atualizada com sucesso!");
        ra.addFlashAttribute("error", 0);
        return "redirect:/tarefas";
    }

    @DeleteMapping("/{idTarefa}")
    public String excluir(@PathVariable("idTarefa") Integer idTarefa, RedirectAttributes ra) {
        List<Tarefa> tarefasAvailable = tarefaService.listar();
        List<Integer> tarefasIds = new ArrayList<>();
        for (Tarefa t : tarefasAvailable) {
            tarefasIds.add(t.getId());
        }
        if (tarefasIds.contains(idTarefa)) {
            tarefaService.remover(idTarefa);
            ra.addFlashAttribute("msg", "Tarefa excluída com sucesso!");
            ra.addFlashAttribute("error", 0);
        } else {
            ra.addFlashAttribute("msg", "Não foi possível excluir a tarefa: Tarefa inexistente.");
            ra.addFlashAttribute("error", 1);
        }
        return "redirect:/tarefas";
    }
}