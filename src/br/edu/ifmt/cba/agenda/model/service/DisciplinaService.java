package br.edu.ifmt.cba.agenda.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifmt.cba.agenda.database.Database;
import br.edu.ifmt.cba.agenda.database.DatabaseException;
import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.repository.interfaces.DisciplinaDatabase;

public class DisciplinaService implements DisciplinaDatabase {

	protected enum DisciplinaSQL {
		
		SAVE("insert into disciplina (nome, professor, quantidade_de_aulas) values (?, ?, ?)"),
		UPDATE("update disciplina set nome=?, professor=?, quantidade_de_aulas=? where id = ?"),
		DELETE_BY_ID("delete from disciplina where id=?"),
		FIND_BY_ID("select * from disciplina where id=?"),
		FIND_BY_DISCIPLINA("select * from disciplina where nome=?"),
		FIND_ALL("select * from disciplina");
		
		private String value;
		
		DisciplinaSQL(String value){
			this.value = value;
		}
		
		protected String getValue() {
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
			st = conexao.prepareStatement(DisciplinaSQL.SAVE.getValue(), Statement.RETURN_GENERATED_KEYS);
			st.setString(1, d.getNome());
			st.setString(2, d.getProfessor());
			st.setInt(3, d.getNumeroDeAulas());
			
			var linhas = st.executeUpdate();
			
			if( linhas > 0 ) {
				System.out.println("Linhas alteradas: " + linhas);
				ResultSet rs = st.getGeneratedKeys();
				if( rs.next() ) {
					var id = rs.getInt(1);
					d.setId(id);
				}
				Database.closeResultSet(rs);
			}
			
		}
		catch( SQLException e) {
			throw new DatabaseException("Erro ao executar Save -> " + e.getMessage() );
		}
		finally{
			Database.closeStatement(st);
		}
	}

	@Override public void update(Disciplina d) {
		PreparedStatement st = null;
		try {
			st = conexao.prepareStatement(DisciplinaSQL.UPDATE.getValue());
			st.setString(1, d.getNome());
			st.setString(2, d.getProfessor());
			st.setInt(3, d.getNumeroDeAulas());
			st.setInt(4, d.getId());
			st.executeUpdate();
		}
		catch( SQLException e) {
			throw new DatabaseException("Erro ao executar Update -> " + e.getMessage() );
		}
		finally{
			Database.closeStatement(st);
		}
	}

	@Override public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conexao.prepareStatement(DisciplinaSQL.DELETE_BY_ID.getValue());
			st.setInt(1, id);
			st.executeUpdate();
		}
		catch( SQLException e) {
			throw new DatabaseException("Erro ao executar DeleteById -> " + e.getMessage() );
		}
		finally{
			Database.closeStatement(st);
		}
	}

	@Override public Disciplina findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conexao.prepareStatement(DisciplinaSQL.FIND_BY_ID.getValue());
			st.setInt(1, id);
			rs = st.executeQuery();
			if( rs.next() ) {
				Disciplina d = instanciarDisciplina(rs);
				return d;
			}
			else {
				throw new DatabaseException("Erro ao executar findById -> ");
			}
		}
		catch( SQLException e) {
			throw new DatabaseException("Erro ao executar FindById -> " + e.getMessage() );
		}
		finally{
			Database.closeStatement(st);
		}
	}

	@Override public Disciplina findByDisciplina(String nome) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conexao.prepareStatement(DisciplinaSQL.FIND_BY_DISCIPLINA.getValue());
			st.setString(1, nome);
			rs = st.executeQuery();
			if( rs.next() ) {
				Disciplina d = instanciarDisciplina(rs);
				return d;
			}
			else {
				throw new DatabaseException("Erro ao executar findById -> ");
			}
		}
		catch( SQLException e) {
			throw new DatabaseException("Erro ao executar FindByNome -> " + e.getMessage() );
		}
		finally{
			Database.closeStatement(st);
		}
	}

	@Override public List<Disciplina> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conexao.prepareStatement(DisciplinaSQL.FIND_ALL.getValue());
			rs = st.executeQuery();
			List<Disciplina> disciplinas = new ArrayList<>();
			
			while( rs.next() ) {
				disciplinas.add( instanciarDisciplina(rs));
			}
			return disciplinas;
		}
		catch( SQLException e) {
			throw new DatabaseException("Erro ao executar FindAll -> " + e.getMessage() );
		}
		finally{
			Database.closeStatement(st);
		}
	}
	
	protected Disciplina instanciarDisciplina(ResultSet rs) throws SQLException {
		var disciplina = new Disciplina();
		disciplina.setId(rs.getInt("id"));
		disciplina.setNome(rs.getString("nome"));
		disciplina.setProfessor(rs.getString("professor"));
		disciplina.setNumeroDeAulas(rs.getInt("quantidade_de_aulas"));
		return disciplina;
	}

}
