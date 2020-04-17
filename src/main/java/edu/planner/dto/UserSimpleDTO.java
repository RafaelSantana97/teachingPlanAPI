package edu.planner.dto;

import javax.validation.constraints.NotEmpty;

import edu.planner.enums.LevelDegree;
import edu.planner.interfaces.IModel;
import edu.planner.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserSimpleDTO implements IModel {

	private Long id;

	@NotEmpty(message = "is required")
	private String name;

	@NotEmpty(message = "is required")
	private String levelDegree;

	public static UserSimpleDTO toDTO(User user) {
		return new UserSimpleDTO(user.getId(), user.getName(), user.getLevelDegree());
	}

	public static User fromDTO(UserSimpleDTO user) {
		return new User(user.getId(), user.getName(), user.getLevelDegree());
	}

	public String getLevelDegree() {
		return LevelDegree.toEnum(levelDegree).getId();
	}
}