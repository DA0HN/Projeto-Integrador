package br.edu.ifmt.cba.agenda.model.recurso;

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
import br.edu.ifmt.cba.agenda.model.entities.Disciplina;
import br.edu.ifmt.cba.agenda.model.entities.Nota;
import br.edu.ifmt.cba.agenda.model.exception.DadosInvalidos;
import br.edu.ifmt.cba.agenda.model.repositorio.DaoFactory;
import br.edu.ifmt.cba.agenda.model.repositorio.daoInterfaces.HistoricoAlunoDao;
import br.edu.ifmt.cba.agenda.model.service.HistoricoAlunoService;

public class HistoricoAlunoRecurso implements HistoricoAlunoDao {

	protected enum HistoricoSQL{
		
		VERIFICA_HISTORICO("select * from historicoAluno where id_aluno in(?) and id_disciplina in(?)"),
		MATRICULAR_ALUNO_EM_DISCIPLINA("insert into historicoAluno (id_aluno,id_disciplina) value (?,?)"),
		SAVE_FALTA("update historicoAluno set faltas=? where id_aluno=? and id_disciplina=?"),
		SAVE_NOTA("insert into notas (id_aluno, id_disciplina, nota) value (?,?,?)"),	// só salva notas se for em uma matéria que o aluno frequenta
		DELETE_NOTAS_BY_ID("delete from notas where id=? and id_disciplina=? and id_aluno=?"),
		REMOVE_FALTA_BY_ID(""),
		FIND_DISCIPLINA_BY_ALUNO("select id_disciplina from historicoAluno where id_aluno in(?)"),
		FIND_FALTA("select faltas from historicoAluno where id_aluno in(?) and id_disciplina in(?)"),
		FIND_NOTAS_BY_DISCIPLINA("select * from notas where id_aluno in(?) and id_disciplina in(?)"),
		FIND_FALTAS_BY_DISCIPLINA("");
		
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
	
	@Override public List<Disciplina> findDisciplinasByAluno(Integer id) {
		PreparedStatement st = null;
		List<Disciplina> disciplinas = new ArrayList<>();
		try {
			st = conexao.prepareStatement(HistoricoSQL.FIND_DISCIPLINA_BY_ALUNO.getValue());
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			while( rs.next() ) {
				disciplinas.add(DaoFactory.createDisciplinaDao().findById(rs.getInt("id_disciplina")));
			}
			
			return disciplinas;
		}
		catch( SQLException e) {
			throw new DatabaseException( e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}
	
	
	@Override public Integer getFalta(Aluno a, Disciplina d) {
		
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
				throw new DatabaseException("Não foi possivel recuperar as faltas do aluno.");
			}
			
		}
		catch( SQLException e ) {
			throw new DatabaseException( e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}
	
	public Boolean matricularEmDisciplina(Aluno aluno, Disciplina disciplina) {
		/* para que o relacionamento entre aluno e disciplina seja feito
		 * a disciplina em questão tem que estar cadastrada no banco de dados,
		 * caso contrário não é possivel criar a relação de matricula*/
		PreparedStatement st = null;
		try {
			if( disciplina.getId() == null ) {
				throw new DadosInvalidos("Os dados da disciplina são inválidos: id=null.");
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
					System.out.println("Inserção feita com sucesso! numero de linhas alteradas: " + linha);
					
					HistoricoAlunoService.matricularEmDisciplina(aluno, disciplina);
				}
				return true;
			}
			else {
				return false;
			}
		}
		catch(SQLException e) {
			throw new DatabaseException( e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}
	
	@Override public Boolean saveNota(Aluno a, Disciplina d, Double nota) {
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
						return true;
					}
				}
				return false;
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

	@Override public Boolean saveFalta(Aluno aluno, Disciplina disciplina, Integer falta) {
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
					System.out.println("Sucesso na atualização de faltas! Numero de linhas alteradas: " + linha);
					return true;
				}
				return false;
			}
			else {
				throw new DadosInvalidos("O aluno " + aluno.getNome() + " não está matriculado na disciplina " + disciplina.getNome() +" .");
			}
		}
		catch(SQLException e) {
			throw new DatabaseException( e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}

	@Override public Boolean deleteNota(Aluno aluno, Disciplina disciplina, Nota nota) {
		PreparedStatement st = null;
		try {
			st = conexao.prepareStatement(HistoricoSQL.DELETE_NOTAS_BY_ID.getValue());
			st.setInt(1, nota.getId());
			st.setInt(2, disciplina.getId());
			st.setInt(3, aluno.getId());
			var linha = st.executeUpdate();
			
			if( linha > 0) {
				return true;
			}
			return false;
		}
		catch(SQLException e) {
			throw new DatabaseException( e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
		}
	}

	@Override public Boolean deleteFalta(Aluno aluno, Disciplina disciplina, Integer falta) {
		return saveFalta(aluno, disciplina, (-falta));
	}

	@Override public List<Nota> findNotasByDisciplina(Aluno aluno, Disciplina disciplina) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			if( verificaHistorico(aluno.getId(), disciplina.getId())) {
				
				st = conexao.prepareStatement(HistoricoSQL.FIND_NOTAS_BY_DISCIPLINA.getValue());
				st.setInt(1, aluno.getId());
				st.setInt(2, disciplina.getId());
				rs = st.executeQuery();
				
				List<Nota> lista = new ArrayList<Nota>();
				
				while( rs.next() ) {
					lista.add(instanciarNota(rs));
				}
				return lista;
			}
			else {
				throw new DadosInvalidos("O aluno " + aluno.getNome() + " não está matriculado na disciplina " + disciplina.getNome() +" .");
			}
			
		}
		catch(SQLException e) {
			throw new DatabaseException( e.getMessage() );
		}
		finally {
			Database.closeStatement(st);
			Database.closeResultSet(rs);
		}
	}
	
	private Nota instanciarNota(ResultSet rs) throws SQLException {
		Nota nota = new Nota();
		nota.setId( rs.getInt("id"));
		nota.setNota( rs.getDouble("nota"));
		return nota;
	}
	
}
