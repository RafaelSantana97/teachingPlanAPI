package edu.planner.dto;

import edu.planner.enums.LevelDegree;
import edu.planner.interfaces.IModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class UserSimpleDTO implements IModel {

    private Long id;

    @NotEmpty(message = "is required")
    private String name;

    @NotEmpty(message = "is required")
    private String levelDegree;

    public String getLevelDegree() {
        return LevelDegree.toEnum(levelDegree).getId();
    }
}