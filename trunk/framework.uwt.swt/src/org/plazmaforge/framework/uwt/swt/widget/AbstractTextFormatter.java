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

package org.plazmaforge.framework.uwt.swt.widget;

import java.awt.Toolkit;

import org.eclipse.swt.widgets.Text;


/**
 * Abstract base formatter
 * 
 * @author ohapon
 *
 */
public abstract class AbstractTextFormatter implements TextFormatter {

    
    private Class valueClass;
    
    public static final String TEXT_FORMATTER = "TEXT_FORMATTER";
    
    
    /** Exception text for invalid edit masks */
    protected static final String INVALID_PATTERN = "EditMask is invalid : "; //$NON-NLS-1$
    
    /** Exception text for invalid values */
    protected static final String INVALID_VALUE = "Invalid value"; //$NON-NLS-1$
    
    /** Space character */
    protected static final char SPACE = ' ';
    
    /** Empty String */
    protected static final String EMPTY = ""; //$NON-NLS-1$
    
    
    /**
     * UI Text control
     */
    protected Text text;
    
    protected boolean ignore;
    
    
    protected void beep() {
	    Toolkit.getDefaultToolkit().beep();
    }
    
    public void detach() {
	
    }
    

    /**
     * Set UI Text control
     */
    public void setText(Text text) {
        this.text = text;
    }

    public void setIgnore(boolean ignore) {
        this.ignore = ignore;
    }
    
    protected void updateText(String str) {
	updateText(str, text.getCaretPosition());
    }
    
    
    protected void updateText(String str, int pos) {
	ignore = true;
        text.setText(str);
        text.setSelection(pos);
        ignore = false;
    }

    public Class getValueClass() {
        return valueClass;
    }

    public void setValueClass(Class valueClass) {
        this.valueClass = valueClass;
    }
    
    /**
     * Initialize formatter
     * @param text
     * @param formatter
     */
    public static void initFormatter(Text text, TextFormatter formatter) {
	if (text == null) {
	    return;
	}
	
	String displayString = null;
	
	// Detach formatter
	TextFormatter oldFormatter = getFormatter(text);
	if ( oldFormatter != null ) {
	    displayString = oldFormatter.getDisplayString();
	    text.removeVerifyListener(oldFormatter);
	    oldFormatter.detach();
	    if (formatter != null && formatter.getValueClass() == null) {
		formatter.setValueClass(oldFormatter.getValueClass());
	    }
    	}
	
	// Attach formatter
        setFormatter(text, formatter);
        
        if (formatter == null) {
            return;
        }
        formatter.setText(text);
        
        text.addVerifyListener(formatter);
        formatter.setIgnore(true);
        
        if (oldFormatter == null) {
            displayString = formatter.getDisplayString();
        }
        text.setText(displayString);
        formatter.setIgnore(false);
    }
    
    
    public static void setValue(Text text, TextFormatter formatter, Object value) {
	if (text == null) {
	    return;
	}
	if (formatter == null) {
	    text.setText(value == null ? "" : value.toString());
	    return;
	}
        formatter.setValue(value);
        formatter.setIgnore(true);
        String strValue = text.isFocusControl() ? formatter.getEditString() : formatter.getDisplayString();
        text.setText(strValue);
        formatter.setIgnore(false);
    }
    
    public static void setValue(Text text, Object value) {
	TextFormatter formatter = getFormatter(text);
	setValue(text, formatter, value);
    }
    
    public static Object getValue(Text text) {
	if (text == null) {
	    return null;
	}
	TextFormatter formatter = getFormatter(text);
	return formatter == null ? text.getText() : formatter.getValue();
    }
    

    public static TextFormatter getFormatter(Text text) {
	return text == null ? null : (TextFormatter) text.getData(TEXT_FORMATTER); 
    }
    
    public static void setFormatter(Text text, TextFormatter formatter) {
	text.setData(TEXT_FORMATTER, formatter); 
    }

}
