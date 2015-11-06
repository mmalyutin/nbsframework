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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.jdom.Document;
import org.jdom.Element;
import org.plazmaforge.framework.config.AppConfiguration;
import org.plazmaforge.framework.config.configurer.ObjectConfigurer;
import org.plazmaforge.framework.config.configurer.PackageConfigurer;
import org.plazmaforge.framework.config.object.IPackageConfig;
import org.plazmaforge.framework.config.object.PackageConfig;
import org.plazmaforge.framework.util.FileUtils;

/**
 * 
 * @author ohapon
 *
 */
public class XMLPackageConfigurer extends XMLObjectConfigurer<IPackageConfig> implements PackageConfigurer {


    public static String NAME = "XMLPackageConfigurer";
    
    public static final String XML_ROOT = "package-config";    

    public static final String DEFAULT_CONFIG_FILE = XML_ROOT + ".xml";
    
    public static final String DEFAULT_CONFIG_PROPERTIES = XML_ROOT;

    public static final String XML_PACKAGE = "package";

    public static final String XML_COMPRESSION_PERIOD_EXECUTOR = "compression-period-executor";
    
    
    
    private Map<String, ObjectConfigurer<?>> configurers = new HashMap<String, ObjectConfigurer<?>>();
    
    private boolean partConf;
    
    
    public XMLPackageConfigurer(AppConfiguration configuration) {
	super(configuration);
	setConfigFileName(DEFAULT_CONFIG_FILE);
	setPropertiesBaseName(DEFAULT_CONFIG_PROPERTIES);
    }
    
    
    
    @Override
    protected void doLoadObjectsProcess() throws Exception {
	doLoadObjects(); // Without check file
    }

    @Override
    protected void doStoreObjectsProcess() throws Exception {
	doStoreObjects(); // Without check file
    }

    /**
     * Load packages from file
     */
    protected void doLoadObjects() throws Exception {
	loadObjectsFromXML();
	loadFSFoldersLikePackages();
    }

    protected void doLoadVirtualObjects() throws Exception {
	loadFSFoldersLikePackages();
    }    

    /**
     * Store packages to file
     */
    protected void doStoreObjects() throws Exception {
	storeObjectsToXML();
    }

    protected void loadObjectsFromXML() throws Exception {

	    
	    if (!existsConfigFile()) {
		return;
	    }

	    String absoluteRootConfigFolder = getAbsoluteRootConfigDir();
	    String absoluteConfigFolder = FileUtils.getPath(absoluteRootConfigFolder,  AppConfiguration.DEFAULT_PACKAGES_DIR);
	    
	    
	    Element rootElement = getRootElement(getConfigInputStream());
	    List list = getChildren(rootElement, XML_PACKAGE);
	    if (list == null) {
		return;
	    }
	    Iterator i = list.iterator();
	    IPackageConfig pkg = null;
	    Element packageElement = null;
	    
	    while (i.hasNext()) {
		packageElement = ((Element) i.next());

		String id = getAttributeId(packageElement);
		String name = packageElement.getAttributeValue(XML_NAME);
		String klass = packageElement.getAttributeValue(XML_CLASS);
		String enabled = packageElement.getAttributeValue(XML_IS_ENABLED);


		Element compressionElement = packageElement.getChild(XML_COMPRESSION_PERIOD_EXECUTOR);
		String compressionClass = null;

		if (compressionElement != null) {
		    compressionClass = compressionElement.getText();
		}

		pkg = new PackageConfig();
		pkg.setId(id);
		pkg.setName(name);
		pkg.setClassName(klass);

		if (!isEmpty(enabled)) {
		    pkg.setEnabled(getBoolean(enabled));
		}
		
		pkg.setCompressionClassName(compressionClass);
		
		pkg.setAbsoluteRootConfigFolder(absoluteRootConfigFolder);
		pkg.setAbsoluteConfigFolder(absoluteConfigFolder);
		pkg.setPartConf(isPartConf());

		// NLS
		if (store != null) {
		    loadNLSCaption(store, pkg);
		    loadNLSDescription(store, pkg);
		}
		
		addObject(pkg);
	    }
	    
	    // DISABLE
	    //loadFSFoldersLikePackages();
	    
    }
    
    protected void storeObjectsToXML() throws Exception {
	Element root = createRootElement(XML_ROOT);
	Document doc = new Document(root);
	List<IPackageConfig> packages = getObjects();
	if (packages == null) {
	    storeWithNLS(doc, store);
	    return;
	}
	Element packageElement = null;
	List<Element> children = new ArrayList<Element>();
	for (IPackageConfig pkg : packages) {
	    
	    // We can't store virtual package conf
	    if (pkg.isVirtual()) {
		continue;
	    }
	    
	    packageElement = new Element(XML_PACKAGE);

	    children.add(packageElement);

	    setAttributeId(packageElement, pkg.getId());
	    setAttribute(packageElement, XML_NAME, pkg.getName());
	    setAttribute(packageElement, XML_CLASS, pkg.getClassName());
	    setEnabledAttribute(packageElement, XML_IS_ENABLED, pkg.isEnabled());
	    setAttribute(packageElement, XML_COMPRESSION_PERIOD_EXECUTOR, pkg.getCompressionClassName());

	    
	    // NLS
	    if (store != null) {
		storeNLSCaption(store, pkg);
		storeNLSDescription(store, pkg);
	    }
	    

	}
	root.setContent(children);
	storeWithNLS(doc, store);
    }
    
    
    

    public IPackageConfig getPackageConfigByFolder(String folder) {
	List<IPackageConfig> packages = getObjects();
	if (packages == null || packages.isEmpty()) {
	    return null;
	}
	for (IPackageConfig p : packages) {
	    String pf = p.getPackageFolder();
	    if (pf == null) {
		continue;
	    }
	    if (pf.equals(folder)) {
		return p;
	    }
	}
	return null;
    }
    
    public boolean existsPackageFolder(String folder) {
	return getPackageConfigByFolder(folder) != null;
    }
    
    /**
     * Load packages like folders.
     * Packages have virtual mode. 
     */
    protected void loadFSFoldersLikePackages() {
	if (getAbsoluteRootConfigDir() == null) {
	    return;
	}
	String absoluteRootConfigFolder = getAbsoluteRootConfigDir();
	String absoluteConfigFolder = FileUtils.getPath(absoluteRootConfigFolder, AppConfiguration.DEFAULT_PACKAGES_DIR);
	
	File rootFolder = new File(absoluteConfigFolder);
	if (!rootFolder.exists()) {
	    return;
	}
	File[] files = rootFolder.listFiles();
	if (files == null || files.length == 0) {
	    return;
	}
	
	for (File f : files) {
	    if (!f.isDirectory()) {
		continue;
	    }
	    String name = f.getName();
	    // TODO: Must add special ignore list
	    if ("CVS".equals(name) || ".svn".equals(name)) {
		continue;
	    }
	    if (existsPackageFolder(name)) {
		continue;
	    }
	    IPackageConfig packageConfig = new PackageConfig();
	    packageConfig.setId(generateId());
	    packageConfig.setName(name);
	    packageConfig.setEnabled(true);
	    
	    packageConfig.setAbsoluteRootConfigFolder(absoluteRootConfigFolder);
	    packageConfig.setAbsoluteConfigFolder(absoluteConfigFolder);
	    packageConfig.setPartConf(isPartConf());
	    
	    packageConfig.setVirtual(true);
	    
	    addObject(packageConfig);
	}
    }
    
    
    public void setConfigurer(String name, ObjectConfigurer<?> configurer) {
	configurers.put(name, configurer);
    }

    public ObjectConfigurer<?> getConfigurer(String name) {
	return configurers.get(name);
    }

    public List<ObjectConfigurer<?>> getConfigurers() {
	List<ObjectConfigurer<?>> list = new ArrayList<ObjectConfigurer<?>>(configurers.values());
	return list;
    }

    public boolean isPartConf() {
        return partConf;
    }

    public void setPartConf(boolean partConf) {
        this.partConf = partConf;
    }


    ////
    
   
    
    
}
