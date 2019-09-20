package br.edu.ifmt.cba.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifmt.cba.application.Login;
import br.edu.ifmt.cba.application.Principal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PrincipalController implements Initializable{
	
	@FXML private Button btTrocarUsuario;
	@FXML private Button btDisciplina;
    @FXML private Button btNotas;
    @FXML private Button btHorarios;
    @FXML private Button btCadastrarDisciplina; 
    
    @FXML private AnchorPane anchorPanePrincipal;
   
    @Override public void initialize(URL arg0, ResourceBundle arg1) {
    	
	}
	
    @FXML public void handleButtonDisciplina() {
    	trocarAnchorPane("../view/AnchorPaneDisciplinas.fxml");
    }
    
    @FXML public void handleButtonNotas() {
	   trocarAnchorPane("../view/AnchorPaneNotas.fxml");
    }
    
    @FXML public void handleButtonCadastrarDisciplina() {
    	trocarAnchorPane("../view/AnchorPaneCadastrarDisciplinas.fxml");
    }
    
    @FXML public void handleButtonHorarios() {
    	trocarAnchorPane("../view/AnchorPaneHorarios.fxml");
    }
    
    public void trocarAnchorPane(String str) {
    	try {
			AnchorPane newAnchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource(str));
			anchorPanePrincipal.getChildren().setAll(newAnchorPane);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    
    public void handleButtonTrocarUsuario() {
    	Login login = new Login();
    	login.start(new Stage());
    	fecha();
    }
    public static void fecha() {
    	Principal.getStage().close();
    }
    
}
