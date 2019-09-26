package br.edu.ifmt.cba.agenda.model.service;

import java.sql.Connection;
import java.util.List;

import br.edu.ifmt.cba.agenda.model.entities.Aluno;
import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.entities.Nota;
import br.edu.ifmt.cba.agenda.model.repository.HistoricoAlunoDao;

public class HistoricoAlunoService implements HistoricoAlunoDao {

	protected enum HistoricoAlunoSQL{
		
		SAVE_FALTA(""),
		SAVE_NOTA(""),	// só salva notas se for em uma matéria que o aluno frequenta
		UPDATE_NOTA(""),
		UPATE_FALTA(""),
		DELETE_NOTAS_BY_ID(""),
		DELETE_FALTAS_BY_ID(""),
		FIND_NOTAS_BY_DISCIPLINA(""),
		FIND_FALTAS_BY_DISCIPLINA(""),
		FIND_NOTAS("");
		
		private String value;
		
		HistoricoAlunoSQL(String value){
			this.value = value;
		}
		
		protected String getValue() {
			return value;
		}
	}
	
	private Connection conexao;
	
	public HistoricoAlunoService(Connection conexao){
		this.conexao = conexao;
	}

	@Override public void saveNota(Aluno aluno, Disciplina disciplina, Double nota) {
		// TODO Auto-generated method stub
		
	}

	@Override public void saveFalta(Aluno aluno, Disciplina disciplina, Integer falta) {
		// TODO Auto-generated method stub
		
	}

	@Override public void updateNota(Aluno aluno, Disciplina disciplina, Double nota) {
		// TODO Auto-generated method stub
		
	}

	@Override public void updateFalta(Aluno aluno, Disciplina disciplina, Integer falta) {
		// TODO Auto-generated method stub
		
	}

	@Override public void deleteNotaById(Aluno aluno, Disciplina disciplina) {
		// TODO Auto-generated method stub
		
	}

	@Override public void deleteFaltaById(Aluno aluno, Disciplina disciplina) {
		// TODO Auto-generated method stub
		
	}

	@Override public Integer findFaltaByDisciplina(Aluno aluno, Disciplina disciplina) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override public List<Nota> findNotasByDisciplina(Aluno aluno, Disciplina disciplina) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
