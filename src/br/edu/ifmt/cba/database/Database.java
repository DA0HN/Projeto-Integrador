package br.edu.ifmt.cba.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
 * Classe criada para para utilizar alguns metodos estaticos
 *  para conectar e desconectar do banco de dados
 */

public class Database {
	
	private static Connection conexao = null;
	
	// metodo para conectar ao banco de dados
	public static Connection getConnection() {
		if( conexao == null ) {
			try {
				Properties propriedadesDeConexao = carregarPropriedades();	// pega as propriedades do banco de dados
				String url = propriedadesDeConexao.getProperty("dburl");
				
				// instancia um objeto do tipo Connection
				conexao = DriverManager.getConnection(url, propriedadesDeConexao);
			}
			catch(SQLException e) {
				throw new DatabaseException(e.getMessage());
			}
				
		}
		return conexao;
	}
	
	// metodo para carregar as configuracoes do arquivo db.properties
	private static Properties carregarPropriedades() {
		try (FileInputStream fs = new FileInputStream("db.properties")){
			Properties propriedadesDeConexao = new Properties();
			propriedadesDeConexao.load(fs);
			return propriedadesDeConexao;
		}
		catch(IOException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public static void closeConnection() {
		if( conexao != null ) {
			try {
				conexao.close();
			} catch (SQLException e) {
				throw new DatabaseException(e.getMessage());
			}
		}
	}
	public static void closeStatement(Statement st) {
		if( st != null ) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DatabaseException( e.getMessage() );
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if( rs != null ) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DatabaseException( e.getMessage() );
			}
		}
	}
}
