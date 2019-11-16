package br.edu.ifmt.cba.agenda.gui.view;

import java.io.IOException;

import br.edu.ifmt.cba.agenda.gui.path.Path;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VincularDisciplina extends Application{

	private static Stage stage;
	
	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		VincularDisciplina.stage = stage;
	}

	@Override public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(Path.CRIA_VINCULO.getValue()));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(Path.STYLE.getValue()).toExternalForm());
			stage.setScene(scene);
			stage.setResizable(false);
			setStage(stage);
			stage.show();
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
//			throw new ViewException("Erro ao tentar startar a tela VincularDisciplina");
		}
		
	}

}
