package br.edu.ifmt.cba.agenda.model.service;

import java.util.List;

import br.edu.ifmt.cba.agenda.model.entities.Aluno;
import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.repositorio.DaoFactory;

public class UsuarioAtual {

	private static Aluno usuario;

	public static Aluno getUsuario() {
		return usuario;
	}

	public static void setUsuario(Aluno usuario) {
		if( UsuarioAtual.usuario != null ) {
			throw new IllegalStateException("Não é possivel alterar o usuário atual");
		}
		usuario.setDisciplinas( DaoFactory.createHistoricoDao().findDisciplinasByAluno(usuario.getId()));
		popularNotas(usuario);
		popularFaltas(usuario);
		UsuarioAtual.usuario = usuario;
	}
	
	private static void popularNotas(Aluno usuario) {
		List<Disciplina> lista = usuario.getDisciplinas();
		for(Disciplina d : lista) {
			d.setNotas( DaoFactory.createHistoricoDao().findNotasByDisciplina(usuario, d));
		}
	}
	
	private static void popularFaltas(Aluno usuario) {
		List<Disciplina> lista = usuario.getDisciplinas();
		for(Disciplina d : lista) {
			d.setFaltas( DaoFactory.createHistoricoDao().getFalta(usuario, d) );
		}
		
	}
	
}
