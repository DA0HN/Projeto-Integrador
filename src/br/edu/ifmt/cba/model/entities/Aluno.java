package br.edu.ifmt.cba.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
	
	private String nome;
	private String matricula;
	private String senha;
	private String email;
	
	private List<Disciplina> disciplinas = new ArrayList<>();
	
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getSenha() {
		return senha;
	}
	
}
