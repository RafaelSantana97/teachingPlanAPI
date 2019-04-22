package edu.planner.enums;

public enum Profile {

	// These 2 are just for searches and constraints
	ALL((short) -1, "ALL"),
	NONE((short) 0, "NONE"),
	
	ADMIN((short) 1, "ROLE_ADMIN"),
	COORDINATOR((short) 2, "ROLE_COORDINATOR"),
	TEACHER((short) 3, "ROLE_TEACHER");

	private Short id;
	private String description;

	private Profile(Short id, String description) {
		this.id = id;
		this.description = description;
	}

	public Short getId() {
		return id;
	}

	public String getDescription () {
		return description;
	}

	public static Profile toEnum(Short id) {

		if (id == null) {
			return null;
		}

		for (Profile x : Profile.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Invalid ID: " + id);
	}

}