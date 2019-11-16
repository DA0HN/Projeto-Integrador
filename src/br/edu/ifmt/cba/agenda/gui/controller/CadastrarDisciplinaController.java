package br.edu.ifmt.cba.agenda.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifmt.cba.agenda.gui.utils.Alerta;
import br.edu.ifmt.cba.agenda.gui.utils.ButtonEvent;
import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.repository.ServiceFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class CadastrarDisciplinaController implements Initializable{
	
	@FXML private TextField txNomeDisciplina;
    @FXML private TextField txNomeProfessor;
    @FXML private TextField txNumeroAulas;
    @FXML private Button btLimpar; 
    @FXML private Button btCadastrar;
    @FXML private AnchorPane cadastrarDisciplinaController;
    
    //private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
    
    @FXML void btLimparOnClicked(MouseEvent event) {
    	limparTextField();
    }
    
    @FXML void btLimparOnKeyPressed(KeyEvent event ) {
    	if( ButtonEvent.hasUserConfirmed(event) ) {
    		limparTextField();
    	}
    }
    
    @FXML void btCadastrarOnClicked(MouseEvent event) {
    	cadastrarNovaDisciplina();
    }

    @FXML void btCadastrarOnKeyPressed(KeyEvent event) {
    	if( ButtonEvent.hasUserConfirmed(event) ) {
    		cadastrarNovaDisciplina();
    	}
    }

    @FXML void textFieldHasEnterPressed(KeyEvent event) {
    	if( ButtonEvent.hasEnterPressed(event) ) {
    		cadastrarNovaDisciplina();
    	}
    }
	
    private void limparTextField() {
    	txNomeDisciplina.setText("");
    	txNomeProfessor.setText("");
    	txNumeroAulas.setText("");
    }
    
    private void cadastrarNovaDisciplina() {
    	String nomeDisciplina = txNomeDisciplina.getText();
    	String nomeProfessor = txNomeProfessor.getText();
    	Integer numeroAulas = Integer.parseInt(txNumeroAulas.getText());	
    	
    	if( verificaDados(nomeDisciplina) && verificaDados(nomeProfessor) && numeroAulas > 0) {
    		var disciplina = new Disciplina(nomeDisciplina, nomeProfessor, numeroAulas);
    		boolean isSave = ServiceFactory.createDisciplinaDao().save(disciplina);
    		if( isSave ) {
    			Alerta.mostrar(AlertType.INFORMATION, "Sucesso", "Sucesso ao criar nova disciplina.");
    		}
    		else {
    			Alerta.mostrar(AlertType.ERROR, "Ocorreu um erro",
    					"n�o foi poss�vel criar uma nova disciplina.");
    		}
    		limparTextField();
    		
    	}
    	else {
    		Alerta.mostrar(AlertType.ERROR, "Os dados inseridos n�o s�o v�lidos",
    				"Os dados n�o foram inseridos corretamente.");
    		return;
    	}
    }
    private boolean verificaDados(String str) {
    	return !(str.isBlank() || str.isEmpty());
    }
	@Override public void initialize(URL arg0, ResourceBundle arg1) {}

}
