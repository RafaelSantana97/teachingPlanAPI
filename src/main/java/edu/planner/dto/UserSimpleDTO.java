package edu.planner.dto;

import edu.planner.enums.LevelDegree;
import edu.planner.interfaces.IModel;
import edu.planner.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class UserSimpleDTO implements IModel {

    private Long id;

    @NotEmpty(message = "is required")
    private String name;

    @NotEmpty(message = "is required")
    private String levelDegree;

    public static UserSimpleDTO toDTO(@NotNull User user) {
        return new UserSimpleDTO(user.getId(), user.getName(), user.getLevelDegree());
    }

    public static User fromDTO(UserSimpleDTO user) {
        return new User(user.getId(), user.getName(), user.getLevelDegree());
    }

    public String getLevelDegree() {
        return LevelDegree.toEnum(levelDegree).getId();
    }
}