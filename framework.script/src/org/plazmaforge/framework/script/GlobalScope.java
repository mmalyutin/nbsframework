/*
 * Copyright (C) 2012-2015 Oleh Hapon ohapon@users.sourceforge.net
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

package org.plazmaforge.framework.script;

import org.plazmaforge.framework.script.lang.LValue;

/**
 * Global (External) scope
 *  
 * @author ohapon
 *
 */
public class GlobalScope extends Scope {

    /**
     * External value provider (get/set value)
     */
    private VariableProvider variableProvider;

    
    public GlobalScope() {
	super();
    }


    public GlobalScope(VariableProvider variableProvider) {
	super();
	this.variableProvider = variableProvider;
    }


    public VariableProvider getVariableProvider() {
        return variableProvider;
    }

    
    public boolean exists(String var) {
	if (!isGlobalVariableMode(var)) {
	    return super.exists(var);
	}
	return variableProvider.existsVariable(var);
    }
    
    protected LValue getVariableValue(String var) {
	if (!isGlobalVariableMode(var)) {
	    return super.getVariableValue(var);
	}
	Object value = variableProvider.getVariableValue(var); // get NATIVE value form external environment
	LValue lValue = ValueAdapter.fromNativeValue(value);
	super.setVariableValue(var, lValue); // WARNING!

//	LValue lValue = super.getVariableValue(var);
//	if (lValue == null) {
//	    Object value = variableProvider.getVariableValue(var); // get NATIVE value form external environment
//	    lValue = ValueAdapter.fromNativeValue(value);
//	    super.setVariableValue(var, lValue); // WARNING!
//	}
	
	return lValue;
    }

    public void setVariableValue(String var, LValue value) {
	if (!isGlobalVariableMode(var)) {
	    super.setVariableValue(var, value);
	    return;
	}
	super.setVariableValue(var, value);
	Object nativeValue = ValueAdapter.toNativeValue(value);
	variableProvider.setVariableValue(var, nativeValue); // set NATIVE value to external environment (if it is possible)
    }

    protected boolean isGlobalVariable(String identifier) {
	return ScriptUtils.isGlobalVariable(identifier);
    }
    
    protected boolean isGlobalVariableMode(String identifier) {
	return variableProvider != null && ScriptUtils.isGlobalVariable(identifier);
    }
}
