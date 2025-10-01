package com.example.serving_web_content.services;

import com.example.serving_web_content.dto.TarefaFiltro;
import com.example.serving_web_content.models.Usuario;
import com.example.serving_web_content.repository.TarefaRepository;
import com.example.serving_web_content.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.example.serving_web_content.models.Tarefa;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TarefaService {
    private final TarefaRepository TarefaRepository;
    private final UsuarioRepository UsuarioRepository;

    public TarefaService(TarefaRepository TarefaRepository, UsuarioRepository usuarioRepository) {
        this.TarefaRepository = TarefaRepository;
        this.UsuarioRepository = usuarioRepository;
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
        Integer cor = tarefa.getCor();
        Integer statusTarefa = tarefa.getStatusTarefa();

        List<Usuario> usuariosAvailable = UsuarioRepository.findAll();
        List<Integer> usuarioIds = new ArrayList<>();
        for (Usuario u : usuariosAvailable) {
            usuarioIds.add(u.getId());
        }

        List<Tarefa> titulosAvailable = TarefaRepository.findAll();
        List<String> titulosUsed = new ArrayList<>();
        for (Tarefa t : titulosAvailable) {
            titulosUsed.add(t.getTitulo());
        }

        //Validações
        if (idUsuario == null) { // Validação de usuário
            throw new IllegalArgumentException("O usuário não pode ser nulo.");
        }
        if(!usuarioIds.contains(idUsuario)) {
            throw new IllegalArgumentException("O usuário deve existir.");
        }
        if (titulo == null || titulo.isEmpty()) { // Validação de titulo
            throw new IllegalArgumentException("O título não pode estar vazio.");
        }
        if (titulosUsed.contains(titulo)) {
            throw new IllegalArgumentException("O título fornecido já foi usado.");
        }
        if (tipo == null || tipo.isEmpty()) { // Validação de tipo
            throw new IllegalArgumentException("O tipo não pode estar vazio.");
        }
        if (descricao == null || descricao.isEmpty()) { // Validação de descrição
            throw new IllegalArgumentException("A descrição não pode estar vazia.");
        }
        if (dataInicio == null || dataInicio.isEmpty()) { // Validação de data
            throw new IllegalArgumentException("A data não pode ser nula.");
        }
        for(int i = 0; i < dataInicio.length(); i++) { //Validação de data por caracter
            if(Character.isLetter(dataInicio.charAt(i))){
                throw new IllegalArgumentException("A data não pode conter letras");
            }
        }
        if(cor<0 || cor>5){ //Validacao de cor da tarefa
            throw new IllegalArgumentException("Cor inexistente.");
        }
        if(statusTarefa != 0 && statusTarefa != 1){ //Validação de status da tarefa
            throw new IllegalArgumentException("Status inexistente.");
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
        TarefaRepository.deleteByIdTarefa(idTarefa);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Tarefa> atualizar(Integer index, @Valid Tarefa tarefa) {
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
        Integer cor = tarefa.getCor();
        Integer statusTarefa = tarefa.getStatusTarefa();

        List<Usuario> usuariosAvailable = UsuarioRepository.findAll();
        List<Integer> usuarioIds = new ArrayList<>();
        for (Usuario u : usuariosAvailable) {
            usuarioIds.add(u.getId());
        }

        List<Tarefa> titulosAvailable = TarefaRepository.findAll();
        List<List<String>> titulosUsed = new ArrayList<>();

        for (Tarefa t : titulosAvailable) {
            List<String> entry = new ArrayList<>();
            entry.add(t.getTitulo());
            entry.add(String.valueOf(t.getId()));
            titulosUsed.add(entry);
        }

        //Validações
        if (idUsuario == null) { // Validação de usuário
            throw new IllegalArgumentException("O usuário não pode ser nulo.");
        }
        if(!usuarioIds.contains(idUsuario)) {
            throw new IllegalArgumentException("O usuário deve existir.");
        }
        if (titulo == null || titulo.isEmpty()) { // Validação de titulo
            throw new IllegalArgumentException("O título não pode estar vazio.");
        }
        for (List<String> entry : titulosUsed) {
            String existingTitulo = entry.get(0);
            Integer existingIdTarefa = Integer.valueOf(entry.get(1));
            if (existingTitulo.equals(titulo)) {
                if (!existingIdTarefa.equals(index)) {
                    throw new IllegalArgumentException("O título fornecido já foi usado.");
                }
            }
        }
        if (tipo == null || tipo.isEmpty()) { // Validação de tipo
            throw new IllegalArgumentException("O tipo não pode estar vazio.");
        }
        if (descricao == null || descricao.isEmpty()) { // Validação de descrição
            throw new IllegalArgumentException("A descrição não pode estar vazia.");
        }
        if (dataInicio == null || dataInicio.isEmpty()) { // Validação de data
            throw new IllegalArgumentException("A data não pode ser nula.");
        }
        for(int i = 0; i < dataInicio.length(); i++) { //Validação de data por caracter
            if(Character.isLetter(dataInicio.charAt(i))){
                throw new IllegalArgumentException("A data não pode conter letras");
            }
        }
        if(cor<0 || cor>5){ //Validacao de cor da tarefa
            throw new IllegalArgumentException("Cor inexistente.");
        }
        if(statusTarefa != 0 && statusTarefa != 1){ //Validação de status da tarefa
            throw new IllegalArgumentException("Status inexistente.");
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