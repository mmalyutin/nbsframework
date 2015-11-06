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
package org.plazmaforge.framework.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

import org.plazmaforge.framework.core.logging.Logger;
import org.plazmaforge.framework.util.FileUtils;
import org.plazmaforge.framework.util.StringUtils;
import org.plazmaforge.framework.util.SystemInfo;



/**
 * @author ohapon
 *
 */
public class AppConfiguration implements Serializable {

    private static final long serialVersionUID = 4058778627104842087L;
    
    
    protected static final Logger logger = Logger.getLogger(AppConfiguration.class.getName());
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // APPLICATION PROPERTIES
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    /** Root (start) directory **/
    public static final String PROPERTY_ROOT_DIR          = "plazma.platform.dir";

    /** Configuration directory **/
    // [...]
    public static final String PROPERTY_CONFIGURATION_DIR = "plazma.platform.configuration.dir";
    
    /** Configuration storage (contains more configurations) **/
    // [...]
    public static final String PROPERTY_CONFIGURATION_STORAGE = "plazma.platform.configuration.storage";
    
    /** Configuration module **/
    // [...]
    public static final String PROPERTY_CONFIGURATION_MODULE = "plazma.platform.configuration.module";
    

    
    /** Application context **/
    public static final String PROPERTY_APP_CONTEXT       = "plazma.platform.app.context";
    

    /** System context **/
    // configuration: client, server, standalone
    public static final String PROPERTY_SYSTEM_CONTEXT = "plazma.platform.system.conf";
    
    /** UI context**/
    // configuration: ui/swt, ui/swing
    public static final String PROPERTY_UI_CONTEXT = "plazma.platform.ui.conf";
    
    
    
    /** Is Demo **/
    public static final String PROPERTY_IS_DEMO              = "plazma.platform.demo";

    /** Auto login **/
    public static final String PROPERTY_AUTOLOGIN        = "plazma.platform.autologin";
    
    /** Input Dialog **/
    public static final String PROPERTY_INPUTDIALOG        = "plazma.platform.inputdialog";
    
    
    
    
	
    /** User dir **/
    public static final String USER_DIR                   = System.getProperty("user.dir");

    /** Default 'conf' directory **/
    public static final String DEFAULT_CONF        = "conf";
    
    /** Default logs directory **/
    public static final String DEFAULT_LOGS_DIR      = "logs";    

    /** Default packages directory **/
    public static final String DEFAULT_PACKAGES_DIR  = "packages";


    /** Default application context name **/
    public static final String DEFAULT_APP_CONTEXT = "plazma";
    
    /** Default root property file name **/
    public static final String DEFAULT_ROOT_PROPERTY_FILE = DEFAULT_APP_CONTEXT + ".properties";

    /** Default application config file **/
    public static final String DEFAULT_APPLICATION_CONFIG_FILE = "application.xml";
    
    /** Default system configuration file **/
    public static final String DEFAULT_SYSTEM_CONFIG_FILE = "system-config.xml";
    
    
	

    public static final String STD_OUT = "std.out";
    
    public static final String STD_ERR = "std.err";
    
    public static final String PROPERTIES_FILE_EXT = ".properties";
    
    
    
    
    private final Properties properties;
    
    private Properties systemProperties;
    
    private Properties platformProperties;
    

    /**
     * Root directory (start directory). By default it is user directory (System.getProperty("user.dir"))
     */
    private String rootDir;
	
    /**
     * Configuration directory
     */
    private String configurationDir;
    
    /**
     * Array of configuration directories. 
     * We can use more configurations. configurationModuleDirs[0] is default configuration. 
     */
    private String[] configurationModuleDirs;
    
    
    

    private String appContextName; // 'plazma' by default
    
    private String systemContextName; // 'standalone', 'client', 'server'
    
    private String uiContextName; // 'ui/swt', 'ui/swing'
    
    
    
    private String logsDirName;    

    private boolean externalConfigFile;
	
    private boolean externalDatabaseConfigFile;

    private String applicationManagerClass;
    
    /** If <b>true<b> the application has demo mode **/
    private boolean demo;
    
    /** If <b>true<b> the login dialog sets login and password automaticaly **/
    private boolean autoLogin;    
    
    /** If <b>true<b> the application has special input dialog to set parameters (UI, Organization, Branch and etc.) **/
    private boolean inputDialog;    
	
    
    private String applicationConfigFile;
    
    private String systemConfigFile;
    
    private String userInterface;
    


    
    
    private boolean isInit;
    
    
    public static class Replacer {
	
	public String from;

	public String to;
    }    
    
    public AppConfiguration() {
	properties = new Properties();
    }

    public Properties getProperties() {
        return properties;
    }

    public void clearProperties() {
	properties.clear();
    }

    public String getProperty(Properties properties, String key, String def) {
	if (properties == null) {
	    return def;
	}
	return properties.getProperty(key, def);
    }
    
    public String getConfigurationProperty(String key) {
	return getConfigurationProperty(key, null);
    }
    
    public String getConfigurationProperty(String key, String def) {
	
	// Property value 
	String value = null;
	
	value = getProperty(getProperties(), key, null);
	if (value != null) {
	    return value;
	}
	value = getProperty(getSystemProperties(), key, null);
	if (value != null) {
	    return value;
	}
	value = getProperty(getPlatformProperties(), key, null);
	if (value != null) {
	    return value;
	}
	value = System.getProperty(key);
	if (value != null) {
	    return value;
	}
	return value == null ? def: value;
    }

    public String getProperty(String key, String def) {
	if (properties == null) {
	    return def;
	}
	return properties.getProperty(key, def);
    }

    
    public Properties getSystemProperties() {
	if (systemProperties == null) {
	    systemProperties = new Properties();
	}
        return systemProperties;
    }

    public void setSystemProperties(Properties systemProperties) {
        this.systemProperties = systemProperties;
    }

    public Properties getPlatformProperties() {
	if (platformProperties == null) {
	    platformProperties = new Properties();
	}
        return platformProperties;
    }

    public void setPlatformProperties(Properties platformProperties) {
        this.platformProperties = platformProperties;
    }

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDir) {
        this.rootDir = rootDir;
    }

    public String getConfigurationDir() {
        return configurationDir;
    }

    public void setConfigurationDir(String configurationDir) {
        this.configurationDir = configurationDir;
    }

    public String[] getConfigurationModuleDirs() {
        return configurationModuleDirs;
    }

    public void setConfigurationModuleDirs(String[] configurationModuleDirs) {
        this.configurationModuleDirs = configurationModuleDirs;
    }
    

    public String getAppContextName() {
        return appContextName;
    }

    public void setAppContextName(String appContextName) {
        this.appContextName = appContextName;
    }

    public String getSystemContextName() {
        return systemContextName;
    }

    public void setSystemContextName(String systemContextName) {
        this.systemContextName = systemContextName;
    }

    public String getUiContextName() {
        return uiContextName;
    }

    public void setUiContextName(String uiContextName) {
        this.uiContextName = uiContextName;
    }

    
    
    
    public String getLogsDirName() {
        return logsDirName;
    }

    public void setLogsDirName(String logsDirName) {
        this.logsDirName = logsDirName;
    }

    public boolean isExternalConfigFile() {
        return externalConfigFile;
    }

    public void setExternalConfigFile(boolean externalConfigFile) {
        this.externalConfigFile = externalConfigFile;
    }

    public boolean isExternalDatabaseConfigFile() {
        return externalDatabaseConfigFile;
    }

    public void setExternalDatabaseConfigFile(boolean externalDatabaseConfigFile) {
        this.externalDatabaseConfigFile = externalDatabaseConfigFile;
    }

    public String getApplicationManagerClass() {
        return applicationManagerClass;
    }

    public void setApplicationManagerClass(String applicationManagerClass) {
        this.applicationManagerClass = applicationManagerClass;
    }

    public boolean isDemo() {
        return demo;
    }

    public void setDemo(boolean demo) {
        this.demo = demo;
    }

    public boolean isAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(boolean autoLogin) {
        this.autoLogin = autoLogin;
    }

    public boolean isInputDialog() {
        return inputDialog;
    }

    public void setInputDialog(boolean inputDialog) {
        this.inputDialog = inputDialog;
    }

    public String getApplicationConfigFile() {
        return applicationConfigFile;
    }

    public void setApplicationConfigFile(String applicationConfigFile) {
        this.applicationConfigFile = applicationConfigFile;
    }

    public String getSystemConfigFile() {
        return systemConfigFile;
    }

    public void setSystemConfigFile(String systemConfigFile) {
        this.systemConfigFile = systemConfigFile;
    }

    public String getUserInterface() {
        return userInterface;
    }

    public void setUserInterface(String userInterface) {
        this.userInterface = userInterface;
    }

    ////
    
    public String getRootConfigFileName(String rootConfigDir, String name) {
        return FileUtils.getPath(rootConfigDir, name);
    }

    public String getRootConfigFileName(String name) {
        return FileUtils.getPath(getRootConfigDir(), name);
    }
    
    public String getRootConfigDir() {
        return FileUtils.getPath(getConfigurationDir(), DEFAULT_CONF);
    }

    public String getRootConfigDir(String configurationDir) {
        return FileUtils.getPath(configurationDir, DEFAULT_CONF);
    }
    
    ////
    
    public static Replacer[] createReplacers(String rootDir) {

	Replacer[] replacer = new Replacer[12];

	String userDir = USER_DIR;
	String parentUserDir = FileUtils.getFolderName(userDir);
	String parentRootDir = FileUtils.getFolderName(rootDir);

	replacer[0] = new Replacer();
	replacer[0].from = "${user.dir}";
	replacer[0].to = userDir;

	replacer[1] = new Replacer();
	replacer[1].from = "${root.dir}";
	replacer[1].to = rootDir;

	replacer[2] = new Replacer();
	replacer[2].from = "${plazma.platform.dir}";
	replacer[2].to = rootDir;

	// Parent dir

	replacer[3] = new Replacer();
	replacer[3].from = "${parent.user.dir}";
	replacer[3].to = parentUserDir;

	replacer[4] = new Replacer();
	replacer[4].from = "${parent.root.dir}";
	replacer[4].to = parentRootDir;

	replacer[5] = new Replacer();
	replacer[5].from = "${parent.plazma.platform.dir}";
	replacer[5].to = parentRootDir;

	// .. - ONLY FOR ROOT DIR (NOT USER DIR)
	replacer[6] = new Replacer();
	replacer[6].from = "../";
	replacer[6].to = FileUtils.getPath(parentRootDir, "");

	replacer[7] = new Replacer();
	replacer[7].from = "..\\";
	replacer[7].to = FileUtils.getPath(parentRootDir, "");

	// replacer[8] = new Replacer();
	// replacer[8].from = "${version}";
	// replacer[8].to = IFrameworkProductInfo.VERSION;

	// replacer[9] = new Replacer();
	// replacer[9].from = "{version}";
	// replacer[9].to = IFrameworkProductInfo.VERSION;

	replacer[10] = new Replacer();
	replacer[10].from = "./";
	replacer[10].to = FileUtils.getPath(rootDir, "");

	replacer[11] = new Replacer();
	replacer[11].from = ".\\";
	replacer[11].to = FileUtils.getPath(rootDir, "");
	return replacer;
    }

    public Replacer[] createReplacers() {
	return createReplacers(getRootDir());
    }
    
    public static String replaceValue(Replacer[] replacer, String value) {
	if (replacer == null || value == null) {
	    return null;
	}
	String val = value;
	for (Replacer element: replacer) {
	    if (element == null) {
		continue;
	    }
	    val = StringUtils.replaceAll(val, element.from, element.to);
	}
	return val;
    }
    
    ////
    
    public void init(Properties initProperties) {
	init(initProperties, false);
    }
    
    public void init(Properties initProperties, boolean forceInit) {

	if (!forceInit && isInit) {
	    return;
	}
	isInit = true;
	logInfo("Initializing Application...");

	clearProperties();

	logsDirName = DEFAULT_LOGS_DIR;
	// checkLogsFolder();

	rootDir = getProperty(initProperties, PROPERTY_ROOT_DIR, USER_DIR);

	appContextName = getProperty(initProperties, PROPERTY_APP_CONTEXT, DEFAULT_APP_CONTEXT);
	if (StringUtils.isEmpty(appContextName)) {
	    appContextName = DEFAULT_APP_CONTEXT;
	}

	String propertiesFileName = appContextName + ".properties";
	propertiesFileName = FileUtils.getPath(rootDir, propertiesFileName);
	initSystemProperties(propertiesFileName, this.properties);

	// We can use more configurations
	// Each configuration is like one project (module)
	// We can set configuration directory like string with separator ';'

	String configurationModuleDirString = getProperty(initProperties, PROPERTY_CONFIGURATION_DIR, rootDir);
	String[] modules = configurationModuleDirString.split(";");

	for (int i = 0; i < modules.length; i++) {
	    modules[i] = modules[i].trim();
	}
	configurationModuleDirs = modules;

	// First directory is default (base) configuration
	configurationDir = configurationModuleDirs[0];

	systemContextName = getProperty(initProperties, PROPERTY_SYSTEM_CONTEXT, ""); // 'standalone', 'client', 'server'
	uiContextName = getProperty(initProperties, PROPERTY_UI_CONTEXT, ""); // 'ui/swt', 'ui/swing'

	applicationConfigFile = getProperty(initProperties, "plazma.platform.application.config.file", DEFAULT_APPLICATION_CONFIG_FILE);
	systemConfigFile = getProperty(initProperties, "plazma.platform.system.config.file", DEFAULT_SYSTEM_CONFIG_FILE);

	if (!StringUtils.isEmpty(systemContextName)) {
	    applicationConfigFile = FileUtils.getPath(systemContextName, applicationConfigFile);
	    systemConfigFile = FileUtils.getPath(systemContextName, systemConfigFile);
	}

	demo = Boolean.valueOf(getProperty(initProperties, PROPERTY_IS_DEMO, "false")).booleanValue();
	autoLogin = Boolean.valueOf(getProperty(initProperties, PROPERTY_AUTOLOGIN, "false")).booleanValue();
	inputDialog = Boolean.valueOf(getProperty(initProperties, PROPERTY_INPUTDIALOG, "true")).booleanValue();

	externalConfigFile = true; // By default configuration files are external
	externalDatabaseConfigFile = false;

	// initApplicationType();

	logInfo("CONFIGURATION_DIR = '" + configurationDir + "'");
	logInfo("APPLICATION_CONFIG_FILE = '" + applicationConfigFile + "'");
	logInfo("SYSTEM_CONFIG_FILE = '" + systemConfigFile + "'");
	logInfo("********** Application was initialized ***********");
    }
    
    
    /**
     * Load properties from file.
     */
    protected void initSystemProperties(String fileName, Properties properties) {
	try {
	    
	    
	    // If file not found return
	    if (!FileUtils.exists(fileName)) {
		return;
	    }
	    if (SystemInfo.isWindows) {
		 fixingPropertiesFile(fileName);
	    }
	    Properties props = new Properties();
	    props.load(new FileInputStream(fileName));
	    Enumeration<Object> enums = props.keys();
	    Replacer[] replacers = AppConfiguration.createReplacers(rootDir);
	    
	    while (enums.hasMoreElements()) {
		String key = (String) enums.nextElement();
		String value = props.getProperty(key);
		String replValue = AppConfiguration.replaceValue(replacers, value);
		logInfo("KEY = " + key +", VALUE = " + replValue);
		properties.put(key, replValue);
	    }

	} catch (IOException ex) {
	    logError("Error initialize system properties", ex);
	}

    }
    
    protected void fixingPropertiesFile(String fileName) {
	BufferedReader reader = null;
	BufferedWriter writer = null;
	try {
	    reader = new BufferedReader(new FileReader(fileName));
	    String str = null;

	    ArrayList<String> buff = new ArrayList<String>();
	    boolean isReplace = false;
	    while ((str = reader.readLine()) != null) {
		if (str.startsWith(PROPERTY_ROOT_DIR)) {
		    str = str.replace("\\", "/");
		    isReplace = true;
		}
		buff.add(str);
	    }

	    reader.close();
	    reader = null;

	    if (buff.size() == 0 || !isReplace) {
		return;
	    }

	    writer = new BufferedWriter(new FileWriter(fileName));

	    for (String line : buff) {
		writer.write(line + '\n');
	    }

	    writer.close();
	    writer = null;
	    logInfo("Properties file '" + fileName + "' was fixed");
	} catch (Exception ex) {
	    ex.printStackTrace();
	} finally {
	    try {
		if (reader != null) {
		    reader.close();
		}
		if (reader != null) {
		    reader.close();
		}

	    } catch (IOException ex) {
		//
	    }

	}

    }

    protected void log(String message) {
	logger.log(message);
    }

    protected void logInfo(String message) {
	logger.info(message);
    }
    
    protected void logError(String message) {
	logger.error(message);
    }
    
    protected void logError(String message, Throwable error) {
	logger.error(message, error);
    }
    
    

}
