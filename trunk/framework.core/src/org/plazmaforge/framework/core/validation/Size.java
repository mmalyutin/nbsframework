package org.plazmaforge.framework.core.validation;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.util.StringUtils;

public class Size extends AbstractExpressionValidator {

    public static final String NAME = Validator.Size;
    
    private static final int NAME_LEN = Validator.Size.length();
    
    private static final int EMPTY_LEN = NAME_LEN + 2;
    
    private int minValue;
    
    private int maxValue = Integer.MAX_VALUE;
    
    
    public Size() {
    }
    
    public Size(int value) {
	super();
	this.minValue = value;
	this.maxValue = value;
    }

    public Size(int minValue, int maxValue) {
	this.minValue = minValue;
	this.maxValue = maxValue;
    }



    @Override
    public List<ValidationResult> validate(Object value, boolean breakOnError) {
	int objectSize = getObjectSize(value);
	if (objectSize >= minValue && objectSize <= maxValue) {
	    return null;
	}
	return toResults("Size of value must be " + ((minValue == maxValue) ? ("'" + minValue + "'") : ("'[" + minValue +  ", " + maxValue + "]'")));
    }

    @Override
    public boolean accept(String expression) {
	return startsWith(expression, Validator.Size + "(");
    }

    @Override
    public ExpressionValidator create(String expression) {
	if (!bracketsWith(expression, Validator.Size + "(", ")") || expression.length() == EMPTY_LEN) {
	    handleParseException(expression);
	}
	String prmString = expression.substring(NAME_LEN + 1, expression.length() - 1);
	String[] prms = StringUtils.split(prmString, ",");
	if (prms.length == 0 || prms.length > 2) {
	    handleParseException(expression);
	}
	Integer value = null;
	if (prms.length == 1) {
	    value = getInteger(prms[0]);
	    if (value == null) {
		handleParseException(expression);
	    }
	    return new Size(value);
	}
	int min = 0;
	int max = 0;
	value = getInteger(prms[0]);
	if (value == null) {
	     handleParseException(expression);
	}
	min = value;
	value = getInteger(prms[1]);
	if (value == null) {
	     handleParseException(expression);
	}
	max = value;
	return new Size(min, max);
    }
    
    private void handleParseException(String expression) {
	throw new RuntimeException("Size expression '" + expression + "' is invalid");
    }

    private int getObjectSize(Object obj) {
	if (obj == null) {
	    return 0;
	}
	if (obj instanceof String) { 
	    return ((String) obj).length();
	}
	if (obj instanceof Collection) { 
	    return ((Collection) obj).size();
	}
	if (obj instanceof Object[]) { 
	    return ((Object[]) obj).length;
	}
	if (obj instanceof Map) { 
	    return ((Map) obj).size();
	}
	return 0;
    }
}
