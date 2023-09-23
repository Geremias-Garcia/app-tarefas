package ifpr.pgua.eic.tarefas.model.entities;

import java.util.ArrayList;

public class Categorias {
    private int id;
    private String nome;
    private ArrayList<Tarefa> tarefa;

    public Categorias(int id, String nome){
        this.id = id;
        this.nome = nome;
        tarefa = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Tarefa> getTarefa() {
        return tarefa;
    }

    public void setTarefa(ArrayList<Tarefa> tarefa) {
        this.tarefa = tarefa;
    }
}
