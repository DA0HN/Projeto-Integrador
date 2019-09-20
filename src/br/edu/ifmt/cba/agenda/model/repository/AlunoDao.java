package br.edu.ifmt.cba.agenda.model.repository;

import java.util.List;

import br.edu.ifmt.cba.agenda.model.entities.Aluno;

public interface AlunoDao {
	
	void save(Aluno a);
	void update(Aluno a);
	void deleteById(Integer id);
	void deleteByLogin(String login);
	Aluno findById(Integer id);
	Aluno findByLogin(String login);
	List<Aluno> findAll();
	
}
