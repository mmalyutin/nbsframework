/*
 * Copyright (C) 2005-2010 Oleh Hapon ohapon@users.sourceforge.net
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

package org.plazmaforge.framework.ext.service.support.spring;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
//import org.plazmaforge.framework.app.AppEnvironment;
import org.plazmaforge.framework.config.AppConfigurationUtils;
import org.plazmaforge.framework.config.ConfigurerManager;
import org.plazmaforge.framework.config.configurer.PackageConfigurer;
import org.plazmaforge.framework.config.object.IPackageConfig;
import org.plazmaforge.framework.core.data.path.TypeInfo;
import org.plazmaforge.framework.core.data.path.TypeResolver;
import org.plazmaforge.framework.core.exception.ApplicationException;
//import org.plazmaforge.framework.core.exception.ServiceNotFoundException;
import org.plazmaforge.framework.erm.Configuration;
import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.ext.association.AssociationCreator;
import org.plazmaforge.framework.ext.association.AssociationManager;
import org.plazmaforge.framework.ext.service.AbstractServiceLocator;
import org.plazmaforge.framework.ext.service.GenericEntityService;
import org.plazmaforge.framework.ext.service.Service;
import org.plazmaforge.framework.ext.service.ServiceFactory;

//import org.plazmaforge.framework.platform.PlatformEnvironment;
//import org.plazmaforge.framework.platform.service.cache.ServiceCache;
//import org.plazmaforge.framework.platform.service.cache.ServiceCacheEnvironment;
//import org.plazmaforge.framework.platform.service.cache.ServiceHandler;

import org.plazmaforge.framework.util.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;


/**
 * A <code>SpringServiceLocator</code> implemented a Spring conception The
 * <code>SpringServiceLocator</code> has a <code>ApplicationContext</code>
 * 
 * The <code>SpringServiceLocator</code> initialize:
 * 
 *  - Spring Framework
 *  - ERM Framework 
 *  - Service Locator
 * 
 * @author ohapon
 * 
 */
public class SpringServiceLocator extends AbstractServiceLocator {

    /** Location List **/
    public static final String DEFAULT_LOCATION_LIST = "application-context-list.xml";
    
    /** Location * */
    public static final String DEFAULT_LOCATION = "application-context.xml";

    /** Data Source * */
    public static final String DEFAULT_DATA_SOURCE_PROPERTY = "dataSource";

    /** Session Factory * */
    public static final String DEFAULT_SESSION_FACTORY_PROPERTY = "sessionFactory";

    /** Property Configurer * */
    public static final String DEFAULT_PROPERTY_CONFIGURER_PROPERTY = "propertyConfigurer";

    /** Transaction Manager * */
    public static final String DEFAULT_TRANSACTION_MANAGER_PROPERTY = "transactionManager";
    
    
    
    private final String PACKAGE = "${package}";
    
    
    private List<IPackageConfig> packages;
    
    private List<IPackageConfig> enabledPackages;
    
    private boolean isLoadPackages;
    
    private boolean isLoadEnabledPackages;
    
    
    /** Spring application context * */
    private ApplicationContext applicationContext;

    
    /**
     * ERM Configuration
     */
    private Configuration configuration;
    

    
    protected String getRootDir() {
	return (String) getProperty("root.dir");
    }

    protected String getRootConfigDir() {
	return (String) getProperty("root.config.dir");
    }

    protected String[] getDefaultLocation() {
	return new String[] { (String) getProperty("location", DEFAULT_LOCATION)};
    }

    protected String getLocationList() {
	return (String) getProperty("location.list", DEFAULT_LOCATION_LIST);
    }

    private Entity getEntity(String className) {
	return configuration.getEntityByClassName(className);
    }

    protected boolean isClasspathProtocol(String path) {
	// Check 'classpath:' protocol
	return (path != null && path.startsWith("classpath:"));
    }
    
    /**
     * Get application context
     * 
     * @return
     */
    public ApplicationContext getApplicationContext() {
	return applicationContext;
    }

    /**
     * Set application context
     * 
     * @param applicationContext
     */
    public void setApplicationContext(ApplicationContext applicationContext) {
	this.applicationContext = applicationContext;
    }


    private Object getSpringBean(Class serviceInterface) {
	Object bean = getSpringBean(getServiceNameByClass(serviceInterface));
	if (bean == null) {
	    return null;
	}
	return getServiceProxy(bean, serviceInterface);
    }
    
    private Object getSpringBean(String name) {
   	try {
   	    return getApplicationContext().getBean(name);
   	} catch (Exception ex) {
   	    return null;
   	}
    }
    
    
    /**
     * Get service by interface class
     */
    public Object getService(Class<?> serviceInterface) {
	if (serviceInterface == null) {
	    // TODO: Must throw ServiceNotFoundException
	    return null;
	}
	Object service = getSpringBean(serviceInterface);
	if (service != null) {
	    return service;
	}
	return getServiceByClass(serviceInterface.getName(), false);
    }

    public Object getServiceByClass(String serviceClassName) {
	return getServiceByClass(serviceClassName, true);
    }
    
    private Object getServiceByClass(String serviceClassName, boolean useBean) {
	if (serviceClassName == null) {
	    // TODO: Must throw ServiceNotFoundException
	    return null;
	}
	try {
	    Object serviceObject = null;
	    
	    //DISABLE SIMPLE NAME 
	    /*
	    if (serviceClassName.indexOf(".") == -1) {
		// Simple class name
		serviceObject = getSpringBean(serviceClassName);
		if (serviceObject != null) {
		    return serviceObject;
		}
	    }
	    */

	    
	    //////////////////////////////////////////////////////////////////////////////
	    //
	    // Try use Service by class
	    //
	    //////////////////////////////////////////////////////////////////////////////

	    // Load class by class name
	    Class<?> serviceClass = getLazyClass(serviceClassName);
	    
	    if (serviceClass != null && useBean) {
		// If class is found (non virtual) then get service by real class
		serviceObject = getSpringBean(serviceClass);
		if (serviceObject != null) {
		    return serviceObject;
		}
	    }
	    serviceClass = null;
	    
	    
	    //////////////////////////////////////////////////////////////////////////////
	    //
	    // Try use GenericService
	    //
	    //////////////////////////////////////////////////////////////////////////////
	    
	    // Get entity class name by service class name
	    String entityClassName = getEntityClassName(serviceClassName);
	    
	    if (entityClassName == null) {
		throw new RuntimeException("Error initialize generic service. Entity class is null.");
	    }
	    
	    //if (entityClassName != null) {
		
		// Get ERM Entity (Metadata) by class name
		Entity entity = getEntity(entityClassName);
		if (entity != null) {
		    
		   // Get generic service class name form ERM Entity configuration  
		   String genericServiceClass = entity.getConfigProperty("generic.service.class", true);
		   if (genericServiceClass != null) {
		       serviceClass = getLazyClass(genericServiceClass);
		   }
		}
	    //}
	    
	    // Generic service implementation
	    if (serviceClass == null) {
		serviceClass = GenericEntityService.class;
	    }
	    
	    GenericEntityService service = (GenericEntityService) getSpringBean(serviceClass);
	    if (service == null) {
		throw new RuntimeException("Generic service not found.");
	    }
	    Class entityClass = getLazyClass(entityClassName);
	    if (entityClass == null) {
		//TODO: throw new ServiceNotFoundException("Error initialize generic service. Entity class not found for service '" + serviceClassName + "'");
		throw new RuntimeException("Error initialize generic service. Can not load entity class '" + entityClassName + "' for service '" + serviceClassName + "'");
	    }
	    
	    service.setEntityClass(entityClass);
	    return service;
	    
	    
	    //return null;
	} catch (Exception ex) {
	    //TODO: DISABLE: OHA
	    throw new RuntimeException(ex);
	    //throw new ServiceNotFoundException(ex);
	}
    }

    
    protected  AssociationCreator getAssociationCreator() {
	return AssociationManager.getAssociationCreator();
    }
    
    protected String getEntityClassName(String serviceClassName) {
	if (serviceClassName == null) {
	    return null;
	}
	AssociationCreator associationCreator = getAssociationCreator();
	if (associationCreator == null) {
	    TypeResolver typeResolver = getTypeResolver();
	    if (typeResolver == null) {
		return null;
	    }
	    TypeInfo typeInfo = typeResolver.parseClass(serviceClassName, "Service");
	    return typeResolver.getClassName(typeInfo, "Entity");
	}
	return associationCreator.createBeanClassName(serviceClassName, Service.class);
    }

    protected String[] loadLocationList() {
	
	// application-context-list.xml
	// --------------------------------------------------------------------
	// <context>application-context-rm.xml</context>
	// <context>application-context-ejb.xml</context>
	// <context>application-context-ws.xml</context>
	
	String locationListFile = getLocationList();
	String rootDir = getRootConfigDir();

	locationListFile = AppConfigurationUtils.getRootConfigFileName(rootDir, locationListFile);
	
	// Get enabled packages
	List<IPackageConfig> enablePackages = getEnabledPackages();
	
	// Load list of context files from 'application-context-list.xml'
	try {
	    InputStream is = AppConfigurationUtils.getConfigInputStream(locationListFile);
	    SAXBuilder builder = new SAXBuilder();
	    org.jdom.Document doc = builder.build(is);
	    Element root =  doc.getRootElement();
	    List children = root.getChildren("context");
	    if (children == null || children.size() == 0) {
		return getDefaultLocation(); // DEFAULT
	    }
	    List<String> files = new ArrayList<String>();
	    for (int i = 0; i < children.size(); i++) {
		Element element = (Element) children.get(i);
		String file = element.getText();
		
		
		if (StringUtils.isEmpty(file)) {
		    // Empty file: ignore;
		    continue;
		}
		
		
		int index = file.indexOf(PACKAGE);
		if (index == -1) {
		    // Standard file (without package marker ${package}): add and continue
		    files.add(getAbsoluteFileLocation(rootDir, file));
		    continue;
		}
		
		
		if (enablePackages == null) {
		    // No packages: ignore
		    continue;
		}
		
		// Resolve package marker ${package}
		for (IPackageConfig pkg : enablePackages) {
		    String packageFolder = pkg.getPackageFolder();
		    if (StringUtils.isEmpty(packageFolder)) {
			continue;
		    }
		    
		    // Replace package marker ${package} to real package
		    String packageFile = StringUtils.replaceAll(file, PACKAGE, packageFolder);
		    
		    // Fixed file name of part package
		    String configFolder = pkg.isPartConf() ? pkg.getAbsoluteRootConfigFolder() : rootDir;
		    packageFile = getAbsoluteFileLocation(configFolder, packageFile);
		    
		    files.add(packageFile);
		}
		
		
		
	    }
	    if (files.size() == 0) {
		return getDefaultLocation(); // DEFAULT
	    }
	    return files.toArray(new String[0]);
	} catch (IOException ex) {
	    log("Location list file [" + locationListFile + "] not found"); // Why log info ? Must be log warn.
	    return getDefaultLocation(); // DEFAULT
	} catch (Exception ex) {	    
	    return getDefaultLocation(); // DEFAULT
	}

    }
    
 
    
 
    public void init() throws ApplicationException {

	ApplicationContext ctx = createApplicationContext();

	setApplicationContext(ctx);

	// Initialize ERM
	initERM();
	
	// Initialize Service Locator
	initServiceLocator();

	// Initialize Connection Manager
	initConnectionManager();

    }
    
    protected String getAbsoluteFileLocation(String rootDir, String location) {
	return AppConfigurationUtils.getRootConfigFileName(rootDir, location);
    }

    protected ApplicationContext createApplicationContext() {

	String rootDir = getRootConfigDir();
	
	// Check 'classpath:' protocol
	boolean isClasspathProtocol = isClasspathProtocol(rootDir);

	
	
	
	// Load list of context files from 'application-context-list.xml' 
	String[] contextLocation = loadLocationList();
	
	// Set current ClassLoader
	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	//log("SPRING CLASS LOADER: " + classLoader);
	
	
	log(isClasspathProtocol ? "APPLICATION_CONTEXT_LOCATION:" : "APPLICATION_CONTEXT_FILES:");
	for (int i = 0; i < contextLocation.length; i++) {
	    log("" + formatLine(i + 1) + ": " + contextLocation[i]);
	}
	ConfigurableApplicationContext ctx = null;
	if (isClasspathProtocol) {
	    ctx = new ClassPathXmlApplicationContext(contextLocation);
	} else {
	    ctx = new MyFileSystemXmlApplicationContext(contextLocation, classLoader);
	}

	// Replace ${args}
	// Example ${user.dir}
	//
	// This you can replace properties for DEMO mode
	// Example
	// jdbc.url=jdbc:hsqldb:file:${user.dir}/db/hsql/plazma_eng_demo

	// TODO: Use method clone() of Properties

	// IT DOESN'T WORK !!!
	Properties props = System.getProperties();
	PropertyPlaceholderConfigurer cfg = new PropertyPlaceholderConfigurer();
	cfg.setProperties(props);
	cfg.postProcessBeanFactory(ctx.getBeanFactory());
	

	return ctx;

    }
    
    protected void initERM() throws ApplicationException {

	// Create empty ERM configuration
	configuration = Configuration.create();
	String rootDir = getRootConfigDir();
	
	// Check 'classpath:' protocol
	boolean isClasspathProtocol = isClasspathProtocol(rootDir);
	
	// Set protocol, because in ERM default protocol is 'classpath:' 
	String protocol = isClasspathProtocol ? "classpath:" : "file:";
	
	try {
	    
	    // Load 'erm.xml' file
	    // If file not found the ignore (It is normal situation)
	    String ermConfigFile = Configuration.DEFAULT_CONFIGURATON_FILE; // By default 'erm.xml'
	    ermConfigFile = AppConfigurationUtils.getRootConfigFileName(rootDir, ermConfigFile);
	    InputStream is = AppConfigurationUtils.getConfigInputStream(ermConfigFile);
	    configuration.configure(is);
	} catch (FileNotFoundException ex) {	
	    // Ignore if file 'erm.xml' not found
	} catch (Exception ex) {
	    throw new ApplicationException(ex);
	}
	
	// Set current ClassLoader
	configuration.setClassLoader(Thread.currentThread().getContextClassLoader());
	
	// Set resource (loader) class
	configuration.setResourceClass(getClass());
	
	List<IPackageConfig> packages = getEnabledPackages();
	if (packages == null) {
	    return;
	}
	List<String> mapping = new ArrayList<String>();
	for (IPackageConfig pkg : packages) {
	    String packageFolder = pkg.getPackageFolder();
	    if (StringUtils.isEmpty(packageFolder)) {
		continue;
	    }
	    mapping.add(protocol + pkg.getAbsoluteConfigFolder() + "/" + packageFolder + "/entities");
	    /*
	    if (isFileProtocol) {
		//FILE PROTOCOL
		mapping.add("file:" + pkg.getAbsoluteConfigFolder() + "/" + packageFolder + "/entities");
	    } else {
		//CLASSPATH PROTOCOL
		mapping.add("classpath:" + basePackage + packageFolder + "/server/entities");
	    }
	    */
	    
	}
	configuration.addMapping(mapping.toArray(new String[0]));
    }
    

    /**
     * Initialize <code>ServiceLocator</code>
     * 
     * @throws ApplicationException
     */
    protected void initServiceLocator() throws ApplicationException {
	setApplicationContext(this.getApplicationContext());
	ServiceFactory.setServiceLocator(this);
    }

    /**
     * Initialize <code>ConnectionManager</code>
     * 
     * @throws ApplicationException
     */
    protected void initConnectionManager() throws ApplicationException {
	SpringConnectionHolder connectionHolder = new SpringConnectionHolder();
	connectionHolder.setApplicationContext(this.getApplicationContext());
    }

    public List<IPackageConfig> getPackages() {
	if (packages == null && !isLoadPackages) {
	    isLoadPackages = true;
	    PackageConfigurer conf = (PackageConfigurer) ConfigurerManager.getConfigurer(PackageConfigurer.NAME);
	    if (conf != null) {
		packages = conf.getObjects();
	    }
	}
        return packages;
    }
    
    public List<IPackageConfig> getEnabledPackages() {
	if (enabledPackages == null && !isLoadEnabledPackages) {
	    isLoadEnabledPackages = true;
	    List<IPackageConfig> pkgs = getPackages();
	    if (pkgs != null) {
		List<IPackageConfig> resultPackages = new ArrayList<IPackageConfig>();
		for (IPackageConfig pkg: pkgs) {
		    if (!pkg.isEnabled()) {
			continue;
		    }
		    resultPackages.add(pkg);
		}
		if (resultPackages.size() > 0) {
		    packages = resultPackages;
		}
	    }
	}
        return packages;
    }
 
    class MyFileSystemXmlApplicationContext extends FileSystemXmlApplicationContext {

	
	////
	public MyFileSystemXmlApplicationContext(String configLocation) throws BeansException {
	    super(configLocation);
	}

	public MyFileSystemXmlApplicationContext(String[] configLocations, ApplicationContext parent) throws BeansException {
	    super(configLocations, parent);
	}

	public MyFileSystemXmlApplicationContext(String[] configLocations, boolean refresh, ApplicationContext parent) throws BeansException {
	    super(configLocations, refresh, parent);
	}

	public MyFileSystemXmlApplicationContext(String[] configLocations, boolean refresh) throws BeansException {
	    super(configLocations, refresh);
	}

	public MyFileSystemXmlApplicationContext(String[] configLocations) throws BeansException {
	    super(configLocations);
	}

	public MyFileSystemXmlApplicationContext(String[] configLocations, ClassLoader classLoader) throws BeansException {
	    //this(configLocations);
	    super((ApplicationContext) null);
	    setClassLoader(classLoader);
	    setConfigLocations(configLocations);
	    refresh();
	}

	 protected Resource getResourceByPath(String path)  {
	     
	     //Disable remove first '/'. It is problem at Linux
	     //if(path != null && path.startsWith("/")) {
	     //  path = path.substring(1);
	     //}
	     
	     return new FileSystemResource(path);
         }
	 
	protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
	    
	    // TODO: Stub
	    String beanName = "propertyConfigurer";
	    String propertyName = "location";
	    
	    BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
 
	    MutablePropertyValues values = bd.getPropertyValues();
	    PropertyValue v = values.getPropertyValue(propertyName);
	    
	    TypedStringValue tv = (TypedStringValue) v.getValue();
	    String text = tv.getValue();
	    
	    String rootDir = getRootDir();
	    String parentRootDir = getParentDir(rootDir) + System.getProperty("file.separator");
	    text = StringUtils.replaceAll(text, "../", parentRootDir);
	    text = StringUtils.replaceAll(text, "..\\", parentRootDir);
	    
	    values.removePropertyValue(v);
	    
	    TypedStringValue ntv = new TypedStringValue(text);
	    
	    PropertyValue nv = new PropertyValue(propertyName, ntv);
	    
	    values.addPropertyValue(nv);
	}
	
	private String getParentDir(String dir) {
		if (dir == null) {
		    return null;
		}
		int index = dir.lastIndexOf(File.separator);
		if (index < 0) {
		    return dir;
		}
		return dir.substring(0, index);
	    }
	
    }

    
   
    
    
    
    protected Object getServiceProxy(Object service, Class serviceInterface) {
	
	
	if (service == null) {
	    return null;
	}
	
	//DISABLE:OHA
	//return null;
	return service;

	/*
	// We use special data cache
	String serviceClassName = serviceInterface.getName();
	ServiceCache serviceCache = ServiceCacheEnvironment.getServiceCache(serviceClassName);
	if (serviceCache == null) {
	    
	    // TODO
	    //ServiceCacheEnvironment.addServiceCache(serviceClassName);
	    return service;
	}
	
	ServiceHandler serviceHandler = new ServiceHandler(service, serviceCache);
	Object serviceProxy = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] {serviceInterface}, serviceHandler);
	return serviceProxy;
	*/
    }


    
   

}
