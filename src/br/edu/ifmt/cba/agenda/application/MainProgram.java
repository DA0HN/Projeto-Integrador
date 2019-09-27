package br.edu.ifmt.cba.agenda.application;

import br.edu.ifmt.cba.agenda.gui.view.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainProgram extends Application {

	@Override public void start(Stage stage) {
		ViewFactory.createLogin().start(new Stage());
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
