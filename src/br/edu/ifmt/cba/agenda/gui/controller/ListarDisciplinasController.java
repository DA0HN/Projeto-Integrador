package br.edu.ifmt.cba.agenda.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifmt.cba.agenda.database.DatabaseException;
import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.recurso.DisciplinaRecurso;
import br.edu.ifmt.cba.agenda.model.repositorio.DaoFactory;
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
    @FXML private Label labelNumeroDeAulas;
    @FXML private Label labelNumeroDeFaltas;
    
    private DisciplinaRecurso recurso = DaoFactory.createDisciplinaDao();
    
	private ObservableList<Disciplina> observableDisciplina;
	
    
    private void initTableView() {
    	columnDisciplina.setCellValueFactory(new PropertyValueFactory<>("nome"));
    
    	atualizaTabela();
    }
    
    @Override public void initialize(URL arg0, ResourceBundle arg1) {
    	initTableView();
    }
    
    private void atualizaTabela() {
    	if( recurso == null ) {
    		throw new DatabaseException("Erro ao atualizar a tabela de disciplinas.");
    	}
    	List<Disciplina> list = recurso.findAll();
    	observableDisciplina = FXCollections.observableArrayList(list);
    	tableDisciplinas.setItems(observableDisciplina);
    }
    
}
