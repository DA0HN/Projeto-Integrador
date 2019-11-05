package br.edu.ifmt.cba.agenda.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifmt.cba.agenda.gui.enums.Path;
import br.edu.ifmt.cba.agenda.gui.exceptions.ViewException;
import br.edu.ifmt.cba.agenda.gui.view.Login;
import br.edu.ifmt.cba.agenda.gui.view.Principal;
import br.edu.ifmt.cba.agenda.gui.view.ViewFactory;
import br.edu.ifmt.cba.agenda.model.recurso.UsuarioAtual;
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
    @FXML private Button btPrincipal;
    @FXML private Button btCadastrarDisciplina; 
    
    @FXML private AnchorPane principalController;
	private Object viewController;
	
    @FXML public void handleButtonDisciplina() {
    	trocarAnchorPane(Path.LISTAR_DISCIPLINAS.getValue());
    }
    
    @FXML public void handleButtonNotas() {
	   trocarAnchorPane(Path.NOTAS.getValue());
    }
    
    @FXML public void handleButtonCadastrarDisciplina() {
    	trocarAnchorPane(Path.CADASTRAR_DISCIPLINAS.getValue());
    }
    
    @FXML public void handleButtonPrincipal() {
    	trocarAnchorPane(Path.MENU_PRINCIPAL.getValue());
    }
    
    public void trocarAnchorPane(String str) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource(str));
			AnchorPane newAnchorPane = (AnchorPane) loader.load();
			setViewController(loader.getController());
			principalController.getChildren().setAll(newAnchorPane);
		} catch (IOException e) {
			throw new ViewException( e.getMessage() );
		}
    }
    
    @FXML public void handleButtonTrocarUsuario() {
    	UsuarioAtual.terminarSessao();
    	Login login = ViewFactory.createLogin();
    	login.start(new Stage());
    	fecha();
    }
    public static void fecha() {
    	Principal.getStage().close();
    }
	
	public Object getViewController() {
		return viewController;
	}

	public void setViewController(Object viewController) {
		this.viewController = viewController;
	}

	@Override public void initialize(URL arg0, ResourceBundle arg1) {}
    
}
