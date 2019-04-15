package edu.planner.enums;

public enum Semestre {

	SEMESTRE_1("S1", "1º Semestre"),
	SEMESTRE_2("S2", "2º Semestre");
	
	private String id;
	private String descricao;

	private Semestre(String id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public String getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Semestre toEnum(String id) {
		if (id == null) {
			return null;
		}
		
		for (Semestre x : Semestre.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido " + id);
	}
}