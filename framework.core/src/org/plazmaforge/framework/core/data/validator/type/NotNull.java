package org.plazmaforge.framework.core.data.validator.type;

import java.util.List;

import org.plazmaforge.framework.core.data.validator.AbstractValidator;
import org.plazmaforge.framework.core.data.validator.ExpressionValidator;
import org.plazmaforge.framework.core.data.validator.Message;
import org.plazmaforge.framework.core.data.validator.ValidationResult;
import org.plazmaforge.framework.core.data.validator.Validator;

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
