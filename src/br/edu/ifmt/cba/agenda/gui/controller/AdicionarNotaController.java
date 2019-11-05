package br.edu.ifmt.cba.agenda.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifmt.cba.agenda.gui.enums.Path;
import br.edu.ifmt.cba.agenda.gui.utils.Alerta;
import br.edu.ifmt.cba.agenda.gui.utils.ButtonEvent;
import br.edu.ifmt.cba.agenda.gui.view.AdicionarNota;
import br.edu.ifmt.cba.agenda.gui.view.Principal;
import br.edu.ifmt.cba.agenda.model.constraints.Constraints;
import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.repositorio.DaoFactory;
import br.edu.ifmt.cba.agenda.model.service.UsuarioAtual;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class AdicionarNotaController implements Initializable{

	private Disciplina disciplina;
	
	@FXML private TextField txNota;
    @FXML private Button btAdicionar;
    
    @Override public void initialize(URL arg0, ResourceBundle arg1) {
    	Constraints.setTextFieldDouble(txNota);
    }

	@FXML void btAdicionarOnClicked(MouseEvent event) {
    	adicionarNota();
    }

    @FXML void btAdicionarOnKeyPressed(KeyEvent event) {
    	if( ButtonEvent.hasUserConfirmed(event) ) {
    		adicionarNota();
    		
    	}
    }

    @FXML void txNotaOnKeyPressed(KeyEvent event) {
    	if( ButtonEvent.hasUserConfirmed(event) ) {
    		adicionarNota();
    	}
    }
	
    private void adicionarNota() {
    	disciplina = NotasController.getDisciplinaAtual();
    	if( disciplina == null) {
    		Alerta.mostrar(AlertType.ERROR, "Nenhuma disciplina selecionada", "nenhuma disciplina foi selecionada para adicionar a nova nota.");
    		fechar();
    	}
    	
    	if( txNota.getText().equals("") || txNota.getText().isBlank() || txNota.getText().isEmpty() ) {
    		return;
    	}
    	
    	Double nota = Double.parseDouble(txNota.getText()); 
    	
    	
    	if( nota != null && ( nota >= 0 && nota <= 10) ) {
    		boolean isSave = DaoFactory.createHistoricoDao().saveNota(UsuarioAtual.getUsuario(), disciplina, nota);
    		if( isSave ) {
    			Alerta.mostrar(AlertType.INFORMATION, "Sucesso", "Sucesso ao salvar a nota " + nota + " na disciplina de " + disciplina.getNome());
    		}
    		else {
    			Alerta.mostrar(AlertType.ERROR, "Erro", "Não foi possivel salvar sua nota.");
    		}
    		atualizaViewNotas();
    		fechar();
    	}
    	else {
    		Alerta.mostrar(AlertType.ERROR, "Erro", "A nota inserida não é válida");
    		txNota.setText("");
    	}
    }
    
    private void atualizaViewNotas() {
    	PrincipalController c = Principal.getPrincipalController();
    	c.trocarAnchorPane(Path.NOTAS.getValue());
    	NotasController n = (NotasController) c.getViewController();
    	UsuarioAtual.atualizarDados();
    	n.atualizar();
    }
    
    private void fechar() {
    	AdicionarNota.getStage().close();
    }
	
}
