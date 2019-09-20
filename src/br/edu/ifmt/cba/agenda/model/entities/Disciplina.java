package br.edu.ifmt.cba.agenda.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {
	
	private String nome;
	private String professor;
	private Double media;
	
	private List<Double> notas = new ArrayList<>();
	
	
	public Double getMedia() {
		for(Double d : notas ) {
			media += d;
		}
		return media/notas.size();
	}

	public void setMedia(Double media) {
		this.media = media;
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

	public List<Double> getNotas() {
		return notas;
	}

	public void setNotas(List<Double> notas) {
		this.notas = notas;
	}
}
