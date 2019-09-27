package br.edu.ifmt.cba.agenda.model.entities;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifmt.cba.agenda.model.exception.DadosInvalidos;

public class Aluno {
	
	private Integer id;

	private String nome;
	private String matricula;
	private String senha;
	private String email;
	private List<Disciplina> disciplinas = new ArrayList<>();
	
	
	public Aluno() {}
	
	public Aluno(Integer id, String nome, String matricula, String senha, String email) {
		this.id = id;
		this.nome = nome;
		this.matricula = matricula;
		this.senha = senha;
		this.email = email;
	}

	
	public Aluno(String nome, String matricula, String senha, String email) {
		this.nome = nome;
		this.matricula = matricula;
		this.senha = senha;
		this.email = email;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
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

	@Override public String toString() {
		return "Aluno [id="+ id +", nome=" + nome + ", matricula=" + matricula + ", senha=" + senha + ", email=" + email + "]";
	}

	@Override public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
