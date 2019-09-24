package br.edu.ifmt.cba.agenda.model.service;

import java.sql.Connection;
import java.util.List;

import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.repository.DisciplinaDao;

public class DisciplinaService implements DisciplinaDao {

	private enum DisciplinaSQL {
		
		SAVE(""),
		UPDATE(""),
		DELETE_BY_ID(""),
		FIND_BY_ID(""),
		FIND_BY_NOME(""),
		FIND_ALL("");
		
		private String value;
		
		DisciplinaSQL(String value){
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
	}
	
	private Connection conexao;
	
	public DisciplinaService(Connection conexao) {
		this.conexao = conexao;
	}

	@Override public void save(Disciplina d) {

	}

	@Override public void update(Disciplina d) {

	}

	@Override public void deleteById(Integer id) {

	}

	@Override public Disciplina findById(Integer id) {
		return null;
	}

	@Override public Disciplina findByNome(String Nome) {
		return null;
	}

	@Override public List<Disciplina> findAll() {
		return null;
	}

}
