package com.example.serving_web_content.services;

import com.example.serving_web_content.dto.TarefaFiltro;
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
        Integer idTarefa = tarefa.getId();
        Integer idUsuario = tarefa.getIdUsuario();
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


        //Validações
        if (idUsuario == null) { // Validação de usuário
            return ResponseEntity.badRequest().build();
        }
        if (titulo == null || titulo.isEmpty()) { // Validação de titulo
            return ResponseEntity.badRequest().build();
        }
        if (tipo == null || tipo.isEmpty()) { // Validação de tipo
            return ResponseEntity.badRequest().build();
        }
        if (descricao == null || descricao.isEmpty()) { // Validação de descrição
            return ResponseEntity.badRequest().build();
        }
        if (dataInicio == null || dataInicio.isEmpty()) { // Validação de data
            return ResponseEntity.badRequest().build();
        }
        for(int i = 0; i < dataInicio.length(); i++) { //Validação de data por caracter
            if(Character.isLetter(dataInicio.charAt(i))){
                return ResponseEntity.badRequest().build();
            }
        }
        if(cor<0 || cor>5){ //Validacao de cor da tarefa
            return ResponseEntity.badRequest().build();
        }
        if(statusTarefa != 0 && statusTarefa != 1){ //Validação de status da tarefa
            return ResponseEntity.badRequest().build();
        }

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
    public ResponseEntity<String> remover(Integer idTarefa) {
        TarefaRepository.deleteByIdTarefa((long) idTarefa);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Tarefa> atualizar(Long index, @Valid Tarefa tarefa) {
        Integer idTarefa = tarefa.getId();
        Integer idUsuario = tarefa.getIdUsuario();
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

        //Validações
        if (idTarefa == null) { //validação de idtarefa
            return ResponseEntity.badRequest().build();
        }
        if (idUsuario == null) { // Validação de usuário
            return ResponseEntity.badRequest().build();
        }
        if (titulo == null || titulo.isEmpty()) { // Validação de titulo
            return ResponseEntity.badRequest().build();
        }
        if (tipo == null || tipo.isEmpty()) { // Validação de tipo
            return ResponseEntity.badRequest().build();
        }
        if (descricao == null || descricao.isEmpty()) { // Validação de descrição
            return ResponseEntity.badRequest().build();
        }
        if (dataInicio == null || dataInicio.isEmpty()) { // Validação de data
            return ResponseEntity.badRequest().build();
        }
        for(int i = 0; i < dataInicio.length(); i++) { //Validação de data por caracter
            if(Character.isLetter(dataInicio.charAt(i))){
                return ResponseEntity.badRequest().build();
            }
        }
        if(cor<0 || cor>5){ //Validacao de cor da tarefa
            return ResponseEntity.badRequest().build();
        }
        if(statusTarefa != 0 && statusTarefa != 1){ //Validação de status da tarefa
            return ResponseEntity.badRequest().build();
        }

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

    public Tarefa listarPorId(Integer id) {
        return TarefaRepository.findById((long) id).orElse(null);
    }

    public List<Tarefa> listarTarefasUsuarioData(Integer idUsuario, String dataInicio) {
        return TarefaRepository.findByIdUsuarioAndDataInicio(idUsuario, dataInicio);
    }

    public List<Tarefa> listarTarefasUsuario(Integer idUsuario) {
        return TarefaRepository.findByIdUsuario(idUsuario);
    }

    public List<Tarefa> listarTarefasFiltradas(TarefaFiltro tarefaFilter){
        System.out.print(tarefaFilter.getDataInicio());
        return TarefaRepository.findByTarefaFiltradas(
                tarefaFilter.getIdTarefa(),
                tarefaFilter.getIdUsuario(),
                tarefaFilter.getTitulo(),
                tarefaFilter.getTipo(),
                tarefaFilter.getDescricao(),
                tarefaFilter.getDataInicio(),
                tarefaFilter.getCor(),
                tarefaFilter.getStatusTarefa()
        );
    }
}