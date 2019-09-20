package br.edu.ifmt.cba.model.service;

import java.sql.Connection;
import java.util.List;

import br.edu.ifmt.cba.model.entities.Disciplina;
import br.edu.ifmt.cba.model.repository.DisciplinaDao;

public class DisciplinaService implements DisciplinaDao {

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
