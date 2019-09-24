package br.edu.ifmt.cba.agenda.application.test;

import br.edu.ifmt.cba.agenda.model.repository.DaoFactory;
import br.edu.ifmt.cba.agenda.model.service.AlunoService;

public class Main {

	public static void main(String[] args) {
		AlunoService service = DaoFactory.createAlunoDao();
		
		service.findAll().forEach(System.out::println);
		
		
	}
}
