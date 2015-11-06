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

package org.plazmaforge.framework.config.object;

/**
 * 
 * @author ohapon
 *
 */
public class ModuleConfig extends ObjectConfig implements IModuleConfig {

    private static final long serialVersionUID = 3931605613835002933L;

    private boolean defaultModule;
    
    private String moduleDir;
    
    private String rootConfigDir;
    
    
    public boolean isDefaultModule() {
        return defaultModule;
    }

    public void setDefaultModule(boolean defaultModule) {
        this.defaultModule = defaultModule;
    }

    public String getModuleDir() {
        return moduleDir;
    }

    public void setModuleDir(String moduleDir) {
        this.moduleDir = moduleDir;
    }

    public String getModulePath() {
	return ROOT_PATH + PATH_SEPARATOR + moduleDir;
    }

    public String getRootConfigDir() {
        return rootConfigDir;
    }

    public void setRootConfigDir(String rootConfigDir) {
        this.rootConfigDir = rootConfigDir;
    }
    
}
