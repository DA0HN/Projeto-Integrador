package br.edu.ifmt.cba.application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Disciplinas extends Application {
	private static Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override public void start(Stage stage) {
		try {
			setStage(stage);
			Parent root = FXMLLoader.load(getClass().getResource("../view/AnchorPaneDisciplinas.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../style/modena_dark.css").toExternalForm());
			stage.show();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getStage() {
		return stage;
	}
	public static void setStage(Stage stage) {
		Disciplinas.stage = stage;
	}
}
