package edu.planner.dto.mapper;

import edu.planner.dto.CourseDTO;
import edu.planner.dto.SubjectDTO;
import edu.planner.dto.UserSimpleDTO;
import edu.planner.enums.LevelDegree;
import edu.planner.enums.Profile;
import edu.planner.models.Course;
import edu.planner.models.Subject;
import edu.planner.models.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class CourseMapperTest {

    @Test
    @Disabled
    @DisplayName("Given a Course object when it's mapped to DTO then return a valid one")
    void to() {
        SubjectDTO subj = new SubjectDTO();
        subj.setName("Química Geral");
        Course course = this.createCourse("Engenharia Química", "Joana Alquimista", "Química Geral");
        CourseDTO expectedCourseDTO = this.createCourseDTO("Engenharia Química", "Joana Alquimista");

        CourseDTO actualCourseDTO = CourseMapper.to(course, Collections.singletonList(subj));

        assertThat(actualCourseDTO).isEqualToComparingFieldByField(expectedCourseDTO);
    }

    @Test
    @Disabled
    @DisplayName("Given a Course DTO when it's mapped to original model then return a valid one")
    void from() {
        SubjectDTO subj = new SubjectDTO();
        subj.setName("História da Arte");
        CourseDTO courseDTO = this.createCourseDTO("Arquitetura", "Oscar Niemeyer");
        Course expectedCourseDTO = this.createCourse("Arquitetura", "Oscar Niemeyer", "História da Arte");

        Course actualCourse = CourseMapper.from(courseDTO);

        assertThat(actualCourse).isEqualToComparingFieldByField(expectedCourseDTO);
    }

    private Course createCourse(String name, String coordinatorName, String subjectName) {
        Subject subj = new Subject();
        subj.setName(subjectName);

        User user = new User();
        user.setId(4200L);
        user.setName(coordinatorName);
        user.getProfiles().add(Profile.COORDINATOR);
        user.setLevelDegree("Dr.");

        Course course = new Course();
        course.setId(1L);
        course.setName(name);
        course.setSubjects(Collections.singletonList(subj));
        course.setCoordinators(Collections.singletonList(user));

        return course;
    }

    private CourseDTO createCourseDTO(String name, String coordinatorName) {
        UserSimpleDTO user = new UserSimpleDTO(1L, coordinatorName, LevelDegree.DOUTOR.getId());

        CourseDTO course = new CourseDTO();
        course.setId(1L);
        course.setName(name);
        course.setCoordinators(Collections.singletonList(user));

        return course;
    }
}