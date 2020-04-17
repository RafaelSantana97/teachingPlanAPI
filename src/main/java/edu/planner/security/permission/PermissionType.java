package edu.planner.security.permission;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum PermissionType {

	LIST((short) 1,"LIST"),
	CREATE((short) 2, "CREATE"),
	READ((short) 3, "READ"),
	UPDATE((short) 4,"UPDATE"),
	DELETE((short) 5,"DELETE");
	
	private final Short id;
	private final String description;

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