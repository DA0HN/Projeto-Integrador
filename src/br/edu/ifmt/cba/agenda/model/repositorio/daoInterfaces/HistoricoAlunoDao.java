package br.edu.ifmt.cba.agenda.model.repositorio.daoInterfaces;

import java.util.List;

import br.edu.ifmt.cba.agenda.model.entities.Aluno;
import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.entities.Nota;

public interface HistoricoAlunoDao {
	
	void saveNota(Aluno aluno, Disciplina disciplina, Double nota);
	void saveFalta(Aluno aluno, Disciplina disciplina, Integer falta);
	void deleteNotaById(Aluno aluno, Disciplina disciplina);
	void deleteFaltaById(Aluno aluno, Disciplina disciplina);
	Integer findFaltaByDisciplina(Aluno aluno, Disciplina disciplina);
	List<Nota> findNotasByDisciplina(Aluno aluno, Disciplina disciplina);
}
