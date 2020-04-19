package edu.planner.models.validation;

import edu.planner.dto.UserSimpleDTO;
import edu.planner.enums.Profile;
import edu.planner.models.User;
import edu.planner.service.UserService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@RequiredArgsConstructor
public class ProfilesValidator implements ConstraintValidator<ProfilesConstraint, List<UserSimpleDTO>> {

    private final UserService userService;
    private Profile requiredProfile;

    @Override
    public void initialize(ProfilesConstraint constraintAnnotation) {
        requiredProfile = constraintAnnotation.only();
    }

    @Override
    public boolean isValid(List<UserSimpleDTO> users, ConstraintValidatorContext context) {
        return this.hasUsers(users) && users.stream().allMatch(this::isUserValid);
    }

    private boolean hasUsers(List<UserSimpleDTO> users) {
        return users != null && !users.isEmpty();
    }

    private boolean isUserValid(UserSimpleDTO user) {
        User checkedUser = userService.findOne(user.getId());
        return checkedUser != null && hasUserRequiredProfile(checkedUser);
    }

    private boolean hasUserRequiredProfile(User user) {
        return noRequiredProfile() || user.getProfiles().stream().anyMatch(requiredProfile::equals);
    }

    private boolean noRequiredProfile() {
        return requiredProfile == Profile.ALL;
    }
}