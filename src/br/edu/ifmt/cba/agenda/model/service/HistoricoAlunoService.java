package br.edu.ifmt.cba.agenda.model.service;

import br.edu.ifmt.cba.agenda.model.entities.Aluno;
import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.entities.Nota;
import br.edu.ifmt.cba.agenda.model.exception.DadosInvalidos;

public abstract class HistoricoAlunoService {
	
	public static void matricularEmDisciplina(Aluno aluno, Disciplina disciplina) {
		try {
			if( disciplina.getId() != null ) {
				aluno.getDisciplinas().add(disciplina);
			}
			else {
				throw new DadosInvalidos("Os dados da disciplina não são válidos"); 
			}
		} catch (DadosInvalidos e) {
			throw new DadosInvalidos(e.getMessage());
		}
	}
	
	public static void adicionaNovaNota(Aluno aluno, Disciplina disciplina, Nota nota) {
		for(Disciplina d : aluno.getDisciplinas() ) {
			if( d.equals(disciplina) ) {
				d.getNotas().add(nota);
			}
		}
	}
	
	public static Disciplina findDisciplinaByNome(Aluno aluno, String nome ) {
		
		for(Disciplina d : aluno.getDisciplinas() ) {
			if( d.getNome().equals(nome)) {
				return d;
			}
		}
		return null;
	}
	
	public static int atualizaFaltas(Aluno aluno, Disciplina disciplina, Integer faltas) {
		for( Disciplina d : aluno.getDisciplinas() ) {
			if( d.equals(disciplina) ) {
				d.setFaltas(faltas);
				return d.getFaltas();
			}
		}
		return 0;
	}
}
