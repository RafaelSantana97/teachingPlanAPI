package edu.planner.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Period {
    MATUTINO((short) 1, "Matutino"),
    VERSPERTINO((short) 2, "Vespertino"),
    NOTURNO((short) 3, "Noturno");

    private final Short id;
    private final String description;

    public static Period toEnum(Short id) {
        return id == null ? null : findById(id);
    }

    private static Period findById(Short id) {
        return Arrays.stream(Period.values())
                .filter(ld -> id.equals(ld.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Invalid ID %s", id)));
    }
}