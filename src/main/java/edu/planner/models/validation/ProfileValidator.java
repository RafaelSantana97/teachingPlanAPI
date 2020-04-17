package edu.planner.models.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import lombok.RequiredArgsConstructor;
import edu.planner.dto.UserSimpleDTO;
import edu.planner.enums.Profile;
import edu.planner.models.User;
import edu.planner.service.UserService;

@RequiredArgsConstructor
public class ProfileValidator implements ConstraintValidator<ProfileConstraint, UserSimpleDTO> {

    private final UserService userService;

    private Profile requiredProfile;

    @Override
    public void initialize(ProfileConstraint constraintAnnotation) {
        requiredProfile = constraintAnnotation.only();
    }

    @Override
    public boolean isValid(UserSimpleDTO user, ConstraintValidatorContext context) {

        if (user == null || user.getId() == null || user.getId() <= 0) {
            return false;
        }

        User object = userService.findOne(user.getId());

        if (object == null) {
            return false;
        }

        if (requiredProfile == Profile.ALL) {
            return true;
        }

        return object.getProfiles().stream().anyMatch(profile -> profile == requiredProfile);
    }
}