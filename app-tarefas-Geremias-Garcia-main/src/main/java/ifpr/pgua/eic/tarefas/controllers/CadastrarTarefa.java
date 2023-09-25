package ifpr.pgua.eic.tarefas.controllers;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.tarefas.App;
import ifpr.pgua.eic.tarefas.model.entities.Categoria;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioCategorias;
import ifpr.pgua.eic.tarefas.model.repositories.RepositorioTarefa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class CadastrarTarefa implements Initializable{
    @FXML
    private TextField tfTitulo;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfPrazo;

    @FXML
    private ComboBox<Categoria> cbCategorias;

    private RepositorioTarefa repositorioTarefa;
    private RepositorioCategorias repositorioCategorias;

    public CadastrarTarefa(RepositorioTarefa repositorioTarefa, RepositorioCategorias repositorioCategorias) {
        this.repositorioTarefa = repositorioTarefa;
        this.repositorioCategorias = repositorioCategorias;
    }

    @FXML
    void cadastrar(ActionEvent event) throws ParseException {
        
        String nome = tfTitulo.getText();
        String desc = tfDescricao.getText();
        String prazo = tfPrazo.getText();
        Categoria categoria = cbCategorias.getValue();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate dataEmLocalDate = LocalDate.now();
        try {
            dataEmLocalDate = LocalDate.parse(prazo, formato);
            System.out.println("Data em LocalDate: " + dataEmLocalDate);
        } catch (DateTimeParseException e) {
            System.err.println("Formato de data inválido.");
            e.printStackTrace();
        }

        Alert alert;

        LocalDate atual = LocalDate.now();
        if(dataEmLocalDate.isBefore(atual)){
            alert = new Alert(AlertType.ERROR,"Data inválida");
            alert.showAndWait();
        }else{
            String msg = ""; 
            Resultado rs = repositorioTarefa.criar(nome, desc, dataEmLocalDate,categoria);

            msg = rs.getMsg();
            if(rs.foiErro()){
                alert = new Alert(AlertType.ERROR,msg);
            }else{
                alert = new Alert(AlertType.INFORMATION,msg);  
            }

            alert.showAndWait();
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        Resultado r1 = repositorioCategorias.listar();

        if(r1.foiSucesso()){
            List<Categoria> list = (List)r1.comoSucesso().getObj();
            cbCategorias.getItems().addAll(list);
        }else{
            Alert alert = new Alert(AlertType.ERROR,r1.getMsg());
            alert.showAndWait();
        }
    }

}
