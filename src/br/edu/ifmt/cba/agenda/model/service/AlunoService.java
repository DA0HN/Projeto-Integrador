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
import br.edu.ifmt.cba.agenda.model.entities.Aluno;
import br.edu.ifmt.cba.agenda.model.repository.interfaces.AlunoDatabase;

public class AlunoService implements AlunoDatabase {

	protected enum AlunoSQL {

		SAVE("insert into aluno (nome, matricula, senha, email) values (?, ?, ?, ?)"),
		UPDATE("update aluno set nome=?, matricula=?, email=? where id=?"),
		DELETE_BY_ID("delete aluno where id = ?"),
		DELETE_BY_MATRICULA("delete aluno where matricula = ?"),
		FIND_BY_ID("select * from aluno where id = ?"),
		FIND_BY_MATRICULA("select * from aluno where matricula = ?"),
		FIND_ALL("select * from aluno");

		private String value;

		AlunoSQL(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	
	private Connection conexao;
	
	public AlunoService(Connection conexao) {
		this.conexao = conexao;
	}

	@Override public boolean save(Aluno a) {
		PreparedStatement st = null;
		try {
			st = conexao.prepareStatement(AlunoSQL.SAVE.getValue(), Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, a.getNome());
			st.setString(2, a.getMatricula());
			st.setString(3, a.getSenha());
			st.setString(4, a.getEmail());
			
			var linhas = st.executeUpdate();
			
			if( linhas > 0) {
				System.out.println("Linhas alteradas: " + linhas);
				ResultSet rs = st.getGeneratedKeys();
				if( rs.next() ) {
					int id = rs.getInt(1);
					a.setId(id);
				}
				Database.closeResultSet(rs);
				return true;
			}
			return false;
		}
		catch( SQLException e) {
			throw new DatabaseException("Erro ao executar SAVE -> " + e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}

	@Override public void update(Aluno a) {
		PreparedStatement st = null;
		try {
			st = conexao.prepareStatement(AlunoSQL.UPDATE.getValue());
			st.setString(1, a.getNome());
			st.setString(2, a.getMatricula());
			st.setString(3, a.getEmail());
			st.setInt(4, a.getId());
			
			st.executeUpdate();
			
		}
		catch( SQLException e) {
			throw new DatabaseException("Erro ao executar UPDATE -> " + e.getMessage());
		}
		finally {
			Database.closeStatement(st);
		}
	}

	@Override public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conexao.prepareStatement(AlunoSQL.DELETE_BY_ID.getValue());
			st.setInt(1, id);
			st.executeUpdate();
		}
		catch( SQLException e ) {
			throw new DatabaseException("Erro ao executar deleteById -> " + e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}

	@Override public void deleteByMatricula(String matricula) {
		PreparedStatement st = null;
		try {
			st = conexao.prepareStatement(AlunoSQL.DELETE_BY_MATRICULA.getValue());
			st.setString(1, matricula);
			st.executeUpdate();
		}
		catch( SQLException e ) {
			throw new DatabaseException("Erro ao executar deleteByMatricula -> " + e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}

	@Override public Aluno findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conexao.prepareStatement(AlunoSQL.FIND_BY_ID.getValue());
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if( rs.next() ) {
				Aluno a = instanciarAluno(rs);
				return a;
			}
			else {
				throw new DatabaseException("Errp ao executar findById -> ");
			}
			
		}
		catch( SQLException e ) {
			throw new DatabaseException("Erro ao executar deleteByMatricula -> " + e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}


	@Override public Aluno findByMatricula(String matricula) throws DatabaseException{
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conexao.prepareStatement(AlunoSQL.FIND_BY_MATRICULA.getValue());
			st.setString(1, matricula);
			rs = st.executeQuery();
			
			if( rs.next() ) {
				Aluno a = instanciarAluno(rs);
				return a;
			}
			else {
				throw new DatabaseException("Erro ao executar findById -> ");
			}
			
		}
		catch( SQLException e ) {
			throw new DatabaseException("Erro ao executar deleteByMatricula -> " + e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}

	@Override public List<Aluno> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conexao.prepareStatement(AlunoSQL.FIND_ALL.getValue());
			rs = st.executeQuery();
			
			List<Aluno> alunos = new ArrayList<>();
			
			while( rs.next() ) {
				alunos.add( instanciarAluno(rs) );
			}
			return alunos;
			
		}
		catch( SQLException e ) {
			throw new DatabaseException("Erro ao executar deleteByMatricula -> " + e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}
	protected Aluno instanciarAluno(ResultSet rs) throws SQLException {
		var aluno = new Aluno();
		
		aluno.setNome( rs.getString("nome"));
		aluno.setSenha( rs.getString("senha"));
		aluno.setMatricula( rs.getString("matricula"));
		aluno.setEmail( rs.getString("email"));
		aluno.setId( rs.getInt("id"));
		
		return aluno;
	}
}
