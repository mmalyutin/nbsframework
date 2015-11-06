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


package org.plazmaforge.framework.config;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.config.configurer.AcceptorConfigurer;
import org.plazmaforge.framework.config.configurer.ActionConfigurer;
import org.plazmaforge.framework.config.configurer.AssociationConfigurer;
import org.plazmaforge.framework.config.configurer.ConfigurerInfo;
import org.plazmaforge.framework.config.configurer.FormConfigurer;
import org.plazmaforge.framework.config.configurer.InterfaceConfigurer;
import org.plazmaforge.framework.config.configurer.MenuBarConfigurer;
import org.plazmaforge.framework.config.configurer.ModuleConfigurer;
import org.plazmaforge.framework.config.configurer.ObjectConfigurer;
import org.plazmaforge.framework.config.configurer.PackageConfigurer;
import org.plazmaforge.framework.config.configurer.ReportConfigurer;
import org.plazmaforge.framework.config.configurer.SystemConfigurer;
import org.plazmaforge.framework.config.configurer.ToolBarConfigurer;
import org.plazmaforge.framework.config.configurer.xml.XMLAcceptorConfigurer;
import org.plazmaforge.framework.config.configurer.xml.XMLActionConfigurer;
import org.plazmaforge.framework.config.configurer.xml.XMLAssociationConfigurer;
import org.plazmaforge.framework.config.configurer.xml.XMLFormConfigurer;
import org.plazmaforge.framework.config.configurer.xml.XMLInterfaceConfigurer;
import org.plazmaforge.framework.config.configurer.xml.XMLMenuBarConfigurer;
import org.plazmaforge.framework.config.configurer.xml.XMLPackageConfigurer;
import org.plazmaforge.framework.config.configurer.xml.XMLReportConfigurer;
import org.plazmaforge.framework.config.configurer.xml.XMLSystemConfigurer;
import org.plazmaforge.framework.config.configurer.xml.XMLToolBarConfigurer;
import org.plazmaforge.framework.config.object.IFormConfig;
import org.plazmaforge.framework.config.object.IPackageConfig;
import org.plazmaforge.framework.config.object.IReportDescriptorConfig;
import org.plazmaforge.framework.config.object.IModuleConfig;
import org.plazmaforge.framework.config.object.IObjectConfig;
import org.plazmaforge.framework.config.object.IAcceptorConfig;
import org.plazmaforge.framework.config.object.IReportConfig;
import org.plazmaforge.framework.util.ClassUtils;
import org.plazmaforge.framework.util.StringUtils;

/**
 * 
 * @author ohapon
 * 
 */
public class ConfigurerManager {

    private static ConfigurerManager instance;

    private Map<String, String> defaultConfigurerClassNames = new HashMap<String, String>();

    
    private SystemConfigurer systemConfigurer;
    
    // Base configures
    public static final String[] CONFIGURER_NAMES = {
	ActionConfigurer.NAME,
	InterfaceConfigurer.NAME,
	ToolBarConfigurer.NAME,
	MenuBarConfigurer.NAME,
	FormConfigurer.NAME,
	AcceptorConfigurer.NAME,
	ReportConfigurer.NAME,
	AssociationConfigurer.NAME,
	PackageConfigurer.NAME,
    };
    
    static {

	// Initialize default configurers
	
	setDefaultConfigurerClassName(XMLSystemConfigurer.NAME, XMLSystemConfigurer.class.getName());
	
	setDefaultConfigurerClassName(XMLActionConfigurer.NAME, XMLActionConfigurer.class.getName());
	setDefaultConfigurerClassName(XMLInterfaceConfigurer.NAME, XMLInterfaceConfigurer.class.getName());
	setDefaultConfigurerClassName(XMLMenuBarConfigurer.NAME, XMLMenuBarConfigurer.class.getName());
	setDefaultConfigurerClassName(XMLToolBarConfigurer.NAME, XMLToolBarConfigurer.class.getName());
	setDefaultConfigurerClassName(XMLReportConfigurer.NAME, XMLReportConfigurer.class.getName());
	setDefaultConfigurerClassName(XMLFormConfigurer.NAME, XMLFormConfigurer.class.getName());
	setDefaultConfigurerClassName(XMLAcceptorConfigurer.NAME, XMLAcceptorConfigurer.class.getName());
	setDefaultConfigurerClassName(XMLPackageConfigurer.NAME, XMLPackageConfigurer.class.getName());
	setDefaultConfigurerClassName(XMLAssociationConfigurer.NAME, XMLAssociationConfigurer.class.getName());
    }

    private ConfigurerManager() {

    }

    public static ConfigurerManager getInstance() {
	if (instance == null) {
	    instance = new ConfigurerManager();
	}
	return instance;
    }

    /**
     * Get global configurer by name
     * @param name
     * @return
     */
    public static ObjectConfigurer getConfigurer(String name) {
	if (getInstance().systemConfigurer == null) {
	    return null;
	}
	return getInstance().systemConfigurer.getConfigurer(name);
    }

    /**
     * Get all configurers
     * @return
     */
    public static List<ObjectConfigurer<?>> getConfigurers() {
	List<ObjectConfigurer<?>> list = new ArrayList<ObjectConfigurer<?>>();
	if (getInstance().systemConfigurer == null) {
	    return list;
	}
	list.addAll(getInstance().systemConfigurer.getConfigurers());
	return list;
    }

    public static List<ConfigurerInfo<?>> getConfigurersInfo() {
	return getConfigurersInfo(null);
    }
    
    /**
     * Get configurer info list
     * @return
     */
    public static List<ConfigurerInfo<?>> getConfigurersInfo(String ui) {
	List<ConfigurerInfo<?>> configurerInfoList = new ArrayList<ConfigurerInfo<?>>();
	List<ObjectConfigurer<?>> configurerList = getConfigurers();
	if (configurerList == null) {
	    return configurerInfoList;
	}
	for (ObjectConfigurer c : configurerList) {
	    ConfigurerInfo inf = new ConfigurerInfo();
	    inf.setName(c.getName());
	    inf.setClassName(c.getClass().getName());
	    configurerInfoList.add(inf);

	    List<IObjectConfig> objects = null;
	    if (ui == null) {
		objects = c.getObjects();
	    } else {
		//TODO: Use special marker (UIDepenedencyConfigurer)
		if (c instanceof ToolBarConfigurer || c instanceof MenuBarConfigurer){
		    objects = c.getObjects(ui);
		} else {
		    objects = c.getObjects();
		}
	    }
	    
	    if (objects == null) {
		continue;
	    }
	    for (IObjectConfig objectConfig : objects) {
		inf.addObject(objectConfig);
	    }
	}
	return configurerInfoList;
    }

    public static void setDefaultConfigurerClassName(String name,  String className) {
	getInstance().defaultConfigurerClassNames.put(name, className);
    }

    public static String getDefaultConfigurerClassName(String name) {
	return (String) getInstance().defaultConfigurerClassNames.get(name);
    }
    
    public static SystemConfigurer createSystemConfigurer(AppConfiguration configuration) {
	// TODO: Stub. Must get configurer by name 'SystemConfigurer'
	return new XMLSystemConfigurer(configuration);
    }
    
    public static SystemConfigurer getSystemConfigurer(AppConfiguration configuration) {
	if (getInstance().systemConfigurer == null) {
	    getInstance().systemConfigurer = createSystemConfigurer(configuration);
	    
	}
	return getInstance().systemConfigurer;
    }
    
    
    public static ObjectConfigurer newConfigurer(AppConfiguration configuration, String name, String type, String className) {
	try {
	    if (StringUtils.isEmpty(name)) {
		return null;
	    }
	    String cn = className;
	    if (StringUtils.isEmpty(className)) {
		if (StringUtils.isEmpty(type)) {
		    return null;
		}
		cn = getDefaultConfigurerClassName(type);

	    }

	    // Create instance of configurer
	    Class<?> configurerClass = ClassUtils.getClass(cn);
	    Constructor<?> constructor = configurerClass.getConstructor(AppConfiguration.class);
	    if (constructor == null) {
		return null;
	    }
	    ObjectConfigurer configurer = (ObjectConfigurer) constructor.newInstance(configuration);
	    //ObjectConfigurer configurer = (ObjectConfigurer) ClassUtils.newInstance(cn);
	    configurer.setName(name);

	    return configurer;
	} catch (Exception ex) {
	    return null;
	}
    }


    /**
     * Create configurer by name, type, className and register
     * 
     * @param name
     * @param type
     * @param className
     * @return
     */
    public static ObjectConfigurer createConfigurer(AppConfiguration configuration, String name, String type, String className, boolean isAutoRegister) {
	try {
	    ObjectConfigurer configurer = newConfigurer(configuration, name, type, className);
	    return configurer;
	} catch (Exception ex) {
	    return null;
	}
    }

    /**
     * Create child configurer
     * @param name
     * @param type
     * @param className
     * @return
     */
    public static ObjectConfigurer createChildConfigurer(AppConfiguration configuration, String name, String type, String className) {
	try {
	    
	    ObjectConfigurer configurer = newConfigurer(configuration, name, type, className);
	    ObjectConfigurer parentConfigurer = getConfigurer(name);
	    configurer.setParentConfigurer(parentConfigurer);
	    
	    return configurer;
	} catch (Exception ex) {
	    return null;
	}
    }

    /**
     * Create configurer by name and type
     * 
     * @param name
     * @param type
     * @return
     */
    public static ObjectConfigurer createConfigurer(AppConfiguration configuration, String name, String type, boolean isAutoRegister) {
	return createConfigurer(configuration, name, type, null, isAutoRegister);
    }

    
    ////
    
    public static List<IFormConfig> getFormsByReport(IReportConfig report) {
	if (report == null) {
	    return null;
	} 
	return getFormsByReportName(report.getName());
    }
    
    public static List<IFormConfig> getFormsByReportName(String reportName) {
	if (reportName == null) {
	    return null;
	}
	reportName = reportName.trim();
	FormConfigurer formConfigurer = (FormConfigurer) getConfigurer(FormConfigurer.NAME);
	if (formConfigurer == null) {
	    return null;
	}
	List<IFormConfig> forms = formConfigurer.getObjects();
	List<IFormConfig> result = new ArrayList<IFormConfig>();
	for (IFormConfig form : forms) {
	    List<IReportDescriptorConfig> reportDescriptors = form.getReportDescriptors();
	    if (reportDescriptors == null) {
		continue;
	    }
	    for (IReportDescriptorConfig reportDescriptor : reportDescriptors) {
		if (reportDescriptor.getRef() == null) {
		    continue;
		}
		if (reportName.equals(reportDescriptor.getRef())) {
		    result.add(form);
		}
	    }
	}
	if (result.isEmpty()) {
	    return null;
	}
	return result;
    }
    
    public static IAcceptorConfig getReportAcceptorByName(List<IAcceptorConfig> acceptors, String acceptorName) {
	if (acceptors == null) {
	    return null;
	}
	if (acceptorName == null) {
	    return null;
	}
	acceptorName = acceptorName.trim();
	for (IAcceptorConfig c : acceptors) {
	    String currName = c.getName();
	    if (currName == null) {
		continue;
	    }
	    if (acceptorName.equals(currName.trim())) {
		return c;
	    }
	}
	return null;
    }
    
    /*
    public static IEntityConfig getEntityByObject(List<IEntityConfig> entities, IObjectConfig object, Class type) {
	if (entities == null || object == null || type == null) {
	    return null;
	}
	String objectId = object.getConfigId();
	String objectName = objectId; // !!!
	String beanName = AssociationManager.getAssociationCreator().createBeanName(objectName, type);
	if (beanName == null) {
	    return null;
	}
	String id = beanName.trim();
	id = ConfigUtils.generateIdByCode(id); // UPPER
	
	for (IEntityConfig entity: entities) {
	    String entityId = entity.getConfigId(); // UPPER
	    if (id.equals(entityId)) {
		return entity;
	    }
	}
	return null;
    }
    */

    /*
    public static IObjectConfig getObjectByIndex(ObjectConfigurer configurer, List<IEntityConfig> entities, int index, Class type) throws ApplicationException {
   	if (entities == null) {
   	    return null;
   	}
   	IEntityConfig entity = entities.get(index);
   	return getObjectByEntity(configurer, entity, type);
   }
       

    public static IObjectConfig getObjectByEntity(ObjectConfigurer configurer, IEntityConfig entity, Class type) throws ApplicationException {
	String objectId = getObjectIdByEntity(configurer, entity, type);
	IObjectConfig object = configurer.getObjectById(objectId);
	return object;
    }
    

    public static String getObjectIdByEntity(ObjectConfigurer configurer, IEntityConfig entity, Class type) throws ApplicationException {
	if  (entity == null || type == null) {
	    return null;
	}
	Association association = AssociationManager.getAssociationByEntity(entity);
	if (association == null) {
	    return null;
	}
	String className = association.getElementClassNameByType(type);
	if (className != null) {
	    int index = className.lastIndexOf(".");
	    if (index > 0) {
		className = className.substring(index + 1);
	    }
	}
	
	// Class name is ID !!!
	return className;
	
    }
    */
    
    /**
     * Return all modules of application
     * @return
     */
    public static List<IModuleConfig> getModules() {
	ModuleConfigurer moduleConfigurer = (ModuleConfigurer) getConfigurer(ModuleConfigurer.NAME);
	if (moduleConfigurer == null) {
	    return null;
	}
	List<IModuleConfig> modules = moduleConfigurer.getObjects();
	return modules;
    }
    
    /**
     * Return all configurers of application
     * @return
     */
    public static List<ObjectConfigurer<?>> getStorableConfigurers() {
	return getStorableConfigurers(false);
    }
    
    /**
     * Return all configurers of application.
     * If isFlat is true then return only configurers of modules.
     * @param isFlat
     * @return
     */
    public static List<ObjectConfigurer<?>> getStorableConfigurers(boolean isFlat) {
	
	List<IModuleConfig> modules = getModules();
	if (modules == null || modules.isEmpty()) {
	    return null;
	}
	List<ObjectConfigurer<?>> configurers = new ArrayList<ObjectConfigurer<?>>();
	List<ObjectConfigurer<?>> moduleConfigurers = null;
	List<ObjectConfigurer<?>> packageConfigurers = null;
	
	for (IModuleConfig module : modules) {
	    if (module == null) {
		continue;
	    }
	    
	    // Only module configurers
	    //moduleConfigurers = module.getConfigurers();
	    moduleConfigurers = getConfigurersByPath(module.getModulePath());
	    if (moduleConfigurers != null && !moduleConfigurers.isEmpty()) {
		configurers.addAll(moduleConfigurers);
	    }
	    
	    if (isFlat) {
		continue;
	    }

	    // Package configurers
	    packageConfigurers = getPackageConfigurers(module);
	    if (packageConfigurers != null && !packageConfigurers.isEmpty()) {
		configurers.addAll(packageConfigurers);
	    }
	    
	    
	}
	return configurers;
    }
    
    
    /**
     * Return configurers by type.
     * @param type
     * @return
     */
    public static List<ObjectConfigurer<?>> getStorableConfigurersByType(String type) {
	return getStorableConfigurersByType(type, false);
    }
    
    /**
     * Return configurers by type.
     * If isFlat is true then return only configurers of modules. 
     * @param type
     * @param isFlat
     * @return
     */
    public static List<ObjectConfigurer<?>> getStorableConfigurersByType(String type, boolean isFlat) {
	List<ObjectConfigurer<?>> configurers = new ArrayList<ObjectConfigurer<?>>();
	if (type == null) {
	    return configurers;
	}
	List<ObjectConfigurer<?>> allConfigurers = getStorableConfigurers();
	if (allConfigurers == null || allConfigurers.isEmpty()) {
	    return configurers;
	}
	for (ObjectConfigurer<?> c: allConfigurers) {
	    if (type.equals(c.getName())) {
		configurers.add(c);
	    }
	}
	return configurers;
	
    }
    
    public static boolean isModifyConfigurers() {
	List<ObjectConfigurer<?>> configurers = getStorableConfigurers();
	if (configurers == null || configurers.isEmpty()) {
	    return false;
	}
	for (ObjectConfigurer<?> c : configurers) {
	    if (c.isModify()) {
		return true;
	    }
	}
	return false;
    }

    //[MODULE]
    public static List<IPackageConfig> getPackages(IModuleConfig module) {
   	if (module == null) {
   	    return null;
   	}
   	PackageConfigurer packageConfigurer = (PackageConfigurer) getConfigurerByPath(module.getModulePath(), PackageConfigurer.NAME);
   	if (packageConfigurer == null) {
   	 return null;
   	}
   	return packageConfigurer.getObjects();
    }
    
    // [MODULE]
    public static List<ObjectConfigurer<?>> getPackageConfigurers(IModuleConfig module) {
	if (module == null) {
	    return null;
	}
	List<IPackageConfig> packages = getPackages(module);
	if (packages == null) {
	    return null;
	}
	List<ObjectConfigurer<?>> configurers = new ArrayList<ObjectConfigurer<?>>();
	List<ObjectConfigurer<?>> packageConfigurers = null;
	for (IPackageConfig p : packages) {
	    // packageConfigurers = p.getConfigurers();
	    packageConfigurers = getConfigurersByPath(p.getPackagePath());
	    if (packageConfigurers == null || packageConfigurers.isEmpty()) {
		continue;
	    }
	    configurers.addAll(packageConfigurers);
	}
	return configurers;
    }
     
    // [MODULE]
    public static List<ObjectConfigurer<?>> getAllConfigurers(IModuleConfig module) {
	if (module == null) {
	    return null;
	}
	List<ObjectConfigurer<?>> configurers = new ArrayList<ObjectConfigurer<?>>();

	// Only module configurers
	List<ObjectConfigurer<?>> c = getConfigurersByPath(module.getModulePath());
	if (c != null && !c.isEmpty()) {
	    configurers.addAll(c);
	}

	// Package configurers
	c = getPackageConfigurers(module);
	if (c != null && !c.isEmpty()) {
	    configurers.addAll(c);
	}
	return configurers;
    }
     
     public static List getStructureObjects(IModuleConfig module) {
	 if (module == null) {
	     return null;
	 }
 	List objects = new ArrayList();
 	List<IPackageConfig> packages = getPackages(module);
 	if (packages != null && !packages.isEmpty()) {
 	    objects.addAll(packages);
 	}
 	//List<ObjectConfigurer> configurers = getConfigurers();
 	List<ObjectConfigurer<?>> configurers = getConfigurersByPath(module.getModulePath());
 	if (configurers != null && !configurers.isEmpty()) {
 	    objects.addAll(configurers);
 	}
 	return objects;
     }    
    
    public static void reset() {
	if (instance == null) {
	    return;
	}
	if (instance.systemConfigurer != null) {
	    instance.systemConfigurer.reset();
	    instance.systemConfigurer = null;
	}
    }
    
    
    /////////////////////////////////////////////////////////////////
    
    public static List<ObjectConfigurer<?>> getConfigurersByPath(String path) {
	return getInstance().systemConfigurer == null ? null : getInstance().systemConfigurer.getConfigurersByPath(path);
    }
      
    public static ObjectConfigurer<?> getConfigurerByPath(String path, String name) {
	return getInstance().systemConfigurer == null ? null : getInstance().systemConfigurer.getConfigurerByPath(path, name);
    }

    public static ObjectConfigurer<?> getConfigurer(List<ObjectConfigurer<?>> configurers, String name) {
	return getInstance().systemConfigurer == null ? null : getInstance().systemConfigurer.getConfigurer(configurers, name);
    }
       
}
