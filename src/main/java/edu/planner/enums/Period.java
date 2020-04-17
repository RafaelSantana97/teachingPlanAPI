package edu.planner.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Period {

    MATUTINO((short) 1, "Matutino"),
    VERSPERTINO((short) 2, "Vespertino"),
    NOTURNO((short) 3, "Noturno");

    private final Short id;
    private final String description;

    public static Period toEnum(Short id) {
        if (id == null) {
            return null;
        }

        for (Period x : Period.values()) {
            if (id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid ID " + id);
    }
}