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


package org.plazmaforge.framework.config.configurer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.plazmaforge.framework.config.AppConfiguration;
import org.plazmaforge.framework.config.AppConfigurationUtils;
import org.plazmaforge.framework.config.object.IElement;
import org.plazmaforge.framework.config.object.IObjectConfig;
import org.plazmaforge.framework.config.object.IPackageConfig;
import org.plazmaforge.framework.core.logging.Logger;
import org.plazmaforge.framework.util.IPropertiesStore;
import org.plazmaforge.framework.util.StringUtils;
import org.plazmaforge.framework.util.SystemUtils;


/**
 * 
 * @author ohapon
 *
 * @param <T>
 */
public abstract class AbstractObjectConfigurer<T extends IObjectConfig> implements ObjectConfigurer<T> {

    
    protected AppConfiguration configuration;
    
    
    private String name;

    private List<T> objects = new ArrayList<T>();

    private Map<Serializable, T> objectMap = new HashMap<Serializable, T>();

    /**
     * File name of configuration
     * For example: 'action-config.xml'
     */
    private String configFileName;

    /**
     * Base name of properties (without locale prefix and '.properties')
     * For example: 'action'
     */
    private String propertiesBaseName;

    
    private String absoluteRootConfigDir;
    
    /**
     * Absolute file name of configuration
     * For example: 'C:\MyConfiguration\action-config.xml'
     */
    private String absoluteConfigFileName;


    /**
     * Absolute base name of properties (without locale prefix and '.properties')
     * For example: 'C:\MyConfiguration\action'
     */
    private String absolutePropertiesBaseName;
    
    
    /**
     * Absolute file name of properties (with locale prefix and '.properties')
     * For example: 'C:\MyConfiguration\action_en_US.properties'
     */
    private String absolutePropertiesFileName;
    
    
    
    private String absoluteRootPropertiesFileName;
    
    
    private Locale locale;
    
    // Parent
    private ObjectConfigurer<T> parentConfigurer;
    
    // Package (optional)
    private IPackageConfig packageConfig;
    
    // Processing flag
    private boolean processing;
    
    // Modify flag 
    private boolean modify;
    
    private boolean resolveFile;
    
    private boolean resolvePropertiesFile;
    
    
    // en_US: action_en.properties
    // ru_RU: action_ru.properties
    
    private boolean newPropertiesFileWithoutCountry = true;
    
    private Logger logger;
    
    public AbstractObjectConfigurer(AppConfiguration configuration) {
	this.configuration = configuration;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    
    /**
     * Return all objects
     * @return
     */
    public List<T> getObjects() {
	return objects;
    }

    
    /**
     * Return objects by category
     * @param category
     * @return
     */
    public List<T> getObjects(String category) {
	if (objects == null) {
	    return null;
	}
	
	// If category is null them return all objects
	if (category == null) {
	    return objects;
	}
	List<T> resultObjects = new ArrayList<T>();
	for (T o: objects) {
	    if (category.equals(o.getCategory())) {
		resultObjects.add(o);
	    }
	}
	return resultObjects;
    }
  
    
    /**
     * Return object by ID
     * @param id
     * @return
     */
    public T getObjectById(String id) {
	return objectMap.get(id);
    }

    /**
     * Return object by Name
     * @param code
     * @return
     */
    public T getObjectByName(String name) {
	if (name == null || objects == null) {
	    return null;
	}
	for (T o : objects) {
	    if (name.equals(o.getName())) {
		return o;
	    }
	}
	return null;
    }
    
    
    /**
     * Add object
     */
    public void addObject(T obj) {
	
	doAddObject(obj);
	obj.setPackageConfig(getPackageConfig());

	// Fire event
	doFireModifyObject(obj);

	if (parentConfigurer != null) {
	    parentConfigurer.addObjectLink(obj);
	}
    }

    /**
     * Remove object
     * @param obj
     */
    public void removeObject(T obj) {
	
	doRemoveObject(obj);
	obj.setPackageConfig(null);
	
	// Fire event
	doFireModifyObject(obj);
	
	if (parentConfigurer != null) {
	    parentConfigurer.removeObjectLink(obj);
	}
    }

    
    /////////////////////////////////////////////////////////////////////////////////////////////
    
    
    /**
     * Do add object
     */
    protected void doAddObject(T obj) {
	objects.add(obj);
	objectMap.put(obj.getId(), obj);
    }

    /**
     * Do remove object
     * @param obj
     */
    protected void doRemoveObject(T obj) {
	objects.remove(obj);
	objectMap.put(obj.getId(), null);
    }

    
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    

    /**
     * Add object link
     */
    public void addObjectLink(T obj) {
	doAddObject(obj);
	if (parentConfigurer != null) {
	    parentConfigurer.addObjectLink(obj);
	}
    }

    /**
     * Remove object link
     * @param obj
     */
    public void removeObjectLink(T obj) {
	doRemoveObject(obj);
	if (parentConfigurer != null) {
	    parentConfigurer.removeObjectLink(obj);
	}
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    
    public void updateObject(T obj) {
	fireModifyObject(obj);
	// 
    }
    
    public void fireModifyObject(T obj) {
	doFireModifyObject(obj);
    }

    protected void doFireModifyObject(T obj) {
	if (isProcessing()) {
	    return;
	}
	obj.setModified();
	setModify();
    }

    
    
    
    
    ///////////////////////////////////////////////////////////////////////
    
    public String getConfigFileName() {
        return configFileName;
    }

    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }

    public String getPropertiesBaseName() {
        return propertiesBaseName;
    }

    public void setPropertiesBaseName(String propertiesBaseName) {
        this.propertiesBaseName = propertiesBaseName;
    }
    
    
    ///////////////////////////////////////////////////////////////////////
    
    
    public String getAbsoluteConfigFileName() {
	return absoluteConfigFileName;
    }

    public void setAbsoluteConfigFileName(String absoluteConfigFileName) {
	this.absoluteConfigFileName = absoluteConfigFileName;
    }

    
    public String getAbsolutePropertiesBaseName() {
        return absolutePropertiesBaseName;
    }

    public void setAbsolutePropertiesBaseName(String absolutePropertiesBaseName) {
        this.absolutePropertiesBaseName = absolutePropertiesBaseName;
    }

    public String getAbsolutePropertiesFileName() {
	return absolutePropertiesFileName;
    }

    public void setAbsolutePropertiesFileName(String absolutePropertiesFileName) {
	this.absolutePropertiesFileName = absolutePropertiesFileName;
	
	if (absoluteRootPropertiesFileName == null) {
	    absoluteRootPropertiesFileName = absolutePropertiesFileName; 
	}
    }

    
    public String getAbsoluteRootPropertiesFileName() {
        return absoluteRootPropertiesFileName;
    }
    
    public boolean isDifBundle() {
	if (absoluteRootPropertiesFileName == null ||  absolutePropertiesFileName == null) {
	    return false;
	}
	return !absoluteRootPropertiesFileName.equals(absolutePropertiesFileName);
	
    }


    protected boolean isEmpty(Collection<?> collection) {
	return SystemUtils.isEmpty(collection);
    }

    protected boolean isEmpty(String str) {
	return SystemUtils.isEmpty(str);
    }

    protected boolean isEmpty(String[] array) {
	return SystemUtils.isEmpty(array);
    }

    protected boolean isEmptyAll(String[] array) {
	return SystemUtils.isEmptyAll(array);
    }

    protected String nullIfEmpty(String str) {
	return StringUtils.nullIfEmpty(str);
    }

    protected String replaceAll(String str, String s1, String s2) {
	return StringUtils.replaceAll(str, s1, s2);
    }

    protected boolean getBoolean(String str) {
	return str == null ? false : Boolean.valueOf(str);
    }

    protected String getBooleanString(boolean value) {
	return Boolean.toString(value);
    }
    
    protected String getBooleanStringNullIfFalse(boolean value) {
	if  (!value) {
	    return null;
	}
	return getBooleanString(value);
    }

    protected String getBooleanStringNullIfTrue(boolean value) {
	if  (value) {
	    return null;
	}
	return getBooleanString(value);
    }


    /**
     * The special initialize config object name.
     * If name is null set name like code or id
     * @param objectConfig
     */
    protected void initConfigObjectName(IObjectConfig objectConfig) {
	if (objectConfig == null) {
	    return;
	}
	//deprecated
    }
    
    public String toString() {
	return "ObjectConfigurer name = [" + name + "]";
    }

    public Locale getLocale() {
	if (locale == null) {
	    locale = Locale.getDefault();
	}
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }


    ///////////////////////////////////////////////////////////
    

    public boolean initFiles(String rootConfigDir) {
	return initFiles(rootConfigDir, false);
    }
    
    public boolean initFiles(String rootConfigDir, boolean checkFile) {
	absoluteRootConfigDir = rootConfigDir;
	setAbsoluteConfigFileName(createConfigFileName(rootConfigDir, getConfigFileName()));
	setAbsolutePropertiesBaseName(createConfigFileName(rootConfigDir, getPropertiesBaseName()));
	
	String baseName = getAbsolutePropertiesBaseName();
	if (baseName == null ) {
	    setAbsolutePropertiesFileName(null);
	} else {
	    String fileName = findPropertiesFileName(getAbsolutePropertiesBaseName());
	    if (fileName == null) { // FINE NOT FOUND
		fileName = createPropertiesFileName(baseName); // CREATE NEW FILE
	    }
	    setAbsolutePropertiesFileName(fileName);
	}
	//setAbsolutePropertiesFileName(findPropertiesFileName(getAbsolutePropertiesBaseName()));
	
	
	resolvePropertiesFile = existsFile(getAbsolutePropertiesFileName());

	if (checkFile) {
	    resolveFile = existsFile(getAbsoluteConfigFileName());
	    if (!resolveFile) {
		return false;
	    }
	}
	return true;
    }

    protected String createConfigFileName(String rootConfigDir, String name) {
	return AppConfigurationUtils.getRootConfigFileName(rootConfigDir, name);
    }

    /**
     * Find properties file name
     * If it not found then return null
     * @param configName
     * @return
     */
    protected String findPropertiesFileName(String configName) {
	return findPropertiesFileName(configName, getLocale());
    }

    /**
     * Find properties file name by locale
     * If it not found then return null
     * @param configName
     * @param locale
     * @return
     */
    protected String findPropertiesFileName(String configName, Locale locale) {
	return AppConfigurationUtils.findPropertiesFileName(configName, locale);
    }
    
    
    /**
     * Create properties file name by locale
     * @param name
     * @param locale
     * @return
     */
    protected String createPropertiesFileName(String name, Locale locale) {
	
	// By default we create file name with only language prefix
	// For example: 
	// en_US: action_en.properties
	// ru_RU: action_ru.properties
	
	if (isNewPropertiesFileWithoutCountry()) {
	    String language = locale.getLanguage();
	    if ("en".equals(language)) {
		language = null;
	    }
	    return AppConfigurationUtils.createPropertiesFileName(name, language, null);
	}
	return AppConfigurationUtils.createPropertiesFileName(name, locale);
    }
    
    /**
     * Create properties file name
     * @param name
     * @return
     */
    protected String createPropertiesFileName(String name) {
	return createPropertiesFileName(name, getLocale());
    }

    public boolean isResolve() {
	return isResolveFile();
    }
    
    public boolean isResolveFile() {
        return resolveFile;
    }
    
    public boolean isResolvePropertiesFile() {
        return resolvePropertiesFile;
    }
    

    public String getAbsoluteRootConfigDir() {
        return absoluteRootConfigDir;
    }

    
    //
    
    public ObjectConfigurer<T> getParentConfigurer() {
        return parentConfigurer;
    }

    public void setParentConfigurer(ObjectConfigurer<T> parentConfigurer) {
        this.parentConfigurer = parentConfigurer;
    }

    public IPackageConfig getPackageConfig() {
        return packageConfig;
    }

    public void setPackageConfig(IPackageConfig packageConfig) {
        this.packageConfig = packageConfig;
    }
    

    public ObjectConfigurer<T> getRootConfigurer() {
	if (parentConfigurer == null) {
	    return this;
	}
        return parentConfigurer.getParentConfigurer();
    }

    public boolean isModify() {
        return modify;
    }

    public void setModify(boolean modify) {
	if (isProcessing()){
	    return;
	}
        this.modify = modify;
    }

    public void setModify() {
	setModify(true);
    }

    protected boolean isProcessing() {
        return processing;
    }

    protected void setProcessing(boolean processing) {
        this.processing = processing;
    }


    public boolean isEmpty() {
	return objects == null || objects.isEmpty();
    }

    protected String generateId() {
	return generateId(null);
    }
    
    protected String generateId(String type) {
	String id = AppConfigurationUtils.generateID();
	if (!isEmpty(type)) {
	    return type + "_" + id;
	}
	return id;
    }
    

    protected boolean existsFile(String fileName) {
	if (isEmpty(fileName)) {
	    return false;
	}
	File f = new File(fileName);
	return f.exists();
    }

    protected boolean existsConfigFile() {
	return existsFile(getAbsoluteConfigFileName());
    }

    
    protected String createUIConfigFileName(String name) {
	if (name == null ) {
	    return null;
	}
	//TODO !!!
	String uiConf = configuration.getUiContextName(); //"ui/swt"; // AppEnvironment.getUIConf();
	if (isEmpty(uiConf)) {
	    return name; 
	}
	return uiConf + "/" + name;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Bundle
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////    
    
    protected InputStream getConfigInputStream() throws IOException {
	return AppConfigurationUtils.getConfigInputStream(getAbsoluteConfigFileName());
    }


    protected IPropertiesStore getConfigPropertiesStore() throws IOException {
	return AppConfigurationUtils.getConfigPropertiesStoreByFile(getAbsolutePropertiesFileName());
    }

    protected String getNLSString(IPropertiesStore store, String key) {
	return ConfigurerHelper.getNLSString(store, store.getLocale(), key);
    }

    
    protected String getNLSStringNullIfEmpty(IPropertiesStore store, String key) {
	return nullIfEmpty(getNLSString(store, key));
    }

    protected String getNLSStringNullIfEmpty(IPropertiesStore store, String objectName, String propertyName) {
	return getNLSStringNullIfEmpty(store, createPropertyKey(objectName, propertyName));
    }

    protected String createPropertyKey(String objectName, String propertyName) {
	return ConfigurerHelper.createPropertyKey(objectName, propertyName);
    }
    
    ////
    
    protected void loadNLSCaption(IPropertiesStore store, IObjectConfig objectConfig, String propertyName) {
	String nlsName = getNLSStringNullIfEmpty(store, objectConfig.getName(), propertyName);
	if (nlsName == null) {
	    return;
	}
	objectConfig.setCaption(nlsName);
    }
    
    protected void loadNLSDescription(IPropertiesStore store, IObjectConfig objectConfig, String propertyName) {
	String nlsDescription = getNLSStringNullIfEmpty(store, objectConfig.getName(), propertyName);
	if (nlsDescription == null) {
	    return;
	}
	objectConfig.setDescription(nlsDescription);
    }
    
    //// STORE
    
    protected void storeNLSName(IPropertiesStore store, IObjectConfig objectConfig, String propertyName) {
	setNLSString(store, objectConfig, propertyName, objectConfig.getName());
    }

    protected void storeNLSDescription(IPropertiesStore store, IObjectConfig objectConfig, String propertyName) {
	setNLSString(store, objectConfig, propertyName, objectConfig.getDescription());
    }

    protected void storeNLS(IPropertiesStore store, IObjectConfig objectConfig, String propertyName, String propertyValue) {
	setNLSString(store, objectConfig, propertyName, propertyValue);
    }
    
    protected void setNLSString(IPropertiesStore store, IElement objectConfig, String propertyName, String propertyValue) {
	
	if (isEmpty(propertyValue) && store.isKeysEmpty()) {
	    return;
	}
	ConfigurerHelper.setNLSString(store, store.getLocale(), objectConfig, propertyName, propertyValue);
    }
    
    public boolean isNewPropertiesFileWithoutCountry() {
        return newPropertiesFileWithoutCountry;
    }

    public void setNewPropertiesFileWithoutCountry(boolean newPropertiesFileWithoutCountry) {
        this.newPropertiesFileWithoutCountry = newPropertiesFileWithoutCountry;
    }

    protected Logger getLogger() {
	if (logger == null) {
	    logger =  Logger.getLogger(getClass().getName());
	}
        return logger;
    }

}
