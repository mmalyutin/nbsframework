package org.plazmaforge.framework.core.validation;

import java.util.List;

public class NotNull extends AbstractValidator implements ExpressionValidator {

    public static final String NAME = Validator.NotNull;
    
    @Override
    public List<ValidationResult> validate(Object value, boolean breakOnError) {
	return toResults(value == null ? Message.MSG_NOT_NULL: null);
    }

    @Override
    public boolean accept(String expression) {
	return Validator.NotNull.equalsIgnoreCase(expression);
    }

    @Override
    public ExpressionValidator create(String expression) {
	return this;
    }

}
