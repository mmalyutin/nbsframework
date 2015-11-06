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

import java.util.List;

import org.plazmaforge.framework.config.object.IMenuBarConfig;
import org.plazmaforge.framework.config.object.IMenuConfig;

/**
 * 
 * @author ohapon
 * 
 */
public interface MenuBarConfigurer extends ObjectConfigurer<IMenuBarConfig> {

    String NAME = "MenuBarConfigurer";

    /**
     * Return all global menus
     * @return
     */
    List<IMenuConfig> getGlobalMenus();
    
    /**
     * Return global menu by type
     * @param type
     * @return
     */
    IMenuConfig getGlobalMenu(String type);
    
    
    /**
     * Return all menus (non global) with type (type is not empty)
     * @return
     */
    List<IMenuConfig> getMenusWithType();
    
    
    /**
     * Return all menus (non global) by type
     * @param type
     * @return
     */
    List<IMenuConfig> getMenusByType(String type);
    
    
    /**
     * Update test of the all menus (non global) after change text of the global menu
     * @param globalMenu
     */
    void updateTextByGlobalMenu(IMenuConfig globalMenu);
    
}
