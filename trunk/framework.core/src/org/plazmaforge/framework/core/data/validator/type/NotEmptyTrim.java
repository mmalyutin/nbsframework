package org.plazmaforge.framework.core.data.validator.type;

import java.util.List;

import org.plazmaforge.framework.core.data.validator.AbstractValidator;
import org.plazmaforge.framework.core.data.validator.ExpressionValidator;
import org.plazmaforge.framework.core.data.validator.Message;
import org.plazmaforge.framework.core.data.validator.ValidationResult;
import org.plazmaforge.framework.core.data.validator.Validator;

public class NotEmptyTrim extends AbstractValidator implements ExpressionValidator {

    public static final String NAME = Validator.NotEmptyTrim;
    
    @Override
    public List<ValidationResult> validate(Object value, boolean breakOnError) {
	if (value == null) {
	    return toResults(Message.MSG_NOT_EMPTY);
	}
	if (value instanceof String) {
	    String str = (String) value;
	    if (str.isEmpty() || str.trim().isEmpty()) {
		return toResults(Message.MSG_NOT_EMPTY);
	    }
	}
	return null;
    }

    @Override
    public boolean accept(String expression) {
	return Validator.NotEmptyTrim.equalsIgnoreCase(expression);
    }

    @Override
    public ExpressionValidator create(String expression) {
	return this;
    }


}
