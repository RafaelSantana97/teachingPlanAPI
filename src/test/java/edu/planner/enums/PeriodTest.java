package edu.planner.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeriodTest {

    @Test
    void whenToEnumParamIsZero() {
        assertThrows(IllegalArgumentException.class, () -> Period.toEnum((short) 0), "Should throw an exception");
    }

    @Test
    void whenToEnumParamIsNull() {
        assertNull(Period.toEnum(null), "Should return null value");
    }

    @Test
    void whenToEnumParamIsValid() {
        assertEquals(Period.NOTURNO, Period.toEnum((short) 3), "Should return enum");
    }

    @Test
    void whenGetDescription() {
        assertEquals("Vespertino", Period.VERSPERTINO.getDescription(), "Should return valid description");
    }
}