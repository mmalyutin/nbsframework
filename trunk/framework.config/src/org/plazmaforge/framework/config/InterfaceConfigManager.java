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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.plazmaforge.framework.config.configurer.InterfaceConfigurer;
import org.plazmaforge.framework.config.configurer.MenuBarConfigurer;
import org.plazmaforge.framework.config.object.IInterfaceConfig;
import org.plazmaforge.framework.config.object.IMenuBarConfig;
import org.plazmaforge.framework.config.object.InterfaceConfig;

/** 
 * User Interface Config Manager 
 * 
 * @author ohapon
 */

public class InterfaceConfigManager {

    public static String getInterfaceName(String id) {
	List<IInterfaceConfig> uis = getInterfaces();
	return getInterfaceName(uis, id);
    }

    
    public static String getInterfaceName(List<IInterfaceConfig> uis, String id) {
	IInterfaceConfig ui = getInterface(uis, id);
	return ui == null ? null : ui.getName();
    }
    
    public static IInterfaceConfig getInterface(String id) {
	List<IInterfaceConfig> uis = getInterfaces();
	return getInterface(uis, id);
    }
    
    public static IInterfaceConfig getInterface(List<IInterfaceConfig> uis, String id) {
	if (id == null || id.trim().length() == 0) {
	    return null;
	}
	if (uis == null || uis.isEmpty()) {
	    return null;
	}
	
	if (uis == null || uis.isEmpty()) {
	    return null;
	}
	
	for (IInterfaceConfig ui : uis) {
	    if (!ui.isEnabled()) {
		continue;
	    }
	    if (id.equals(ui.getId())) {
		return ui;
	    }
	}
	return null;
    }
    
    /**
     * Return list of all UI from config
     * @return
     */
    public static List<IInterfaceConfig> getInterfaces() {
	
	
	// Get list of all UI
	List<IInterfaceConfig> uis = getInterfacesFromConfig();
	if (uis != null) {
	    return uis;
	}
	
	// If UIs not found generate list of UI by menu bars
	uis = generateInterfaces();
	if (uis != null) {
	    return uis;
	}
	
	// If menu bars not found return empty list 
	return new ArrayList<IInterfaceConfig>();
	
    }
    
    /**
     * Return list of all UI from config
     * @return
     */
    private static List<IInterfaceConfig> getInterfacesFromConfig() {
	InterfaceConfigurer conf = (InterfaceConfigurer) ConfigurerManager.getConfigurer(InterfaceConfigurer.NAME);
	if (conf == null) {
	    return null;
	}
	List<IInterfaceConfig> uiList = conf.getObjects();
	if (uiList == null || uiList.size() == 0) {
	    return null;
	}
	List<IInterfaceConfig> uis = new ArrayList<IInterfaceConfig>();
	for (IInterfaceConfig ui : uiList) {
	    if (!ui.isEnabled()) {
		continue;
	    }
	    uis.add(ui);
	}
	return uis;
    }
    
    /**
     * Auto generate list of UI by menu bars
     * @return
     */
    private static List<IInterfaceConfig> generateInterfaces() {
	MenuBarConfigurer conf = (MenuBarConfigurer) ConfigurerManager.getConfigurer(MenuBarConfigurer.NAME);
	if (conf == null) {
	    return null;
	}
	List<IMenuBarConfig> menuBars = conf.getObjects();
	if (menuBars == null || menuBars.size() == 0) {
	    return null;
	}
	List<IInterfaceConfig> uis = new ArrayList<IInterfaceConfig>();
	Set<String> set = new HashSet<String>();
	for (IMenuBarConfig bar : menuBars) {
	    set.add(bar.getCategory());
	}
	
	
	for (String ui : set) {
	    InterfaceConfig impl = new InterfaceConfig();
	    impl.setId(ui);
	}
	return uis;
    }

}
