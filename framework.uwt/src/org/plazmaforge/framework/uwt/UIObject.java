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

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.plazmaforge.framework.core.data.Customizer;
import org.plazmaforge.framework.core.exception.ErrorHandler;
import org.plazmaforge.framework.core.logging.Logger;
import org.plazmaforge.framework.core.resource.NullResource;
import org.plazmaforge.framework.core.resource.Resource;
import org.plazmaforge.framework.core.resource.ResourceProvider;
import org.plazmaforge.framework.core.type.TypeUtils;
import org.plazmaforge.framework.core.type.Types;
import org.plazmaforge.framework.uwt.util.StorageUtils;

/**
 * 
 * @author ohapon
 * 
 * General UI Object.
 *
 * The <code>UIObject</code> links to native object (delegate) 
 * and uses <code>UIAdapter</code> to call methods of native object.
 *
 */
public class UIObject {


    //////////////////////////////////////////////////////////////////////
    // CONFIG
    //////////////////////////////////////////////////////////////////////

    public static final String CONFIG_LOCALE = ApplicationContext.CONFIG_LOCALE;
    public static final String CONFIG_RESOURCE_PROVIDER = ApplicationContext.CONFIG_RESOURCE_PROVIDER;
    public static final String CONFIG_SERVICE_CALLER_FACTORY = ApplicationContext.CONFIG_SERVICE_CALLER_FACTORY;
    public static final String CONFIG_SERVICE_CALLER = ApplicationContext.CONFIG_SERVICE_CALLER;
    
    public static final String CONFIG_FORMAT_NUMBER = ApplicationContext.CONFIG_FORMAT_NUMBER;
    public static final String CONFIG_FORMAT_CURRENCY = ApplicationContext.CONFIG_FORMAT_CURRENCY;
    public static final String CONFIG_FORMAT_QUANTITY = ApplicationContext.CONFIG_FORMAT_QUANTITY;
    public static final String CONFIG_FORMAT_PERCENT = ApplicationContext.CONFIG_FORMAT_PERCENT;
    public static final String CONFIG_FORMAT_DATE = ApplicationContext.CONFIG_FORMAT_DATE;
    public static final String CONFIG_FORMAT_TIME = ApplicationContext.CONFIG_FORMAT_TIME;
    public static final String CONFIG_FORMAT_DATE_TIME = ApplicationContext.CONFIG_FORMAT_DATE_TIME;

    public static final String CONFIG_TEMPLATE_PROVIDER = ApplicationContext.CONFIG_TEMPLATE_PROVIDER;
    public static final String CONFIG_TEMPLATE_PROVIDER_ASYNC = ApplicationContext.CONFIG_TEMPLATE_PROVIDER_ASYNC;
    
    //////////////////////////////////////////////////////////////////////
    // PROPERTIES
    //////////////////////////////////////////////////////////////////////

    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_TYPE = "type";
    public static final String PROPERTY_DATA = "data";
    public static final String PROPERTY_KEY_SEPARATOR = "@";
    public static final String PROPERTY_DATA_PREFIX = PROPERTY_DATA + PROPERTY_KEY_SEPARATOR;
    public static final String PROPERTY_LOCALE = "locale";
    public static final String PROPERTY_LOCALE_NAME = "localeName";
    public static final String PROPERTY_RESOURCE = "resource";
    public static final String PROPERTY_RESOURCE_NAME = "resourceName";
    
    
    
    /**
     * Identifier of the object.
     */
    private String id;
    
    
    /**
     * Name of the object.
     */
    private String name;
    
    
    /**
     * Type of the object.
     * Use type to lookup UIAdapter for the UIObject
     */
    private String type;
    
    
    /**
     * Data associated object
     */
    private Object data;

    /**
     * Data associated map
     */
    private Map<String, Object> dataMap;
    
    //////////////////////////////////////////////////////////////////////
    
    /**
     * Parent of the object
     */
    private UIObject parent;
    
    /**
     * Adapter of the object.
     */
    private UIAdapter adapter;
    
    /**
     * Delegate of the object. 
     */
    private Object delegate;
    

    /**
     * Init properties.
     * 
     * We use the properties before create delegate. 
     * 
     */
    private Map<String, Object> initProperties = new LinkedHashMap<String, Object>(); 

    //////////////////////////////////////////////////////////////////////
    
    /**
     * Application
     */
    private Application application;
    
    
    /**
     * Application Context
     */
    private ApplicationContext applicationContext;
    
    
    /**
     * Pre Customizer (before general customizer and createUI)
     */
    private Customizer preCustomizer;
    
    /**
     * General Customizer
     */
    private Customizer customizer;
    
    
    /**
     * Post Customizer (after createUI and general customizer)
     */
    private Customizer postCustomizer;
    
    
    /**
     * Name of Locale  ("default, "en", "en_US", etc.)
     */
    private String localeName;
    
    
    /**
     * Resource of the form
     */
    private Resource resource;
    
    /**
     * Name of the Resource of the form
     */
    private String resourceName;
    
    
    /**
     * Resource Provider of the form
     */
    private ResourceProvider resourceProvider;

    
    private boolean created;
    
    private ErrorHandler errorHandler;
    
    private Logger logger;
    
    
    public UIObject() {
	super();
    }
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getType() {
        return type;
    }

    
    public void setType(String type) {
        this.type = type;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
        fireChangeProperty(PROPERTY_DATA, data);
    }
    
    private Map<String, Object> getDataMap() {
	if (dataMap == null) {
	    dataMap = new HashMap<String, Object>();
	}
	return dataMap;
    }

    
    public Object getData(String key) {
        return getDataMap().get(key);
    }

    public void setData(String key, Object data) {
	getDataMap().put(key, data);
	fireChangeProperty(PROPERTY_DATA, key, data);
    }

    ////////////////////////////////////////////////////////////////////
    
    public final void create() {
	if (created) {
	    return;
	}
	created = true;
	preConfigure(); // PRE CONFIGURE
	configure();
	createUI();
	postConfigure(); // POST CONFIGURE
    }
    

    /**
     * Configure before General Configuration and createUI
     */
    protected void preConfigure() {
	
    }

    /**
     * Configure after General Configuration and createUI
     */
    protected void postConfigure() {
	
    }

    /**
     * General configuration
     */
    protected void configure() {
	
    }

    /**
     * Create UI
     */
    protected void createUI() {
	
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // CUSTOMIZERS (PRE, GENERAL, POST)
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    public Customizer getPreCustomizer() {
        return preCustomizer;
    }


    public void setPreCustomizer(Customizer preCustomizer) {
        this.preCustomizer = preCustomizer;
    }


    public Customizer getCustomizer() {
        return customizer;
    }


    public void setCustomizer(Customizer customizer) {
        this.customizer = customizer;
    }


    public Customizer getPostCustomizer() {
        return postCustomizer;
    }


    public void setPostCustomizer(Customizer postCustomizer) {
        this.postCustomizer = postCustomizer;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean isCreated() {
        return created;
    }


    public void setDelegate(Object delegate) {
	if (this.delegate != null) {
	    throw new IllegalArgumentException("Delegate is created");
	}
	if (delegate == null) {
	    throw new IllegalArgumentException("Delegate must be not null");
	}
        this.delegate = delegate;
    }


    public Object getDelegate() {
        return delegate;
    }

    protected Application getOwnApplication() {
	return application;
    }
    
    public Application getApplication() {
	if (application != null) {
	    return application;
	}
	Application currentApplication = getApplicationOfParent();
	if (currentApplication != null) {
	    return currentApplication;
	}
	currentApplication = Application.getCurrent();
	return currentApplication;
    }

    protected Application getApplicationOfParent() {
        return parent == null ? null : parent.getApplication() ;
    }
    
    public void assign(Application application) {
	this.application = application;
    }

    /**
     * Set ApplicationContext. Override context of Application
     * @param applicationContext
     */
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    public ApplicationContext getApplicationContext() {
	if (applicationContext != null) {
	    return applicationContext;
	}
	Application application = getApplication();
        return application == null ? null : application.getApplicationContext();
    }
    
    public String getConfigProperty(String name) {
	ApplicationContext context = getApplicationContext();
	return context == null ? null : context.getProperty(name);
    }

    public Object getConfigAttribute(String name) {
	ApplicationContext context = getApplicationContext();
	return context == null ? null : context.getAttribute(name);
    }

    public Object getConfigAttributeByPriority(String[] names) {
	ApplicationContext context = getApplicationContext();
	if (context == null) {
	    return null;
	}
	Object value = null;
	for (String name: names) {
	    value = context.getAttribute(name);
	    if (value != null) {
		return value;
	    }
	}
	return null;
    }

    ////////////////////////////////////////////////////////////////////
    

    /**
     * Return true if the object has the delegate
     * 
     * @return
     */
    public final boolean hasDelegate() {
	return delegate != null;
    }
    
    /**
     * Return UIAdapter
     * @return
     */
    public UIAdapter getAdapter() {
	if (adapter == null) {
	    adapter = createAdapter();
	}
        return adapter;
    }
    
    /**
     * Create UIAdapter
     * @return
     */
    protected UIAdapter createAdapter() {
	return UIAdapterFactory.getAdapter(getClass());
    }

    /**
     * Create the delegate
     * @return
     */
    protected Object createDelegate() {
	if (delegate != null) {
	    return delegate;
	}
	UIAdapter adapter = getAdapter();
	if (adapter == null) {
	    return null;
	}
	delegate = adapter.createDelegate(parent, this);
	
	initDelegate();
	
	adapter.checkDelegate(this);
	
	return delegate;
    }
    
    /**
     * Destroy the delegate
     */
    protected void disposeDelegate() {
	if (delegate == null) {
	    return;
	}
	UIAdapter adapter = getAdapter();
	if (adapter == null) {
	    return;
	}
	adapter.disposeDelegate(parent, this);
	delegate = null;
    }
    
    /**
     * Initialize the delegate
     */
    protected void initDelegate() {
	initDelegateProperties();
    }
    
    /**
     * Initialize properties of the delegate
     */
    protected void initDelegateProperties() {

	if (!hasDelegate() || initProperties.isEmpty()) {
	    return;
	}
	Set<String> names = initProperties.keySet();
	Object value = null;
	for (String name : names) {
	    value = initProperties.get(name);
	    initDelegateProperty(name, value);
	    //setDelegateProperty(name, value);
	}
	initProperties.clear();
    }
    
    protected void initDelegateProperty(String name, Object value) {
	setDelegateProperty(name, value);
    }
	    
    protected Object getInitProperty(String name) {
	return initProperties.get(name);
    }

    protected Object setInitProperty(String name, Object value) {
	return initProperties.put(name, value);
    }

    
    /**
     * Activate the object:
     * - Create the delegate
     * - Link the object to the delegate
     */
    public final void activateUI() {
	create();
        createDelegate();
    }

    /**
     * Activate the object with force initialize the delegate
     * @param force
     */
    public final void activateUI(boolean force) {
        activateUI();
        if (force){
            initDelegate();
        }
    }

    /**
     * Deactivate the object
     * - Destroy the delegate
     * - Reset link the object to delegate
     */
    public final void deactivateUI() {
	created = false;
	dispose();
	disposeDelegate();
    }
   
    protected void dispose() {
	// do nothing by default
    }

    /**
     * Return true if the object is active
     * @return
     */
    public final boolean isActiveUI() {
	return hasDelegate();
    }

    
    public UIObject getUIParent() {
        return parent;
    }


    public void setUIParent(UIObject parent) {
        this.parent = parent;
    }

    protected void fireChangeProperty(String name, Object value) {
	if (name == null) {
	    return;
	}
	
	// If delegate is null and property is init property then add to initProperties map
	if (delegate == null && isInitProperty(name)) {
	    initProperties.put(name, value);
	    return;
	}
	setDelegateProperty(name, value);
    }
    
    /**
     * Return true if property use to initialize delegate
     * @param name
     * @return
     */
    protected boolean isInitProperty(String name) {
	// By default all properties uses to initialize delegate
	return true;
    }
    
    
    
    /**
     * Set property to delegate
     * @param name
     * @param value
     */
    protected void setDelegateProperty(String name, Object value) {
	getAdapter().setProperty(this, name, value);
    }

    /**
     * Get property from delegate
     * @param name
     * @return
     */
    protected Object getDelegateProperty(String name) {
	return getAdapter().getProperty(this, name);
    }
    
    /**
     * Invoke method of the delegate
     * @param element
     * @param methodName
     * @return
     */
    protected Object invokeDelegate(UIObject element, String methodName) {
	return invokeDelegate(element, methodName, null);
    }
    
    /**
     * Invoke method of the delegate
     * @param element
     * @param methodName
     * @param args
     * @return
     */
    protected Object invokeDelegate(UIObject element, String methodName, Object[] args) {
	return getAdapter().invoke(element, methodName, args);
    }

    ////

    protected String getStringDelegateProperty(String name) {
	return getString(getDelegateProperty(name));
    }
    
    protected void setStringDelegateProperty(String name, String value) {
	setDelegateProperty(name, value);
    }

    protected Integer getIntegerDelegateProperty(String name) {
	return getInteger(getDelegateProperty(name));
    }

    protected void setIntegerDelegateProperty(String name, Integer value) {
	setDelegateProperty(name, value);
    }

    protected Boolean getBooleanDelegateProperty(String name) {
	return getBoolean(getDelegateProperty(name));
    }

    protected void setBooleanDelegateProperty(String name, Boolean value) {
	setDelegateProperty(name, value);
    }

    ////

    protected void fireChangeProperty(String name, int index, Object value) {
	if (name == null) {
	    return;
	}
	name = name + PROPERTY_KEY_SEPARATOR + index;
	fireChangeProperty(name, value);
    }

    protected void fireChangeProperty(String name, String key, Object value) {
	if (name == null) {
	    return;
	}
	name = name + PROPERTY_KEY_SEPARATOR + key;
	fireChangeProperty(name, value);
    }

    // Utilities

    protected String getString(Object value) {
	return (String) value;
    }

    protected Number getNumber(Object value) {
	return (Number) value;
    }
    
    protected Integer getInteger(Object value) {
	return (Integer) value;
    }
    

    protected Date getDate(Object value) {
	return (Date) value;
    }

    protected Boolean getBoolean(Object value) {
	return (Boolean) value;
    }

    protected boolean booleanValue(Object value) {
	return value == null ? false : getBoolean(value);
    }

    ////
    
    public String getPath(String storage, String path) {
	return StorageUtils.getPath(storage, path);
    }
    
    /**
     * Return path of application storage by type
     * 
     * @param type
     * @return path
     */
    public String getStorage(String type, String path) {
	return StorageUtils.getStorage(getApplicationContext(), type, path);
    }

    public String getImageStorage(String path) {
	return StorageUtils.getImageStorage(getApplicationContext(), path);
    }

    public String getFontStorage(String path) {
	return StorageUtils.getFontStorage(getApplicationContext(), path);
    }

    public String getFileStorage(String path) {
	return StorageUtils.getFileStorage(getApplicationContext(), path);
    }

    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // RESOURCES
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getLocaleName() {
	if (localeName == null) {
	    localeName = getConfigProperty(CONFIG_LOCALE);
	}
        return localeName;
    }

    public void setLocaleName(String localeName) {
        this.localeName = localeName;
    }

    public Resource getResource() {
	if (resource == null) {
	    resource = createResource();
	}
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
    
    protected Resource createResource() {
	if (getResourceProvider() == null) {
	    return getNullResource();
	}
	String resourceName = getResourceName();
	String localeName = getLocaleName();
	Resource resource = getResourceProvider().getResource(resourceName, localeName);
	return resource == null ? getNullResource() : resource;
    }
    
    protected Resource getNullResource() {
	return new NullResource();
    }

    public String getResourceName() {
	if (resourceName == null) {
	    if (getName() != null) {
		resourceName = getName();
	    } else {
		// Get class name
		resourceName = getClass().getName();
		int lastDot = resourceName.lastIndexOf(".");
		if (lastDot > 0) {
		    resourceName = resourceName.substring(0, lastDot + 1); // with '.'
		}
		
		//TODO: HARD CODE: messages.properties 
		resourceName = resourceName + "messages";
	    }
	}
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public ResourceProvider getResourceProvider() {
	if (resourceProvider == null) {
	    resourceProvider = createResourceProvider(); 
	}
        return resourceProvider;
    }

    public void setResourceProvider(ResourceProvider resourceProvider) {
        this.resourceProvider = resourceProvider;
    }

    
    protected ResourceProvider createResourceProvider() {
	return (ResourceProvider) getConfigAttributeByPriority(getResourceProviderProperties());
    }
    
    protected String[] getResourceProviderProperties() {
	return new String[] {CONFIG_RESOURCE_PROVIDER}; 
    }

    
    public String getResourceString(String key) {
	return getResource().getString(key);
    }

    public String[] getResourceStringArray(String key) {
	return getResource().getStringArray(key);
    }

    ////
    
    public String getString(String key) {
	return getResourceString(key);
    }

    public String[] getStringArray(String key) {
	return getResourceStringArray(key);
    }

    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected void checkCreate() {
	if (created) {
	    return;
	}
	throw new UWTException("Object is not created");
    }

    public ErrorHandler getErrorHandler() {
	if (errorHandler == null) {
	    errorHandler = (ErrorHandler) getConfigAttribute("error.handler");
	    if (errorHandler == null) {
		errorHandler = Application.getDefaultErrorHandler(this);
	    }
	}
	return errorHandler;
    }


    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }
    
    /**
     * Return format pattern by data type
     * @return
     */
    protected String getConfigFormat(String dataType) {
	//TODO: Must remove to configurer
	String configProperty = null;
	
	if ("Currency".equals(dataType)) {
	    configProperty = CONFIG_FORMAT_CURRENCY;
	} else if ("Quantity".equals(dataType)) {
	    configProperty = CONFIG_FORMAT_QUANTITY;
	} else if ("Percent".equals(dataType)) {
	    configProperty = CONFIG_FORMAT_PERCENT;
	} else if (TypeUtils.isLikeNumberType(dataType)) {
	    configProperty = CONFIG_FORMAT_NUMBER;
	} else if (Types.DateType.equals(dataType)) {
	    configProperty = CONFIG_FORMAT_DATE;
	} else 	if (Types.TimeType.equals(dataType)) {
	    configProperty = CONFIG_FORMAT_TIME;
	} else 	if (Types.DateTimeType.equals(dataType)) {
	    configProperty = CONFIG_FORMAT_DATE_TIME;
	}
	return configProperty == null ? null : getConfigProperty(configProperty);
    }
    
    protected Logger getLogger() {
	if (logger == null) {
	    logger = Logger.getLogger(getClass().getName());
	}
	return logger;
    }
}

