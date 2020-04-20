package edu.planner.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ModelValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ModelConstraint {
	String message() default "is required";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}