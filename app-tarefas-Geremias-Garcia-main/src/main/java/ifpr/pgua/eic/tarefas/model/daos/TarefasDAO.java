package ifpr.pgua.eic.tarefas.model.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.model.entities.Tarefa;

public interface TarefasDAO {
    Resultado criarTarefa(Tarefa tarefa);

    Resultado listar();

    Resultado buscarPorId(int id);
}
