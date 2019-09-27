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
	
	public static void adicionaNovaNota(Aluno aluno, Nota nota) {
		
	}
	
	public static void findDisciplinaByNome() {
		
	}
	
}
