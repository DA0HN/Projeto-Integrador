package br.edu.ifmt.cba.agenda.model.repositorio.daoInterfaces;

import java.util.List;

import br.edu.ifmt.cba.agenda.model.entities.Aluno;
import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.entities.Nota;

public interface HistoricoAlunoDao {
	
	void saveNota(Aluno aluno, Disciplina disciplina, Double nota);
	void saveFalta(Aluno aluno, Disciplina disciplina, Integer falta);
	void deleteNota(Nota nota);
	void deleteFalta(Aluno aluno, Disciplina disciplina, Integer falta);
	Integer getFalta(Aluno aluno, Disciplina disciplina);
	List<Nota> findNotasByDisciplina(Aluno aluno, Disciplina disciplina);
	public List<Disciplina> findDisciplinasByAluno(Integer id);
}
