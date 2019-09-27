package br.edu.ifmt.cba.agenda.model.repositorio;

import br.edu.ifmt.cba.agenda.database.Database;
import br.edu.ifmt.cba.agenda.model.recurso.AlunoRecurso;
import br.edu.ifmt.cba.agenda.model.recurso.DisciplinaRecurso;
import br.edu.ifmt.cba.agenda.model.recurso.HistoricoAlunoRecurso;

public class DaoFactory {
	
	public static AlunoRecurso createAlunoDao() {
		return new AlunoRecurso( Database.getConnection() );
	}
	
	public static DisciplinaRecurso createDisciplinaDao() {
		return new DisciplinaRecurso( Database.getConnection() );
	}
	
	public static HistoricoAlunoRecurso createHistoricoDao() {
		return new HistoricoAlunoRecurso( Database.getConnection() );
	}
	
}
