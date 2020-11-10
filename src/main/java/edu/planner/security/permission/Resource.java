package edu.planner.security.permission;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Resource {

	COURSE((short) 1,"COURSE"),
	SUBJECT((short) 2, "SUBJECT"),
	CLASS((short) 3, "CLASS"),
	USER((short) 4,"USER");
	
	private final Short id;
	private final String description;

	public static Resource toEnum(Short id) {
		return id == null ? null : findById(id);
	}

	private static Resource findById(Short id) {
		return Arrays.stream(Resource.values())
				.filter(ld -> id.equals(ld.getId()))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.format("Invalid ID %s", id)));
	}
}