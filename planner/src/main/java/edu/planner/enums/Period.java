package edu.planner.enums;

public enum Period {

	MATUTINO((short) 1, "Matutino"),
	VERSPERTINO((short) 2, "Vespertino"),
	NOTURNO((short) 3,"Noturno");
	
	private Short id;
	private String description;

	private Period(Short id, String description) {
		this.id = id;
		this.description = description;
	}

	public Short getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static Period toEnum(Short id) {
		if (id == null) {
			return null;
		}
		
		for (Period x : Period.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID " + id);
	}
}