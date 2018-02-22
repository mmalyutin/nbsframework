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
import org.plazmaforge.framework.uwt.Application;
import org.plazmaforge.framework.uwt.ApplicationContext;
import org.plazmaforge.framework.uwt.UIResourceException;
import org.plazmaforge.framework.uwt.UWT;
import org.plazmaforge.framework.uwt.UWTDesktopToolit;
import org.plazmaforge.framework.uwt.storage.ClassTemplateProvider;
import org.plazmaforge.framework.uwt.storage.TemplateProvider;
import org.plazmaforge.framework.uwt.storage.TemplateProviderAsync;
import org.plazmaforge.framework.uwt.layout.FitLayout;
import org.plazmaforge.framework.uwt.widget.Frame;


public class DemoDesktopApplication extends Application {


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
	if (!UWTDesktopToolit.initUWT(ui)) {
	    return;
	}
	
	// Create and start application
	DemoDesktopApplication application = new DemoDesktopApplication();
	
	////
	String locale = properties.get(ApplicationContext.CONFIG_LOCALE);
	if (locale == null) {
	    locale = "en";
	}
	application.getApplicationContext().setProperty(ApplicationContext.CONFIG_LOCALE, locale);
	////
	
	application.start(properties);
    }

    @Override
    public void init() {
	setInitializer(new DesktopApplicationInitializer());
    }
    
    class DesktopApplicationInitializer extends DemoApplicationInitializer {

	@Override
	protected void initializeApplication(Application application, Callback<Object> callback) {
	    
	    final String locale = getApplicationContext().getProperty("locale");
	    ApplicationContext applicationContext = application.getApplicationContext();
	    
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
	    
	    applicationContext.setAttribute(ApplicationContext.CONFIG_RESOURCE_PROVIDER, resourceProvider);
	    applicationContext.setAttribute(ApplicationContext.CONFIG_TEMPLATE_PROVIDER_ASYNC, uiResourceProviderAsync);	   	    

	    // Initialize Application Context
	    initializeApplicationContext(applicationContext);
	    
	    // Load Application
	    loadApplication();
	    
	    //callback.onSuccess(Application.METHOD_PRESTART);
	    callback.onSuccess(null);
	    
	}

	@Override
	protected void initializeApplicationContext(ApplicationContext applicationContext) {
	    
	
	    // Initialize locale (number/date/time formats)
	    initializeLocale(applicationContext);
	    
	    applicationContext.setProperty("imageStorage", "org/plazmaforge/framework/uwt/resources/images");
	    applicationContext.setAttribute(ApplicationContext.CONFIG_PROPERTY_PROVIDER_FACTORY, new ClassPropertyProviderFactory());
	    applicationContext.setAttribute(ApplicationContext.CONFIG_SERVICE_CALLER_FACTORY, new DemoServiceCallerFactory());
	 
	}
	
	protected void loadApplication() {

	    // Get frame
	    Frame frame = getFrame();

	    frame.setWidth(640);
	    frame.setHeight(480);
	    frame.setTitle("UWT Demo Application");
	    frame.setLayout(new FitLayout());
	    
	    populateFrame(frame);

	}
	
    }
    
   


}
