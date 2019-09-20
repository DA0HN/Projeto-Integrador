package br.edu.ifmt.cba.model.service;

import java.sql.Connection;
import java.util.List;

import br.edu.ifmt.cba.model.entities.Aluno;
import br.edu.ifmt.cba.model.repository.AlunoDao;

public class AlunoService implements AlunoDao {

	private Connection conexao;
	
	public AlunoService(Connection conexao) {
		this.conexao = conexao;
	}

	@Override public void save(Aluno a) {

	}

	@Override public void update(Aluno a) {

	}

	@Override public void deleteById(Integer id) {

	}

	@Override public void deleteByLogin(String login) {

	}

	@Override public Aluno findById(Integer id) {
		return null;
	}

	@Override public Aluno findByLogin(String login) {
		return null;
	}

	@Override public List<Aluno> findAll() {
		return null;
	}

}
