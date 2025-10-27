package com.example.serving_web_content.dto;

public class TarefaFiltro {
    private Integer idTarefa;
    private Integer idUsuario;
    private String titulo;
    private String tipo;
    private String descricao;
    private String dataInicio;
    private Integer cor;
    private Integer statusTarefa;

    public Integer getIdTarefa() { return idTarefa; }
    public void setIdTarefa(Integer idTarefa) {this.idTarefa = idTarefa;}

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) {this.idUsuario = idUsuario;}

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) {this.titulo = titulo;}

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) {this.tipo = tipo;}

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) {this.descricao = descricao;}

    public String getDataInicio() { return dataInicio; }
    public void setDataInicio(String dataInicio) {this.dataInicio = dataInicio;}

    public Integer getCor() { return cor; }
    public void setCor(Integer cor) {this.cor = cor;}

    public Integer getStatusTarefa() { return statusTarefa; }
    public void setStatusTarefa(Integer statusTarefa) {this.statusTarefa = statusTarefa;}

    public boolean isFiltering(){
        return idTarefa != null || titulo != null || tipo != null || descricao != null || cor != null
                || statusTarefa != null || dataInicio != null;
    }
}
