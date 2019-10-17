package br.edu.ifmt.cba.agenda.gui.view;

import java.io.IOException;

import br.edu.ifmt.cba.agenda.gui.enums.Path;
import br.edu.ifmt.cba.agenda.gui.exceptions.ViewException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CriaConta extends Application{

	private static Stage stage;
	
	@Override public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(Path.CRIA_CONTA.getValue()));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(Path.STYLE.getValue()).toExternalForm());
			stage.setTitle("Criar nova conta");
			stage.setScene(scene);
			stage.setResizable(false);
			setStage(stage);
			stage.show();
		} catch (IOException e) {
			throw new ViewException("Erro ao tentar startar a tela CriaConta.");
		}
	}

	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage stage) {
		CriaConta.stage = stage;
	}
	
}
