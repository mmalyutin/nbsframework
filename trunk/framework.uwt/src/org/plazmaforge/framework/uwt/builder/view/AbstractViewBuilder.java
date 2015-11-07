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

package org.plazmaforge.framework.uwt.builder.view;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.builder.widget.CompositeBuilder;
import org.plazmaforge.framework.uwt.view.View;
import org.plazmaforge.framework.uwt.widget.Composite;

public abstract class AbstractViewBuilder extends CompositeBuilder {

    protected abstract View createView(); 
    
    
    @Override
    public UIObject buildObject(IData data) {
	if (data == null) {
	    return null;
	}
	View view = createView();
	populate(data, view);
	return view;
    }
    
    @Override
    protected void populateCommon(IData data, UIObject element) {
	if (data == null) {
	    return;
	}
	
	super.populateCommon(data, element);
	
	View view = (View) element;
	
	// Title
	String title = getRSString(data, View.PROPERTY_TITLE);
	if (title != null) {
	    view.setTitle(title);
	}

    }    
    
    @Override
    protected void populateBody(IData data, UIObject element) {
	
	View view = (View) element;
	
	// HEADER
	// TODO
	
	// CONTENT
	IData contentNode = (IData) getValue(data, View.PROPERTY_CONTENT);
	if (contentNode != null) {
	    Composite content = view.getContent();
	    populateLayout(contentNode, content);
	    populateContentChildren(contentNode, content);	
	    
	}
	
	// FOOTER
	// TODO
	
	
	// MENU BAR
	// TODO
	
	// TOOL BAR
	// TODO
	
	// BUTTON BAR
	// TODO
    }    

}
