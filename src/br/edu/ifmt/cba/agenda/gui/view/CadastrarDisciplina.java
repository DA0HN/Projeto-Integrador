package br.edu.ifmt.cba.agenda.gui.view;

import br.edu.ifmt.cba.agenda.gui.enums.Path;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CadastrarDisciplina extends Application {

	private Stage stage;
	
	@Override public void start(Stage stage) {
		try {
			this.setStage(stage);
			Parent root = FXMLLoader.load(getClass().getResource(Path.CADASTRAR_DISCIPLINAS.getValue()));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(Path.STYLE.getValue()).toExternalForm());
			stage.setTitle("Cadastrar nova Disciplina");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch(Exception e) {
			e.getStackTrace();
		}
		
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
