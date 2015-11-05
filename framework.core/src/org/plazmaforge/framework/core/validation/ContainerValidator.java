package org.plazmaforge.framework.core.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * The container of validators
 * @author ohapon
 *
 */
public class ContainerValidator extends AbstractValidator implements Validator {

    
    private List<Validator> validators = new ArrayList<Validator>();
    
    public void addValidator(Validator validator) {
	validators.add(validator);
    }
    
    public void removeValidator(Validator validator) {
	validators.remove(validator);
    }
    
  
    
    @Override
    public List<ValidationResult> validate(Object value, boolean breakOnError) {
	if (validators.isEmpty()) {
	    return null;
	}
	List<ValidationResult> result = new ArrayList<ValidationResult>();
	for (Validator validator: validators) {
	    List<ValidationResult> r = validator.validate(value);
	    if (r != null && !r.isEmpty()){
		result.addAll(r);
		if (breakOnError) {
		    break;
		}
	    }
	}
	return result.isEmpty() ? null : result;
    }


}
