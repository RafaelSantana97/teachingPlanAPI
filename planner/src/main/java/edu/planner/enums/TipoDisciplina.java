package edu.planner.enums;

public enum TipoDisciplina {

	TEORIA("T", "Teórica"),
	LABORATORIO("L", "Prática");

	private String id;
	private String descricao;

	private TipoDisciplina(String id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public String getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoDisciplina toEnum(String id) {
		if (id == null) {
			return null;
		}

		for (TipoDisciplina x : TipoDisciplina.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido " + id);
	}
}