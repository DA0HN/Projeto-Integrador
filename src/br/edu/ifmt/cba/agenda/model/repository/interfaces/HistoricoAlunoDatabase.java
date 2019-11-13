package br.edu.ifmt.cba.agenda.model.repositorio.daoInterfaces;

import java.util.List;

import br.edu.ifmt.cba.agenda.model.entities.Aluno;
import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.entities.Nota;

public interface HistoricoAlunoDao {
	
	Boolean saveNota(Aluno aluno, Disciplina disciplina, Double nota);
	Boolean saveFalta(Aluno aluno, Disciplina disciplina, Integer falta);
	Boolean deleteNota(Aluno aluno, Disciplina disciplina, Nota nota);
	Boolean deleteFalta(Aluno aluno, Disciplina disciplina, Integer falta);
	Boolean updateNota(Aluno aluno, Disciplina disciplina, Integer idNota ,Double nota);
	Integer getFalta(Aluno aluno, Disciplina disciplina);
	List<Nota> findNotasByDisciplina(Aluno aluno, Disciplina disciplina);
	public List<Disciplina> findDisciplinasByAluno(Integer id);
}
