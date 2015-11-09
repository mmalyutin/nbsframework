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

import org.plazmaforge.framework.uwt.widget.Control;

public class SWTLayoutUtils {

    private SWTLayoutUtils() {
	super();
    }

    public static void prepare(org.eclipse.swt.widgets.Composite xParent, org.eclipse.swt.widgets.Control xControl, 
	    org.eclipse.swt.widgets.Layout xLayout, Object xLayoutData) {
	if (xParent == null || xControl == null || xLayout == null) {
	    return;
	}
	if (xLayout instanceof org.eclipse.swt.custom.StackLayout) {
	    org.eclipse.swt.custom.StackLayout xStackLayout = (org.eclipse.swt.custom.StackLayout) xLayout;
	    
	    // VERY IMPORTANT !!! IF TOP CONTROL IS NULL THEN CHILDREN ARE NOT VISIBLE !!!
	    // If topControl is null then set current control
	    if (xStackLayout.topControl == null) {
		xStackLayout.topControl = xControl;
	    }
	    
	}
    }
    
    public static boolean isCompatible(org.eclipse.swt.widgets.Layout xLayout, Object xLayoutData) {
	if (xLayout == null) {
	    return false;
	}
	SWTLayoutDataAdapter adapter = getAdapter(xLayout);
	if (adapter == null) {
	    //TODO WHY?
	    return true;
	}
	// TODO
	return adapter.isCompatible(xLayoutData);
    }

    public static Object createDefaultLayoutData(org.eclipse.swt.widgets.Layout xLayout, Control control) {
	if (xLayout == null) {
	    return null;
	}
	SWTLayoutDataAdapter adapter = getAdapter(xLayout);
	if (adapter == null) {
	    return null;
	}
	return adapter.createDefaultLayoutData(control);
    }
    
    /**
     * Get adapter of layoutData by XLayout
     * @param xLayout
     * @return
     */
    private static SWTLayoutDataAdapter getAdapter(org.eclipse.swt.widgets.Layout xLayout) {
	if (xLayout == null) {
	    return null;
	}
	//TODO: May create MAP to store adapters by class name of XLayout
	if (xLayout instanceof org.eclipse.swt.layout.GridLayout) {
	    return new SWTGridDataAdapter();
	} else if (xLayout instanceof org.eclipse.swt.layout.RowLayout) {
	    return new SWTBoxDataAdapter();
	}
	
	return null;
    }
}
