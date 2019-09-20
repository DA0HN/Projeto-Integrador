package br.edu.ifmt.cba.agenda.gui.view;
	
import java.io.IOException;

import br.edu.ifmt.cba.agenda.gui.enums.Path;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application {
	
	private static Stage stage;

	@Override public void start(Stage stage) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource(Path.LOGIN.getValue()));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(Path.STYLE.getValue()).toExternalForm());
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
