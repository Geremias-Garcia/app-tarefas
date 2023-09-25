package ifpr.pgua.eic.tarefas.model.entities;

import java.time.LocalDate;

public class Tarefa {
    private int id;
    private String titulo;
    private String descricao;
    private LocalDate prazo;
    private Categoria categoria;

    public Tarefa(String titulo, String descricao, LocalDate prazo, Categoria categoria) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
        this.categoria = categoria;
    }

    public Tarefa(int id, String titulo, String descricao, LocalDate prazo, Categoria categoria) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
        this.categoria = categoria;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public LocalDate getPrazo() {
        return prazo;
    }
    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String toString(){
        return titulo;
    }
}
