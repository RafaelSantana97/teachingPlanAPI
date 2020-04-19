package edu.planner.dto;

import edu.planner.enums.LevelDegree;
import edu.planner.enums.Profile;
import edu.planner.interfaces.IModel;
import edu.planner.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

	private Set<Profile> requestedRoles;

	public static User fromDTO(UserInsertDTO user) {
		return new User(user.getId(), user.getName(), user.getLevelDegree(), user.getEmail(), user.getPassword(),
				new HashSet<>(), Profile.profilesToShorts(user.requestedRoles));
	}

	public String getLevelDegree() {
		return LevelDegree.toEnum(levelDegree).getId();
	}
}