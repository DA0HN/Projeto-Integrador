package br.edu.ifmt.cba.agenda.model.service;

import java.util.List;

import br.edu.ifmt.cba.agenda.model.entities.Aluno;
import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.repositorio.DaoFactory;

public class UsuarioAtual {

	private static Aluno usuario = null;

	public static Aluno getUsuario() {
		return usuario;
	}

	public static void setUsuario(Aluno usuario) {
		if( UsuarioAtual.usuario != null ) {
			throw new IllegalStateException("Não é possivel alterar o usuário atual");
		}
		UsuarioAtual.usuario = usuario;
		atualizaDisciplinas();
		popularNotas();
		popularFaltas();
		popularMedia();
	}
	
	public static void atualizarDados() {
		atualizaDisciplinas();
		popularNotas();
		popularFaltas();
		popularMedia();
	}
	
	private static void atualizaDisciplinas() {
		usuario.setDisciplinas( DaoFactory.createHistoricoDao().findDisciplinasByAluno(usuario.getId()) );
	}

	private static void popularMedia() {
		List<Disciplina> lista = UsuarioAtual.usuario.getDisciplinas();
		for(Disciplina d : lista) {
			d.calcularMedia();	
//			System.out.println(d.getMedia());
		}
	}

	public static void popularNotas() {
		if( UsuarioAtual.usuario == null ) {
			throw new IllegalStateException("Erro ao popular notas");
		}
		List<Disciplina> lista = UsuarioAtual.usuario.getDisciplinas();
		for(Disciplina d : lista) {
//			System.out.println(d);
			d.setNotas( DaoFactory.createHistoricoDao().findNotasByDisciplina(usuario, d));
		}
	}
	
	private static void popularFaltas() {
		if( UsuarioAtual.usuario == null ) {
			throw new IllegalStateException("Erro ao popular faltas");
		}
		List<Disciplina> lista = UsuarioAtual.usuario.getDisciplinas();
		for(Disciplina d : lista) {
//			System.out.println(d);
			d.setFaltas( DaoFactory.createHistoricoDao().getFalta(usuario, d) );
		}
	}

	public static void terminarSessao() {
		UsuarioAtual.usuario = null;
	}
	
}
