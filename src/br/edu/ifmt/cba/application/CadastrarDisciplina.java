package br.edu.ifmt.cba.application;

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
			Parent root = FXMLLoader.load(getClass().getResource("../view/AnchorPaneCadastrarDisciplinas.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../style/modena_dark.css").toExternalForm());
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
	
	public static void main(String[] args) {
		launch(args);
	}

}
