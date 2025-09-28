package com.example.serving_web_content.dto;

public class TarefaPos {
    private Long idTarefa;
    private int x;
    private int y;

    // getters and setters
    public TarefaPos(Long idTarefa, int x, int y) {
        this.idTarefa = idTarefa;
        this.x = x;
        this.y = y;
    }

    public Long getId() {return idTarefa;}
    public int getX() {return x;}
    public int getY() {return y;}
    public void setId(Long id) {this.idTarefa = id;}
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
}