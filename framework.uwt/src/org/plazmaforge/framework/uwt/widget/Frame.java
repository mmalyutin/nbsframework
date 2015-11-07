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

import org.plazmaforge.framework.uwt.Application;
import org.plazmaforge.framework.uwt.widget.menu.MenuBar;

/**
 * The Frame if top-level window with title and MenuBar
 * 
 * @author ohapon
 *
 */
public class Frame extends Window {

    private MenuBar menuBar;

    public Frame() {
	super();
	setMinimizable(true); // By default Iconified button (hide t tray)
	setMaximizable(true); // By default Min/Max button
    }


    /**
     * Return true if the frame is root frame of the application
     * @return
     */
    public boolean isRootFrame() {
	Application application = getOwnApplication();
	return application != null && this == application.getFrame(); 
    }



    public MenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;
        menuBar.setParent(this);
        fireChangeProperty(PROPERTY_MENU_BAR, menuBar);
    }
    
    
}
