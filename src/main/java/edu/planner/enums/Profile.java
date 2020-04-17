package edu.planner.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Profile {
	// These 2 are just for searches and constraints
	ALL((short) -1, "ALL"),
	NONE((short) 0, "NONE"),
	
	ADMIN((short) 1, "ROLE_ADMIN"),
	COORDINATOR((short) 2, "ROLE_COORDINATOR"),
	TEACHER((short) 3, "ROLE_TEACHER");

	private final Short id;
	private final String description;

	public static Profile toEnum(Short id) {
		return id == null ? null : findById(id);
	}

	private static Profile findById(Short id) {
		return Arrays.stream(Profile.values())
				.filter(ld -> id.equals(ld.getId()))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.format("Invalid ID %s", id)));
	}
}