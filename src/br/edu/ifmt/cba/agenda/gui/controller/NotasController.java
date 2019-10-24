package br.edu.ifmt.cba.agenda.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifmt.cba.agenda.gui.enums.Path;
import br.edu.ifmt.cba.agenda.gui.utils.Alerta;
import br.edu.ifmt.cba.agenda.gui.utils.ButtonEvent;
import br.edu.ifmt.cba.agenda.gui.view.Principal;
import br.edu.ifmt.cba.agenda.gui.view.ViewFactory;
import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.entities.Nota;
import br.edu.ifmt.cba.agenda.model.repositorio.DaoFactory;
import br.edu.ifmt.cba.agenda.model.service.UsuarioAtual;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NotasController implements Initializable{
	
	// parte esquerda da view
	@FXML private TableView<Disciplina> tableDisciplinas;
    @FXML private TableColumn<Disciplina, String> columnDisciplina;
    @FXML private TableColumn<Disciplina, Double> columnMedia;
    // parte direita da view
    @FXML private TableView<Nota> tableNotas;
    @FXML private TableColumn<Nota, Double> columnNotas;
    @FXML private Label labelNomeDisciplina;
    @FXML private Label labelMedia;
	// botões de CRUD de notas da parte direita da view
    @FXML private Button btAdicionar;
    @FXML private Button btExcluir;
    @FXML private Button btAlterar;
    
    private ObservableList<Disciplina> observableDisciplinas;
    private ObservableList<Nota> observableNotas;
    
    private static Nota notaAtual = null;
	private static Disciplina disciplinaAtual = null;

	private void atualizarView() {
    	PrincipalController c = Principal.getPrincipalController();
    	c.trocarAnchorPane(Path.NOTAS.getValue());
    	NotasController n = (NotasController) c.getViewController();
    	UsuarioAtual.atualizarDados();
    	n.disciplinaSelecionada(disciplinaAtual);
	}
	
    // Metodos responsáveis pela tabela de disciplinas
    private void initTableDisciplinas() {
    	columnDisciplina.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	columnMedia.setCellValueFactory(new PropertyValueFactory<>("media"));
    	carregaTableDisciplinas();
    }
    private void carregaTableDisciplinas() {
    	//UsuarioAtual.atualizarDados();
    	List<Disciplina> lista = UsuarioAtual.getUsuario().getDisciplinas();
    	observableDisciplinas = FXCollections.observableArrayList(lista);
    	tableDisciplinas.getItems().clear();
    	tableDisciplinas.setItems(observableDisciplinas);
    }
    public void disciplinaSelecionada(Disciplina d) {
    	labelNomeDisciplina.setText(d.getNome());
    	d.calcularMedia();
    	labelMedia.setText(String.format("%.2f", d.getMedia()));
    	setDisciplinaAtual(d);
    	initTableNotas(d);
    }
    
    // Metodos responsáveis pela tabela de notas
    private void initTableNotas(Disciplina d) {
    	columnNotas.setCellValueFactory(new PropertyValueFactory<>("nota"));
    	carregaTableNotas(d);
    }
    
    public void carregaTableNotas(Disciplina d) {
    	UsuarioAtual.atualizarDados();
    	List<Nota> lista = d.getNotas();
    	observableNotas = FXCollections.observableArrayList(lista);
    	tableNotas.setItems(observableNotas);
    }
    
    // botoes da tela  
    @FXML void btAdicionarOnKeyPressed(KeyEvent event) {
    	if( ButtonEvent.hasUserConfirmed(event) ) {
    		adicionarNota();
    	}
    }

    @FXML void btAdicionarOnClicked(MouseEvent event) {
    	adicionarNota();
    }
    
    
    @FXML void btAlterarOnClicked(MouseEvent event) {
    	System.out.println(notaAtual);

    }

    @FXML void btAlterarOnKeyPressed(KeyEvent event) {
    	System.out.println(notaAtual);

    }

    @FXML void btExcluirOnClicked(MouseEvent event) {
    	deletarNota();
    }

    @FXML void btExcluirOnKeyPressed(KeyEvent event) {
    	if( ButtonEvent.hasUserConfirmed(event) ) {
    		deletarNota();
    	}
    }
    
    private void configuraBindings() {
    	BooleanBinding algoSelecionado = tableNotas.getSelectionModel().selectedItemProperty().isNull();
    	btAlterar.disableProperty().bind(algoSelecionado);
    	btExcluir.disableProperty().bind(algoSelecionado);
    }
    
	@Override public void initialize(URL arg0, ResourceBundle arg1) {
		configuraBindings();
		initTableDisciplinas();
		tableDisciplinas.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> disciplinaSelecionada(newValue));
		tableNotas.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> setNotaAtual(newValue) );
	}
	
	public static void setNotaAtual(Nota notaAtual) {
		NotasController.notaAtual = notaAtual;
	}
	private void deletarNota() {
		if( DaoFactory.createHistoricoDao().deleteNota(UsuarioAtual.getUsuario(), getDisciplinaAtual(), getNotaAtual()) ) {
			Alerta.mostrar(AlertType.INFORMATION, "Sucesso", "Sucesso ao deletar a nota " + getNotaAtual() + " da disciplina "+ getDisciplinaAtual() );
		}
		else {
			Alerta.mostrar(AlertType.ERROR, "Erro", "Não foi possivel deletar a nota.");
		}
		atualizarView();
		setNotaAtual(null);
	}
	
	private void adicionarNota() {
		ViewFactory.createAdicionarNota().start(new Stage());
	}
	
	public static Disciplina getDisciplinaAtual() {
		return disciplinaAtual;
	}
	
	public static void setDisciplinaAtual(Disciplina d) {
		disciplinaAtual = d;
	}
	
	public static Nota getNotaAtual() {
		return notaAtual;
	}
}
