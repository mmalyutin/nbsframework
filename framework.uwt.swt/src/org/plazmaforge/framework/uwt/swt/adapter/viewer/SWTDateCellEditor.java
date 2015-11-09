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

package org.plazmaforge.framework.uwt.swt.adapter.viewer;

import java.util.Date;

import org.eclipse.jface.util.Assert;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.plazmaforge.framework.uwt.swt.widget.XDateField;

public class SWTDateCellEditor extends SWTTextCellEditor {

    private XDateField xDateField;
    
    private String pattern;
    
    public SWTDateCellEditor() {
	super();
    }


    public SWTDateCellEditor(Composite parent, int style) {
	super(parent, style);
    }


    public SWTDateCellEditor(Composite parent) {
	super(parent);
    }


    protected Control createControl(Composite parent) {
	xDateField = new XDateField(parent, getStyle());
	if (pattern != null) {
	    xDateField.setPattern(pattern);
	}
	xDateField.addSelectionListener(new SelectionAdapter() {
	    public void widgetSelected(SelectionEvent e) {
		markDirty();
		doSetValue(xDateField.getDate());
		fireApplyEditorValue();
		
		
		//focusLost();
		//handleDefaultSelection(e);
	    }
	});
	control = xDateField;
	textControl = xDateField.getTextControl();
	initTextControl(parent);
	return control;
    }
    
    
    protected void focusLost() {
	//TODO: focusLost is disabled because after click by calendar button
	//      the editor lost focus and edit mode is disabled
	
	//if (xDateField.isDropping()) {
	//    return;
	//}
	//super.focusLost();
    }
    
    protected Object doGetValue() {
	return xDateField.getDate();
    }

    protected void doSetValue(Object value) {
	Assert.isTrue(textControl != null && (value instanceof Date));
	textControl.removeModifyListener(getModifyListener());
	xDateField.setValue((Date) value);
	textControl.addModifyListener(getModifyListener());
    }
    
    protected boolean dependsOnExternalFocusListener() {
	return getClass() != SWTDateCellEditor.class;
    }

    public void setPattern(String pattern) {
	this.pattern = pattern;
	if (xDateField == null) {
	    return;
	}
	xDateField.setPattern(pattern);
    }
}
