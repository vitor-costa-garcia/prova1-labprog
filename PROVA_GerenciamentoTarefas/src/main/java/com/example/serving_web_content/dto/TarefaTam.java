package com.example.serving_web_content.dto;

public class TarefaTam {
    private Long idTarefa;
    private int comprimento;
    private int altura;

    // getters and setters
    public TarefaTam(Long  idTarefa, int comprimento, int altura) {
        this.idTarefa = idTarefa;
        this.comprimento = comprimento;
        this.altura = altura;
    }

    public Long getIdTarefa() { return idTarefa; }
    public int getComprimento() { return comprimento; }
    public int getAltura() { return altura; }

    public void setIdTarefa(Long idTarefa) { this.idTarefa = idTarefa; }
    public void setComprimento(int comprimento) { this.comprimento = comprimento; }
    public void setAltura(int altura) { this.altura = altura; }
}