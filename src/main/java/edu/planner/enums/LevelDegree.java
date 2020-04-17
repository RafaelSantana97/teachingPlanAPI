package edu.planner.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum LevelDegree {

	NENHUM("", "Nenhum"),
	ESPECIALISTA("Esp.", "Especialista"),
	MESTRE("Me.", "Mestre"),
	DOUTOR("Dr.", "Doutor");

	private final String id;
	private final String description;

	public static LevelDegree toEnum(String id) {
		if (id == null) {
			return null;
		}

		for (LevelDegree x : LevelDegree.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID " + id);
	}
}