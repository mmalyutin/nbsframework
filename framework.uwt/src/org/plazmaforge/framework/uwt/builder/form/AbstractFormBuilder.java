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

package org.plazmaforge.framework.uwt.builder.form;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.builder.widget.CompositeBuilder;
import org.plazmaforge.framework.uwt.form.Form;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Frame;
import org.plazmaforge.framework.uwt.widget.tool.ToolBar;

public abstract class AbstractFormBuilder extends CompositeBuilder {

    protected abstract Form<?> createForm(); 
    
    
    @Override
    public UIObject buildObject(IData data) {
	if (data == null) {
	    return null;
	}
	Form<?> form = createForm();
	populate(data, form);
	return form;
    }
    
    @Override
    protected void populateCommon(IData data, UIObject element) {
	if (data == null) {
	    return;
	}
	
	super.populateCommon(data, element);
	
	Form<?> form = (Form<?>) element;
	
	// Title
	String title = getRSString(data, Form.PROPERTY_TITLE);
	if (title != null) {
	    form.setTitle(title);
	}

    }
    
    @Override
    protected void populateBody(IData data, UIObject element) {
	Form<?> form = (Form<?>) element;
	
	
	// STUB
	form.init();
	
	// Assign form resource
	assignResource(data, element);
	
	// TODO
	// MENU BAR
	//MenuBar menuBar = (MenuBar) buildNodeObject(data, Frame.PROPERTY_MENU_BAR);
	//if (menuBar != null) {
	//   form.setMenuBar(menuBar);
	//}
	
	// TOOL BAR
	ToolBar toolBar = (ToolBar) buildNodeObject(data, Frame.PROPERTY_TOOL_BAR);
	if (toolBar != null) {
	    form.setToolBar(toolBar);
	}
		
	// HEADER
	// TODO
	
	// CONTENT
	IData contentNode = getDataValue(data, Form.PROPERTY_CONTENT);
	if (contentNode != null) {
	    Composite content = form.getContent();
	    populateLayout(contentNode, content);
	    populateContentChildren(contentNode, content);	
	}
	
	// FOOTER
	// TODO
	
	
	
	// BUTTON BAR
	// TODO
    }

}
