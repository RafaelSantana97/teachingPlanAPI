package edu.planner.enums;

public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"),
	COORDINATOR(2, "ROLE_COORDINATOR"),
	TEACHER(3, "ROLE_TEACHER");

	private int id;
	private String description;

	private Perfil(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getDescription () {
		return description;
	}

	public static Perfil toEnum(Integer id) {

		if (id == null) {
			return null;
		}

		for (Perfil x : Perfil.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Invalid ID: " + id);
	}

}