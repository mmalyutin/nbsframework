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

package org.plazmaforge.framework.uwt.jfx.adapter;

import org.plazmaforge.framework.core.data.Callback;
import org.plazmaforge.framework.core.data.Notifier;
import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.desktop.DesktopItem;
import org.plazmaforge.framework.uwt.jfx.widget.XDesktop;
import org.plazmaforge.framework.uwt.jfx.widget.XDesktopItem;


/**
 * 
 * @author ohapon
 *
 */
public class JFXDesktopItemAdapter extends JFXContainerAdapter {
    

    public Object createDelegate(UIElement parent, UIElement element) {
	DesktopItem desktopItem = (DesktopItem) element;
	XDesktop xDesktop = (XDesktop) parent.getDelegate();
	
	String title = asSafeString(desktopItem.getTitle());
   	XDesktopItem xDesktopItem = xDesktop.createItem(title);
   	xDesktopItem.setNotifier(createNotifier(desktopItem));
   	
   	desktopItem.resetInitProperty(DesktopItem.PROPERTY_TITLE);
   	return xDesktopItem; 
   }


    @Override
    public void setProperty(UIElement element, String name, Object value) {
	XDesktopItem xDesktopItem = (XDesktopItem) element.getDelegate();
	if (xDesktopItem == null) {
	    return;
	}
	if (DesktopItem.PROPERTY_TITLE.equals(name)) {
	    xDesktopItem.setTitle(asSafeString(value));
	    return;
	} else if (DesktopItem.PROPERTY_CHILDREN.equals(name)) {
	    xDesktopItem.layout();
	    return;
	} 

	// BLOCK
	// super.setProperty(element, name, value);
    }

    @Override
    public Object getProperty(UIElement element, String name) {
	// TODO
	return null;
    }

    @Override
    public Object invoke(UIElement element, String methodName, Object[] args) {
	//BLOCK
	//return super.invoke(element, methodName, args);
	return null;
    }
    
    protected Notifier createNotifier(final DesktopItem desktopItem) {
   	// TODO: Must dispose in dispose method
   	Notifier notifier = new Notifier() {
   	    
   	    public void canNotify(String message, final Callback<Boolean> callback) {
   		desktopItem.canNotify(message, new Callback<Boolean>() {

   		    @Override
   		    public void onFailure(Throwable error) {
   			// do nothing
   		    }

   		    @Override
   		    public void onSuccess(Boolean result) {
   			if (callback == null) {
   			    return;
   			}
   			boolean can = result == null ? false: result;
   			callback.onSuccess(can);
   		    }
   		    
   		});
   	    }
   	    
   	    public void notify(String message) {
   		desktopItem.notify(message);
   	    }
   	    
   	};
   	return notifier;
   }
    
    
}
