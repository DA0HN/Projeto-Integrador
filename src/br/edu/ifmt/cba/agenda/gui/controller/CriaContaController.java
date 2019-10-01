package br.edu.ifmt.cba.agenda.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifmt.cba.agenda.gui.utils.ButtonEvent;
import br.edu.ifmt.cba.agenda.gui.view.CriaConta;
import br.edu.ifmt.cba.agenda.gui.view.ViewFactory;
import br.edu.ifmt.cba.agenda.model.entities.Aluno;
import br.edu.ifmt.cba.agenda.model.exception.DadosInvalidos;
import br.edu.ifmt.cba.agenda.model.repositorio.DaoFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CriaContaController implements Initializable {

 	@FXML private TextField txNome;
    @FXML private TextField txEmail;
    @FXML private TextField txMatricula;
    @FXML private PasswordField psSenha;
    @FXML private PasswordField psConfirm;
    @FXML private Button btCadastrar;
    @FXML private Button btSair;

    @FXML void btCadastrarOnClicked(MouseEvent event) {
    	getData();
    }
    
    @FXML void btCadastrarOnKeyPressed(KeyEvent event) {
    	if( ButtonEvent.hasEnterPressed(event) ) {
    		getData();
    	}
    }
    
    @FXML void btSairOnKeyPressed(KeyEvent event ) {
    	if( ButtonEvent.hasUserConfirmed(event) ) {
    		buildLogin();
    	}
    }
    
    @FXML void btSairOnClicked(MouseEvent event) {
    	buildLogin();
    }
    
    @FXML void textFieldHasEnterPressed(KeyEvent event) {
    	if( ButtonEvent.hasUserConfirmed(event) ) {
    		getData();
    	}
    }
    
    private void getData() {
    	try {
    		String nome = txNome.getText();
    		String email = txEmail.getText();
    		String matricula = txMatricula.getText();
    		String senha = psSenha.getText();
    		String senhaConfirm = psConfirm.getText();
    		
    		if( verificaDados(nome) && verificaDados(email) && verificaDados(matricula) && verificaDados(senha) && verificaDados(senhaConfirm) ) {
    			if( !senha.equals(senhaConfirm) ) {
        			throw new IllegalStateException("Senha inválida, por favor digite novamente.");
        		}
    			Aluno novoUsuario = new Aluno(nome, matricula, senha, email);
    			
    			DaoFactory.createAlunoDao().save(novoUsuario);
    			buildLogin();
    		}
    		else {
    			throw new IllegalStateException("Os dados inseridos não são válidos.");
    		}
    		
    	}
    	catch( IllegalStateException e) {
    		throw new DadosInvalidos( e.getMessage() );
    	}
    }
    
    private boolean verificaDados(String str) {
    	return !(str.isBlank() || str.isEmpty());
    }
    
    private void buildLogin() {
    	CriaConta.getStage().close();
    	ViewFactory.createLogin().start(new Stage());
    }
    
	@Override public void initialize(URL arg0, ResourceBundle arg1) {}
	
}
