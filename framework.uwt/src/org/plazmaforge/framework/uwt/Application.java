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

import java.util.Map;
import java.util.Set;

import org.plazmaforge.framework.core.data.CallbackAdapter;
import org.plazmaforge.framework.core.data.Destroyer;
import org.plazmaforge.framework.core.data.Initializer;
import org.plazmaforge.framework.core.exception.ErrorHandler;
import org.plazmaforge.framework.util.CoreUtils;
import org.plazmaforge.framework.uwt.widget.Frame;
import org.plazmaforge.framework.uwt.widget.MessageBox;
import org.plazmaforge.framework.uwt.widget.Widget;


/**
 * General UWT Application
 * 
 * @author ohapon
 *
 */
public abstract class Application extends Widget implements IApplication {
    
    //////////////////////////////////////////////////////////////////////
    // METHODS
    //////////////////////////////////////////////////////////////////////
    
    public static final String METHOD_PRESTART = "prestart";
    
    public static final String METHOD_START = "start";
    
    public static final String METHOD_STOP = "stop";
    
    public static final String METHOD_ASYNC_EXEC = "asyncExec";
    
    public static final String METHOD_SYNC_EXEC = "syncExec";
    

    /**
     * Application context
     */
    private ApplicationContext applicationContext = new ApplicationContext();
    
    /**
     * General frame of the application
     */
    private Frame frame;
    
    
    /**
     * Static current application
     */
    private static Application current;
    
    /**
     * Initializer of the application
     */
    private Initializer initializer;
    
    
    /**
     * Destroyer of application
     */
    private Destroyer destroyer;
    
    
    /**
     * Running flag
     */
    private boolean running;
    

    private static ErrorHandler defaultErrorHandler;  
    
    
    
    public Application() {
	super();
	current = this;
    }

    /**
     * Return current application
     * 
     * @return
     */
    public static Application getCurrent() {
        return current;
    }



    @Override
    public Frame getFrame() {
	if (frame == null) {
	    frame = createFrame();
	}
	return frame;
    }
    
    protected Frame createFrame() {
	Frame frame = new Frame();
	frame.assign(this);
	return frame;
    }
    
    /**
     * Return map of properties by array of arguments 
     * @param args
     * @return
     */
    public static Map<String, String> getProperties(String[] args) {
	return CoreUtils.toInputMap(args);
    }
    
    
    public void init() {
	// do nothing by default
    }
    
    /**
     * Starts the Application by command line arguments
     * @param args
     */
    public void start(String[] args) {
	start(getProperties(args));
    }
    
    /**
     * Start the Application by input properties
     * @param properties
     */
    public void start(Map<String, String> properties) {
	
 	// Transfer start properties to application
 	if (properties != null) {
 	    ApplicationContext applicationContext = getApplicationContext();
 	    Set<String> keys = properties.keySet();
 	    for (String key: keys) {
 		applicationContext.setProperty(key, properties.get(key));
 	    }
 	}
 	start();
    }
         

    /**
     * Start the Application
     */
    @Override
    public void start() {
	
	if (running) {
	    throw new IllegalArgumentException("Application is running");
	}
	running = true;
	
	// Initialize the Application
	init();
	
	// Activate UI
	activateUI();
	
	if (getInitializer() != null) {
	    getInitializer().initialize(this, new CallbackAdapter<Object>() {

		@Override
		public void onSuccess(Object result) {
		    
		    if (result != null && (METHOD_PRESTART.equalsIgnoreCase(result.toString()))) {
			doPreStart();
			return;
		    }
		    
		    doStart();
		}
		
	    });
	    return;
	}
	doStart();
    }

    
    
    @Override
    public void stop() {
	
	if (!running) {
	    return;
	    //throw new IllegalArgumentException("Application is not running");
	}
	
	running = false;

	if (getDestroyer() != null) {
	    getDestroyer().destroy(this, new CallbackAdapter<Object>() {

		@Override
		public void onSuccess(Object result) {
		    
		    doStop();
		}
		
	    });
	    return;
	}
	doStop();
    }


    protected void doPreStart() {
	activateUI();
	getAdapter().invoke(this, METHOD_PRESTART, null);
    }

    protected void doStart() {
	activateUI();
	getAdapter().invoke(this, METHOD_START, null);
    }

    protected void doStop() {
	deactivateUI();
	getAdapter().invoke(this, METHOD_STOP, null);
    }

    public void asyncExec(Runnable runnable) {
	activateUI();
	getAdapter().invoke(this, METHOD_ASYNC_EXEC, new Object[] {runnable});
    }

    public void syncExec(Runnable runnable) {
	activateUI();
	getAdapter().invoke(this, METHOD_SYNC_EXEC, new Object[] {runnable});
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public Initializer getInitializer() {
        return initializer;
    }

    public void setInitializer(Initializer initializer) {
        this.initializer = initializer;
    }

    public Destroyer getDestroyer() {
        return destroyer;
    }

    public void setDestroyer(Destroyer destroyer) {
        this.destroyer = destroyer;
    }


    public static ErrorHandler getDefaultErrorHandler(UIElement owner) {
	if (defaultErrorHandler == null) {
	    defaultErrorHandler = createDefaultErrorHandler(owner);
	}
	return defaultErrorHandler;
    }
    
    
    private static ErrorHandler createDefaultErrorHandler(final UIElement owner) {
	
	return new ErrorHandler() {

	    @Override
	    public void handleError(Throwable e) {
		owner.getLogger().error(e);
		MessageBox.error("Application Error: " + e.getMessage());
	    }
	};
    }
    
}
