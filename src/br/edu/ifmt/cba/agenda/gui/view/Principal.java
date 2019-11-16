package br.edu.ifmt.cba.agenda.gui.view;

import java.io.IOException;

import br.edu.ifmt.cba.agenda.gui.controller.PrincipalController;
import br.edu.ifmt.cba.agenda.gui.exceptions.ViewException;
import br.edu.ifmt.cba.agenda.gui.path.Path;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Principal extends Application{
	
	private static Stage stage;
	private static PrincipalController controller;
	
	
	@Override public void start(Stage stage) {
		try {
			setStage(stage);
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Path.PRINCIPAL.getValue()));
			Parent root = loader.load();
			controller = loader.getController();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(Path.STYLE.getValue()).toExternalForm());
			stage.setTitle("Principal");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		} 
		catch (IOException e) {
			throw new ViewException("Erro ao tentar startar a tela Principal.");
		}	

	}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static PrincipalController getPrincipalController() {
		return controller;
	}
	
	public static void setStage(Stage stage) {
		Principal.stage = stage;
	}
}
