package com.example.serving_web_content.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idTarefa", nullable = false, unique = true)
    private int idTarefa;

    //chave estrangeira
    @Column(name="idUsuario", nullable = false)
    private int idUsuario;

    @NotBlank(message = "O título não pode ser nulo.")
    @Size(max = 100, message = "O título deve ter no máximo 100 caracteres")
    @Column(name="titulo", nullable = false, unique = true, length = 100)
    private String titulo;

    @NotBlank(message = "O tipo não pode ser nulo")
    @Size(max = 150, message = "O tipo deve ter no máximo 50 caracteres")
    @Column(name="tipo", nullable = false, unique = false, length = 50)
    private String tipo;

    @NotBlank(message = "A descrição não pode ser nulo")
    @Size(max = 200, message = "A descrição deve ter no máximo 200 caracteres")
    @Column(name="descricao", nullable = true, unique = false, length = 200)
    private String descricao;

    @NotBlank(message = "Uma data de início deve ser fornecida")
    @DateTimeFormat()
    @Size(max = 30, message = "A data de início deve ter no máximo 12 caracteres")
    @Column(name="dataInicio", nullable = false, unique = false, length = 12)
    private String dataInicio;

    @DateTimeFormat()
    @Size(max = 30, message = "A data de fim deve ter no máximo 12 caracteres")
    @Column(name="dataFim", nullable = true, unique = false, length = 12)
    private String dataFim;

    @Column(name="posx", nullable = false)
    private int posx;

    @Column(name="posy", nullable = false)
    private int posy;

    @Column(name="comprimento", nullable = false)
    private int comprimento;

    @Column(name="altura", nullable = false)
    private int altura;

    @Column(name="cor", nullable = false, unique = false)
    private int cor;

    @Column(name="statusTarefa", nullable = false)
    private int statusTarefa;


    public Tarefa(int idTarefa,
                  int idUsuario,
                  String titulo,
                  String tipo,
                  String descricao,
                  String dataInicio,
                  String dataFim,
                  int posx,
                  int posy,
                  int comprimento,
                  int altura,
                  int cor,
                  int statusTarefa) {
        this.idTarefa = idTarefa;
        this.idUsuario = idUsuario;
        this.titulo = titulo;
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.posx = posx;
        this.posy = posy;
        this.comprimento = comprimento;
        this.altura = altura;
        this.cor = cor;
        this.statusTarefa = statusTarefa;
    }

    public Tarefa() {

    }

    public int     getId(){
        return idTarefa;
    }
    public int    getIdUsuario(){ return idUsuario; }
    public String getTitulo(){ return titulo; }
    public String getTipo(){ return tipo; }
    public String getDescricao(){ return descricao; }
    public String getDataInicio(){ return dataInicio; }
    public String getDataFim(){ return dataFim; }
    public int    getPosx(){ return posx; }
    public int    getPosy(){ return posy; }
    public int    getComprimento(){ return comprimento; }
    public int    getAltura(){ return altura; }
    public int    getCor(){ return cor; }
    public int    getStatusTarefa(){ return statusTarefa; }


    public void setId(int idTarefa){ this.idTarefa = idTarefa; }
    public void setIdUsuario(int idUsuario){ this.idUsuario = idUsuario; }
    public void setTitulo(String titulo){ this.titulo = titulo; }
    public void setTipo(String tipo){ this.tipo = tipo; }
    public void setDescricao(String descricao){ this.descricao = descricao; }
    public void setDataInicio(String dataInicio){ this.dataInicio = dataInicio; }
    public void setDataFim(String dataFim){ this.dataFim = dataFim; }
    public void setPosx(int posx){ this.posx = posx; }
    public void setPosy(int posy){ this.posy = posy; }
    public void setComprimento(int comprimento){ this.comprimento = comprimento; }
    public void setAltura(int altura){ this.altura = altura; }
    public void setCor(int cor){ this.cor = cor; }
    public void setStatusTarefa(int statusTarefa){ this.statusTarefa = statusTarefa; }
// getters e setters
}
