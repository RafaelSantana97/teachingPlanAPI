package edu.planner.models.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import edu.planner.interfaces.IModel;

public class ModelValidator implements ConstraintValidator<ModelConstraint, IModel> {

	@Override
	public void initialize(ModelConstraint constraintAnnotation) {
	}

	@Override
	public boolean isValid(IModel value, ConstraintValidatorContext context) {

		return value != null && value.getId() != null && value.getId() > 0;
	}
}