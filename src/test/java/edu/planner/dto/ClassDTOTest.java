package edu.planner.dto;

import edu.planner.enums.Period;
import edu.planner.enums.Semester;
import edu.planner.models.Class;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unit-test")
class ClassDTOTest {

    @Test
    void whenFromDTOHasValidValues() {
        ClassDTO classDTO = ClassDTO.builder()
                .id(10L)
                .code("P1NST0")
                .period(Period.MATUTINO.getId())
                .semester(Semester.SEMESTRE_1.getId())
                .year((short) 2020)
                .build();

        Class clazz = Class.builder()
                .id(10L)
                .code("P1NST0")
                .period(Period.MATUTINO.getId())
                .semester(Semester.SEMESTRE_1.getId())
                .year((short) 2020)
                .build();

        assertEquals(clazz, ClassDTO.fromDTO(classDTO), "Should return converted class");
    }

    @Test
    void whenFromDTOHasNullValues() {
        assertNull(ClassDTO.fromDTO(null), "Should return null");
    }
}