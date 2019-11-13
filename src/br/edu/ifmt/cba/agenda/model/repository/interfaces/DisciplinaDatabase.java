package br.edu.ifmt.cba.agenda.model.repositorio.daoInterfaces;

import java.util.List;

import br.edu.ifmt.cba.agenda.model.entities.Disciplina;

public interface DisciplinaDao {
	
	void save(Disciplina d);
	void update(Disciplina d);
	void deleteById(Integer id);
	Disciplina findById(Integer id);
	Disciplina findByDisciplina(String Nome);
	List<Disciplina> findAll();

}
