package br.edu.ifmt.cba.agenda.gui.view;

import br.edu.ifmt.cba.agenda.gui.enums.Path;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CriaConta extends Application{

	private static Stage stage;
	
	
	@Override public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource(Path.CRIA_CONTA.getValue()));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource(Path.STYLE.getValue()).toExternalForm());
		stage.setTitle("Criar nova conta");
		stage.setScene(scene);
		stage.setResizable(false);
		setStage(stage);
		stage.show();
	}

	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage stage) {
		CriaConta.stage = stage;
	}
	
}
