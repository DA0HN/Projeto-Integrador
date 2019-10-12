package br.edu.ifmt.cba.agenda.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifmt.cba.agenda.gui.exceptions.ViewException;
import br.edu.ifmt.cba.agenda.gui.utils.ButtonEvent;
import br.edu.ifmt.cba.agenda.gui.view.Login;
import br.edu.ifmt.cba.agenda.gui.view.ViewFactory;
import br.edu.ifmt.cba.agenda.model.entities.Aluno;
import br.edu.ifmt.cba.agenda.model.exception.DadosInvalidos;
import br.edu.ifmt.cba.agenda.model.repositorio.DaoFactory;
import br.edu.ifmt.cba.agenda.model.service.AutenticadorDeUsuario;
import br.edu.ifmt.cba.agenda.model.service.UsuarioAtual;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController implements Initializable{

    @FXML private TextField txLogin;
    @FXML private PasswordField txSenha;
    @FXML private Button btSair;
    @FXML private Button btEntrar;
    @FXML private Button btNovaConta;
	
	@Override public void initialize(URL arg0, ResourceBundle arg1) {
		btEntrar.setOnMouseClicked( (MouseEvent e) -> { 
			autenticacao();
		});
		
		btSair.setOnMouseClicked( (MouseEvent e) -> { fecha(); });
		
		btEntrar.setOnKeyPressed( (KeyEvent e) -> {
			if( ButtonEvent.hasUserConfirmed(e)  ) {
				autenticacao();
			}
		});
		
		btSair.setOnKeyPressed( (KeyEvent e) -> {
			if( ButtonEvent.hasUserConfirmed(e) ) {
				fecha();
			}
		});
		
		txLogin.setOnKeyPressed( (KeyEvent e) -> {
			if( ButtonEvent.hasUserConfirmed(e)  ) {
				autenticacao();
			}
		});
		
		txSenha.setOnKeyPressed( (KeyEvent e) -> {
			if(  ButtonEvent.hasUserConfirmed(e)  ) {
				autenticacao();
			}
		});
	}
	
	@FXML private void btNovaContaOnClicked(MouseEvent e){
			buildCriaConta();
	}
	
	@FXML private void btNovaContaOnKeyPressed(KeyEvent e) {
		if( ButtonEvent.hasUserConfirmed(e) ) { 
			buildCriaConta();
		}
	}
	
	private void buildCriaConta() {
		try {
			ViewFactory.CriaConta().start(new Stage());
			fecha();
		} catch (Exception e) {
			throw new ViewException(e.getMessage());
		}
	}
	
	public void autenticacao() {
		String login = txLogin.getText();
		String senha = txSenha.getText();
		Aluno aluno = null;
		try {
			if( AutenticadorDeUsuario.validaEntradas(login, senha) ) {
				aluno = DaoFactory.createAlunoDao().findByMatricula(login);
			}
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro ao autenticar seus dados");
				alert.setContentText("Os dados inseridos ou são inválidos ou estão incorretos");
				alert.show();
				throw new DadosInvalidos("Erro ao autenticar suas credenciais.");
			}
			if( aluno != null ) {
				if( AutenticadorDeUsuario.autenticar(aluno, senha, login) ) {
					UsuarioAtual.setUsuario(aluno);
					login();
				}
				else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Erro ao autenticar seus dados");
					alert.setContentText("Os dados inseridos ou são inválidos ou estão incorretos");
					alert.show();
					throw new DadosInvalidos("Erro ao autenticar suas credenciais.");
				}
			}
		}
		catch(DadosInvalidos e) {
			throw new DadosInvalidos(e.getMessage());
		}
	}
	
	public static void fecha() {		
		Login.getStage().close();
	}
	public static void login() {
		try {
			ViewFactory.createPrincipal().start(new Stage());
			fecha();
		}
		catch(Exception ex) {
			throw new ViewException( ex.getMessage() );
		}
	}
		
}
