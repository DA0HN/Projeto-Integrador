package br.edu.ifmt.cba.agenda.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifmt.cba.agenda.gui.utils.Alerta;
import br.edu.ifmt.cba.agenda.gui.utils.ButtonEvent;
import br.edu.ifmt.cba.agenda.gui.view.VincularDisciplina;
import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.recurso.UsuarioAtual;
import br.edu.ifmt.cba.agenda.model.repositorio.DaoFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class VincularDisciplinasController implements Initializable {

	@FXML private ComboBox<Disciplina> comboDisciplinas;
    @FXML private Button btVincular;
	
    private ObservableList<Disciplina> observableDisciplina;
    List<Disciplina> todasDisciplinas = new ArrayList<>();
	
	@Override public void initialize(URL arg0, ResourceBundle arg1) {
		carregarTodasDisciplinas();
		
	}

    private void carregarTodasDisciplinas() {
    	todasDisciplinas = DaoFactory.createDisciplinaDao().findAll();
    	observableDisciplina = FXCollections.observableArrayList(todasDisciplinas);
    	comboDisciplinas.setItems(observableDisciplina);
    }

    private void vincularDisciplina(Disciplina d) {
    	if(DaoFactory.createHistoricoDao().matricularEmDisciplina(UsuarioAtual.getUsuario(), d)) {
    		fechar();
    		Alerta.mostrar(AlertType.INFORMATION, "Sucesso ao vincular a disciplina", "Disciplina vinculada ao usuario " + UsuarioAtual.getUsuario().getNome() + " com sucesso.");
    	}
    	else {
    		fechar();
    		Alerta.mostrar(AlertType.ERROR, "Ocorreu um problema", "Não foi possivel fazer a matricula nessa disciplina.");
    	}
    }
    
	@FXML void btVincularOnClicked(MouseEvent event) {
		vincularDisciplina(comboDisciplinas.getValue());
    }
	
	@FXML void btVincularOnKeyPressed(KeyEvent event) {
		if( ButtonEvent.hasUserConfirmed(event) ) {
			vincularDisciplina(comboDisciplinas.getValue());
		}
	}
	
	private void fechar() {
		VincularDisciplina.getStage().close();
	}
	
}
