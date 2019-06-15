package edu.planner.enums;

public enum Semester {

	SEMESTRE_1("S1", "1º Semester"),
	SEMESTRE_2("S2", "2º Semester");
	
	private String id;
	private String description;

	private Semester(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static Semester toEnum(String id) {
		if (id == null) {
			return null;
		}
		
		for (Semester x : Semester.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID " + id);
	}
}