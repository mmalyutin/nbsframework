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

import org.plazmaforge.framework.uwt.AbstractUIAdapter;
import org.plazmaforge.framework.uwt.Application;
import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.jfx.widget.XApplication;
import org.plazmaforge.framework.uwt.widget.Frame;


/**
 * 
 * @author ohapon
 *
 */
public class JFXApplicationAdapter extends AbstractUIAdapter {
    
    private static final String SYS_PRESTART = "prestart"; 

    @Override
    public Object createDelegate(UIElement parent, UIElement element) {
	
	
	Application application = (Application) element;
	
	// Get general frame of the application
	Frame frame = application.getFrame();
	
	XApplication.application = application;
	XApplication.frame = frame;
	
	// Return stub object because JFX Application will be create late 
	return new Object();
    }
    
    @Override
    public void disposeDelegate(UIElement parent, UIElement element) {
	
    }

    @Override
    public Object getProperty(UIElement element, String name) {
	return null;
    }

    @Override
    public Object invoke(UIElement element, String methodName, Object[] args) {
	Application application = (Application) element;
	if (Application.METHOD_PRESTART.equals(methodName)) {
	    prestart(application); 
	} else if (Application.METHOD_START.equals(methodName)) {
	    start(application); 
	} else if (Application.METHOD_STOP.equals(methodName)) {
	    stop(application); 
	} else if (Application.METHOD_ASYNC_EXEC.equals(methodName)) {
	    Runnable runnable = null;
	    if (args != null && args.length > 0){
		runnable = (Runnable) args[0];
	    }
	    exec(application, runnable, true); 
	} else if (Application.METHOD_SYNC_EXEC.equals(methodName)) {
	    Runnable runnable = null;
	    if (args != null && args.length > 0){
		runnable = (Runnable) args[0];
	    }
	    exec(application, runnable, false); 
	}

	return null;
    }




    @Override
    public void setProperty(UIElement element, String name, Object value) {
    }
    
    
    private void prestart(Application application) {

    }    

    private void start(Application application) {
	// Start JFX Application
	javafx.application.Application.launch(XApplication.class, new String[0]); 
    }
    
   
    private void stop(Application application) {
	// Stop JFX Application
	System.exit(0);
    }
    
    private void exec(Application application, Runnable runnable, boolean async) {
	if (runnable == null) {
	    return;
	}
	//TODO
    }


}
