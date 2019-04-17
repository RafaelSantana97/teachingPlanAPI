package edu.planner.exception;

public enum ErrorCode {
	
	COURSE_SAVE(1000, "Erro ao salvar course"),
	COURSE_UPDATE(1001, "Erro ao alterar course"),
	COURSE_DELETE(1002, "Erro ao remover course"),
	COURSE_DELETE_VIOLATION(1003, "Não é possível remover um course em uso"),
	COURSE_SEARCH(1004, "Erro ao consultar course"),
	COURSE_NOT_FOUND(1005, "Course not found"),

	SUBJECT_SAVE(1020, "Erro ao salvar subject"),
	SUBJECT_UPDATE(1021, "Erro ao alterar subject"),
	SUBJECT_DELETE(1022, "Erro ao remover subject"),
	SUBJECT_DELETE_VIOLATION(1023, "Não é possível remover uma subject em uso"),
	SUBJECT_SEARCH(1024, "Erro ao consultar subject"),
	SUBJECT_NOT_FOUND(1025, "Subject não encontrada"),
	SUBJECT_NEED_A_TEACHER(1026, "Somente um teacher pode ser responsável por uma subject"),

	PRIVILEGIO_SAVE(1040, "Erro ao salvar privilégio"),
	PRIVILEGIO_UPDATE(1041, "Erro ao alterar privilégio"),
	PRIVILEGIO_DELETE(1042, "Erro ao remover privilégio"),
	PRIVILEGIO_DELETE_VIOLATION(1043, "Não é possível remover um privilégio em uso"),
	PRIVILEGIO_SEARCH(1044, "Erro ao consultar privilégio"),
	PRIVILEGIO_NOT_FOUND(1045, "Privilégio not found"),
	
	USER_TYPE_SAVE(1060, "Erro ao salvar type de usuário"),
	USER_TYPE_UPDATE(1061, "Erro ao alterar type de usuário"),
	USER_TYPE_DELETE(1062, "Erro ao remover type de usuário"),
	USER_TYPE_DELETE_VIOLATION(1063, "Não é possível remover um type de usuário em uso"),
	USER_TYPE_SEARCH(1064, "Erro ao consultar type de usuário"),
	USER_TYPE_NOT_FOUND(1065, "Type de Usuário not found"),

	CLASS_SAVE(1080, "Erro ao salvar class"),
	CLASS_UPDATE(1081, "Erro ao alterar class"),
	CLASS_DELETE(1082, "Erro ao remover class"),
	CLASS_DELETE_VIOLATION(1083, "Não é possível remover uma class em uso"),
	CLASS_SEARCH(1084, "Erro ao consultar class"),
	CLASS_NOT_FOUND(1085, "Class não encontrada"),
	CLASS_NEED_A_TEACHER(1086, "O usuário informado não é um teacher"),

	USER_SAVE(1100, "Erro ao salvar usuário"),
	USER_UPDATE(1101, "Erro ao alterar usuário"),
	USER_DELETE(1102, "Erro ao remover usuário"),
	USER_DELETE_VIOLATION(1103, "Não é possível remover um usuário em uso"),
	USER_SEARCH(1104, "Erro ao consultar usuário"),
	USER_NOT_FOUND(1105, "User not found"),
	
	DOMINIO_SAVE(1120, "Erro ao salvar domínio"),
	DOMINIO_UPDATE(1121, "Erro ao alterar domínio"),
	DOMINIO_DELETE(1122, "Erro ao remover domínio"),
	DOMINIO_DELETE_VIOLATION(1123, "Não é possível remover um domínio em uso"),
	DOMINIO_SEARCH(1124, "Erro ao consultar domínio"),
	DOMINIO_NOT_FOUND(1125, "Domain not found"),

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