package edu.planner.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import edu.planner.enums.Profile;
import edu.planner.enums.SubjectType;
import edu.planner.interfaces.IModel;
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
public class SubjectInsertDTO implements IModel {

    private Long id;

    @NotEmpty(message = "is required")
    @Size(min = 5, max = 80, message = "Must be between 5 and 80 characters")
    private String name;

    @NotEmpty(message = "is required")
    @Size(max = 1)
    private String type;

    @ProfileConstraint(only = Profile.TEACHER)
    private UserSimpleDTO responsible;

    public static Subject fromDTO(SubjectInsertDTO subject) {
        return new Subject(subject.getId(), subject.getName(), subject.getType(),
                UserSimpleDTO.fromDTO(subject.getResponsible()));
    }

    public String getType() {
        return SubjectType.toEnum(type).getId();
    }
}