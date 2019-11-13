package br.edu.ifmt.cba.agenda.gui.view;

import java.io.IOException;

import br.edu.ifmt.cba.agenda.gui.enums.Path;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdicionarNota extends Application {

	private static Stage stage;
	
	@Override public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(Path.ADICIONAR_NOTA.getValue()));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(Path.STYLE.getValue()).toExternalForm());
			stage.setTitle("Adicionar nova nota");
			stage.setScene(scene);
			
			// seta o foco da janela
			stage.initOwner(Principal.getStage());
			stage.initModality(Modality.WINDOW_MODAL);
			
			setStage(stage);
			stage.show();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage stage) {
		AdicionarNota.stage = stage;
	}

}
