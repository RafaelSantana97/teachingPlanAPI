package edu.planner.enums;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Semester {
	SEMESTRE_1("S1", "1º Semester"),
	SEMESTRE_2("S2", "2º Semester");
	
	private final String id;
	private final String description;

	public static Semester toEnum(String id) {
		return id == null ? null : findById(id);
	}

	private static Semester findById(String id) {
		return Arrays.stream(Semester.values())
				.filter(ld -> id.equals(ld.getId()))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.format("Invalid ID %s", id)));
	}
}