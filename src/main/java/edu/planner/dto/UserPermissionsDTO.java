package edu.planner.dto;

import edu.planner.enums.Profile;
import edu.planner.interfaces.IModel;
import edu.planner.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

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

    private Set<Profile> currentRoles;
    private Set<Profile> requestedRoles;

    public static UserPermissionsDTO toDTO(User user) {
        return new UserPermissionsDTO(user.getId(), user.getName(), user.getLevelDegree(), user.getEmail(),
                user.getProfiles(), user.getRequiredProfiles());
    }
}