package br.edu.ifmt.cba.agenda.model.recurso;

import br.edu.ifmt.cba.agenda.model.entities.Aluno;

public class AutenticadorDeUsuario {
	
	public static boolean validaSenha(String senha) {
		return !( senha == null || senha.isBlank() || senha.isEmpty() );
	}
	
	public static boolean validaLogin(String login) {
		return !( login == null ||login.isBlank() || login.isEmpty() );
	}
	
	public static boolean autenticar(Aluno aluno, String senha, String login) {
		return (aluno.getMatricula().equals(login) && aluno.getSenha().equals(senha));
	}
	
	public static boolean validaEntradas(String senha, String login) {
		return (validaSenha(senha) && validaLogin(login));
	}
	
}
