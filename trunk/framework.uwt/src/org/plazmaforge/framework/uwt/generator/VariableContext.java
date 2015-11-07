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
package org.plazmaforge.framework.uwt.generator;

/**
 * @author ohapon
 *
 */
public class VariableContext {
    
    private String variable;
    
    private String variableClass;

    private String instanceRef;
    
    private boolean forceVariable;
    
    private boolean needPopulate;

    public VariableContext() {
    }

    public VariableContext(String variable, String variableClass) {
	this(variable, variableClass, false);
    }

    public VariableContext(String variable, String variableClass,  boolean forceVariable) {
	this.variable = variable;
	this.variableClass = variableClass;
	this.forceVariable = forceVariable;
    }

    public VariableContext(String variable, String variableClass,   String instanceRef) {
	this.variable = variable;
	this.variableClass = variableClass;
	this.instanceRef = instanceRef;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getVariableClass() {
        return variableClass;
    }

    public void setVariableClass(String variableClass) {
        this.variableClass = variableClass;
    }

    public String getInstanceRef() {
        return instanceRef;
    }

    public void setInstanceRef(String instanceRef) {
        this.instanceRef = instanceRef;
    }

    public boolean isForceVariable() {
        return forceVariable;
    }

    public void setForceVariable(boolean forceVariable) {
        this.forceVariable = forceVariable;
    }

    public boolean isNeedPopulate() {
        return needPopulate;
    }

    public void setNeedPopulate(boolean needPopulate) {
        this.needPopulate = needPopulate;
    }
    
    ////
    
    public void reset() {
	variable = null;
	variableClass = null;
	instanceRef = null;
	forceVariable = false;
	needPopulate = false;
    }
    
    
    

}
