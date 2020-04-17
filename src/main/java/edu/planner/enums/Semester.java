package edu.planner.enums;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Semester {

	SEMESTRE_1("S1", "1º Semester"),
	SEMESTRE_2("S2", "2º Semester");
	
	private final String id;
	private final String description;

	public static Semester toEnum(String id) {
		if (id == null) {
			return null;
		}
		
		for (Semester x : Semester.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID " + id);
	}
}