package br.edu.ifmt.cba.agenda.model.recurso;

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
import br.edu.ifmt.cba.agenda.model.repositorio.daoInterfaces.HistoricoAlunoDao;
import br.edu.ifmt.cba.agenda.model.service.HistoricoAlunoService;

public class HistoricoAlunoRecurso implements HistoricoAlunoDao {

	protected enum HistoricoSQL{
		
		VERIFICA_HISTORICO("select * from historicoAluno where id_aluno in(?) and id_disciplina in(?)"),
		MATRICULAR_ALUNO_EM_DISCIPLINA("insert into historicoAluno (id_aluno,id_disciplina) value (?,?)"),
		SAVE_FALTA("update historicoAluno set faltas=? where id_aluno=? and id_disciplina=?"),
		SAVE_NOTA("insert into notas (id_aluno, id_disciplina, nota) value (?,?,?)"),	// s� salva notas se for em uma mat�ria que o aluno frequenta
		DELETE_NOTAS_BY_ID(""),
		DELETE_FALTAS_BY_ID(""),
		FIND_FALTA("select faltas from historicoAluno where id_aluno in(?) and id_disciplina in(?)"),
		FIND_NOTAS_BY_DISCIPLINA("select nota from notas where id_aluno in(?) and id_disciplina in(?)"),
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
	
	public HistoricoAlunoRecurso(Connection conexao){
		this.conexao = conexao;
	}

	private Boolean verificaHistorico(Integer alunoId, Integer disciplinaId) {
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
	
	private Integer getFalta(Aluno a, Disciplina d) {
		
		PreparedStatement st = null;
		try {
			st = conexao.prepareStatement(HistoricoSQL.FIND_FALTA.getValue());
			st.setInt(1, a.getId());
			st.setInt(2, d.getId());
			ResultSet rs = st.executeQuery();
			if( rs.next() ) {
				int falta = rs.getInt("faltas");
				return falta;
			}
			else {
				throw new DatabaseException("N�o foi possivel recuperar as faltas do aluno.");
			}
			
		}
		catch( SQLException e ) {
			throw new DatabaseException( e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}
	
	public void matricularEmDisciplina(Aluno aluno, Disciplina disciplina) {
		/* para que o relacionamento entre aluno e disciplina seja feito
		 * a disciplina em quest�o tem que estar cadastrada no banco de dados,
		 * caso contr�rio n�o � possivel criar a rela��o de matricula*/
		PreparedStatement st = null;
		try {
			if( disciplina.getId() == null ) {
				throw new DadosInvalidos("Os dados da disciplina s�o inv�lidos: id=null.");
			}
			var alunoId = aluno.getId();
			var disciplinaId = disciplina.getId();
			
			if( verificaHistorico(alunoId, disciplinaId) == false ) {
				/* a relacao so sera criada se nao existir no banco de dados */
				st = conexao.prepareStatement(HistoricoSQL.MATRICULAR_ALUNO_EM_DISCIPLINA.getValue());
				st.setInt(1, alunoId);
				st.setInt(2, disciplinaId);
				var linha = st.executeUpdate();
				if( linha > 0 ) {
					System.out.println("Inser��o feita com sucesso! numero de linhas alteradas: " + linha);
					
					HistoricoAlunoService.matricularEmDisciplina(aluno, disciplina);
				}
			}
			else {
				throw new DadosInvalidos("N�o foi possivel fazer a matricula nessa disciplina.");
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
				st.setInt(1, a.getId());
				st.setInt(2, d.getId());
				st.setDouble(3, nota);
				
				var linha = st.executeUpdate();
				
				if( linha > 0) {
					ResultSet rs = st.getGeneratedKeys();
					if( rs.next() ) {
						var n = new Nota();
						n.setId(rs.getInt(1));
						n.setNota(nota);
						d.getNotas().add(n);
					}
				}
				
			}
			else {
				throw new DadosInvalidos("O aluno " + a.getNome() + " n�o est� matriculado na disciplina " + d.getNome() +" .");
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
			if( verificaHistorico(aluno.getId(), disciplina.getId())) {
				int faltaDatabase = getFalta(aluno, disciplina);
				faltaDatabase += falta;
				System.out.println(faltaDatabase);
				
				HistoricoAlunoService.atualizaFaltas(aluno, disciplina, falta);
				
				st = conexao.prepareStatement(HistoricoSQL.SAVE_FALTA.getValue());
				st.setInt(1, faltaDatabase);
				st.setInt(2, aluno.getId());
				st.setInt(3, disciplina.getId());
				var linha = st.executeUpdate();
				if( linha > 0 ) {
					System.out.println("Sucesso na atualiza��o de faltas! Numero de linhas alteradas: " + linha);
				}
				else {
					throw new DatabaseException("N�o foi possivel executar a atualiza��o de suas faltas.");
				}
			}
			else {
				throw new DadosInvalidos("O aluno " + aluno.getNome() + " n�o est� matriculado na disciplina " + disciplina.getNome() +" .");
			}
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
			st = conexao.prepareStatement(HistoricoSQL.FIND_NOTAS_BY_DISCIPLINA.getValue());
			st.setInt(1, aluno.getId());
			st.setInt(2, disciplina.getId());
		}
		catch(SQLException e) {
			throw new DatabaseException( e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}
	
	public Nota instanciarNota(ResultSet rs) throws SQLException {
		Nota nota = new Nota();
		nota.setId( rs.getInt("id"));
		nota.setNota( rs.getDouble("nota"));
		return nota;
	}
	
	
	
}
