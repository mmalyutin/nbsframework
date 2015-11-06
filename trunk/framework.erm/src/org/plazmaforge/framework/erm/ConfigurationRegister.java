package org.plazmaforge.framework.erm;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.plazmaforge.framework.erm.mapping.Entity;


public class ConfigurationRegister {

    private static ConfigurationRegister instance;

    
    private Map<String, Configuration> configurationMap;
    
    // Main Configuration
    private Configuration configuration;
    

    private EntityAccessor entityAccessor;
    
    private EntityCreator entityCreator;
    

    ////
    
    public static void registerConfiguration(String name, Configuration configuration) {
	getInstance().doRegisterConfiguration(name, configuration);
    }

    public static Configuration getConfiguration(String name) {
	return getInstance().doGetConfiguration(name);
    }

    public static Configuration getConfiguration() {
	return getInstance().doGetConfiguration();
    }
    
    public static boolean hasConfigurations() {
	return getInstance().doHasConfigurations();
    }

    public static String generateConfigurationName() {
	return getInstance().doGenerateConfigurationName();
    }
    
    ////
    
    private void doRegisterConfiguration(String name, Configuration configuration) {
	checkConfigurationName(name);
	checkConfiguration(configuration);
   	doGetConfigurationMap().put(name, configuration);
    }
    
    private Configuration doGetConfiguration(String name) {
   	return doGetConfigurationMap().get(name);
    }
    
    private Configuration doGetConfiguration() {
	if (configuration != null) {
	    return configuration;
	}
	
	if (!hasConfigurations()){
	    return null;
	}
   	Collection<Configuration>  values = doGetConfigurationMap().values();
   	if (values == null || values.isEmpty()) {
   	    return null;
   	}
   	configuration = values.iterator().next(); 
   	return configuration;
    }


    private boolean doHasConfigurations() {
	if (configurationMap == null) {
	    return false;
	}
   	return !configurationMap.isEmpty();
    }

    private Map<String, Configuration> doGetConfigurationMap() {
   	if (configurationMap == null) {
   	    configurationMap = new LinkedHashMap<String, Configuration>();
   	}
   	return configurationMap;
    }

    
    private String doGenerateConfigurationName() {
	if (!doHasConfigurations()) {
	    return "Main";
	}
	Map<String, Configuration> map = doGetConfigurationMap(); 
	int CONFIGURATION_NUMBER_LIMIT = 100;
	int size = map.size();
	if (size >= CONFIGURATION_NUMBER_LIMIT) {
	    throw new RuntimeException("Configuration number limit");
	}
	int index = size;
	while (index < CONFIGURATION_NUMBER_LIMIT) {
	    String name = "Configuration" + index;
	    if (!map.containsKey(name)) {
		return name;
	    }
	    index++;
	}
   	throw new RuntimeException("Configuration number limit");
    }

    private void checkConfigurationName(String name) {
	if (name == null) {
	    throw new RuntimeException("Configuration name must be not null");
	}
	if (configurationMap != null && configurationMap.containsKey(name)) {
	    throw new RuntimeException("Configuration name already register");
	}
    }

    private void checkConfiguration(Configuration configuration) {
	if (configuration == null) {
	    throw new RuntimeException("Configuration must be not null");
	}
	if (configurationMap != null && configurationMap.containsValue(configuration)) {
	    throw new RuntimeException("Configuration already register");
	}
    }
    
    ////
    
    
    private EntityAccessor doGetEntityAccessor() {
	if (entityAccessor == null) {
	    entityAccessor = new EntityAccessor();
	}
	return entityAccessor;
    }
    
    private EntityCreator doGetEntityCreator() {
	if (entityCreator == null) {
	    entityCreator = new EntityCreator();
	}
	return entityCreator;
    }

    
    private static ConfigurationRegister getInstance() {
	if (instance == null) {
	    instance = new ConfigurationRegister();
	}
	return instance;
    }

    
    private void doClear() {
	if (configurationMap == null) {
	    return;
	}
	configurationMap.clear();
	configurationMap = null;
    }

    public static void clear() {
	getInstance().doClear();
    }
    
    public static EntityAccessor getEntityAccessor(Entity entity) {
	//TODO
	return getInstance().doGetEntityAccessor();
    }

    public static EntityCreator getEntityCreator(Entity entity) {
	//TODO
	return getInstance().doGetEntityCreator();
    }


}
