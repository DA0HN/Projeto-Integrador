package br.edu.ifmt.cba.agenda.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifmt.cba.agenda.gui.constraints.Constraints;
import br.edu.ifmt.cba.agenda.gui.path.Path;
import br.edu.ifmt.cba.agenda.gui.utils.Alerta;
import br.edu.ifmt.cba.agenda.gui.utils.ButtonEvent;
import br.edu.ifmt.cba.agenda.gui.view.AlterarFaltas;
import br.edu.ifmt.cba.agenda.gui.view.Principal;
import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.repository.ServiceFactory;
import br.edu.ifmt.cba.agenda.model.resource.UsuarioAtual;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class AlterarFaltasController implements Initializable{

	@FXML private Button btAdicionar;
    @FXML private Button btRetirar;
    @FXML private TextField txNumeroDeFaltas;
    
    private void adicionarFalta() {
    	Disciplina disciplina = ListarDisciplinasController.getDisciplinaAtual();
    	
    	if( disciplina == null ) {
    		Alerta.mostrar(AlertType.ERROR, "Nenhuma disciplina selecionada", "nenhuma disciplina foi selecionada para adicionar a nova nota.");
    		fechar();
    	}
    	
    	if( txNumeroDeFaltas.getText().equals("") || txNumeroDeFaltas.getText().isBlank() || txNumeroDeFaltas.getText().isEmpty()) {
    		return;
    	}
    	
    	Integer falta = Integer.parseInt(txNumeroDeFaltas.getText());
    	
    	if( falta > disciplina.getNumeroDeAulas() 
    			|| falta+disciplina.getFaltas() > disciplina.getNumeroDeAulas()) {
    		Alerta.mostrar(AlertType.ERROR, "O número de faltas inserido não é válido",
    				"O número de faltas inserido é maior que o número de aulas.");
    		return;
    	}
    	
    	boolean isSave = ServiceFactory
    			.createHistoricoDao()
    			.saveFalta(UsuarioAtual.getUsuario(), disciplina, falta);
    	
    	if( isSave ) {
			Alerta.mostrar(AlertType.INFORMATION, "Sucesso",
					"Sucesso ao salvar a falta " + falta +
					" na disciplina de " + disciplina.getNome());
		}
		else {
			Alerta.mostrar(AlertType.ERROR, "Erro",
					"Não foi possivel salvar sua falta.");
		}
    	atualizaViewListarDisciplinas();
    	fechar();
    }
    
    private void retirarFalta() {
    	Disciplina disciplina = ListarDisciplinasController.getDisciplinaAtual();
    	if( disciplina == null ) {
    		Alerta.mostrar(AlertType.ERROR, "Nenhuma disciplina selecionada",
    				"nenhuma disciplina foi selecionada para adicionar a nova nota.");
    		fechar();
    	}
    	
    	if( txNumeroDeFaltas.getText().equals("") || txNumeroDeFaltas.getText().isBlank() || txNumeroDeFaltas.getText().isEmpty()) {
    		return;
    	}
    	Integer faltasRetiradas = Integer.parseInt(txNumeroDeFaltas.getText());
    	if( faltasRetiradas > disciplina.getFaltas() 
    			|| disciplina.getFaltas() - faltasRetiradas < 0 ) {
    		Alerta.mostrar(AlertType.ERROR, "O número de faltas inserido não é válido",
    				"O número de faltas inserido é maior que o número de faltas.");
    		return;
    	}
    	boolean isSave = ServiceFactory
    			.createHistoricoDao()
    			.deleteFalta(UsuarioAtual.getUsuario(), disciplina, faltasRetiradas);
    	if( isSave ) {
			Alerta.mostrar(AlertType.INFORMATION, "Sucesso",
					"Sucesso ao retirar " + faltasRetiradas +
					" da disciplina de " + disciplina.getNome());
		}
		else {
			Alerta.mostrar(AlertType.ERROR, "Erro",
					"Não foi possivel retirar sua falta.");
		}
    	atualizaViewListarDisciplinas();
    	fechar();
    }
    private void atualizaViewListarDisciplinas(){
    	PrincipalController c = Principal.getPrincipalController();
    	c.trocarAnchorPane(Path.LISTAR_DISCIPLINAS.getValue());
    	ListarDisciplinasController l = (ListarDisciplinasController) c.getViewController();
    	UsuarioAtual.atualizarDados();
    	l.atualizar();
    }
    
    @FXML void btAdicionarOnClicked(MouseEvent event) {
    	adicionarFalta();	
    }

    @FXML void btAdicionarOnkeyPressed(KeyEvent event) {
    	if( ButtonEvent.hasUserConfirmed(event)) {
    		adicionarFalta();
    	}
    }

    @FXML void btRetirarOnClicked(MouseEvent event) {
    	retirarFalta();
    }

    @FXML void btRetirarOnKeyPressed(KeyEvent event) {
    	if( ButtonEvent.hasUserConfirmed(event)) {
    		retirarFalta();
    	}
    }
    private void fechar() {
    	AlterarFaltas.getStage().close();
    }
	@Override public void initialize(URL arg0, ResourceBundle arg1) {
		Constraints.setTextFieldDouble(txNumeroDeFaltas);
	}
	
}
