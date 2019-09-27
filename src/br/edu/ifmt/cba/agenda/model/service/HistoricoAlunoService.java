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
import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.entities.Nota;
import br.edu.ifmt.cba.agenda.model.exception.DadosInvalidos;
import br.edu.ifmt.cba.agenda.model.repository.HistoricoAlunoDao;

public class HistoricoAlunoService implements HistoricoAlunoDao {

	protected enum HistoricoSQL{
		
		VERIFICA_HISTORICO("select * from historicoAluno  where id_aluno in(?) and id_disciplina in(?)"),
		MATRICULAR_ALUNO_EM_DISCIPLINA("insert into historicoAluno (id_aluno,id_disciplina) value (?,?)"),
		SAVE_FALTA(""),
		SAVE_NOTA("insert into notas"),	// só salva notas se for em uma matéria que o aluno frequenta
		UPDATE_NOTA(""),
		UPATE_FALTA(""),
		DELETE_NOTAS_BY_ID(""),
		DELETE_FALTAS_BY_ID(""),
		FIND_NOTAS_BY_DISCIPLINA(""),
		FIND_FALTAS_BY_DISCIPLINA(""),
		FIND_NOTAS("");
		
		private String value;
		
		HistoricoSQL(String value){
			this.value = value;
		}
		
		protected String getValue() {
			return value;
		}
	}
	
	private Connection conexao;
	
	public HistoricoAlunoService(Connection conexao){
		this.conexao = conexao;
	}

	public Boolean verificaHistorico(Integer alunoId, Integer disciplinaId) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			st = conexao.prepareStatement(HistoricoSQL.VERIFICA_HISTORICO.getValue());
			st.setInt(1, alunoId);
			st.setInt(2, disciplinaId);
			
			rs = st.executeQuery();
			
			if( rs.next() ) {
				return true;
			}
			return false;
			
		}
		catch( SQLException e) {
			throw new DatabaseException( e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}
	
	public void matricularAlunoEmDisciplina(Integer alunoId, Integer disciplinaId) {
		PreparedStatement st = null;
		try {
			if( verificaHistorico(alunoId, disciplinaId) ) {
				st = conexao.prepareStatement(HistoricoSQL.MATRICULAR_ALUNO_EM_DISCIPLINA.getValue());
				st.setInt(1, alunoId);
				st.setInt(2, disciplinaId);
				var linha = st.executeUpdate();
				if( linha > 0 ) {
					System.out.println("Inserção feita com sucesso! numero de linhas alteradas: " + linha);
				}
			}
			else {
				throw new DadosInvalidos("Esse aluno já está matriculado nessa disciplina.");
			}
		}
		catch(SQLException e) {
			throw new DatabaseException( e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}
	
	@Override public void saveNota(Aluno a, Disciplina d, Double nota) {
		PreparedStatement st = null;
		try {
			if( verificaHistorico(a.getId(), d.getId())) {
				st = conexao.prepareStatement(HistoricoSQL.SAVE_NOTA.getValue(), Statement.RETURN_GENERATED_KEYS);
				
			}
			else {
				throw new DadosInvalidos("O aluno " + a.getNome() + " não está matriculado na disciplina " + d.getNome() +" .");
			}
			
		}
		catch(SQLException e) {
			throw new DatabaseException( e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}

	@Override public void saveFalta(Aluno aluno, Disciplina disciplina, Integer falta) {
		PreparedStatement st = null;
		try {
			
		}
		catch(SQLException e) {
			throw new DatabaseException( e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}

	@Override public void updateNota(Aluno aluno, Disciplina disciplina, Double nota) {
		PreparedStatement st = null;
		try {
			
		}
		catch(SQLException e) {
			throw new DatabaseException( e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}

	@Override public void updateFalta(Aluno aluno, Disciplina disciplina, Integer falta) {
		PreparedStatement st = null;
		try {
			
		}
		catch(SQLException e) {
			throw new DatabaseException( e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}

	@Override public void deleteNotaById(Aluno aluno, Disciplina disciplina) {
		PreparedStatement st = null;
		try {
			
		}
		catch(SQLException e) {
			throw new DatabaseException( e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}

	@Override public void deleteFaltaById(Aluno aluno, Disciplina disciplina) {
		PreparedStatement st = null;
		try {
			
		}
		catch(SQLException e) {
			throw new DatabaseException( e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}

	@Override public Integer findFaltaByDisciplina(Aluno aluno, Disciplina disciplina) {
		PreparedStatement st = null;
		try {
			
			return null;
		}
		catch(SQLException e) {
			throw new DatabaseException( e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}

	@Override public List<Nota> findNotasByDisciplina(Aluno aluno, Disciplina disciplina) {
		PreparedStatement st = null;
		try {
			
			return null;
		}
		catch(SQLException e) {
			throw new DatabaseException( e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}
	
	
	
}
