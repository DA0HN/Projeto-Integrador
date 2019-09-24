package br.edu.ifmt.cba.agenda.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.ifmt.cba.agenda.database.Database;
import br.edu.ifmt.cba.agenda.database.DatabaseException;
import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.repository.DisciplinaDao;

public class DisciplinaService implements DisciplinaDao {

	protected enum DisciplinaSQL {
		
		SAVE("insert into disciplina (nome, professor) values (?, ?)"),
		UPDATE("update disciplina (nome, professor) where id = ?"),
		DELETE_BY_ID("delete disciplina where id = ?"),
		FIND_BY_ID("select * from disciplina where id = ?"),
		FIND_BY_NOME("select * from disciplina where nome = ?"),
		FIND_ALL("select * from disciplina");
		
		private String value;
		
		DisciplinaSQL(String value){
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
	}
	
	private Connection conexao;
	
	public DisciplinaService(Connection conexao) {
		this.conexao = conexao;
	}

	@Override public void save(Disciplina d) {
		PreparedStatement st = null;
		try {
			
		}
		catch( SQLException e) {
			throw new DatabaseException("Erro ao executar SAVE -> " + e.getMessage() );
		}
		finally{
			Database.closeStatement(st);
		}
	}

	@Override public void update(Disciplina d) {
		PreparedStatement st = null;
		try {
			
		}
		catch( SQLException e) {
			throw new DatabaseException("Erro ao executar SAVE -> " + e.getMessage() );
		}
		finally{
			Database.closeStatement(st);
		}
	}

	@Override public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			
		}
		catch( SQLException e) {
			throw new DatabaseException("Erro ao executar SAVE -> " + e.getMessage() );
		}
		finally{
			Database.closeStatement(st);
		}
	}

	@Override public Disciplina findById(Integer id) {
		PreparedStatement st = null;
		try {
			
			return null;
		}
		catch( SQLException e) {
			throw new DatabaseException("Erro ao executar SAVE -> " + e.getMessage() );
		}
		finally{
			Database.closeStatement(st);
		}
	}

	@Override public Disciplina findByNome(String Nome) {
		PreparedStatement st = null;
		try {
			
			return null;
		}
		catch( SQLException e) {
			throw new DatabaseException("Erro ao executar SAVE -> " + e.getMessage() );
		}
		finally{
			Database.closeStatement(st);
		}
	}

	@Override public List<Disciplina> findAll() {
		PreparedStatement st = null;
		try {
			
			return null;
		}
		catch( SQLException e) {
			throw new DatabaseException("Erro ao executar SAVE -> " + e.getMessage() );
		}
		finally{
			Database.closeStatement(st);
		}
	}
	
	protected Disciplina instanciarDisciplina(ResultSet rs) throws SQLException {
		var disciplina = new Disciplina();
		disciplina.setNome(rs.getString("nome"));
		disciplina.setProfessor(rs.getString("professor"));
		return disciplina;
	}

}
