package br.edu.ifmt.cba.agenda.gui.view;

import java.io.IOException;

import br.edu.ifmt.cba.agenda.gui.controller.AlterarNotaController;
import br.edu.ifmt.cba.agenda.gui.enums.Path;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlterarNota extends Application {
	
	private static Stage stage;
	private AlterarNotaController controller;

	public static Stage getStage() {
		return AlterarNota.stage;
	}

	public void setStage(Stage stage) {
		AlterarNota.stage = stage;
	}
	
	public AlterarNotaController getController() {
		return controller;
	}

	public void setController(AlterarNotaController controller) {
		this.controller = controller;
	}

	@Override public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Path.ALTERAR_NOTA.getValue()));
			Parent root = loader.load();
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(Path.STYLE.getValue()).toExternalForm());
			
			setController(loader.getController());
			
			stage.setTitle("Alterar nota");
			stage.setScene(scene);
			
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
