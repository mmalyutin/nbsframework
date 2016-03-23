package org.plazmaforge.framework.core.data.validator.type;

import java.util.List;

import org.plazmaforge.framework.core.data.validator.AbstractValidator;
import org.plazmaforge.framework.core.data.validator.ExpressionValidator;
import org.plazmaforge.framework.core.data.validator.Message;
import org.plazmaforge.framework.core.data.validator.ValidationResult;
import org.plazmaforge.framework.core.data.validator.Validator;

public class NotEmpty extends AbstractValidator implements ExpressionValidator {

    public static final String NAME = Validator.NotEmpty;
    
    @Override
    public List<ValidationResult> validate(Object value, boolean breakOnError) {
	if (value == null || (value instanceof String && ((String) value).isEmpty())) {
	    return toResults(Message.MSG_NOT_EMPTY);
	}
	return null;
    }

    @Override
    public boolean accept(String expression) {
	return Validator.NotEmpty.equalsIgnoreCase(expression);
    }

    @Override
    public ExpressionValidator create(String expression) {
	return this;
    }

}
