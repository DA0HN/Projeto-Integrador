package br.edu.ifmt.cba.agenda.application.test;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.repository.DaoFactory;
import br.edu.ifmt.cba.agenda.model.service.AlunoService;
import br.edu.ifmt.cba.agenda.model.service.DisciplinaService;
import br.edu.ifmt.cba.agenda.model.service.HistoricoAlunoService;

public class Main {

	public static void main(String[] args) {
		AlunoService alunoDao = DaoFactory.createAlunoDao();
		DisciplinaService disciplinaDao = DaoFactory.createDisciplinaDao();
		HistoricoAlunoService historicoDao = DaoFactory.createHistoricoService();
		
//		alunoDao.save(new Aluno("admin","admin","admin","admin"));
		var aluno = alunoDao.findByMatricula("admin");
		var materia = disciplinaDao.findByDisciplina("Calculo IV");
		List<Disciplina> lista = new ArrayList<Disciplina>();
		
		aluno.matricularEmDisciplina(materia);
		
		System.out.println(aluno);		
		System.out.println(materia);
		
		
		aluno.setDisciplinas();
		historicoDao.matricularAlunoEmDisciplina(aluno.getId(), materia.getId());
		
	}
}
