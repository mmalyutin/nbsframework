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

package org.plazmaforge.framework.uwt.widget;

public class CallbackResult implements IActionConstants {

    

    /**
     * Result action (Ok/Cancel/Yes/No/Abort/Retry)
     */
    private String action;

    /**
     * Select/input value
     */
    private Object value;
    
    /**
     * Select/input values
     */
    private Object[] values;

    
    public CallbackResult() {
    }

    public CallbackResult(boolean confirm) {
	this.action = getConfirmAction(confirm);
    }

    public CallbackResult(String action) {
	super();
	this.action = action;
    }

    public CallbackResult(String action, Object value) {
	super();
	this.action = action;
	this.value = value;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }
    
    /**
     * Return true if user selected Ok/Yes action
     * @return
     */
    public boolean isConfirm() {
	return isConfirmAction(action);
    }
    
    
    /**
     * Return true if user selected not Ok/Yes action
     * @return
     */
    public boolean isCancel() {
	return !isConfirm();
    }
    
    private static boolean check(String s1, String s2) {
	if (s1 == null || s2 == null) {
	    return false;
	}
	return s1.equalsIgnoreCase(s2);
    }
    
    /**
     * Return action ('Ok' or 'Cancel') by confirm flag
     * @param confirm
     * @return 'Ok' or 'Cancel'
     */
    public static String getConfirmAction(boolean confirm) {
	return confirm ? OK_ACTION : CANCEL_ACTION;
    }
    
    /**
     * Return true if action is 'Ok' or 'Yes' (ignore case)
     * @param action
     * @return
     */
    public static boolean isConfirmAction(String action) {
	return check(OK_ACTION, action) || check(YES_ACTION, action);
    }
    
    /**
     * Normalize confirm action.
     * If action is 'Ok' or 'Yes' (ignore case) then return 'Ok' else return 'Cancel' 
     * @param action
     * @return
     */
    public static String normalizeConfirmAction(String action) {
	boolean confirm = isConfirmAction(action);
	return getConfirmAction(confirm);
    }
    
    public static boolean isConfirm(CallbackResult result) {
	return result != null && result.isConfirm();
    }

}
