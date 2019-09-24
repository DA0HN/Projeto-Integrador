package br.edu.ifmt.cba.agenda.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.edu.ifmt.cba.agenda.database.Database;
import br.edu.ifmt.cba.agenda.database.DatabaseException;
import br.edu.ifmt.cba.agenda.model.entities.Aluno;
import br.edu.ifmt.cba.agenda.model.repository.AlunoDao;
import br.edu.ifmt.cba.agenda.model.repository.enums.AlunoSQL;

public class AlunoService implements AlunoDao {

	private Connection conexao;
	
	public AlunoService(Connection conexao) {
		this.conexao = conexao;
	}

	@Override public void save(Aluno aluno) {
		PreparedStatement st = null;
		try {
			st = conexao.prepareStatement(AlunoSQL.SAVE.getValue(), Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, aluno.getNome());
			st.setString(2, aluno.getMatricula());
			st.setString(3, aluno.getSenha());
			st.setString(4, aluno.getEmail());
			
			var linhas = st.executeUpdate();
			
			if( linhas > 0) {
				System.out.println("Linhas alteradas: " + linhas);
				ResultSet rs = st.getGeneratedKeys();
				if( rs.next() ) {
					int id = rs.getInt(1);
					aluno.setId(id);
				}
				Database.closeResultSet(rs);
			}
		}
		catch( SQLException e) {
			throw new DatabaseException("Erro ao executar o SAVE -> " + e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}

	@Override public void update(Aluno aluno) {

	}

	@Override public void deleteById(Integer id) {

	}

	@Override public void deleteByLogin(String login) {

	}

	@Override public Aluno findById(Integer id) {
		return null;
	}

	@Override public Aluno findByLogin(String login) {
		return null;
	}

	@Override public List<Aluno> findAll() {
		return null;
	}

}
