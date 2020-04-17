package edu.planner.security.permission;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

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
		return id == null ? null : findById(id);
	}

	private static PermissionType findById(Short id) {
		return Arrays.stream(PermissionType.values())
				.filter(ld -> id.equals(ld.getId()))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.format("Invalid ID %s", id)));
	}
}