package ifpr.pgua.eic.tarefas.model.repositories;

import java.time.LocalDate;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.daos.CategoriasDAO;
import ifpr.pgua.eic.tarefas.model.daos.TarefasDAO;
import ifpr.pgua.eic.tarefas.model.entities.Categoria;
import ifpr.pgua.eic.tarefas.model.entities.Tarefa;

public class RepositorioTarefa {
    
    private ArrayList<Tarefa> tarefas;
    private TarefasDAO dao;
    private CategoriasDAO categoriasDAO;
    
    public RepositorioTarefa(TarefasDAO dao, CategoriasDAO categoriasDAO) {
        this.dao = dao;
        this.categoriasDAO = categoriasDAO;
        tarefas = new ArrayList<>();
    }

    public Resultado criar(String nome, String desc, LocalDate prazo, Categoria categoria) {

        if(nome.isBlank() || nome.isEmpty()){
            return Resultado.erro("Nome inválido!");
        }
        if(desc.isBlank() || desc.isEmpty()){
            return Resultado.erro("Descrição inválida!");
        }

        Tarefa tarefa = new Tarefa(nome, desc, prazo, categoria);

        System.out.println("chegou aqui repositorio "+tarefa);
        return dao.criarTarefa(tarefa);
    }

    public Resultado listar() {
        return dao.listar();
    }

    public Resultado buscarPorId(int id){
        return dao.buscarPorId(id);
    }
}
