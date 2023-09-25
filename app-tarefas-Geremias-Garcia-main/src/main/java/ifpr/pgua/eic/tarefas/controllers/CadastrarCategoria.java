package ifpr.pgua.eic.tarefas.controllers;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioCategorias;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class CadastrarCategoria {
    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfDescricao;

    private RepositorioCategorias repositorioCategorias;

    public CadastrarCategoria(RepositorioCategorias repositorioCategorias) {
        this.repositorioCategorias = repositorioCategorias;
    }

    @FXML
    void cadastrar(ActionEvent event) {
        
        String nome = tfNome.getText();
        String descricao = tfDescricao.getText();
        System.out.println(nome+"-"+descricao);

        Resultado resultado = repositorioCategorias.criar(nome, descricao);

        String msg;

        Alert alert;
        msg = resultado.getMsg();
        if(resultado.foiErro()){
            alert = new Alert(AlertType.ERROR,msg);
        }else{
            alert = new Alert(AlertType.INFORMATION,msg);  
        }

        alert.showAndWait();

    }
    
    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }
    
}
