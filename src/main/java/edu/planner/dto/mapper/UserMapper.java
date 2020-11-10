package edu.planner.dto.mapper;

import edu.planner.dto.UserInsertDTO;
import edu.planner.dto.UserPermissionsDTO;
import edu.planner.dto.UserSimpleDTO;
import edu.planner.enums.Profile;
import edu.planner.models.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.HashSet;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {
    
    public static User from(UserInsertDTO user) {
        return new User(user.getId(), user.getName(), user.getLevelDegree(), user.getEmail(), user.getPassword(),
                new HashSet<>(), Profile.profilesToShorts(user.getRequestedRoles()));
    }

    public static UserSimpleDTO toSimpleDTO(@NotNull User user) {
        return new UserSimpleDTO(user.getId(), user.getName(), user.getLevelDegree());
    }

    public static User from(UserSimpleDTO user) {
        return new User(user.getId(), user.getName(), user.getLevelDegree());
    }

    public static UserPermissionsDTO toPermissionsDTO(User user) {
        return new UserPermissionsDTO(user.getId(), user.getName(), user.getLevelDegree(), user.getEmail(),
                user.getProfiles(), user.getRequiredProfiles());
    }
}
