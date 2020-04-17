package edu.planner.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SubjectType {

	TEORIA("T", "Teórica"),
	LABORATORIO("L", "Prática");

	private final String id;
	private final String description;

	public static SubjectType toEnum(String id) {
		if (id == null) {
			return null;
		}

		for (SubjectType x : SubjectType.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID " + id);
	}
}