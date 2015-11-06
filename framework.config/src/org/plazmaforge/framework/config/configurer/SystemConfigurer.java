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

import org.plazmaforge.framework.config.object.IModuleConfig;
import org.plazmaforge.framework.core.exception.ApplicationException;

/**
 * Root System Configurer
 * 
 * @author ohapon
 * 
 */
public interface SystemConfigurer extends ObjectConfigurer {

    ModuleConfigurer getModuleConfigurer();
    
    List<IModuleConfig> getModules();
    
    /**
     * Return list of global cofigurer
     * @return
     */
    List<ObjectConfigurer<?>> getConfigurers();
    
    /**
     * Return global configurer
     * @param name
     * @return
     */
    ObjectConfigurer<?> getConfigurer(String name);
    
    
    List<ObjectConfigurer<?>> getConfigurersByPath(String path);
       
    ObjectConfigurer<?> getConfigurerByPath(String path, String name);
    
    ObjectConfigurer<?> getConfigurer(List<ObjectConfigurer<?>> configurers, String name);
    
    void reset();
    
    void storeDefaultObjects() throws ApplicationException;
}
