package edu.planner.dto;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import edu.planner.enums.Profile;
import edu.planner.models.Course;
import edu.planner.models.Subject;
import edu.planner.models.User;
import edu.planner.models.validation.ProfilesConstraint;
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

	@ProfilesConstraint(only = Profile.COORDINATOR)
	private List<UserSimpleDTO> coordinators = new ArrayList<>();

	private List<SubjectDTO> subjects = new ArrayList<>();

	public static CourseDTO toDTO(Course course, List<SubjectDTO> subjects) {
		List<UserSimpleDTO> coordinators = new ArrayList<>();
		course.getCoordinators().forEach(coord -> coordinators.add(UserSimpleDTO.toDTO(coord)));

		return new CourseDTO(course.getId(), course.getName(), coordinators, subjects);
	}

	public static Course fromDTO(CourseDTO course) {
		List<User> coordinators = new ArrayList<>();
		course.getCoordinators().forEach(coord -> coordinators.add(UserSimpleDTO.fromDTO(coord)));

		List<Subject> subjects = new ArrayList<>();
		course.getSubjects().forEach(sub -> {
			if (sub.isChecked()) {
				subjects.add(SubjectDTO.fromDTO(sub));
			}
		});

		return new Course(course.getId(), course.getName(), coordinators, subjects);
	}
}