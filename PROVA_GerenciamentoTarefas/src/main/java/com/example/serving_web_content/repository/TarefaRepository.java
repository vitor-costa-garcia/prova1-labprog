package com.example.serving_web_content.repository;

import com.example.serving_web_content.models.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa,Long>{
    List<Tarefa> findByTitulo(String titulo);
    List<Tarefa> findByTipo(String tipo);
    List<Tarefa> findByIdUsuario(Integer idUsuario);
    List<Tarefa> findByDataInicio(String dataInicio);

    @Query("SELECT t FROM Tarefa t WHERE t.idUsuario = ?1 AND t.dataInicio = ?2")
    List<Tarefa> findByIdUsuarioAndDataInicio(Integer idUsuario, String dataInicio);

    void deleteByIdTarefa(Long idTarefa);

    @Query("""
    SELECT t FROM Tarefa t
    WHERE (:idTarefa IS NULL OR t.idTarefa = :idTarefa)
      AND (:idUsuario IS NULL OR t.idUsuario = :idUsuario)
      AND (:titulo IS NULL OR t.titulo LIKE %:titulo%)
      AND (:dataInicio IS NULL OR t.dataInicio = :dataInicio)
      AND (:descricao IS NULL OR t.descricao LIKE %:descricao%)
      AND (:titulo IS NULL OR t.titulo LIKE %:titulo%)
      AND (:cor IS NULL OR t.cor = :cor)
      AND (:statusTarefa IS NULL OR t.statusTarefa = :statusTarefa)
    """)
    List<Tarefa> findByTarefaFiltradas(
            @Param("idTarefa") Integer idTarefa,
            @Param("idUsuario") Integer idUsuario,
            @Param("titulo") String titulo,
            @Param("tipo") String tipo,
            @Param("descricao") String descricao,
            @Param("dataInicio") String dataInicio,
            @Param("cor") Integer cor,
            @Param("statusTarefa") Integer statusTarefa
    );
}
