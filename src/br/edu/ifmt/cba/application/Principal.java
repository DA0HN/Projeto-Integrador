package br.edu.ifmt.cba.application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application{
	
	private static Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override public void start(Stage stage) {
		try {
			setStage(stage);
			Parent root = FXMLLoader.load(getClass().getResource("../view/Principal.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../style/modena_dark.css").toExternalForm());
			stage.setTitle("Principal");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}	

	}
	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		Principal.stage = stage;
	}
}
