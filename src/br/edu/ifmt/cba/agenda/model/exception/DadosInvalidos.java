package br.edu.ifmt.cba.agenda.model.exception;

public class DadosInvalidos extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DadosInvalidos(String msg) {
		super(msg);
	}
	
}
