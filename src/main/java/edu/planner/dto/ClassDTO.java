package edu.planner.dto;

import edu.planner.models.Class;
import edu.planner.models.Subject;
import edu.planner.models.User;
import edu.planner.models.validation.ModelConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassDTO {

    private Long id;

    @NotEmpty(message = "is required")
    @Size(min = 6, max = 10, message = "Must be between 6 and 10 characters")
    private String code;

    @Min(value = 1, message = "is required")
    @Max(value = 3, message = "is required")
    private Short period;

    @NotEmpty(message = "is required")
    @Size(min = 2, max = 2, message = "Must be 2 characters")
    private String semester;

    @Min(value = 1970, message = "is required")
    @Max(value = 9999, message = "is required")
    private Short year;

    @ModelConstraint
    private Subject subject;

    @ModelConstraint
    private User teacher;

    public static Class fromDTO(ClassDTO course) {
        return new Class(course.getId(), course.code, course.period, course.semester, course.year, course.subject, course.teacher);
    }
}