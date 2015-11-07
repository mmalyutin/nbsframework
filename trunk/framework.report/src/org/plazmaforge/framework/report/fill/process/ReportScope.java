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

/**
 * 
 */
package org.plazmaforge.framework.report.fill.process;

import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.datastorage.DSParameter;
import org.plazmaforge.framework.core.datastorage.DSVariable;
import org.plazmaforge.framework.core.datastorage.data.Scope;


/**
 * @author ohapon
 *
 */
public class ReportScope extends Scope {
    
    
    private int recordNo;
    
    
    private DSParameter[] parameters;
    
    private DSVariable[] variables;
    
    private DSField[] fields;    
    
    public ReportScope() {
    }

    public boolean isNewGroup(Object oldValue, Object newValue) {
	return !equalsScopeValues(oldValue, newValue);
    }
    

    public int getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(int recordNo) {
        this.recordNo = recordNo;
    }

    public DSVariable[] getVariables() {
        return variables;
    }

    public void setVariables(DSVariable[] variables) {
        this.variables = variables;
    }

    public DSParameter[] getParameters() {
        return parameters;
    }

    public void setParameters(DSParameter[] parameters) {
        this.parameters = parameters;
    }

    public DSField[] getFields() {
        return fields;
    }

    public void setFields(DSField[] fields) {
        this.fields = fields;
    }
    
    
}
