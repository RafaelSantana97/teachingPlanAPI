package edu.planner.models.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import edu.planner.dto.UserSimpleDTO;
import edu.planner.enums.Profile;
import edu.planner.models.User;
import edu.planner.service.UserService;

public class ProfilesValidator implements ConstraintValidator<ProfilesConstraint, List<UserSimpleDTO>> {

	@Autowired
	UserService userService;

	Profile requiredProfile;

	@Override
	public void initialize(ProfilesConstraint constraintAnnotation) {
		requiredProfile = constraintAnnotation.only();
	}

	@Override
	public boolean isValid(List<UserSimpleDTO> users, ConstraintValidatorContext context) {

		if (users == null || users.size() == 0) {
			return false;
		}

		for (UserSimpleDTO user : users) {
			User object = userService.findOne(user.getId());

			if (object == null) {
				return false;
			}

			if (requiredProfile == Profile.ALL) {
				continue;
			}

			if (!object.getProfiles().stream().anyMatch(profile -> profile == requiredProfile)) {
				return false;
			}
		}
		
		return true;
	}
}