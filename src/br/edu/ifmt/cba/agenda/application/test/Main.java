package br.edu.ifmt.cba.agenda.application.test;

import br.edu.ifmt.cba.agenda.model.recurso.AlunoRecurso;
import br.edu.ifmt.cba.agenda.model.recurso.DisciplinaRecurso;
import br.edu.ifmt.cba.agenda.model.recurso.HistoricoAlunoRecurso;
import br.edu.ifmt.cba.agenda.model.repositorio.DaoFactory;
import br.edu.ifmt.cba.agenda.model.service.HistoricoAlunoService;

public class Main {

	public static void main(String[] args) {
		AlunoRecurso alunoDao = DaoFactory.createAlunoDao();
		DisciplinaRecurso disciplinaDao = DaoFactory.createDisciplinaDao();
		HistoricoAlunoRecurso historicoDao = DaoFactory.createHistoricoDao();
		
//		alunoDao.save(new Aluno("admin","admin","admin","admin"));
		var aluno = alunoDao.findByMatricula("admin");
		var materia = disciplinaDao.findByDisciplina("Calculo IV");
		
		HistoricoAlunoService.matricularEmDisciplina(aluno, materia);
		
		System.out.println(aluno);		
		System.out.println(materia);
		
		historicoDao.matricularEmDisciplina(aluno.getId(), 10);
		
	}
}
