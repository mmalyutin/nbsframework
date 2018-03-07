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

import java.util.HashMap;
import java.util.Map;

import org.plazmaforge.framework.uwt.widget.Label;


/**
 * The Adapter Factory creates a Adapter
 * 
 * @author ohapon
 *
 */
public class UIAdapterFactory {
    
    public static final int DEFAULT_TRY_COUNT = 10; // 4
    
    private static Map<String, UIAdapter> adapters = new HashMap<String, UIAdapter>(); 
    
    private static int tryCount = DEFAULT_TRY_COUNT;
    
    private static boolean useUnknownAdapter = true;
    
    public static int getTryCount() {
        return tryCount;
    }

    public static void setTryCount(int tryCount) {
	if (tryCount < 1) {
	    new IllegalArgumentException("TryCount must be > 0");
	}
        UIAdapterFactory.tryCount = tryCount;
    }

    public static void setUseUnknownAdapter(boolean useUnknownAdapter) {
        UIAdapterFactory.useUnknownAdapter = useUnknownAdapter;
    }

    public static UIAdapter getAdapter(Class<?> uiObjectClass) {	
	if (uiObjectClass == null) {
	    return null;
	}
	int tryCount = getTryCount();
	//String stopClassName = null;
	String thisClassName = uiObjectClass.getName();
	Class<?> curObjectClass = uiObjectClass;
	
	for (int i = 0; i < tryCount; i++) {
	    
	    // Check 'curObjectClass' because 'getSuperclass()' can return null.
	    if (curObjectClass == null) {
		return getUnknownAdapter(thisClassName);
		//throw new UWTException("UIAdapter not found for class '" + thisClassName + "'");
	    }
	    String className = curObjectClass.getName();
	    
	    UIAdapter adapter = adapters.get(className);
	    if (adapter != null) {
		return adapter;
	    }
	    
	    //disable
	    //if (stopClassName != null && stopClassName.equals(className)) {
		//break;
	    //}
	    
	    curObjectClass = curObjectClass.getSuperclass();
	    
	}
	//throw new UWTException("UIAdapter not found for class '" + thisClassName + "'");
	return getUnknownAdapter(thisClassName);
    }
    
    private static UIAdapter getUnknownAdapter(String thisClassName) {
	if (!useUnknownAdapter) {
	    throwNotFounException(thisClassName);
	    return null;
	}
	UIAdapter adapter = createUnknownAdapter();
	if (adapter != null) {
	    return adapter;
	}
	throwNotFounException(thisClassName);
	return null;
    }
    
    private static UIAdapter createUnknownAdapter() {
	UIAdapter adapter = adapters.get(Label.class.getName());
	if (adapter == null) {
	    return null;
	}
	UIAdapter adapterWrapper = new UIAdapterWrapper(adapter) {
	    
	    @Override
	    public Object createDelegate(UIElement parent, UIElement element) {
		
		// Set UNKNOWN text
		//if (element != null && element instanceof Label) {
		//    Label label = (Label) element;
		//    label.setText("[UNKNOWN]");
		//}
		
		Label label = new Label("[UNKNOWN]");
		//label.setText("[UNKNOWN]");
		    
		return super.createDelegate(parent, label);
	    }

	};
	return adapterWrapper;
    }
  
    private static void throwNotFounException(String thisClassName) {
	throw new UWTException("UIAdapter not found for class '" + thisClassName + "'");
    }
  
    public static void addAdapter(Class<?> uiObjectClass, UIAdapter adapter) {
	if (uiObjectClass == null || adapter == null) {
	    return;
	}
	adapters.put(uiObjectClass.getName(), adapter);
    }
    

}
