package edu.planner.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

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
        return id == null ? null : findById(id);
    }

    private static LevelDegree findById(String id) {
        return Arrays.stream(LevelDegree.values())
                .filter(ld -> id.equals(ld.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Invalid ID %s", id)));
    }
}