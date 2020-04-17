package edu.planner.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SubjectType {
    TEORIA("T", "Teórica"),
    LABORATORIO("L", "Prática");

    private final String id;
    private final String description;

    public static SubjectType toEnum(String id) {
        return id == null ? null : findById(id);
    }

    private static SubjectType findById(String id) {
        return Arrays.stream(SubjectType.values())
                .filter(ld -> id.equals(ld.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Invalid ID %s", id)));
    }
}