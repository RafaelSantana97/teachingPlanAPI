package edu.planner.validation;

import edu.planner.dto.UserSimpleDTO;
import edu.planner.enums.Profile;
import edu.planner.models.User;
import edu.planner.service.UserService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@RequiredArgsConstructor
class ProfileValidator implements ConstraintValidator<ProfileConstraint, UserSimpleDTO> {

    private final UserService userService;

    private Profile requiredProfile;

    @Override
    public void initialize(ProfileConstraint constraintAnnotation) {
        requiredProfile = constraintAnnotation.only();
    }

    @Override
    public boolean isValid(UserSimpleDTO user, ConstraintValidatorContext context) {
        return hasDTOValidId(user) && isUserValid(user);
    }

    private boolean hasDTOValidId(UserSimpleDTO user) {
        return user != null && isGreaterThanZero(user.getId());
    }

    private boolean isGreaterThanZero(Long i) {
        return Optional.ofNullable(i).orElse(0L) > 0L;
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