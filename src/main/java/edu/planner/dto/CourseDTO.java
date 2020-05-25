package edu.planner.dto;

import edu.planner.enums.Profile;
import edu.planner.validation.ProfilesConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

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
}