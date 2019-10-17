package br.edu.ifmt.cba.agenda.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifmt.cba.agenda.gui.utils.ButtonEvent;
import br.edu.ifmt.cba.agenda.gui.view.ViewFactory;
import br.edu.ifmt.cba.agenda.model.service.UsuarioAtual;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuPrincipalController implements Initializable{

	@FXML private Label labelAluno;
	@FXML private Label labelMatricula;
	@FXML private Button btVincularDisciplina;

	@FXML void btVincularAlunoOnKeyPressed(KeyEvent e) {
		if( ButtonEvent.hasUserConfirmed(e) ) {
			ViewFactory.createVinculoDisciplina().start(new Stage());
		}
	}
	
	@FXML void btVincularAlunoOnKeyClicked() {
		ViewFactory.createVinculoDisciplina().start(new Stage());
	}

	@Override public void initialize(URL arg0, ResourceBundle arg1) {
		mostrarDados();
	}

	void mostrarDados() {
		labelAluno.setText(UsuarioAtual.getUsuario().getNome());
		labelMatricula.setText(UsuarioAtual.getUsuario().getMatricula());
	}
	
}
