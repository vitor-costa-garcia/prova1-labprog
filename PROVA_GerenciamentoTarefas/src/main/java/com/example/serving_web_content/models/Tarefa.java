package com.example.serving_web_content.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idTarefa", nullable = false, unique = true)
    private Integer idTarefa;

    //chave estrangeira
    @NotNull(message = "O usuário não pode ser nulo.")
    @Column(name="idUsuario", nullable = false)
    private Integer idUsuario;

    @NotBlank(message = "O título não pode ser nulo.")
    @Size(max = 100, message = "O título deve ter no máximo 100 caracteres")
    @Column(name="titulo", nullable = false, unique = true, length = 100)
    @UniqueElements(message = "O título fornecido já foi usado.")
    private String titulo;

    @NotBlank(message = "O tipo não pode ser nulo")
    @Size(max = 50, message = "O tipo deve ter no máximo 100 caracteres")
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

    @Column(name="posx", nullable = false)
    private int posx;

    @Column(name="posy", nullable = false)
    private int posy;

    @Column(name="comprimento", nullable = false)
    private int comprimento;

    @Column(name="altura", nullable = false)
    private int altura;


    @Min(value = 0, message = "Selecione uma das cores pré-definidas no formulário" )
    @Max(value = 5, message = "Selecione uma das cores pré-definidas no formulário")
    @NotNull(message = "A cor não pode ser nula.")
    @Column(name="cor", nullable = false, unique = false)
    private Integer cor;

    @Min(value = 0, message = "Selecione um dos status pré-definidos no formulário" )
    @Max(value = 1, message = "Selecione um dos status pré-definidos no formulário")
    @NotNull(message = "O status não pode ser nulo.")
    @Column(name="statusTarefa", nullable = false)
    private Integer statusTarefa;


    public Tarefa(Integer idTarefa,
                  Integer idUsuario,
                  String titulo,
                  String tipo,
                  String descricao,
                  String dataInicio,
                  int posx,
                  int posy,
                  int comprimento,
                  int altura,
                  Integer cor,
                  Integer statusTarefa) {
        this.idTarefa = idTarefa;
        this.idUsuario = idUsuario;
        this.titulo = titulo;
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.posx = posx;
        this.posy = posy;
        this.comprimento = comprimento;
        this.altura = altura;
        this.cor = cor;
        this.statusTarefa = statusTarefa;
    }

    public Tarefa() {

    }

    public Integer getId(){
        return idTarefa;
    }
    public Integer getIdUsuario(){ return idUsuario; }
    public String  getTitulo(){ return titulo; }
    public String  getTipo(){ return tipo; }
    public String  getDescricao(){ return descricao; }
    public String  getDataInicio(){ return dataInicio; }
    public int     getPosx(){ return posx; }
    public int     getPosy(){ return posy; }
    public int     getComprimento(){ return comprimento; }
    public int     getAltura(){ return altura; }
    public Integer getCor(){ return cor; }
    public Integer getStatusTarefa(){ return statusTarefa; }


    public void setId(Integer idTarefa){ this.idTarefa = idTarefa; }
    public void setIdUsuario(Integer idUsuario){ this.idUsuario = idUsuario; }
    public void setTitulo(String titulo){ this.titulo = titulo; }
    public void setTipo(String tipo){ this.tipo = tipo; }
    public void setDescricao(String descricao){ this.descricao = descricao; }
    public void setDataInicio(String dataInicio){ this.dataInicio = dataInicio; }
    public void setPosx(int posx){ this.posx = posx; }
    public void setPosy(int posy){ this.posy = posy; }
    public void setComprimento(int comprimento){ this.comprimento = comprimento; }
    public void setAltura(int altura){ this.altura = altura; }
    public void setCor(Integer cor){ this.cor = cor; }
    public void setStatusTarefa(Integer statusTarefa){ this.statusTarefa = statusTarefa; }
// getters e setters
}
