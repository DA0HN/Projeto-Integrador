package br.edu.ifmt.cba.agenda.gui.view;

import java.io.IOException;

import br.edu.ifmt.cba.agenda.gui.enums.Path;
import br.edu.ifmt.cba.agenda.gui.exceptions.ViewException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ListarDisciplinas extends Application {
	private static Stage stage;
	
	@Override public void start(Stage stage) {
		try {
			setStage(stage);
			Parent root = FXMLLoader.load(getClass().getResource(Path.LISTAR_DISCIPLINAS.getValue()));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(Path.STYLE.getValue()).toExternalForm());
			stage.show();
		}
		catch(IOException e) {
			//e.printStackTrace();
			throw new ViewException("Erro ao tentar startar a tela ListarDisciplinas.");
		}
	}
	
	public static Stage getStage() {
		return stage;
	}
	public static void setStage(Stage stage) {
		ListarDisciplinas.stage = stage;
	}
}
