package edu.planner.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import edu.planner.models.Course;
import edu.planner.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

	private Long id;

	@NotEmpty(message = "is required")
	@Size(min = 5, max = 80, message = "Must be between 5 and 80 characters")
	private String name;

	private List<User> coordinators = new ArrayList<User>();

	private List<SubjectDTO> subjects = new ArrayList<SubjectDTO>();

	public static CourseDTO toDTO(Course course, List<SubjectDTO> subjects) {
		return new CourseDTO(course.getId(), course.getName(), course.getCoordinators(), subjects);
	}

	public static Course fromDTO(CourseDTO subject) {
		return new Course(subject.getId(), subject.getName());
	}
}