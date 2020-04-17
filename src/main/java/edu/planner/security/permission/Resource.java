package edu.planner.security.permission;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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