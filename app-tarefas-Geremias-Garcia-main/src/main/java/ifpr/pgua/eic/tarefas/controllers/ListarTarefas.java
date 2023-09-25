package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.Tarefa;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioCategorias;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioTarefa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class ListarTarefas implements Initializable{
    @FXML
    private ListView<Tarefa> lstTarefas;

    @FXML
    private TextArea detalhes;

    private RepositorioTarefa repositorioTarefas;
    private RepositorioCategorias repositorioCategorias;

    public ListarTarefas(RepositorioTarefa repositorioTarefa, RepositorioCategorias repositorioCategorias) {
        this.repositorioTarefas = repositorioTarefa;
        this.repositorioCategorias = repositorioCategorias;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("AQUI");
        lstTarefas.getItems().clear();
        Resultado resultado = repositorioTarefas.listar();

        if(resultado.foiErro()){
            Alert alert = new Alert(AlertType.ERROR, resultado.getMsg());
            alert.showAndWait();
        }else{
            List lista = (List)resultado.comoSucesso().getObj();
            lstTarefas.getItems().addAll(lista);
        }
    }
 
    @FXML
    private void mostrarDetalhes(MouseEvent evento){
        Tarefa tarefa = lstTarefas.getSelectionModel().getSelectedItem();
        System.out.println(tarefa.getId());
       
        if(tarefa != null){
            detalhes.clear();
            detalhes.appendText("ID: "+tarefa.getId()+"\n");
            detalhes.appendText("Titulo: "+tarefa.getTitulo()+"\n");
            detalhes.appendText("Descrição: "+tarefa.getDescricao()+"\n");
            detalhes.appendText("Prazo: "+tarefa.getPrazo()+"\n\n");
            detalhes.appendText("Categoria: "+tarefa.getCategoria().getNome()+"\n");
            detalhes.appendText("Categoria ID: "+tarefa.getCategoria().getId()+"\n");
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

}
