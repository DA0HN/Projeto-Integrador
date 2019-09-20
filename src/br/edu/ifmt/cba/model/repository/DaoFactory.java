package br.edu.ifmt.cba.model.repository;

import br.edu.ifmt.cba.database.Database;
import br.edu.ifmt.cba.model.service.AlunoService;
import br.edu.ifmt.cba.model.service.DisciplinaService;

public class DaoFactory {
	
	public static AlunoService createAlunoDao() {
		return new AlunoService( Database.getConnection() );
	}
	
	public static DisciplinaService createDisciplinaDao() {
		return new DisciplinaService( Database.getConnection() );
	}
	
}
