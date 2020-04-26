package edu.planner.validation;

import edu.planner.interfaces.IModel;
import edu.planner.models.Subject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ModelValidatorTest {

    private final ModelValidator modelValidator = new ModelValidator();
    private final IModel iModel = mock(Subject.class);

    @Test
    void whenIsValidParamIsNull() {
        when(iModel.getId()).thenReturn(null);
        boolean actualResult = modelValidator.isValid(iModel, null);

        assertFalse(actualResult, "Should be invalid");
    }

    @Test
    void whenIsValidParamIsZero() {
        when(iModel.getId()).thenReturn(0L);
        boolean actualResult = modelValidator.isValid(iModel, null);

        assertFalse(actualResult, "Should be invalid");
    }

    @Test
    void whenIsValidParamIsNegative() {
        when(iModel.getId()).thenReturn(-1L);
        boolean actualResult = modelValidator.isValid(iModel, null);

        assertFalse(actualResult, "Should be invalid");
    }

    @Test
    void whenIsValidParamIsPositive() {
        when(iModel.getId()).thenReturn(1L);
        boolean actualResult = modelValidator.isValid(iModel, null);

        assertTrue(actualResult, "Should be valid");
    }
}