package br.edu.ifmt.cba.agenda.application.test;

import br.edu.ifmt.cba.agenda.model.entities.Aluno;
import br.edu.ifmt.cba.agenda.model.repository.DaoFactory;
import br.edu.ifmt.cba.agenda.model.service.AlunoService;

public class Main {

	public static void main(String[] args) {
		AlunoService service = DaoFactory.createAlunoDao();
		
		service.update(new Aluno(6, "Gabriel", "123123123", "123123132", "gabriel@gmail.com"));
		System.out.println("coe");
	}
}
