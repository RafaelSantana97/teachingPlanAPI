package edu.planner.dto;

import java.io.Serializable;

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
public class UserPermissionsDTO implements Serializable, IModel {

    private static final long serialVersionUID = 1803107882712740450L;

    private Long id;

    private String name;

    private String levelDegree;

    private String email;

    private Boolean currentAdminRole;
    private Boolean currentCoordinatorRole;
    private Boolean currentTeacherRole;

    private Boolean requiredAdminRole;
    private Boolean requiredCoordinatorRole;
    private Boolean requiredTeacherRole;

    public static UserPermissionsDTO toDTO(User user) {
        return new UserPermissionsDTO(user.getId(), user.getName(), user.getLevelDegree(), user.getEmail(),
                user.getProfiles().contains(Profile.ADMIN), user.getProfiles().contains(Profile.COORDINATOR),
                user.getProfiles().contains(Profile.TEACHER), user.getRequiredProfiles().contains(Profile.ADMIN),
                user.getRequiredProfiles().contains(Profile.COORDINATOR),
                user.getRequiredProfiles().contains(Profile.TEACHER));
    }
}