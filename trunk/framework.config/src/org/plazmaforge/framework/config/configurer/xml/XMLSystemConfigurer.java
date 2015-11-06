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

package org.plazmaforge.framework.config.configurer.xml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.jdom.Document;
import org.jdom.Element;
import org.plazmaforge.framework.config.AppConfiguration;
import org.plazmaforge.framework.config.AppConfiguration.Replacer;
import org.plazmaforge.framework.config.ConfigurerManager;
import org.plazmaforge.framework.config.configurer.ConfigurerInfo;
import org.plazmaforge.framework.config.configurer.ModuleConfigurer;
import org.plazmaforge.framework.config.configurer.ObjectConfigurer;
import org.plazmaforge.framework.config.configurer.PackageConfigurer;
import org.plazmaforge.framework.config.configurer.SystemConfigurer;
import org.plazmaforge.framework.config.object.IModuleConfig;
import org.plazmaforge.framework.config.object.IObjectConfig;
import org.plazmaforge.framework.config.object.IPackageConfig;
import org.plazmaforge.framework.config.object.ModuleConfig;
import org.plazmaforge.framework.core.exception.ApplicationException;
import org.plazmaforge.framework.util.FileUtils;



/**
 * Root System Configurer
 * 
 * The SystemConfigurer doesn't load children objects. 
 * The SystemConfigurer loads system and platform properties and other configurers.
 * 
 * @author ohapon
 * 
 */
public class XMLSystemConfigurer extends XMLObjectConfigurer implements SystemConfigurer {


    
    public static String NAME = "XMLSystemConfigurer";

    public static final String XML_ROOT = "system-config";
    
    public static final String DEFAULT_CONFIG_FILE = AppConfiguration.DEFAULT_SYSTEM_CONFIG_FILE;

    public static final String XML_CONFIGURERS = "configurers";
    
    public static final String XML_CONFIGURER = "configurer";

    

    
    protected Replacer[] replacers;
    
    private ModuleConfigurer moduleConfigurer;
    
    private List<ConfigurerInfo> rootConfigurerDescriptors;
    
    private List<ObjectConfigurer<?>> rootConfigurers;
    
    private Map<String, List<ObjectConfigurer<?>>> configurerTree;
    
    
    
    public XMLSystemConfigurer(AppConfiguration configuration) {
	super(configuration);
	setConfigFileName(DEFAULT_CONFIG_FILE);
	configurerTree = new LinkedHashMap<String, List<ObjectConfigurer<?>>>();
    }
    
    public AppConfiguration getConfiguration() {
        return configuration;
    }

    public List<ObjectConfigurer<?>> getConfigurers() {
	if (rootConfigurers == null) {
	    rootConfigurers = new ArrayList<ObjectConfigurer<?>>();
	}
	return rootConfigurers;
    }
    
    public ObjectConfigurer<?> getConfigurer(String name) {
	if (name == null || rootConfigurers == null) {
	    return null;
	}
	for (ObjectConfigurer<?> configurer : rootConfigurers) {
	    if (name.equals(configurer.getName())) {
		return configurer;
	    }
	}
	return null;
    }
    
      
    public List<ObjectConfigurer<?>> getConfigurersByPath(String path) {
	if (path == null) {
	    return null;
	}
	return configurerTree.get(path);
    }
    
    public ObjectConfigurer<?> getConfigurerByPath(String path, String name) {
	if (path == null || name == null) {
	    return null;
	}
	List<ObjectConfigurer<?>> configurers = getConfigurersByPath(path);
	return getConfigurer(configurers, name);
    }
    
    public ObjectConfigurer<?> getConfigurer(List<ObjectConfigurer<?>> configurers, String name) {
	if (configurers == null || configurers.isEmpty()) {
	    return null;
	}
	for (ObjectConfigurer<?> configurer : configurers) {
	    if (name.equals(configurer.getName())) {
		return configurer;
	    }
	}
	return null;
    }
    
    protected void addCongigurerToPath(String path, ObjectConfigurer<?> configurer) {
	if (path == null) {
	    throw new IllegalArgumentException("Configurer path must be not null");
	}
	if (configurer == null) {
	    throw new IllegalArgumentException("Configurer must be not null");
	}
	List<ObjectConfigurer<?>> configurers = configurerTree.get(path);
	if (configurers == null) {
	    configurers = new ArrayList<ObjectConfigurer<?>>();
	    configurerTree.put(path, configurers);
	}
	configurers.add(configurer);
	
	getLogger().debug("CNFPath: " + path + "  " + configurer.getName() + " [" + System.identityHashCode(configurer) + "]");
    }
    
    
    @Override
    protected boolean isSupportNLSStore() {
	return false;
    }
    
    @Override
    protected void doLoadObjectsProcess() throws Exception {
	doLoadObjects(); // Without check file
    }

    @Override
    protected void doStoreObjectsProcess() throws Exception {
	doStoreObjects(); // Without check file
    }

    protected void doStoreObjects() throws Exception {
	// STUB
    }

    protected void doLoadObjects() throws Exception {
	
	    String rootConfigDir = getConfiguration().getRootConfigDir();
	    Element rootElement = getRootElement(getConfigInputStream());
	    
	    replacers = createReplacers();
	    loadProperties(rootElement, getSystemProperties(), getPlatformProperties());
	    
	    // Transfer properties from PlatformEnvironment.getProperties() to System.getProperties()
	    transferProperties(getPlatformProperties(), getSystemProperties());
	    
	    
	    List configurerElements = getChildren(rootElement, XML_CONFIGURERS, XML_CONFIGURER);
	    
	    rootConfigurerDescriptors = new ArrayList<ConfigurerInfo>();
	    rootConfigurers =  new ArrayList<ObjectConfigurer<?>>();
	    
	    ////
	    String[] CONFIGURER_NAMES = ConfigurerManager.CONFIGURER_NAMES;
	    
	    ///////////////////////////////////////////////////////////////////////////////////////////////
	    //
	    // LOAD ROOT CONFIGURERS FROM SYSTEM CONFIG
	    //
	    ///////////////////////////////////////////////////////////////////////////////////////////////
	    
	    Element element = null;

	    for (Object e: configurerElements) {
		
		element = (Element) e;
		String name = element.getAttributeValue(XML_NAME);
		String type = element.getAttributeValue(XML_TYPE);
		String klass = element.getAttributeValue(XML_CLASS);
		String file = element.getAttributeValue(XML_FILE);

		
		// ////// BLOCK 1
		ConfigurerInfo configurerInfo = new ConfigurerInfo(name, type, klass, file);
		rootConfigurerDescriptors.add(configurerInfo);

		// Load and register configurer
		ObjectConfigurer c = loadConfigurer(name, type, klass, file, true); // TODO: Must analyze
		c.initFiles(rootConfigDir, true);

		rootConfigurers.add(c);
		addCongigurerToPath(IObjectConfig.ROOT_PATH, c);
		// ////// BLOCK 1
	    }
	    
	    ///////////////////////////////////////////////////////////////////////////////////////////////
	    
	    // Create module configurer (has all modules)
	    moduleConfigurer = new XMLModuleConfigurer(configuration);
	    moduleConfigurer.setName(ModuleConfigurer.NAME);
	    
	    
	    //[CONF-DISABLE]
	    //ConfigurerManager.addConfigurer(ModuleConfigurer.NAME, moduleConfigurer);
	    
	    rootConfigurers.add(moduleConfigurer);
	    addCongigurerToPath(IObjectConfig.ROOT_PATH, moduleConfigurer);

	    ///////////////////////////////////////////////////////////////////////////////////////////////
	    //
	    // CHECK ROOT CONFIGURERS
	    // IF CONFIGURER NOT FOUND THEN CREATE AND LOAD DEFAULT
	    //
	    ///////////////////////////////////////////////////////////////////////////////////////////////
	    for (String name: CONFIGURER_NAMES) {
		boolean found = false;
		for (ConfigurerInfo ci: rootConfigurerDescriptors) {
		    if (name.equals(ci.getName())) {
			found = true;
			break;
		    }
		}
		if (!found) {
		
		    String type = "XML" + name; // TODO: stub
		    String klass = null;
		    String file = null;
		    
		    //////// BLOCK 1
		    ConfigurerInfo configurerInfo = new ConfigurerInfo(name, type, klass, file);
		    rootConfigurerDescriptors.add(configurerInfo);

		    // Load and register configurer
		    ObjectConfigurer c = loadConfigurer(name, type, klass, file, true); // TODO: Must analyze
		    c.initFiles(rootConfigDir, true);

		    rootConfigurers.add(c);
		    addCongigurerToPath(IObjectConfig.ROOT_PATH, c);
		    //////// BLOCK 1
		}
	    }
	    
	    
	    ///////////////////////////////////////////////////////////////////////////////////////////////
	    //
	    // LOAD MODULES
	    //
	    ///////////////////////////////////////////////////////////////////////////////////////////////
	    String[] moduleDirs = getConfiguration().getConfigurationModuleDirs();
	    int k = -1;
	    for (String moduleDir : moduleDirs) {
		k++;
		
		ModuleConfig module = new ModuleConfig();
		module.setModuleDir(moduleDir);
		module.setDefaultModule(k == 0);
		module.setRootConfigDir(getConfiguration().getRootConfigDir(moduleDir));
		
		moduleConfigurer.addObject(module);
		
		//  Create configurers for module
		for (ConfigurerInfo configurerInfo : rootConfigurerDescriptors) {
		    ObjectConfigurer c = loadChildConfigurer(configurerInfo.getName(), configurerInfo.getType(), configurerInfo.getClassName(), configurerInfo.getFileName());
		    //module.addConfigurer(c);
		    
		    addCongigurerToPath(module.getModulePath(), c);
			
//		    if (c instanceof PackageConfigurer) {
//			module.setPackageConfigurer((PackageConfigurer) c);
//		    } 
		}
		
	    }
	    
	    ///////////////////////////////////////////////////////////////////////////////////////////////
	    //
	    // PROCESSING MODULES
	    //
	    ///////////////////////////////////////////////////////////////////////////////////////////////
	    
	    List<IModuleConfig> modules = moduleConfigurer.getObjects();
	    for (IModuleConfig module: modules) {
		
		String moduleRootConfigDir = module.getRootConfigDir();
		
		//PackageConfigurer packageConfigurer = module.getPackageConfigurer();
		List<ObjectConfigurer<?>> moduleConfigurers = getConfigurersByPath(module.getModulePath());
		PackageConfigurer packageConfigurer = (PackageConfigurer) getConfigurer(moduleConfigurers, PackageConfigurer.NAME);
		
		if (packageConfigurer != null) {
		    packageConfigurer.setPartConf(!module.isDefaultModule());
		    //packageConfigurer.setFiles(moduleRootConfigDir);
		}

		
		// 1. Load <default> objects
		//loadAllObjects(moduleRootConfigDir, module.getConfigurers());
		loadAllObjects(moduleRootConfigDir, moduleConfigurers);

		// If PackageConfigurer is null then we can't load packages configuration 
		if (packageConfigurer == null) {
		    continue;
		}
		
		List<IPackageConfig> pks = packageConfigurer.getObjects();
		if (pks == null || pks.isEmpty()) {
		    continue;
		}
		
		for (IPackageConfig p: pks) {
		    
		    p.setModuleConfig(module);
		    
		    //  Create configurers for package
		    for (ConfigurerInfo configurerInfo : rootConfigurerDescriptors) {
			ObjectConfigurer c = loadChildConfigurer(configurerInfo.getName(), configurerInfo.getType(), configurerInfo.getClassName(), configurerInfo.getFileName());
			// We can't add package configurer to package (absurd)
			if (c instanceof PackageConfigurer) {
			    continue;
			} 
			//p.addConfigurer(c);
			addCongigurerToPath(p.getPackagePath(), c);
		    }
		}
		
		// 2. Load <package> objects
		loadAllObjectsByPackages(moduleRootConfigDir, packageConfigurer);
		
	    }
	    
	    
	    
	   

	

    }
    
    protected Properties getSystemProperties() {
	return System.getProperties();
    }

    protected Properties getPlatformProperties() {
	//return PlatformEnvironment.getProperties();
	return getConfiguration().getPlatformProperties();
    }

    
    public void loadAllObjectsByPackages(String rootConfigDir, PackageConfigurer packageConfigurer) throws ApplicationException {

	if (packageConfigurer == null) {
	    return;
	}
	
	// Get all packages
	List<IPackageConfig> pks = packageConfigurer.getObjects();
	for (IPackageConfig p: pks) {
	    loadAllObjectsByPackage(rootConfigDir, p);
	}
    }
    
    
    protected Replacer[] createReplacers() {
	return getConfiguration().createReplacers();
    }
    
    
    
    protected String replaceValue(Replacer[] replacer, String value) {
	return getConfiguration().replaceValue(replacer, value);
    }

   
    protected void loadProperties(Element rootElement, Properties systemProperties, Properties platformProperties) {
	if (rootElement == null || (systemProperties == null && platformProperties == null)) {
	    return;
	}
	List propElements = getChildren(rootElement, XML_PROPERTIES, XML_PROPERTY);
	Element element = null;
	for(Object e: propElements) {
	    element = (Element) e;
	    
	    String propName = element.getAttributeValue(XML_NAME);
	    String propValue = replaceValue(replacers, element.getText());
	    boolean isSystem = getBoolean(element.getAttributeValue(XML_IS_SYSTEM));
	    
	    if (platformProperties != null) {
		platformProperties.setProperty(propName, propValue);
	    }
	    if (systemProperties != null && isSystem) {
		systemProperties.setProperty(propName, propValue);
	    }
	}
    }


    /**
     * Transfer properties
     * @param from
     * @param to
     */
    protected void transferProperties(Properties from, Properties to) {
	if (from == null || to == null) {
	    return;
	}
	Set keys = from.keySet();
	if (keys.isEmpty()) {
	    return;
	}
	for (Object objkey : keys) {
	    if (!(objkey instanceof String)) {
		continue;
	    }
	    String key = (String) objkey;
	    to.setProperty(key, from.getProperty(key));
	}
    }
    
    /**
     * Load configurer
     * 
     * @param name
     * @param type
     * @param className
     * @param fileName
     * @throws ApplicationException
     */
    protected ObjectConfigurer loadConfigurer(String name, String type, String className, String fileName, boolean isAutoRegister) throws ApplicationException {

	// Create and register (id isAutoRegiser) configurer
	ObjectConfigurer configurer = ConfigurerManager.createConfigurer(configuration, name, type, className, isAutoRegister);

	if (!isEmpty(fileName)) {
	    configurer.setConfigFileName(fileName);
	}
	return configurer;
    }

    
    protected ObjectConfigurer loadChildConfigurer(String name, String type, String className, String fileName) throws ApplicationException {

	// Create and register configurer
	ObjectConfigurer configurer = ConfigurerManager.createChildConfigurer(configuration, name, type, className);

	if (!isEmpty(fileName)) {
	    configurer.setConfigFileName(fileName);
	}
	return configurer;
    }

    
    /**
     * Load all objects (one configurer)
     * @param rootConfigDir
     * @param configurer
     * @throws ApplicationException
     */
    protected void loadAllObjects(String rootConfigDir, ObjectConfigurer<?> configurer) throws ApplicationException {
	configurer.initFiles(rootConfigDir, true);
	configurer.loadObjects();
    }

    /**
     * Load all objects (more configurers)
     * @param rootConfigDir
     * @param configurers
     * @throws ApplicationException
     */
    protected void loadAllObjects(String rootConfigDir, List<ObjectConfigurer<?>> configurers) throws ApplicationException {
	for (ObjectConfigurer<?> c : configurers) {
	    loadAllObjects(rootConfigDir, c);
	}
    }

    
    /**
     * Load all objects by package (one configurer)
     * @param rootConfigDir
     * @param packageConfig
     * @param configurer
     * @throws ApplicationException
     */
    protected void loadObjectsByPackage(String rootConfigDir, IPackageConfig packageConfig, ObjectConfigurer<?> configurer) throws ApplicationException {
	if (packageConfig == null) {
	    return;
	}
	String packageFolder = packageConfig.getPackageFolder();
	if (isEmpty(packageFolder)) {
	    return;
	}
	String packageConfigDir = FileUtils.getPath(rootConfigDir, AppConfiguration.DEFAULT_PACKAGES_DIR, packageFolder);
	
	loadAllObjects(packageConfigDir, configurer);

	// TODO: Must set package for packagable objects
	// configurer.getObjects();
	// for (...) ... 
    }

    

    /**
     * Load all objects by package
     * @param rootConfigDir
     * @param packageConfig
     * @throws ApplicationException
     */
    protected void loadAllObjectsByPackage(String rootConfigDir, IPackageConfig packageConfig) throws ApplicationException {
	//List<ObjectConfigurer> configurers = packageConfig.getConfigurers();
	List<ObjectConfigurer<?>> configurers = getConfigurersByPath(packageConfig.getPackagePath());
	if (configurers == null || configurers.isEmpty()) {
	    return;
	}
	for (ObjectConfigurer<?> c : configurers) {
	    loadObjectsByPackage(rootConfigDir, packageConfig, c);
	}
    }

    
    public List<ConfigurerInfo> getConfigurerInfoList() {
	if (rootConfigurerDescriptors == null) {
	    rootConfigurerDescriptors = new ArrayList<ConfigurerInfo>();
	}
        return rootConfigurerDescriptors;
    }
    
    public ConfigurerInfo getPackageConfigurerInfo() {
	if (rootConfigurerDescriptors == null || rootConfigurerDescriptors.isEmpty()) {
	    return null;
	}
	for (ConfigurerInfo i : rootConfigurerDescriptors) {
	    if (PackageConfigurer.NAME.equals(i.getName())) {
		return i; 
	    }
	}
	return null;
    }
    
    ////

    
    public ModuleConfigurer getModuleConfigurer() {
        return moduleConfigurer;
    }


    public List<IModuleConfig> getModules() {
        if (moduleConfigurer == null) {
            return Collections.<IModuleConfig>emptyList();
        }
        return moduleConfigurer.getObjects();
    }


    public void reset() {
	replacers = null;
	moduleConfigurer = null;
	if (rootConfigurerDescriptors != null) {
	    rootConfigurerDescriptors.clear();
	    rootConfigurerDescriptors = null;
	}
	if (rootConfigurers != null) {
	    rootConfigurers.clear();
	    rootConfigurers = null;
	}
    }
    

    public void storeDefaultObjects() throws ApplicationException {
	try {
	    setProcessing(true);
	    doStoreDefaultObjects();
	} catch (Exception ex) {
	    throw new ApplicationException(ex);
	} finally {
	    setProcessing(false);
	}
    }


    protected void doStoreDefaultObjects() throws Exception {

	Element root = createRootElement(XML_ROOT);
	Document doc = new Document(root);

	
	List<Element> children = new ArrayList<Element>();
	List<Element> configurers = new ArrayList<Element>();

	String[] CONFIGURER_NAMES = ConfigurerManager.CONFIGURER_NAMES;
	Element configurersElement = new Element(XML_CONFIGURERS);
	children.add(configurersElement);
	Element configurerElement = null;
	
	for (String name : CONFIGURER_NAMES) {

	    configurerElement = new Element(XML_CONFIGURER);

	    String type = "XML" + name; // TODO: stub

	    configurers.add(configurerElement);

	    setAttribute(configurerElement, XML_NAME, name);
	    setAttribute(configurerElement, XML_TYPE, type);

	}
	configurersElement.setContent(configurers);

	root.setContent(children);
	store(doc);
    }
    
}
