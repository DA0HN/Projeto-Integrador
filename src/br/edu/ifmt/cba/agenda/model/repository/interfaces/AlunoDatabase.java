package br.edu.ifmt.cba.agenda.model.repository.interfaces;

import java.util.List;

import br.edu.ifmt.cba.agenda.model.entities.Aluno;

public interface AlunoDatabase {
	
	boolean save(Aluno a);
	void update(Aluno a);
	void deleteById(Integer id);
	void deleteByMatricula(String login);
	Aluno findById(Integer id);
	Aluno findByMatricula(String matricula);
	List<Aluno> findAll();
	
}
