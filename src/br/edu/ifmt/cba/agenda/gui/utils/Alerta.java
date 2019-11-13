package br.edu.ifmt.cba.agenda.gui.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerta {

	public static void mostrar(AlertType tipo, String titulo, String conteudo) {
		var alerta = new Alert(tipo);
		alerta.setHeaderText(titulo);
		alerta.setContentText(conteudo);
		alerta.show();
	}

}
