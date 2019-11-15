package br.edu.ifmt.cba.agenda.gui.view;

import java.io.IOException;

import br.edu.ifmt.cba.agenda.gui.controller.AlterarFaltasController;
import br.edu.ifmt.cba.agenda.gui.enums.Path;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlterarFaltas extends Application{
	
	private static Stage stage;
	private AlterarFaltasController controller;
	
	public static Stage getStage() {
		return stage;
	}
	public static void setStage(Stage stage) {
		AlterarFaltas.stage = stage;
	}
	public AlterarFaltasController getController() {
		return controller;
	}
	public void setController(AlterarFaltasController controller) {
		this.controller = controller;
	}
	@Override public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Path.ALTERAR_FALTA.getValue()));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(Path.STYLE.getValue()).toExternalForm());
			setController(loader.getController());
			stage.setTitle("Alterar falta");
			stage.setScene(scene);
			// não deixa o usuário acessar a janela de trás
			stage.initOwner(Principal.getStage());
			stage.initModality(Modality.WINDOW_MODAL);
			
			setStage(stage);
			stage.show();
			
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
