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

import org.eclipse.jface.viewers.LabelProvider;
import org.plazmaforge.framework.core.data.Accessor;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.swt.adapter.SWTHelper;
import org.plazmaforge.framework.uwt.util.UWTUtils;
import org.plazmaforge.framework.uwt.widget.Column;
import org.plazmaforge.framework.uwt.widget.IViewer;

public class SWTLabelProvider extends LabelProvider {

    public static final String EMPTY_STRING = "";
    
    
    
    protected Accessor getAccessor(Object obj, String property) {
	return UWTUtils.getAccessor(obj, property);
    }

    
    protected Accessor createAccessor(Class<?> entityClass, String property) {
	return Accessor.getAccessor(entityClass, property);
    }
    
    protected Object getValue(Object obj, Accessor accessor) {
	return UWTUtils.getValue(obj, accessor);
    }
    
    protected Accessor getAccessor(Object obj, Column column) {
	return UWTUtils.getAccessor(obj, column);
    }
    
    protected String getTextValue(Object value, Column column) {
	return UWTUtils.getTextValue(value, column);
    }

    protected String getTextValue(Object value, IViewer<?> viewer) {
	return UWTUtils.getTextValue(value, viewer);
    }

    protected Accessor getAccessor(Object obj, String property, IViewer<?> viewer) {
 	return UWTUtils.getAccessor(obj, property, viewer);
    }

    ////
    
    protected String getText(Object element, IViewer<?> viewer, Column column) {
	
	// Get column
	if (column == null) {
	    return EMPTY_STRING;
	}

	String text = null; 
		
	// Get LabelProvider
	org.plazmaforge.framework.uwt.widget.LabelProvider labelProvider = column.getLabelProvider();
	if  (labelProvider != null) {
	    text = getText(element, viewer, labelProvider);
	    if (text == null) {
		text = EMPTY_STRING;
	    }
	    return text;
	}

	Object value = UWTUtils.getValue(element, viewer, column);
	
	// Text value (format)
	text = getTextValue(value, column);
	if (text == null) {
	    text = EMPTY_STRING;
	}
	
	return text; 
    }
    
    protected org.eclipse.swt.graphics.Image getImage(Object element, IViewer<?> viewer, Column column) {
	
	// Get column
	if (column == null) {
	    return null;
	}
	
	// Get LabelProvider
	org.plazmaforge.framework.uwt.widget.LabelProvider labelProvider = column.getLabelProvider();
	if (labelProvider != null) {
	    return getImage(element, viewer, labelProvider);
	}
	return null;  
    }
    
    ////
    
    protected String getText(Object element, IViewer<?> viewer, org.plazmaforge.framework.uwt.widget.LabelProvider labelProvider) {
	
	if (labelProvider == null) {
	    return null;
	}
	
	return labelProvider.getText(element);
    }    
    
    protected org.eclipse.swt.graphics.Image getImage(Object element, IViewer<?> viewer, org.plazmaforge.framework.uwt.widget.LabelProvider labelProvider) {
	
	if (labelProvider == null) {
	    return null;
	}
	
	// Get Image
	Image image = labelProvider.getImage(element);
	if (image == null) {
	    return null;
	}
	
	// Create SWT image
	return SWTHelper.createImage((UIObject) viewer, image);
    }
    
}
