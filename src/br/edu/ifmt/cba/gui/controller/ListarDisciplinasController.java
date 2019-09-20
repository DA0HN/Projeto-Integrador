package br.edu.ifmt.cba.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifmt.cba.model.entities.Disciplina;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListarDisciplinasController implements Initializable {	
    
    @FXML private TableView<Disciplina> tableDisciplinas;
    @FXML private TableColumn<Disciplina, String> columnDisciplina;
    @FXML private Label labelNomeDaDisciplina;
    @FXML private Label labelNomeDoProfessor;
	private ObservableList<Disciplina> observableDisciplina;
	
    
    private void initTableView() {
//    	TODO: descobrir como acessar o aluno atual
//    	para converter sua lista de disciplinas
    	columnDisciplina.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	observableDisciplina = FXCollections.observableArrayList();
    }
    
    @Override public void initialize(URL arg0, ResourceBundle arg1) {
    	
    }
}
