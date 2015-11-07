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
package org.plazmaforge.framework.uwt;

import java.util.Map;

import org.plazmaforge.framework.core.data.Destroyer;
import org.plazmaforge.framework.core.data.Initializer;


/**
 * @author ohapon
 *
 */
public class DesktopApplication extends AbstractDesktopApplication {
    
    /**
     * @param args
     */
    
    public static void main(String[] args) {
	
	// Get application properties by command line arguments
	Map<String, String> properties = getProperties(args);
	
	// Get UI
	String ui = properties.get("ui");
	if (ui == null) {
	    System.err.println("UI is not setting. Use argument -ui to set UI in command line");
	    return;
	}
	// Initialize UWT by UI type
	if (!initUWT(ui)) {
	    return;
	}
	
	// Create application
	DesktopApplication application = new DesktopApplication();
	
	//TODO: May be transfer properties to application 
	
	// Set application initializer
	Initializer initializer = getInitializer(properties.get("application.initializer"));
	if (initializer != null) {
	    application.setInitializer(initializer);
	}
	
	// Set application destroyer
	Destroyer destroyer = getDestroyer(properties.get("application.destroyer"));
	if (destroyer != null) {
	    application.setDestroyer(destroyer);
	}
	
	// Start application
	application.start(properties);
    }
    
    
}
