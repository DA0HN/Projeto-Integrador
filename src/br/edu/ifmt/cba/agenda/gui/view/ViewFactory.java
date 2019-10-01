package br.edu.ifmt.cba.agenda.gui.view;

public class ViewFactory {
	
	public static Login createLogin() {
		return new Login();
	}
	
	public static Principal createPrincipal() {
		return new Principal();
	}
	
	public static ListarDisciplinas createListarDisciplinas() {
		return new ListarDisciplinas();
	}
	
	public static CadastrarDisciplina createCadastrarDisciplina() {
		return new CadastrarDisciplina();
	}
	
	public static CriaConta CriaConta() {
		return new CriaConta();
	}
}
