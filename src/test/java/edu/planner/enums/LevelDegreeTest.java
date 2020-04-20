package edu.planner.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unit-test")
class LevelDegreeTest {

    @Test
    void whenToEnumParamIsEmptyString() {
        LevelDegree ld = LevelDegree.toEnum("");
        assertEquals(LevelDegree.NENHUM, ld, "No parameter means no Level Degree");
    }

    @Test
    void whenToEnumParamIsNull() {
        assertNull(LevelDegree.toEnum(null), "Should return null value");
    }

    @Test
    void whenToEnumParamDoesNotExist() {
        assertThrows(IllegalArgumentException.class, () -> LevelDegree.toEnum("not valid param"),
                "Should throw an exception");
    }

    @Test
    void whenGetDescription() {
        assertEquals("Doutor", LevelDegree.DOUTOR.getDescription(), "Should return valid name");
    }
}