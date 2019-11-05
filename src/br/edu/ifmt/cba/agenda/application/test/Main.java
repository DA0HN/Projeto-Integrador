package br.edu.ifmt.cba.agenda.application.test;

import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.recurso.UsuarioAtual;
import br.edu.ifmt.cba.agenda.model.repositorio.DaoFactory;

public class Main {

	public static void main(String[] args) {
		
		UsuarioAtual.setUsuario( DaoFactory.createAlunoDao().findById(1));
		var a = UsuarioAtual.getUsuario();
		
		for(Disciplina d : a.getDisciplinas()) {
			System.out.println(d);
		}
		
	}
}
