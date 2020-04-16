package edu.planner.enums;

public enum SubjectType {

	TEORIA("T", "Teórica"),
	LABORATORIO("L", "Prática");

	private String id;
	private String description;

	private SubjectType(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static SubjectType toEnum(String id) {
		if (id == null) {
			return null;
		}

		for (SubjectType x : SubjectType.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID " + id);
	}
}