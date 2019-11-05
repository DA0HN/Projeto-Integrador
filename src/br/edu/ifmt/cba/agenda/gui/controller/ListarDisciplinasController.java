package br.edu.ifmt.cba.agenda.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifmt.cba.agenda.database.DatabaseException;
import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.recurso.UsuarioAtual;
import br.edu.ifmt.cba.agenda.model.repositorio.DaoFactory;
import br.edu.ifmt.cba.agenda.model.service.DisciplinaRecurso;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListarDisciplinasController implements Initializable {	
    /*
     * IMPLEMENTADO ListarDisciplinas
     * */
    @FXML private TableView<Disciplina> tableDisciplinas;
    @FXML private TableColumn<Disciplina, String> columnDisciplina;
    @FXML private Label labelNomeDaDisciplina;
    @FXML private Label labelNomeDoProfessor;
    @FXML private Label labelNumeroDeAulas;
    @FXML private Label labelNumeroDeFaltas;
    
    private DisciplinaRecurso dao = DaoFactory.createDisciplinaDao();
    
	private ObservableList<Disciplina> observableDisciplina;
    
	@Override public void initialize(URL arg0, ResourceBundle arg1) {
		initTableView();
	
		tableDisciplinas.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> selecionarDisciplina(newValue));
	
	}
    
	private void selecionarDisciplina(Disciplina d) {
		d = tableDisciplinas.getSelectionModel().getSelectedItem();
		if( d != null ){
			labelNomeDaDisciplina.setText(d.getNome());
			labelNomeDoProfessor.setText(d.getProfessor());
			labelNumeroDeFaltas.setText(String.valueOf(d.getFaltas()));
			labelNumeroDeAulas.setText(String.valueOf(d.getNumeroDeAulas()));
		}	
	}

	private void initTableView() {
    	columnDisciplina.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	atualizaTabela();
    }
    
    private void atualizaTabela() {
    	if( dao == null ) {
    		throw new DatabaseException("Erro ao atualizar a tabela de disciplinas.");
    	}
    	List<Disciplina> list = UsuarioAtual.getUsuario().getDisciplinas();
    	observableDisciplina = FXCollections.observableArrayList(list);
    	tableDisciplinas.setItems(observableDisciplina);
    }
    
}
