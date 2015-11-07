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

/**
 * 
 */
package org.plazmaforge.framework.uwt.builder.widget;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.Window;

/**
 * @author ohapon
 *
 */
public class WindowBuilder extends CompositeBuilder {
    
    @Override
    public UIObject buildObject(IData data) {
	if (data == null) {
	    return null;
	}
	Window window = new Window();
	populate(data, window);
	return window;
    }

    @Override
    protected void populateCommon(IData data, UIObject element) {
	if (data == null) {
	    return;
	}
	
	super.populateCommon(data, element);
	
	Window window = (Window) element;
	
	// Title
	String title = getRSString(data, Window.PROPERTY_TITLE);
  	if (title != null) {
  	    window.setTitle(title);
  	}
  	
  	String iconPath = getRSString(data, Window.PROPERTY_ICON); // image attribute as image path
	if (iconPath != null) {
	    window.setIcon(iconPath);
	}  	

  	////
  	
  	Boolean modal = getBoolean(data, Window.PROPERTY_MODAL);
  	if (modal != null) {
  	    window.setModal(modal);
  	}

  	Boolean pack = getBoolean(data, Window.PROPERTY_PACK);
  	if (pack != null) {
  	    window.setPack(pack);
  	}

  	Boolean center = getBoolean(data, Window.PROPERTY_CENTER);
  	if (center != null) {
  	    window.setCenter(center);
  	}

  	Boolean resizable = getBoolean(data, Window.PROPERTY_RESIZABLE);
  	if (resizable != null) {
  	    window.setResizable(resizable);
  	}

  	Boolean closable = getBoolean(data, Window.PROPERTY_CLOSABLE);
  	if (closable != null) {
  	    window.setClosable(closable);
  	}

  	Boolean minimizable = getBoolean(data, Window.PROPERTY_MINIMIZABLE);
  	if (minimizable != null) {
  	    window.setMinimizable(minimizable);
  	}

  	Boolean maximizable = getBoolean(data, Window.PROPERTY_MAXIMIZABLE);
  	if (maximizable != null) {
  	    window.setMaximizable(maximizable);
  	}

  	Boolean undecorated = getBoolean(data, Window.PROPERTY_UNDECORATED);
  	if (undecorated != null) {
  	    window.setUndecorated(undecorated);
  	}

  	Boolean disposeWhenClose = getBoolean(data, Window.PROPERTY_DISPOSE_WHEN_CLOSE);
  	if (disposeWhenClose != null) {
  	    window.setDisposeWhenClose(disposeWhenClose);
  	}

  	
  	
    }
}
