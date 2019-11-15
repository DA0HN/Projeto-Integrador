package br.edu.ifmt.cba.agenda.model.entities;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifmt.cba.agenda.model.resource.FormatarNumero;

public class Disciplina {

	private Integer id;

	private String nome;
	private String professor;
	private Integer faltas = 0;
	private Integer numeroDeAulas;
	private Double media;

	private List<Nota> notas = new ArrayList<>();
	
	public Disciplina() {}
	
	public Disciplina(Integer id, String nome, String professor, Integer numeroDeAulas) {
		this.id = id;
		this.nome = nome;
		this.professor = professor;
		this.numeroDeAulas = numeroDeAulas;
	}

	public Disciplina(String nome, String professor, Integer numeroDeAulas) {
		this.nome = nome;
		this.professor = professor;
		this.numeroDeAulas = numeroDeAulas;
	}

	public void calcularMedia() {
		Double resultado = calculo();
		if( resultado.isNaN() ) {
			this.media = 0d;
		}
		else {
			this.media = resultado;
		}
	}
	
	private Double calculo() {
		Double soma = 0d;
		for(Nota d : notas ) {
			soma += d.getNota();
		}
		
		Double media = soma/notas.size();
//		System.out.println(media);
		
		if( !media.isNaN() ) {
			return FormatarNumero.format(media);
		}
		else {
			return 0d;
		}
	}

	public void setMedia(Double media) {
		if( media.isNaN() ) {
			this.media = 0d;
		}
		this.media = media;
	}
	
	public Double getMedia() {
		return this.media;
	}
	
	public Integer getFaltas() {
		return faltas;
	}

	public void setFaltas(Integer faltas) {
		this.faltas += faltas;
	}

	public Integer getNumeroDeAulas() {
		return numeroDeAulas;
	}

	public void setNumeroDeAulas(Integer numeroDeAulas) {
		this.numeroDeAulas = numeroDeAulas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	public String mostrarDados() {
		return "Disciplina [id=" + id + ", nome=" + nome + ", professor=" + professor + ", faltas=" + faltas
				+ ", numeroDeAulas=" + numeroDeAulas + ", media=" + media + ", notas=" + notas + "]";
	}
	
	@Override public String toString() {
		return getNome();
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
		Disciplina other = (Disciplina) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
