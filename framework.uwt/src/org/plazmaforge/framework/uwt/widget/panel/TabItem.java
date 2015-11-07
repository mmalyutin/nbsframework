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

package org.plazmaforge.framework.uwt.widget.panel;

import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.HasTitleIcon;
import org.plazmaforge.framework.uwt.widget.Widget;

public class TabItem extends Composite implements HasTitleIcon {

    /**
     * Title of item
     */
    private String title;
    
    /**
     * Icon of item
     */
    private Image icon;
    
    
    
    public TabItem() {
    }
    
    public TabItem(String title) {
	setTitle(title);
    }


    public TabPanel getTabPanel() {
	return (TabPanel) getParent();
    }
    
    
    public void setParent(Widget parent) {
	if (!(parent instanceof TabPanel)) {
	    throw new IllegalArgumentException("Parent must be TabPanel");
	}
	super.setParent(parent);
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
        fireChangeProperty(PROPERTY_TITLE, title);
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
        fireChangeProperty(PROPERTY_ICON, icon);
    }

    public void setIcon(String path) {
        if (icon == null) {
	    icon = new Image();
	}
        icon.setPath(path);
        fireChangeProperty(PROPERTY_ICON_PATH, path);
    }

    
    
}
