package br.edu.ifmt.cba.agenda.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifmt.cba.agenda.database.DatabaseException;
import br.edu.ifmt.cba.agenda.gui.utils.ButtonEvent;
import br.edu.ifmt.cba.agenda.gui.view.ViewFactory;
import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.repository.ServiceFactory;
import br.edu.ifmt.cba.agenda.model.resource.UsuarioAtual;
import br.edu.ifmt.cba.agenda.model.service.DisciplinaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ListarDisciplinasController implements Initializable {	
    
	@FXML private TableView<Disciplina> tableDisciplinas;
    @FXML private TableColumn<Disciplina, String> columnDisciplina;
    @FXML private Label labelNomeDaDisciplina;
    @FXML private Label labelNomeDoProfessor;
    @FXML private Label labelNumeroDeAulas;
    @FXML private Label labelNumeroDeFaltas;
    @FXML private Button btAlterarFalta;
    
    private DisciplinaService dao = ServiceFactory.createDisciplinaDao();
    
    private static Disciplina disciplinaAtual;
	private ObservableList<Disciplina> observableDisciplina;
    
	@Override public void initialize(URL arg0, ResourceBundle arg1) {
		initTableView();
	
		tableDisciplinas.getSelectionModel()
				.selectedItemProperty()
				.addListener( (observable, oldValue, newValue) -> selecionarDisciplina(newValue));
	
	}
    
	private void selecionarDisciplina(Disciplina d) {
		//d = tableDisciplinas.getSelectionModel().getSelectedItem();
		setDisciplinaAtual(d);
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
    
    public void atualizar() {
    	var disciplina = getDisciplinaAtual();
    	setDisciplinaAtual(null);
    	atualizaTabela();
    	setDisciplinaAtual(retornaDisciplinaAtualizada(disciplina));
    	selecionarDisciplina(getDisciplinaAtual());
    }
    
    public Disciplina retornaDisciplinaAtualizada(Disciplina disciplina) {
		for(Disciplina d : observableDisciplina) {
			if( d.getId() == disciplina.getId()) return d;
		}
		return null;
	}
    
    public static Disciplina getDisciplinaAtual() {
		return disciplinaAtual;
	}

	public static void setDisciplinaAtual(Disciplina disciplinaAtual) {
		ListarDisciplinasController.disciplinaAtual = disciplinaAtual;
	}

	private void abrirViewAlterarFaltas() {
		var view = ViewFactory.createAlterarFaltas();
		view.start(new Stage());
	}
	
	@FXML void btAlterarOnKeyPressed(KeyEvent event) {
		if( ButtonEvent.hasUserConfirmed(event)) abrirViewAlterarFaltas();
	}
    
    @FXML void btAlterarOnMouseClicked(MouseEvent event) {
    	abrirViewAlterarFaltas();
    }
    
}
