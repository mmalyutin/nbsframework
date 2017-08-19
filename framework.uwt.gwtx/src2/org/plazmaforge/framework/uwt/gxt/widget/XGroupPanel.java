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

package org.plazmaforge.framework.uwt.gxt.widget;

import com.google.gwt.user.client.Element;

/**
 * Group Panel with text title
 * 
 * @author ohapon
 *
 */
public class XGroupPanel extends XContentPanel {

    private XText titleLabel;
    
    public XGroupPanel() {
	super();
	
	setHeaderVisible(false);
	setCollapsible(false);
	
	titleLabel = new XText();
	titleLabel.assigParent(this);
	
	titleLabel.setStyleAttribute("left", "4px");
	titleLabel.setStyleAttribute("top", "-10px");
	
	//titleLabel.setStyleAttribute("marginLeft", "4px");
	//titleLabel.setStyleAttribute("marginTop", "-10px");
	//titleLabel.setStyleAttribute("width", "100px");
	//titleLabel.setStyleAttribute("height", "20px");
	
	titleLabel.setStyleAttribute("position", "absolute");
	titleLabel.setStyleAttribute("backgroundColor", "white"); // TODO: Must use background color of container
	      
    }

    @Override
    protected void onRender(Element parent, int pos) {
      super.onRender(parent, pos);
      titleLabel.render(el().dom);
    }
    
    public void setTitle(String title) {
	String text = title;
	if (text == null) {
	    text = "";
	} else {
	    text = "&#160;" + text.trim() + "&#160;";
	}
	titleLabel.setText(text);
    }
    
    public String getTitle() {
	return titleLabel.getText();
    }
   
 
}
