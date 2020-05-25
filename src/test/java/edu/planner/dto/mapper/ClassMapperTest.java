package edu.planner.dto.mapper;

import edu.planner.dto.ClassDTO;
import edu.planner.enums.Period;
import edu.planner.enums.Semester;
import edu.planner.models.Class;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassMapperTest {

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

        assertEquals(clazz, ClassMapper.fromDTO(classDTO), "Should return converted class");
    }

    @Test
    void whenFromDTOHasNullValues() {
        assertNull(ClassMapper.fromDTO(null), "Should return null");
    }
}