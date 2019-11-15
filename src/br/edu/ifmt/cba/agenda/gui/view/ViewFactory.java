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
	
	public static VincularDisciplina createVinculoDisciplina() {
		return new VincularDisciplina();
	}
	
	public static AdicionarNota createAdicionarNota() {
		return new AdicionarNota();
	}
	
	public static Notas createNotas() {
		return new Notas();
	}
	
	public static AlterarNota createAlterarNota() {
		return new AlterarNota();
	}
	public static AlterarFaltas createAlterarFaltas() {
		return new AlterarFaltas();
	}
}
