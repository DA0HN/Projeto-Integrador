package br.edu.ifmt.cba.agenda.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifmt.cba.agenda.gui.utils.Alerta;
import br.edu.ifmt.cba.agenda.gui.utils.ButtonEvent;
import br.edu.ifmt.cba.agenda.gui.view.ViewFactory;
import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.entities.Nota;
import br.edu.ifmt.cba.agenda.model.recurso.UsuarioAtual;
import br.edu.ifmt.cba.agenda.model.repositorio.DaoFactory;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NotasController implements Initializable{
	
	// parte esquerda da view
	@FXML private TableView<Disciplina> tableDisciplinas = new TableView<Disciplina>();	// 206x460
    // parte direita da view
    @FXML private TableView<Nota> tableNotas;	// 184x280
    @FXML private Label labelNomeDisciplina;
    @FXML private Label labelMedia;
	// botões de CRUD de notas da parte direita da view
    @FXML private Button btAdicionar;
    @FXML private Button btExcluir;
    @FXML private Button btAlterar;
    
    private TableColumn<Disciplina, String> columnDisciplina;
    private TableColumn<Disciplina, Double> columnMedia;
    private TableColumn<Nota, Double> columnNotas;
    private ObservableList<Disciplina> observableDisciplinas;
    private ObservableList<Nota> observableNotas;
    
    private static Nota notaAtual = null;
	private static Disciplina disciplinaAtual = null;
	
    // Metodos responsáveis pela tabela de disciplinas
	
    private void initTableDisciplinas() {
    	columnDisciplina = new TableColumn<Disciplina, String>("Disciplina");
    	columnMedia = new TableColumn<Disciplina, Double>("Média");
    	tableDisciplinas.setMaxSize(206, 460);
    	columnDisciplina.setMaxWidth(143);
    	columnMedia.setMaxWidth(63);
    	
    	columnDisciplina.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	columnMedia.setCellValueFactory(new PropertyValueFactory<>("media"));
    	
    	tableDisciplinas.getColumns().add(columnDisciplina);
    	tableDisciplinas.getColumns().add(columnMedia);
    	
    	carregaTableDisciplinas();
    }
    private void carregaTableDisciplinas() {
    	List<Disciplina> lista = UsuarioAtual.getUsuario().getDisciplinas();
    	observableDisciplinas = FXCollections.observableArrayList(lista);
    	tableDisciplinas.setItems(observableDisciplinas);
    }
    public void disciplinaSelecionada(Disciplina d) {
    	setDisciplinaAtual(d);
    	labelNomeDisciplina.setText(d.getNome());
    	d.calcularMedia();
    	labelMedia.setText(String.format("%.2f", d.getMedia()));
    	carregaTableNotas(d);
    } 
    private void atualizaTableDisciplina() {
    	tableDisciplinas.getItems().clear();
    	UsuarioAtual.atualizarDados();
    	observableDisciplinas = FXCollections.observableArrayList(UsuarioAtual.getUsuario().getDisciplinas());
    	tableDisciplinas.setItems(observableDisciplinas);
    	tableDisciplinas.refresh();
    }
    // Metodos responsáveis pela tabela de notas
    private void initTableNotas() {
    	columnNotas = new TableColumn<Nota, Double>("Suas notas");
    	columnNotas.setCellValueFactory(new PropertyValueFactory<>("nota"));
    	
    	columnNotas.setPrefWidth(190);
    	
    	tableNotas.getColumns().add(columnNotas);
    }
    private void carregaTableNotas(Disciplina d) {
    	UsuarioAtual.atualizarDados();
    	observableNotas = FXCollections.observableArrayList(UsuarioAtual.atualizaNotasDaDisciplina(getDisciplinaAtual()));
    	tableNotas.setItems(observableNotas);
    }
    private void atualizaTableNotas() {
    	tableNotas.getItems().clear();
    	if( getDisciplinaAtual() == null ) {
    		
    	}
    	observableNotas = FXCollections.observableArrayList(UsuarioAtual.atualizaNotasDaDisciplina(getDisciplinaAtual()));
    	tableNotas.setItems(observableNotas);
    	tableNotas.refresh();
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
    	alterarNota();
    }
    
	@FXML void btAlterarOnKeyPressed(KeyEvent event) {
    	if( ButtonEvent.hasUserConfirmed(event) ) {
    		alterarNota();
    	}

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
		initTableNotas();
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
		atualizar();
	}
	
	public void atualizar() {
		var d = getDisciplinaAtual();
		atualizaTableNotas();
		setDisciplinaAtual(null);
		setNotaAtual(null);
		atualizaTableDisciplina();
		setDisciplinaAtual(retornaDisciplinaAtualizada(d));
		disciplinaSelecionada(getDisciplinaAtual());
	}
	
	public Disciplina retornaDisciplinaAtualizada(Disciplina disciplina) {
		for(Disciplina d : observableDisciplinas) {
			if( d.getId() == disciplina.getId()) return d;
		}
		return null;
	}
	
	private void alterarNota() {
    	var view = ViewFactory.createAlterarNota();
    	view.start(new Stage());
    	view.getController().setDisciplina(getDisciplinaAtual());
    	view.getController().setNota(getNotaAtual());
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
