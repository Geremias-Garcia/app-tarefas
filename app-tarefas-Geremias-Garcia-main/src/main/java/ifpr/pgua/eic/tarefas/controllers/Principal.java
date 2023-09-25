package ifpr.pgua.eic.tarefas.controllers;

import ifpr.pgua.eic.tarefas.App;
import javafx.fxml.FXML;

public class Principal {
    
    @FXML
    private void cadastrarCategoria(){
        App.pushScreen("CADASTROCATEGORIA");
    }

    @FXML
    private void listarCategorias(){
        App.pushScreen("LISTARCATEGORIA");
    }
   
    @FXML
    private void cadastrarTarefa(){
        App.pushScreen("CADASTROTAREFA");
    }

    @FXML
    private void listarTarefa(){
        App.pushScreen("LISTARTAREFA");
    }
}
