package org.plazmaforge.framework.core.validation;

import java.util.List;

public interface Validator {

    String NotNull = "NotNull";
    
    String NotEmpty = "NotEmpty";
    
    String NotEmptyTrim = "NotEmptyTrim";
    
    String Required = "Required";
    
    String Min = "Min";
    
    String Max = "Max";
    
    String Range = "Range";
    
    String Size = "Size";
    
    String Pattern = "Pattern";
    
    
    boolean isValid(Object value);
    
    List<ValidationResult> validate(Object value);
    
    List<ValidationResult> validate(Object value, boolean breakOnError);
    
}
