package edu.planner.dto.mapper;

import edu.planner.dto.ClassDTO;
import edu.planner.models.Class;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClassMapper {

    public static Class fromDTO(ClassDTO course) {
        return (course == null) ? null :
                Class.builder()
                        .id(course.getId())
                        .code(course.getCode())
                        .period(course.getPeriod())
                        .semester(course.getSemester())
                        .year(course.getYear())
                        .subject(course.getSubject())
                        .teacher(course.getTeacher())
                        .build();
    }
}
