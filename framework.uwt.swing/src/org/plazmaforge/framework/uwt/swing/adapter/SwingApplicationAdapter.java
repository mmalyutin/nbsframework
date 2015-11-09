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

package org.plazmaforge.framework.uwt.swing.adapter;

import java.awt.EventQueue;

import javax.swing.SwingUtilities;

import org.plazmaforge.framework.uwt.AbstractUIAdapter;
import org.plazmaforge.framework.uwt.Application;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.Frame;

public class SwingApplicationAdapter extends AbstractUIAdapter {


    @Override
    public Object createDelegate(UIObject parent, UIObject element) {
	Application application = (Application) element;
	
	// Get general frame of the application
	Frame frame = application.getFrame();
	
	// Activate the frame
	frame.activateUI();
	
	// Return stub object because Swing has't native application object 
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
	
	// Initialization without open application frame
	final Frame frame = application.getFrame();
	javax.swing.JFrame xFrame = (javax.swing.JFrame) frame.getDelegate();
	xFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        
    }

    private void start(Application application) {
	
	// Initialization
	prestart(application);
	
	// Open application frame
	Frame frame = application.getFrame();
	javax.swing.JFrame xFrame = (javax.swing.JFrame) frame.getDelegate();
	xFrame.setVisible(true);
    }

    
    private void stop(Application application) {
	// Stop Swing Application
	System.exit(0);
    }
    
    private void exec(Application application, Runnable runnable, boolean async) {
   	if (runnable == null) {
   	    return;
   	}
   	if (async) {
   	    SwingUtilities.invokeLater(runnable);
   	} else {
   	    try {
   		//TODO
   		if (EventQueue.isDispatchThread()) {
   		    runnable.run();
   		    return;
   		}
   		SwingUtilities.invokeAndWait(runnable);
   	    } catch (Throwable e) {
   		System.err.println(e);
   	    }
   	}
    }

}
