package ifpr.pgua.eic.tarefas;

import org.sqlite.JDBC;

import ifpr.pgua.eic.tarefas.controllers.CadastrarCategoria;
import ifpr.pgua.eic.tarefas.controllers.CadastrarTarefa;
import ifpr.pgua.eic.tarefas.controllers.ListarCategorias;
import ifpr.pgua.eic.tarefas.controllers.ListarTarefas;
import ifpr.pgua.eic.tarefas.controllers.Principal;
import ifpr.pgua.eic.tarefas.model.daos.CategoriasDAO;
import ifpr.pgua.eic.tarefas.model.daos.FabricaConexoes;
import ifpr.pgua.eic.tarefas.model.daos.JDBCCategoriasDAO;
import ifpr.pgua.eic.tarefas.model.daos.JDBCTarefasDAO;
import ifpr.pgua.eic.tarefas.model.daos.TarefasDAO;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioCategorias;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioTarefa;
import io.github.hugoperlin.navigatorfx.BaseAppNavigator;
import io.github.hugoperlin.navigatorfx.ScreenRegistryFXML;

/**
 * JavaFX App
 */
public class App extends BaseAppNavigator {

    private CategoriasDAO categoriasDAO = new JDBCCategoriasDAO(FabricaConexoes.getInstance());
    private RepositorioCategorias repositorioCategorias = new RepositorioCategorias(categoriasDAO);

    private TarefasDAO tarefasDAO = new JDBCTarefasDAO(FabricaConexoes.getInstance(), repositorioCategorias);
    private RepositorioTarefa repositorioTarefa = new RepositorioTarefa(tarefasDAO, categoriasDAO);



    public static void main(String[] args) {
        launch();
    }

    @Override
    public String getHome() {
        // TODO Auto-generated method stub
        return "PRINCIPAL";
    }


    @Override
    public String getAppTitle() {
        // TODO Auto-generated method stub
        return "Tarefas";
    }

    
    @Override
    public void registrarTelas() {
        registraTela("PRINCIPAL", new ScreenRegistryFXML(App.class, "principal.fxml", o -> new Principal()));
        registraTela("CADASTROCATEGORIA",
                new ScreenRegistryFXML(App.class,
                        "cadastrarCategorias.fxml",
                        o -> new CadastrarCategoria(repositorioCategorias)));

        registraTela("LISTARCATEGORIA",
                new ScreenRegistryFXML(App.class,
                        "listarCategorias.fxml",
                        o -> new ListarCategorias(repositorioCategorias, repositorioTarefa)));
        
        registraTela("CADASTROTAREFA",
                new ScreenRegistryFXML(App.class,
                        "cadastrarTarefa.fxml",
                        o -> new CadastrarTarefa(repositorioTarefa, repositorioCategorias)));
        registraTela("LISTARTAREFA",
                new ScreenRegistryFXML(App.class,
                        "listarTarefas.fxml",
                        o -> new ListarTarefas(repositorioTarefa, repositorioCategorias)));
    }

}