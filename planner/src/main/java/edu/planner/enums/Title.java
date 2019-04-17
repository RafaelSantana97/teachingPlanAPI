package edu.planner.enums;

public enum Title {

	NENHUM("", "Nenhum"),
	ESPECIALISTA("Esp.", "Especialista"),
	MESTRE("Me.", "Mestre"),
	DOUTOR("Dr.", "Doutor");

	private String id;
	private String description;

	private Title(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static Title toEnum(String id) {
		if (id == null) {
			return null;
		}

		for (Title x : Title.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID " + id);
	}
}