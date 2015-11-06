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
public class PackageConfig extends ObjectConfig implements IPackageConfig {

    private static final long serialVersionUID = 5204655819561213596L;

    private String compressionClassName;

    // without 'packages' 
    private String absoluteRootConfigFolder;
    
    // with 'packages'
    private String absoluteConfigFolder;
    
    
    /** Simple package folder in lower characters (like java package name) **/
    private String packageFolder;
    
    private boolean partConf;
    
    
    private IModuleConfig moduleConfig;
    
    
    /**
     * Virtual mode
     */
    private boolean virtual;
    
    
    public String getCompressionClassName() {
	return compressionClassName;
    }

    public void setCompressionClassName(String compressionClassName) {
	this.compressionClassName = compressionClassName;
    }

    public String getPackageFolder() {
	if (packageFolder == null) {
	    String name = getName();
	    if (name != null) {
		packageFolder = name.toLowerCase();
	    }
	}
        return packageFolder;
    }
    
    public String getPackagePath() {
	return (moduleConfig == null 
		? (ROOT_PATH + PATH_SEPARATOR + "$null" + PATH_SEPARATOR + getId()) 
		: (moduleConfig.getModulePath() + PATH_SEPARATOR + getId())); 
    }

    public void setPackageFolder(String packageFolder) {
        this.packageFolder = packageFolder;
    }

    
    public String getAbsoluteRootConfigFolder() {
        return absoluteRootConfigFolder;
    }

    public void setAbsoluteRootConfigFolder(String absoluteRootConfigFolder) {
        this.absoluteRootConfigFolder = absoluteRootConfigFolder;
    }
    
    public String getAbsoluteConfigFolder() {
        return absoluteConfigFolder;
    }

    public void setAbsoluteConfigFolder(String absoluteConfigDir) {
        this.absoluteConfigFolder = absoluteConfigDir;
    }

    
    public boolean isPartConf() {
        return partConf;
    }

    public void setPartConf(boolean partConf) {
        this.partConf = partConf;
    }

    public IModuleConfig getModuleConfig() {
        return moduleConfig;
    }

    public void setModuleConfig(IModuleConfig moduleConfig) {
        this.moduleConfig = moduleConfig;
    }

    public boolean isVirtual() {
        return virtual;
    }

    public void setVirtual(boolean virtual) {
        this.virtual = virtual;
    }

    
}
