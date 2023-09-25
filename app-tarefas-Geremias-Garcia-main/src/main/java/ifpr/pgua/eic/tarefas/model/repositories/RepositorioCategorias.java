package ifpr.pgua.eic.tarefas.model.repositories;

import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.daos.CategoriasDAO;
import ifpr.pgua.eic.tarefas.model.entities.Categoria;

public class RepositorioCategorias {
    private ArrayList<Categoria> categorias;
    private CategoriasDAO dao;

    public RepositorioCategorias(CategoriasDAO dao){
        categorias = new ArrayList<>();
        this.dao = dao;
    }

    public Resultado criar(String nome, String descricao){

        if (nome.isEmpty() || nome.isBlank()) {
            return Resultado.erro("Nome inválido!");
        }

        if (descricao.isBlank() || descricao.isEmpty()) {
            return Resultado.erro("Contato inválido!");
        }

        Categoria categoria = new Categoria(nome, descricao);
        return dao.criar(categoria);
    }

    public Resultado listar() {
        return dao.listar();
    }

    public Resultado buscarPorId(int id){
        return dao.buscarPorId(id);
    }

}
