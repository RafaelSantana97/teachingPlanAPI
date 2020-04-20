package edu.planner.enums;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unit-test")
class SemesterTest {

    @Test
    void whenToEnumParamIsEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> Semester.toEnum(""),
                "Should throw an exception");
    }

    @Test
    void whenToEnumParamIsValidString() {
        assertEquals(Semester.SEMESTRE_1, Semester.toEnum("S1"),
                "Should return enum");
    }

    @Test
    void whenToEnumParamIsNull() {
        assertNull(Semester.toEnum(null), "Should return null value");
    }

    @Test
    void whenToEnumParamDoesNotExist() {
        assertThrows(IllegalArgumentException.class, () -> Semester.toEnum("not valid param"),
                "Should throw an exception");
    }

    @Test
    void whenGetDescription() {
        assertEquals("2º Semester", Semester.SEMESTRE_2.getDescription(),
                "Should return valid description");
    }
}