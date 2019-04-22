package edu.planner.models.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import edu.planner.enums.Profile;
import edu.planner.models.validation.ProfileConstraint.List;

@Target({ METHOD, FIELD })
@Retention(RUNTIME)
@Repeatable(List.class)
@Documented
@Constraint(validatedBy = ProfileValidator.class)
public @interface ProfileConstraint {
	String message() default "The user is incompatible with the required profile or doesn't exists";

	Profile only() default Profile.ALL;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ METHOD, FIELD })
	@Retention(RUNTIME)
	@Documented
	@interface List {

		ProfileConstraint[] value();
	}
}