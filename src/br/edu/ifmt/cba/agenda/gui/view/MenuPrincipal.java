package br.edu.ifmt.cba.agenda.gui.view;

import java.io.IOException;

import br.edu.ifmt.cba.agenda.gui.enums.Path;
import br.edu.ifmt.cba.agenda.gui.exceptions.ViewException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuPrincipal extends Application{
	/* falta implementar interface e o fxml do MenuPrincipal que possui dados do Aluno e a capacidade
	 * de matricular o aluno em uma disciplina 
	 * */
	private static Stage stage;
	
	@Override public void start(Stage stage) throws Exception {
		try {
			setStage(stage);
			Parent root = FXMLLoader.load(getClass().getResource(Path.MENU_PRINCIPAL.getValue()));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(Path.STYLE.getValue()).toExternalForm());
			stage.show();
		}
		catch(IOException e) {
			throw new ViewException("Erro ao tentar startar MenuPrincipal");
		}
	}
	
	public static Stage getStage() {
		return stage;
	}
	public static void setStage(Stage stage) {
		MenuPrincipal.stage = stage;
	}
	
}
