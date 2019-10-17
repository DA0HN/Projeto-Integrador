package br.edu.ifmt.cba.agenda.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.entities.Nota;
import br.edu.ifmt.cba.agenda.model.repositorio.DaoFactory;
import br.edu.ifmt.cba.agenda.model.service.UsuarioAtual;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class NotasController implements Initializable{
	
	// parte esquerda da view
	@FXML private TableView<Disciplina> tableDisciplinas;
    @FXML private TableColumn<Disciplina, String> columnDisciplina;
    @FXML private TableColumn<Disciplina, Double> columnMedia;
    // parte direita da view
    @FXML private TableView<Nota> tableNotas;
    @FXML private TableColumn<Nota, Double> columnNotas;
    @FXML private Label labelNomeDisciplina;
    @FXML private Label labelMedia;
	
    private ObservableList<Disciplina> observableDisciplina;
    		
    private void initTableDisciplinas() {
    	columnDisciplina.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	columnMedia.setCellValueFactory(new PropertyValueFactory<>("media"));
    	atualizaTabelaDisciplinas();
    }
    
	private void atualizaTabelaDisciplinas() {
		List<Disciplina> lista = UsuarioAtual.getUsuario().getDisciplinas();
		for(Disciplina d:lista) {
			System.out.println(d);
		}
		observableDisciplina = FXCollections.observableArrayList(lista);
		tableDisciplinas.setItems(observableDisciplina);
	}

	@Override public void initialize(URL arg0, ResourceBundle arg1) {
		initTableDisciplinas();
		tableDisciplinas.getSelectionModel().selectedItemProperty().addListener( (observable,oldValue,newValue) -> disciplinaSelecionada(newValue));
	}

	private void disciplinaSelecionada(Disciplina d) {
		d = tableDisciplinas.getSelectionModel().getSelectedItem();
			System.out.println(d);
			labelNomeDisciplina.setText(d.getNome());
			labelMedia.setText(String.format("%.2f", d.getMedia()));
	}
	
}
