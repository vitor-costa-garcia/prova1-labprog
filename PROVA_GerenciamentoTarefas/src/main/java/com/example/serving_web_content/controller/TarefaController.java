package com.example.serving_web_content.controller;

import com.example.serving_web_content.dto.TarefaPos;
import com.example.serving_web_content.dto.TarefaTam;
import com.example.serving_web_content.models.Tarefa;
import com.example.serving_web_content.repository.TarefaRepository;
import com.example.serving_web_content.services.TarefaService;
import com.example.serving_web_content.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String listar(Model model) {
        model.addAttribute("tarefa", tarefaService.listar());
        return "tarefas/lista";
    }

    @GetMapping("/{idUsuario}/{dataInicio}")
    public String verDia(@PathVariable int idUsuario, @PathVariable String dataInicio, Model model) {
        model.addAttribute("tarefa", tarefaService.listarTarefasUsuario(idUsuario, dataInicio));
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
    public String abrirCadastro(Model model) {
        Tarefa formTarefa = new Tarefa();
        formTarefa.setId(-1);
        System.out.print(formTarefa.getId());
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
        tarefaService.adicionar(tarefa);
        ra.addFlashAttribute("msg", "Tarefa cadastrado com sucesso!");
        return "redirect:/tarefas";
    }

    @PostMapping("/atualizar_pos")
    public ResponseEntity<?> updatePosition(@RequestBody TarefaPos dto) {
        Optional<Tarefa> tarefaMovida = tarefaRepository.findById(dto.getId());
        if (tarefaMovida.isPresent()) {
            Tarefa tarefa = tarefaMovida.get();
            tarefa.setPosx(dto.getX());
            tarefa.setPosy(dto.getY());
            tarefaService.atualizar(dto.getId(), tarefa);
            return ResponseEntity.ok(Map.of("status", "success"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("status", "task not found"));
        }
    }

    @PostMapping("/atualizar_tamanho")
    public ResponseEntity<?> updateSize(@RequestBody TarefaTam dto) {
        Optional<Tarefa> optionalTask = tarefaRepository.findById(dto.getIdTarefa());
        if (optionalTask.isPresent()) {
            Tarefa task = optionalTask.get();
            task.setComprimento(dto.getComprimento());
            task.setAltura(dto.getAltura());
            tarefaRepository.save(task);
            return ResponseEntity.ok(Map.of("status", "success"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("status", "task not found"));
        }
    }

    @GetMapping("/editar/{id}")
    public String abrirEdicao(@PathVariable Long id, Model model) {
        model.addAttribute("tarefa", tarefaService.listarPorId(Math.toIntExact(id)));
        model.addAttribute("usuarios", usuarioService.listar());
        return "tarefas/form";
    }
    @PutMapping("/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute Tarefa tarefa,
                            BindingResult erros,
                            RedirectAttributes ra,
                            Model model) {
        if (erros.hasErrors()) {
            model.addAttribute("usuarios", usuarioService.listar());
            return "tarefas/form";
        }
        tarefaService.atualizar(id, tarefa);
        ra.addFlashAttribute("msg", "Tarefa atualizada!");
        return "redirect:/tarefas";
    }

    @DeleteMapping("/{idTarefa}")
    public String excluir(@PathVariable("idTarefa") Long idTarefa, RedirectAttributes ra) {
        tarefaService.remover(idTarefa.intValue());
        ra.addFlashAttribute("msg", "Tarefa excluída!");
        return "redirect:/tarefas";
    }
}