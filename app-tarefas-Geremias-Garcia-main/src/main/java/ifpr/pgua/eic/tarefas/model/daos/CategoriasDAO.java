package ifpr.pgua.eic.tarefas.model.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.entities.Categoria;

public interface CategoriasDAO {
    Resultado criar(Categoria nome);

    Resultado listar();

    Resultado buscarPorId(int id);
}
