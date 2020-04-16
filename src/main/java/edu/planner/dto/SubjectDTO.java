package edu.planner.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import edu.planner.enums.Profile;
import edu.planner.models.Subject;
import edu.planner.models.validation.ProfileConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {

	private Long id;

	@NotEmpty(message = "is required")
	@Size(min = 5, max = 80, message = "Must be between 5 and 80 characters")
	private String name;

	@NotEmpty(message = "is required")
	private String type;

	@ProfileConstraint(only = Profile.TEACHER)
	private UserSimpleDTO responsible;

	private boolean checked = false;

	public static SubjectDTO toDTO(Subject subject, boolean checked) {
		return new SubjectDTO(subject.getId(), subject.getName(), subject.getType(),
				UserSimpleDTO.toDTO(subject.getResponsible()), checked);
	}

	public static Subject fromDTO(SubjectDTO subject) {
		return new Subject(subject.getId(), subject.getName(), subject.getType(),
				UserSimpleDTO.fromDTO(subject.getResponsible()));
	}
}