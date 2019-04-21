package edu.planner.enums;

public enum LevelDegree {

	NENHUM("", "Nenhum"),
	ESPECIALISTA("Esp.", "Especialista"),
	MESTRE("Me.", "Mestre"),
	DOUTOR("Dr.", "Doutor");

	private String id;
	private String description;

	private LevelDegree(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static LevelDegree toEnum(String id) {
		if (id == null) {
			return null;
		}

		for (LevelDegree x : LevelDegree.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID " + id);
	}
}