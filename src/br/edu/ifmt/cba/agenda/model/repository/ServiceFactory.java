package br.edu.ifmt.cba.agenda.model.repository;

import br.edu.ifmt.cba.agenda.database.Database;
import br.edu.ifmt.cba.agenda.model.service.AlunoService;
import br.edu.ifmt.cba.agenda.model.service.DisciplinaService;
import br.edu.ifmt.cba.agenda.model.service.HistoricoAlunoService;

public class ServiceFactory {
	
	public static AlunoService createAlunoDao() {
		return new AlunoService( Database.getConnection() );
	}
	
	public static DisciplinaService createDisciplinaDao() {
		return new DisciplinaService( Database.getConnection() );
	}
	
	public static HistoricoAlunoService createHistoricoDao() {
		return new HistoricoAlunoService( Database.getConnection() );
	}
	
}
