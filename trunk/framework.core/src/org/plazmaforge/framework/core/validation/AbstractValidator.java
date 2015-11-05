/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */

package org.plazmaforge.framework.core.validation;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractValidator implements Validator {

    @Override
    public boolean isValid(Object value) {
	// For isValid we use short mode of validation
	// breakOnError = true
	return validate(value, true) != null;
    }
    
    @Override
    public List<ValidationResult> validate(Object value) {
	// By default validation we use all validation 
	// breakOnError = false
	return validate(value, false);
    }
    
    protected List<ValidationResult> toResults(String errorMessage){
	ValidationResult result = toResult(errorMessage);
	if (result == null) {
	    return null;
	}
	List<ValidationResult> resultList = new ArrayList<ValidationResult>();
	resultList.add(result);
	return resultList;
    }
    
    protected ValidationResult toResult(String errorMessage) {
	if (errorMessage == null) {
	    return null;
	}
	ValidationResult result = new ValidationResult();
	result.setErrorMessage(errorMessage);
	return result;
    }
}
