package edu.planner.enums;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unit-test")
class SubjectTypeTest {

    @Test
    void whenToEnumParamIsEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> SubjectType.toEnum(""),
                "Should throw an exception");
    }

    @Test
    void whenToEnumParamIsNull() {
        assertNull(SubjectType.toEnum(null), "Should return null value");
    }

    @Test
    void whenToEnumParamDoesNotExist() {
        assertThrows(IllegalArgumentException.class, () -> SubjectType.toEnum("not valid param"),
                "Should throw an exception");
    }

    @Test
    void whenGetDescription() {
        assertEquals("Prática", SubjectType.LABORATORIO.getDescription(),
                "Should return valid description");
    }
}