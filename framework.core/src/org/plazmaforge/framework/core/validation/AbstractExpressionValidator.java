package org.plazmaforge.framework.core.validation;

import org.plazmaforge.framework.util.StringUtils;

public abstract class AbstractExpressionValidator extends AbstractValidator implements ExpressionValidator {

    protected Integer getInteger(String str) {
   	try {
   	    if (str == null) {
   		return null;
   	    }
   	    return Integer.valueOf(str.trim());
   	} catch (Exception ex) {
   	    return null;
   	}
    }
    
    protected boolean startsWith(String str, String prefix) {
	return StringUtils.startsWithIgnoreCase(str, prefix);
    }
    
    protected boolean endsWith(String str, String suffix) {
	return StringUtils.endsWithIgnoreCase(str, suffix);
    }
    
    protected boolean bracketsWith(String str, String prefix, String suffix) {
	return StringUtils.bracketsWithIgnoreCase(str, prefix, suffix);
    }
}
