package com.example.serving_web_content.services;

import com.example.serving_web_content.repository.TarefaRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.serving_web_content.models.Tarefa;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TarefaService {
    private final TarefaRepository TarefaRepository;

    public TarefaService(TarefaRepository TarefaRepository) {
        this.TarefaRepository = TarefaRepository;
    }

    public ResponseEntity<Tarefa> adicionar(Tarefa tarefa) {
        int idTarefa = tarefa.getId();
        int idUsuario = tarefa.getIdUsuario();
        String titulo = tarefa.getTitulo();
        String tipo = tarefa.getTipo();
        String descricao = tarefa.getDescricao();
        String dataInicio = tarefa.getDataInicio();
        int posx = tarefa.getPosx();
        int posy = tarefa.getPosy();
        int comprimento = tarefa.getComprimento();
        int altura = tarefa.getAltura();
        int cor = tarefa.getCor();
        int statusTarefa = tarefa.getStatusTarefa();


        /* Validações
        if (nome == null || nome.isEmpty()) { // Validação de nome
            return ResponseEntity.badRequest().build();
        } else if (telefone == null || telefone.isEmpty()) { // Validação de telefone
            return ResponseEntity.badRequest().build();
        } else if (email == null || email.isEmpty() || !email.contains("@")) { // Validação de email
            return ResponseEntity.badRequest().build();
        }
         */

        Tarefa c = new Tarefa(idTarefa,
                              idUsuario,
                              titulo,
                              tipo,
                              descricao,
                              dataInicio,
                              posx,
                              posy,
                              comprimento,
                              altura,
                              cor,
                              statusTarefa);
        TarefaRepository.save(c);

        return ResponseEntity.status(HttpStatus.CREATED).body(tarefa);
    }

    @Transactional
    public ResponseEntity<String> remover(int idTarefa) {
        TarefaRepository.deleteByIdTarefa((long) idTarefa);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Tarefa> atualizar(Long index, @Valid Tarefa tarefa) {
        int idTarefa = tarefa.getId();
        int idUsuario = tarefa.getIdUsuario();
        String titulo = tarefa.getTitulo();
        String tipo = tarefa.getTipo();
        String descricao = tarefa.getDescricao();
        String dataInicio = tarefa.getDataInicio();
        int posx = tarefa.getPosx();
        int posy = tarefa.getPosy();
        int comprimento = tarefa.getComprimento();
        int altura = tarefa.getAltura();
        int cor = tarefa.getCor();
        int statusTarefa = tarefa.getStatusTarefa();

        /* Validações
        if (nome == null || nome.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else if (telefone == null || telefone.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else if (email == null || email.isEmpty() || !email.contains("@")) {
            return ResponseEntity.badRequest().build();
        }
         */

        // Busca o tarefa existente
        return TarefaRepository.findById((long) index).map(Tarefa -> {
            Tarefa.setId(idTarefa);
            Tarefa.setIdUsuario(idUsuario);
            Tarefa.setTitulo(titulo);
            Tarefa.setTipo(tipo);
            Tarefa.setDescricao(descricao);
            Tarefa.setDataInicio(dataInicio);
            Tarefa.setPosx(posx);
            Tarefa.setPosy(posy);
            Tarefa.setComprimento(comprimento);
            Tarefa.setAltura(altura);
            Tarefa.setCor(cor);
            Tarefa.setStatusTarefa(statusTarefa);

            Tarefa atualizado = TarefaRepository.save(Tarefa);
            return ResponseEntity.ok(atualizado);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public List<Tarefa> listar() {
        return TarefaRepository.findAll();
    }

    public Tarefa listarPorId(int id) {
        return TarefaRepository.findById((long) id).orElse(null);
    }

    public List<Tarefa> listarTarefasUsuarioData(int idUsuario, String dataInicio) {
        return TarefaRepository.findByIdUsuarioAndDataInicio(idUsuario, dataInicio);
    }

    public List<Tarefa> listarTarefasUsuario(int idUsuario) {
        return TarefaRepository.findByIdUsuario(idUsuario);
    }
}