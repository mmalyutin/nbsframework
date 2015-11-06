package org.plazmaforge.framework.erm;

import java.util.HashMap;
import java.util.Map;

public class LoadLevel {
    
    public static final String LINK_STRING = "link";
    
    public static final String HEADER_STRING = "header";
    
    public static final String VIEW_STRING = "view";
    
    public static final String DETAIL_STRING = "detail";

    //
    // Standard levels
    //
    public static final LoadLevel LINK = new LoadLevel(100, LINK_STRING);
    
    public static final LoadLevel HEADER = new LoadLevel(200, HEADER_STRING);
    
    public static final LoadLevel VIEW = new LoadLevel(300, HEADER_STRING);
    
    public static final LoadLevel DETAIL = new LoadLevel(999, DETAIL_STRING);

    
    private static Map<String, LoadLevel> registerByName = new HashMap<String, LoadLevel>();
    
    private static Map<Integer, LoadLevel> registerByLevel = new HashMap<Integer, LoadLevel>();
    
    
    private int level;
    
    private String name;
     
    
    public LoadLevel(int level, String name) {
	super();
	this.level = level;
	this.name = name;
    }

    public int getLevel() {
        return level;
    }


    public String getName() {
        return name;
    }

    public boolean accept(LoadLevel loadLevel) {
	if (loadLevel == null) {
	    return true;
	}
	return loadLevel.getLevel() <= this.getLevel();
    }

    ////
    
    public static void addLevel(LoadLevel loadLevel) {
	if (loadLevel == null) {
	    throw new IllegalArgumentException("LoadLevel must be not null");
	}
	String name = loadLevel.getName();
	if (name == null) {
	    throw new IllegalArgumentException("Name of LoadLevel must be not null");
	}
	int level = loadLevel.getLevel();
	if (registerByName.get(name) != null) {
	    throw new RuntimeException("Duplicate LoadLevel by name: name=" + name + ", level=" + level);
	}
	if (registerByLevel.get(level) != null) {
	    throw new RuntimeException("Duplicate LoadLevel by level: name=" + name + ", level=" + level);
	}
	registerByName.put(name, loadLevel);
	registerByLevel.put(level, loadLevel);
    }
    
    
    public static LoadLevel getLevel(String name) {
	return registerByName.get(name);
    }
    
    public static LoadLevel getLevel(int level) {
	return registerByLevel.get(level);
    }
    
}
