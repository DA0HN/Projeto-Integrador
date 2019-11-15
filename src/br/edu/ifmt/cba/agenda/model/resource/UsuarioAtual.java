package br.edu.ifmt.cba.agenda.model.resource;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifmt.cba.agenda.model.entities.Aluno;
import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.entities.Nota;
import br.edu.ifmt.cba.agenda.model.repository.ServiceFactory;

public class UsuarioAtual {

	private static Aluno usuario = null;

	private UsuarioAtual() {}
	
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
	
	public static List<Nota> atualizaNotasDaDisciplina(Disciplina disciplina){
		List<Nota> lista = new ArrayList<Nota>();
		
		lista = ServiceFactory.createHistoricoDao().findNotasByDisciplina(UsuarioAtual.getUsuario(), disciplina);
		
		return lista;
	}
	
	private static void atualizaDisciplinas() {
		usuario.setDisciplinas( ServiceFactory.createHistoricoDao().findDisciplinasByAluno(usuario.getId()) );
	}

	private static void popularMedia() {
		List<Disciplina> lista = UsuarioAtual.usuario.getDisciplinas();
		for(Disciplina d : lista) {
			d.calcularMedia();	
		}
	}

	public static void popularNotas() {
		if( UsuarioAtual.usuario == null ) {
			throw new IllegalStateException("Erro ao popular notas");
		}
		List<Disciplina> lista = UsuarioAtual.usuario.getDisciplinas();
		for(Disciplina d : lista) {
			d.setNotas( ServiceFactory.createHistoricoDao().findNotasByDisciplina(usuario, d));
		}
	}
	
	private static void popularFaltas() {
		if( UsuarioAtual.usuario == null ) {
			throw new IllegalStateException("Erro ao popular faltas");
		}
		List<Disciplina> lista = UsuarioAtual.usuario.getDisciplinas();
		for(Disciplina d : lista) {
			d.setFaltas( ServiceFactory.createHistoricoDao().getFalta(usuario, d) );
		}
	}

	public static void terminarSessao() {
		UsuarioAtual.usuario = null;
	}
	
}
