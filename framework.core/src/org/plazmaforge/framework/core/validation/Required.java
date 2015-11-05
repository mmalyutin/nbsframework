package org.plazmaforge.framework.core.validation;

public class Required extends NotEmpty {

    public static final String NAME = Validator.Required;
    
    @Override
    public boolean accept(String expression) {
	return Validator.Required.equalsIgnoreCase(expression);
    }

    @Override
    public ExpressionValidator create(String expression) {
	return this;
    }
}
