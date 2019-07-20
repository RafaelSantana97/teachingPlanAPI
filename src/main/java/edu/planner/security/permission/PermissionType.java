package edu.planner.security.permission;

public enum PermissionType {

	LIST((short) 1,"LIST"),
	CREATE((short) 2, "CREATE"),
	READ((short) 3, "READ"),
	UPDATE((short) 4,"UPDATE"),
	DELETE((short) 5,"DELETE");
	
	private Short id;
	private String description;

	private PermissionType(Short id, String description) {
		this.id = id;
		this.description = description;
	}

	public Short getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public static PermissionType toEnum(Short id) {
		if (id == null) {
			return null;
		}
		
		for (PermissionType x : PermissionType.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID " + id);
	}
}