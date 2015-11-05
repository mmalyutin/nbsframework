package org.plazmaforge.framework.core.validation;

/**
 * Validator by expression
 * 
 * @author ohapon
 *
 */
public interface ExpressionValidator extends Validator {

    boolean accept(String expression);
    
    ExpressionValidator create(String expression);
    
}
