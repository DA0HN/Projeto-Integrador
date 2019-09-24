package br.edu.ifmt.cba.agenda.model.repository.enums;

public enum AlunoSQL {

	SAVE("insert into aluno (nome, matricula, senha, email) values (?, ?, ?, ?)"),
	UPDATE(""),
	DELETE_BY_ID(""),
	DELETE_BY_LOGIN(""),
	FIND_BY_ID(""),
	FIND_BY_LOGIN(""),
	FIND_ALL("");

	private String value;

	AlunoSQL(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
