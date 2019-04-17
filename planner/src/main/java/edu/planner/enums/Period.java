package edu.planner.enums;

public enum Period {

	MATUTINO(1, "Matutino"),
	VERSPERTINO(2, "Vespertino"),
	NOTURNO(3,"Noturno");
	
	private int id;
	private String description;

	private Period(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static Period toEnum(Integer id) {
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