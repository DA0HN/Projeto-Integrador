package br.edu.ifmt.cba.agenda.gui.enums;

public enum Path {

	LOGIN("../fxml/Login.fxml"), 
	LISTAR_DISCIPLINAS("../fxml/ListarDisciplinas.fxml"),
	CADASTRAR_DISCIPLINAS("../fxml/CadastrarDisciplinas.fxml"), 
	PRINCIPAL("../fxml/Principal.fxml"),
	NOTAS("../fxml/Notas.fxml"),
	HORARIOS("../fxml/Horarios.fxml"),
	STYLE("../style/modena_dark.css");
	
	private String value;
	
	Path(String value){
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
}
