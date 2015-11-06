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

import org.plazmaforge.framework.config.object.IObjectConfig;
import org.plazmaforge.framework.config.object.IPackageConfig;
import org.plazmaforge.framework.core.exception.ApplicationException;
import org.plazmaforge.framework.util.IPropertiesStore;


/**
 * Object Configurer
 * 
 * - XML Configurer (XML file)
 * - SQL Configurer (Database)
 * 
 * @author ohapon
 * 
 */
public interface ObjectConfigurer<T extends IObjectConfig> {

    String getName();

    void setName(String name);

    
    String getConfigFileName();

    void setConfigFileName(String congiFileName);

    String getPropertiesBaseName();

    void setPropertiesBaseName(String propertiesFile);

    
    

    String getAbsoluteConfigFileName();

    void setAbsoluteConfigFileName(String absoluteCongiFileName);

    String getAbsolutePropertiesFileName();

    void setAbsolutePropertiesFileName(String absolutePropertiesFile);
    
    boolean isResolve();
    
    void addObject(T obj);
    
    void removeObject(T obj);

    void updateObject(T obj);
    
    boolean isEmpty();
    
    void fireModifyObject(T obj);
    
    
    void addObjectLink(T obj);

    void removeObjectLink(T obj);
    
        
    
    List<T> getObjects();
    
    List<T> getObjects(String category);

    T getObjectById(String id);

    T getObjectByName(String code);

    
    void loadObjects() throws ApplicationException;

    void storeObjects() throws ApplicationException;

    
    
    boolean initFiles(String rootConfigDir);
    
    boolean initFiles(String rootConfigDir, boolean checkFile);
    
    
    ////
    
    /**
     * Return parent configurer
     */
    ObjectConfigurer<T> getParentConfigurer();

    /**
     * Set parent configurer
     * @param parentConfigurer
     */
    void setParentConfigurer(ObjectConfigurer<T> parentConfigurer);

    
    IPackageConfig getPackageConfig();

    void setPackageConfig(IPackageConfig packageConfig);

    
    
    boolean isModify();

    void setModify(boolean modify);

    void setModify();

    
    IPropertiesStore getStore();
    
}
