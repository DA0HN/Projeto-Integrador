package br.edu.ifmt.cba.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifmt.cba.application.Login;
import br.edu.ifmt.cba.application.Principal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController implements Initializable{

    @FXML private TextField txEmail;
    @FXML private PasswordField txSenha;
    @FXML private Button btSair;
    @FXML private Button btEntrar;
	
	@Override public void initialize(URL arg0, ResourceBundle arg1) {
		btEntrar.setOnMouseClicked( (MouseEvent e) -> { 
			loginCondition();
		});
		
		btSair.setOnMouseClicked( (MouseEvent e) -> { fecha(); });
		
		btEntrar.setOnKeyPressed( (KeyEvent e) -> {
			if( e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE ) {
				loginCondition();
			}
		});
		
		btSair.setOnKeyPressed( (KeyEvent e) -> {
			if( e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE) {
				fecha();
			}
		});
		
		txEmail.setOnKeyPressed( (KeyEvent e) -> {
			if( e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE ) {
				loginCondition();
			}
		});
		
		txSenha.setOnKeyPressed( (KeyEvent e) -> {
			if( e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE) {
				loginCondition();
			}
		});
	}
	public void loginCondition() {
		if( txEmail.getText().equals("root") && txSenha.getText().equals("root")) {
			logar();
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Eu sou");
			alert.setHeaderText("Daleste");
			alert.setContentText("Cheguei mas to saindo fora");
			alert.show();
		}
	}
	
	public static void fecha() {		
		Login.getStage().close();
	}
	public static void logar() {
		Principal p = new Principal();
		try {
			p.start(new Stage());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		fecha();
	}
	
	
}
