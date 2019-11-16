package br.edu.ifmt.cba.agenda.gui.path;

public enum Path {

	LOGIN("../fxml/Login.fxml"), 
	LISTAR_DISCIPLINAS("../fxml/ListarDisciplinas.fxml"),
	CADASTRAR_DISCIPLINAS("../fxml/CadastrarDisciplinas.fxml"), 
	PRINCIPAL("../fxml/Principal.fxml"),
	NOTAS("../fxml/Notas.fxml"),
	MENU_PRINCIPAL("../fxml/MenuPrincipal.fxml"),
	HORARIOS("../fxml/Horarios.fxml"),
	CRIA_CONTA("../fxml/CriaConta.fxml"),
	CRIA_VINCULO("../fxml/VincularDisciplina.fxml"),
	ADICIONAR_NOTA("../fxml/AdicionarNota.fxml"),
	ALTERAR_NOTA("../fxml/AlterarNota.fxml"),
	ALTERAR_FALTA("../fxml/AlterarFaltas.fxml"),
	ICON("../images/ifmt_logo.png"),
	STYLE("../style/modena_dark.css");
	private String value;
	
	Path(String value){
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
}
