package br.edu.ifmt.cba.agenda.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifmt.cba.agenda.gui.constraints.Constraints;
import br.edu.ifmt.cba.agenda.gui.path.Path;
import br.edu.ifmt.cba.agenda.gui.utils.Alerta;
import br.edu.ifmt.cba.agenda.gui.utils.ButtonEvent;
import br.edu.ifmt.cba.agenda.gui.view.AlterarNota;
import br.edu.ifmt.cba.agenda.gui.view.Principal;
import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.entities.Nota;
import br.edu.ifmt.cba.agenda.model.repository.ServiceFactory;
import br.edu.ifmt.cba.agenda.model.resource.UsuarioAtual;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class AlterarNotaController implements Initializable{

	private Nota nota;
	private Disciplina disciplina;
	
    @FXML private TextField txNota;
    @FXML private Button btAlterar;

    @FXML void btAlterarOnClicked(MouseEvent event) {
    	alterarNota();
    }

    @FXML void btAlterarOnKeyPressed(KeyEvent event) {
    	if( ButtonEvent.hasUserConfirmed(event) ) {
    		alterarNota();
    	}
    }

    @FXML void txNotaOnKeyPressed(KeyEvent event) {
    	if( ButtonEvent.hasUserConfirmed(event) ) {
    		alterarNota();
    	}
    }
	
    private void alterarNota() {
    	this.disciplina = NotasController.getDisciplinaAtual();	
    	if( this.disciplina == null ) {
    		Alerta.mostrar(AlertType.ERROR, "Nenhuma disciplina selecionada",
    				"nenhuma disciplina foi selecionada para adicionar a nova nota.");
    		fechar();
    	}
    	Double novaNota = Double.parseDouble(txNota.getText());
    	
    	if( novaNota != null && (novaNota >= 0 && novaNota <= 10) ) {
    		boolean isChanged = ServiceFactory.createHistoricoDao().
    				updateNota(UsuarioAtual.getUsuario(),
    						this.disciplina, this.nota.getId(), novaNota);
    		if( isChanged ) {
    			Alerta.mostrar(AlertType.INFORMATION, "Sucesso",
    					"Sucesso ao alterar a nota "
    					+ nota + " na disciplina de " + disciplina.getNome());
    		}
    		else {
    			Alerta.mostrar(AlertType.ERROR, "Erro", "Não foi possivel alterar sua nota.");
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
    	AlterarNota.getStage().close();
    }
    
	@Override public void initialize(URL arg0, ResourceBundle arg1) {
		Constraints.setTextFieldDouble(txNota);
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
}
