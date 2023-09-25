package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.Categoria;
import ifpr.pgua.eic.tarefas.model.entities.Tarefa;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioCategorias;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioTarefa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;

public class ListarCategorias implements Initializable {

    @FXML
    private ListView<Categoria> lstCategorias;

    private RepositorioCategorias repositorioCategorias;
    private RepositorioTarefa repositorioTarefa;

    

    public ListarCategorias(RepositorioCategorias repositorioCategorias, RepositorioTarefa repositorioTarefa) {
        this.repositorioCategorias = repositorioCategorias;
        this.repositorioTarefa = repositorioTarefa;
    }



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lstCategorias.getItems().clear();
        Resultado resultado = repositorioCategorias.listar();

        if(resultado.foiErro()){
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
        }else{
            List lista = (List)resultado.comoSucesso().getObj();
            lstCategorias.getItems().addAll(lista);
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }
}
