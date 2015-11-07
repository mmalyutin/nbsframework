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

package org.plazmaforge.framework.uwt.demo;


import java.util.Map;

import org.plazmaforge.framework.core.data.Callback;
import org.plazmaforge.framework.core.data.ClassPropertyProviderFactory;
import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.core.resource.BundleResourceProvider;
import org.plazmaforge.framework.core.resource.ResourceProvider;
import org.plazmaforge.framework.uwt.AbstractDesktopApplication;
import org.plazmaforge.framework.uwt.ApplicationContext;
import org.plazmaforge.framework.uwt.UIResourceException;
import org.plazmaforge.framework.uwt.UWT;
import org.plazmaforge.framework.uwt.storage.ClassTemplateProvider;
import org.plazmaforge.framework.uwt.storage.TemplateProvider;
import org.plazmaforge.framework.uwt.storage.TemplateProviderAsync;
import org.plazmaforge.framework.uwt.layout.FitLayout;
import org.plazmaforge.framework.uwt.widget.Frame;


public class DemoUWT extends AbstractDesktopApplication {


    public static void main(String[] args) {
	
	// Get application properties
	Map<String, String> properties = getProperties(args);
	
	// Get UI
	String ui = properties.get("ui");
	if (ui == null) {
	    System.err.println("UI is not setting. Use '-ui' argument in command line. For example '-ui swing'");
	    return;
	}
	// Initialize UWT by UI type
	if (!initUWT(ui)) {
	    return;
	}
	
	// Create and start application
	DemoUWT application = new DemoUWT();
	application.start(properties);
    }

    
    public void start(Map<String, String> properties) {

	// Create application
	//Application app = new Application();

	String locale = properties.get(ApplicationContext.CONFIG_LOCALE);
	if (locale == null) {
	    locale = "en";
	}

	// TODO: It is desktop always !!!
	if (UWT.isDesktop()) {

	    // i18n provider
	    ResourceProvider resourceProvider = new BundleResourceProvider();
	    UWT.setResourceProvider(resourceProvider, locale);

	    // UI File Provider (*.ui.xml)
	    final TemplateProvider templateProvider = new ClassTemplateProvider();
	    final TemplateProviderAsync uiResourceProviderAsync = new TemplateProviderAsync() {

		@Override
		public void getData(String path, Callback<IData> callback) {
		    try {
			IData data = templateProvider.getData(path);
			callback.onSuccess(data);
		    } catch (UIResourceException e) {
			callback.onFailure(e);
		    }
		}
		
	    };
	    
	    getApplicationContext().setProperty(ApplicationContext.CONFIG_LOCALE, locale);
	    getApplicationContext().setProperty(ApplicationContext.CONFIG_FORMAT_DATE, "dd-MM-yyyy");
	    getApplicationContext().setProperty(ApplicationContext.CONFIG_FORMAT_DATE_TIME, "dd-MM-yyyy HH:mm:ss");
	    getApplicationContext().setProperty(ApplicationContext.CONFIG_FORMAT_TIME, "HH:mm:ss");
	    getApplicationContext().setProperty("imageStorage", "org/plazmaforge/framework/uwt/resources/images");

	    getApplicationContext().setAttribute(ApplicationContext.CONFIG_PROPERTY_PROVIDER_FACTORY, new ClassPropertyProviderFactory());
	    getApplicationContext().setAttribute(ApplicationContext.CONFIG_SERVICE_CALLER_FACTORY, new DemoServiceCallerFactory());
	    getApplicationContext().setAttribute(ApplicationContext.CONFIG_RESOURCE_PROVIDER, resourceProvider);
	    
	    getApplicationContext().setAttribute(ApplicationContext.CONFIG_TEMPLATE_PROVIDER_ASYNC, uiResourceProviderAsync);
	}
	
	// Get frame
	Frame frame = getFrame();
	frame.setWidth(640);
	frame.setHeight(480);
	frame.setTitle("UWT Demo Application");
	
	frame.setLayout(new FitLayout());
	DemoApplicationBuilder builder = new DemoApplicationBuilder();
	builder.populateFrame(frame);
	
	start();
    }
    


}
