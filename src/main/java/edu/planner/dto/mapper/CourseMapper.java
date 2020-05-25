package edu.planner.dto.mapper;

import edu.planner.dto.CourseDTO;
import edu.planner.dto.SubjectDTO;
import edu.planner.dto.UserSimpleDTO;
import edu.planner.models.Course;
import edu.planner.models.Subject;
import edu.planner.models.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseMapper {

    public static CourseDTO to(Course course, List<SubjectDTO> subjects) {
        List<UserSimpleDTO> coordinators = course.getCoordinators().stream()
                .map(UserMapper::toSimpleDTO)
                .collect(Collectors.toList());

        return new CourseDTO(course.getId(), course.getName(), coordinators, subjects);
    }

    public static Course from(CourseDTO course) {
        List<User> coordinators = getCoordinatorsFromUserDTO(course.getCoordinators());
        List<Subject> subjects = getCheckedSubjectsFromDTO(course.getSubjects());

        return new Course(course.getId(), course.getName(), coordinators, subjects);
    }

    private static List<User> getCoordinatorsFromUserDTO(List<UserSimpleDTO> coordinatorsDTO) {
        return coordinatorsDTO.stream()
                .map(UserMapper::from)
                .collect(Collectors.toList());
    }

    private static List<Subject> getCheckedSubjectsFromDTO(List<SubjectDTO> subjectsDTO) {
        return subjectsDTO.stream()
                .filter(SubjectDTO::isChecked)
                .map(SubjectMapper::from)
                .collect(Collectors.toList());
    }
}
