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

package org.plazmaforge.framework.uwt.swt.adapter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.Composite;


public class SWTRadioGroupAdapter extends SWTCompositeAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	org.eclipse.swt.widgets.Composite xParent = (org.eclipse.swt.widgets.Composite) getContent(parent.getDelegate());
	
	// To emulate RadioGroup we use Composite
	org.eclipse.swt.widgets.Composite xRadioGroup = new org.eclipse.swt.widgets.Composite(xParent, SWT.NONE);
	xRadioGroup.setLayout(new RowLayout(SWT.HORIZONTAL));
	addToParent(xParent, xRadioGroup, element);
	return xRadioGroup;
    }

    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	org.eclipse.swt.widgets.Composite xRadioGroup = (org.eclipse.swt.widgets.Composite) element.getDelegate();
	if (xRadioGroup == null) {
	    return;
	}
	if (Composite.PROPERTY_LAYOUT.equals(name)) {
	    // ignore
	    // TODO: Use horizontal/vertical orientation
	    return;
	}
	super.setProperty(element, name, value);
    }
    
}
