package edu.planner.models.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

import edu.planner.interfaces.IModel;

public class ModelValidator implements ConstraintValidator<ModelConstraint, IModel> {

    @Override
    public boolean isValid(IModel value, ConstraintValidatorContext context) {
        return value != null && isGreaterThanZero(value.getId());
    }

    private boolean isGreaterThanZero(Long i) {
        return Optional.ofNullable(i).orElse(0L) > 0L;
    }
}