package br.edu.ifmt.cba.agenda.model.repository.enums;

public enum DisciplinaSQL {
	
	SAVE(""),
	UPDATE(""),
	DELETE_BY_ID(""),
	FIND_BY_ID(""),
	FIND_BY_NOME(""),
	FIND_ALL("");
	
	private String value;
	
	DisciplinaSQL(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
