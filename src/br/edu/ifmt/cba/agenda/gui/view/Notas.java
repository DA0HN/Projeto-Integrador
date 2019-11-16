package br.edu.ifmt.cba.agenda.gui.view;

import java.io.IOException;

import br.edu.ifmt.cba.agenda.gui.controller.NotasController;
import br.edu.ifmt.cba.agenda.gui.path.Path;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Notas extends Application{

	private static Stage stage;
	private static NotasController controller;
	@Override public void start(Stage stage) {
		try {
			setStage(stage);
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Path.NOTAS.getValue()));
			Parent root = loader.load();
			
			setController(loader.getController());
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(Path.STYLE.getValue()).toExternalForm());
			stage.show();
		}
		catch( IOException e) {
			System.out.println(e.getMessage());
		}
	}

	
	
	public static NotasController getController() {
		return controller;
	}

	private static void setController(NotasController controller) {
		Notas.controller = controller;
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		Notas.stage = stage;
	}
}
