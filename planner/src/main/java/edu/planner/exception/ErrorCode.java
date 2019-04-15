package edu.planner.exception;

public enum ErrorCode {
	
	CURSO_SAVE(1000, "Erro ao salvar curso"),
	CURSO_UPDATE(1001, "Erro ao alterar curso"),
	CURSO_DELETE(1002, "Erro ao remover curso"),
	CURSO_DELETE_VIOLATION(1003, "Não é possível remover um curso em uso"),
	CURSO_SEARCH(1004, "Erro ao consultar curso"),
	CURSO_NOT_FOUND(1005, "Curso não encontrado"),

	DISCIPLINA_SAVE(1020, "Erro ao salvar disciplina"),
	DISCIPLINA_UPDATE(1021, "Erro ao alterar disciplina"),
	DISCIPLINA_DELETE(1022, "Erro ao remover disciplina"),
	DISCIPLINA_DELETE_VIOLATION(1023, "Não é possível remover uma disciplina em uso"),
	DISCIPLINA_SEARCH(1024, "Erro ao consultar disciplina"),
	DISCIPLINA_NOT_FOUND(1025, "Disciplina não encontrada"),
	DISCIPLINA_NEED_A_PROFESSOR(1026, "Somente um professor pode ser responsável por uma disciplina"),

	PRIVILEGIO_SAVE(1040, "Erro ao salvar privilégio"),
	PRIVILEGIO_UPDATE(1041, "Erro ao alterar privilégio"),
	PRIVILEGIO_DELETE(1042, "Erro ao remover privilégio"),
	PRIVILEGIO_DELETE_VIOLATION(1043, "Não é possível remover um privilégio em uso"),
	PRIVILEGIO_SEARCH(1044, "Erro ao consultar privilégio"),
	PRIVILEGIO_NOT_FOUND(1045, "Privilégio não encontrado"),
	
	TIPO_USUARIO_SAVE(1060, "Erro ao salvar tipo de usuário"),
	TIPO_USUARIO_UPDATE(1061, "Erro ao alterar tipo de usuário"),
	TIPO_USUARIO_DELETE(1062, "Erro ao remover tipo de usuário"),
	TIPO_USUARIO_DELETE_VIOLATION(1063, "Não é possível remover um tipo de usuário em uso"),
	TIPO_USUARIO_SEARCH(1064, "Erro ao consultar tipo de usuário"),
	TIPO_USUARIO_NOT_FOUND(1065, "Tipo de Usuário não encontrado"),

	TURMA_SAVE(1080, "Erro ao salvar turma"),
	TURMA_UPDATE(1081, "Erro ao alterar turma"),
	TURMA_DELETE(1082, "Erro ao remover turma"),
	TURMA_DELETE_VIOLATION(1083, "Não é possível remover uma turma em uso"),
	TURMA_SEARCH(1084, "Erro ao consultar turma"),
	TURMA_NOT_FOUND(1085, "Turma não encontrada"),
	TURMA_NEED_A_PROFESSOR(1086, "O usuário informado não é um professor"),

	USUARIO_SAVE(1100, "Erro ao salvar usuário"),
	USUARIO_UPDATE(1101, "Erro ao alterar usuário"),
	USUARIO_DELETE(1102, "Erro ao remover usuário"),
	USUARIO_DELETE_VIOLATION(1103, "Não é possível remover um usuário em uso"),
	USUARIO_SEARCH(1104, "Erro ao consultar usuário"),
	USUARIO_NOT_FOUND(1105, "Usuário não encontrado"),
	
	DOMINIO_SAVE(1120, "Erro ao salvar domínio"),
	DOMINIO_UPDATE(1121, "Erro ao alterar domínio"),
	DOMINIO_DELETE(1122, "Erro ao remover domínio"),
	DOMINIO_DELETE_VIOLATION(1123, "Não é possível remover um domínio em uso"),
	DOMINIO_SEARCH(1124, "Erro ao consultar domínio"),
	DOMINIO_NOT_FOUND(1125, "Domínio não encontrado"),

	ULTIMO_ERRO(9999, "");
	
	private final int code;
	private final String message;
    
    ErrorCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

    @Override
    public String toString() {
    	String message = "";
    	if(this.message != null && this.message.trim().length() > 0) {
    		message = " - " + this.message;
    	}
        return String.valueOf(this.code) + message;
    }
}