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

package org.plazmaforge.framework.uwt.swt.adapter;

import org.plazmaforge.framework.uwt.AbstractUIAdapter;
import org.plazmaforge.framework.uwt.Application;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.swt.util.SWTUtils;
import org.plazmaforge.framework.uwt.widget.Frame;
import org.plazmaforge.framework.uwt.widget.Widget;

public class SWTApplicationAdapter extends AbstractUIAdapter {
    
    private static final String SYS_PRESTART = "prestart"; 

    @Override
    public Object createDelegate(UIObject parent, UIObject element) {
	
	Application application = (Application) element;
	
	// Get general frame of the application
	Frame frame = application.getFrame();
	
	// Create SWT Display
	org.eclipse.swt.widgets.Display xDisplay = new org.eclipse.swt.widgets.Display();
	
	// Create UWT Display wrapper
	Widget display = new Widget();
	display.setDelegate(xDisplay);
	frame.setParent(display);
	
	// Activate the frame
	frame.activateUI();

	// Return stub object because SWT has't native application object 
	return new Object();
    }
    
    @Override
    public void disposeDelegate(UIObject parent, UIObject element) {
	
    }

    @Override
    public Object getProperty(UIObject element, String name) {
	return null;
    }

    @Override
    public Object invoke(UIObject element, String methodName, Object[] args) {
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
    public void setProperty(UIObject element, String name, Object value) {
    }
    
    
    private void prestart(Application application) {

   	// Get general frame of the application
   	Frame frame = application.getFrame();

   	// Get general display of the application
   	Widget display = frame.getParent();

   	final org.eclipse.swt.widgets.Display xDisplay = (org.eclipse.swt.widgets.Display) display.getDelegate();
   	org.eclipse.swt.widgets.Shell xFrame = (org.eclipse.swt.widgets.Shell) frame.getDelegate();
   	xFrame.setData(SYS_PRESTART, true);
   	while (!xFrame.isDisposed()) {
   	    if (!xDisplay.readAndDispatch()) {
   		xDisplay.sleep();
   	    }
   	}
   	xDisplay.dispose();
    }    

    private void start(Application application) {
	
	// Get general frame of the application
	Frame frame = application.getFrame();
	
	// Get general display of the application
	Widget display = frame.getParent();

	final org.eclipse.swt.widgets.Display xDisplay = (org.eclipse.swt.widgets.Display) display.getDelegate();
	org.eclipse.swt.widgets.Shell xFrame = (org.eclipse.swt.widgets.Shell) frame.getDelegate();
	
	//TODO
	//xFrame.open();
	
	if (frame.isPack()) {
	    xFrame.pack();
	}
	if (frame.isCenter()) {
	    SWTUtils.centerShell(xFrame);
	}
	
	xFrame.open();
	
	boolean isPrestart = booleanValue(xFrame.getData(SYS_PRESTART)); 
	if (isPrestart) {
	    return;
	}
	while (!xFrame.isDisposed()) {
	    if (!xDisplay.readAndDispatch()) {
		xDisplay.sleep();
	    }
	}
	xDisplay.dispose();
    }
    
   
    private void stop(Application application) {
	// Stop SWT Application
	System.exit(0);
    }
    
    private void exec(Application application, Runnable runnable, boolean async) {
	if (runnable == null) {
	    return;
	}

	org.eclipse.swt.widgets.Display xDisplay = org.eclipse.swt.widgets.Display.getCurrent(); 
	if (xDisplay == null) {
	    
	    // Get general frame of the application
	    Frame frame = application.getFrame();
		
	    // Get general display of the application
	    Widget display = frame.getParent();
	    
	  xDisplay = (org.eclipse.swt.widgets.Display) display.getDelegate();
	    
	}
	if (async) {
	    xDisplay.asyncExec(runnable);
	} else {
	    xDisplay.syncExec(runnable);
	}
    }


}
