package edu.planner.security.permission;

public enum Resource {

	COURSE((short) 1,"COURSE"),
	SUBJECT((short) 2, "SUBJECT"),
	CLASS((short) 3, "CLASS"),
	USER((short) 4,"USER");
	
	private Short id;
	private String description;

	private Resource(Short id, String description) {
		this.id = id;
		this.description = description;
	}

	public Short getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static Resource toEnum(Short id) {
		if (id == null) {
			return null;
		}
		
		for (Resource x : Resource.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID " + id);
	}
}