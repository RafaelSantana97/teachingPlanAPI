package edu.planner.enums;

public enum Titulacao {

	NENHUM("", "Nenhum"),
	ESPECIALISTA("Esp.", "Especialista"),
	MESTRE("Me.", "Mestre"),
	DOUTOR("Dr.", "Doutor");

	private String id;
	private String descricao;

	private Titulacao(String id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public String getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Titulacao toEnum(String id) {
		if (id == null) {
			return null;
		}

		for (Titulacao x : Titulacao.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido " + id);
	}
}