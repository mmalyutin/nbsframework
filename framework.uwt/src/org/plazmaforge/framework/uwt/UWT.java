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

package org.plazmaforge.framework.uwt;

import org.plazmaforge.framework.core.resource.NullResource;
import org.plazmaforge.framework.core.resource.ProxyResource;
import org.plazmaforge.framework.core.resource.Resource;
import org.plazmaforge.framework.core.resource.ResourceProvider;

public class UWT {

    private static String uiType;
    
    private static String locale;
    
    private static Resource resource;
    
    private static ResourceProvider resourceProvider;
    
  
    
    
    /**
     * Form type
     */
    
    public enum FormType {
	LIST, EDIT;
    }


    /**
     * Form mode
     */
    public enum FormMode {
	EDIT, VIEW, ADD, COPY;
    }
    
    
    public static int DEFAULT = -1;
    

    /**
     * Buttons
     */
    
    public static final int POPUP_BUTTON = 1 << 19;

    public static final int VIEW_BUTTON = 1 << 20;

    public static final int EDIT_BUTTON = 1 << 21;

    public static final int DELETE_BUTTON = 1 << 22;

    public static final int ADD_BUTTON = 1 << 25;

    public static final int EMPTY_BUTTON = 1 << 77;

    
    /////////////////////////////////////////////////////////////////////
    //
    // Devices
    //
    /////////////////////////////////////////////////////////////////////
    
    public static final int KEYBOARD_DEVICE = 1;
    
    public static final int MOUSE_DEVICE = 2;
    
    public static final int TOUCHPAD_DEVICE = 3;
    
    /////////////////////////////////////////////////////////////////////
    
    public static final String AWT_UI = "AWT";
    
    public static final String SWING_UI = "Swing";
    
    public static final String SWT_UI = "SWT";
    
    public static final String GWT_UI = "GWT";
    
    public static final String GXT_UI = "GXT";
    
    /////////////////////////////////////////////////////////////////////
    
    public static final String GEN_SUFFIX = "_Gen";
    
    public static String getUIType() {
        return uiType;
    }

    public static void setUIType(String type) {
	if (UWT.uiType != null) {
	    throw new IllegalArgumentException("UI type initialize only one");
	}
        UWT.uiType = type.trim();
    }
    
    public static boolean isUIType(String type) {
	if (type == null || UWT.uiType == null) {
	    return false;
	}
	return UWT.uiType.toUpperCase().equals(type.toUpperCase());
    }
    
    public static boolean isDesktop() {
	if (UWT.uiType == null) {
	    return true;
	}
	return UWT.uiType.equals(AWT_UI)  || UWT.uiType.equals(SWING_UI) || UWT.uiType.equals(SWT_UI); 
    }

    public static boolean isWeb() {
	if (UWT.uiType == null) {
	    return false;
	}
	return UWT.uiType.equals(GWT_UI) || UWT.uiType.equals(GXT_UI);
    }
    
    public static Resource getResource() {
	if (resource == null) {
	    if (resourceProvider != null) {
		resource = resourceProvider.getResource("org.plazmaforge.framework.uwt.resources.messages", getLocale());
	    }
	    resource = resource == null ? new NullResource() : new ProxyResource(resource);
	}
	return resource;
    }
    
    public static void setResourceProvider(ResourceProvider resourceProvider) {
	setResourceProvider(resourceProvider, null);
    }
    
    public static void setResourceProvider(ResourceProvider resourceProvider, String locale) {
	UWT.resourceProvider = resourceProvider; 
	if (locale != null) {
	    UWT.locale = locale;
	}
    }
    
    public static String getString(String key) {
	return getResource().getString(key);
    }

    public static String getLocale() {
        return locale;
    }

    public static void setLocale(String locale) {
        UWT.locale = locale;
    }

    
}
