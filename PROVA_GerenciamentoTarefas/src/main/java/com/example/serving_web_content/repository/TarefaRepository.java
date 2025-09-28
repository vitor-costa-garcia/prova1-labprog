package com.example.serving_web_content.repository;

import com.example.serving_web_content.models.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa,Long>{
    List<Tarefa> findByTitulo(String titulo);
    List<Tarefa> findByTipo(String tipo);
    List<Tarefa> findByIdUsuario(int idUsuario);
    List<Tarefa> findByDataInicio(String dataInicio);

    @Query("SELECT t FROM Tarefa t WHERE t.idUsuario = ?1 AND t.dataInicio = ?2")
    List<Tarefa> findByIdUsuarioAndDataInicio(int idUsuario, String dataInicio);

    void deleteByIdTarefa(Long idTarefa);
}
