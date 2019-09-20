package br.edu.ifmt.cba.application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application {
	
	private static Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override public void start(Stage stage) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../style/modena_dark.css").toExternalForm());
			stage.setTitle("Login");
			stage.setScene(scene);
			stage.setResizable(false);
			setStage(stage);
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
		Login.stage = stage;
	}
}
