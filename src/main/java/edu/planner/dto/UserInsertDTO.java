package edu.planner.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import edu.planner.enums.LevelDegree;
import edu.planner.enums.Profile;
import edu.planner.interfaces.IModel;
import edu.planner.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInsertDTO implements Serializable, IModel {

	private static final long serialVersionUID = -7534934355364959330L;

	private Long id;

	@NotEmpty(message = "is required")
	private String name;

	@NotNull(message = "is required")
	private String levelDegree;

	@NotEmpty(message = "is required")
	@Email
	private String email;

	@NotEmpty(message = "is required")
	private String password;

	private Boolean requireAdminRole;
	private Boolean requireTeacherRole;
	private Boolean requireCoordinatorRole;

	public static User fromDTO(UserInsertDTO user) {
		Set<Short> requiredProfiles = new HashSet<>();

		if (user.getRequireAdminRole())
			requiredProfiles.add(Profile.ADMIN.getId());
		if (user.getRequireTeacherRole())
			requiredProfiles.add(Profile.TEACHER.getId());
		if (user.getRequireCoordinatorRole())
			requiredProfiles.add(Profile.COORDINATOR.getId());

		return new User(user.getId(), user.getName(), user.getLevelDegree(), user.getEmail(), user.getPassword(),
				new HashSet<>(), requiredProfiles);
	}

	public String getLevelDegree() {
		return LevelDegree.toEnum(levelDegree).getId();
	}
}