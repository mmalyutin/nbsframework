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
public interface IObjectConfig extends IElement, IConfigIdentifier {

    String ROOT_PATH = "$root";
    
    String PATH_SEPARATOR = "::";
    
    String PARAMETERS_DELIM = ",";
    
    
    /**
     * Indicates normal state.
     */
    int NORMAL = 0;

    /**
     * Indicates modified state.
     */
    int MODIFIED = 1;

    /**
     * Indicates deleted state.
     */
    int DELETED = 2;

    /**
     * Indicates that this details was newly created.
     */
    int CREATED = 3;
    
    
    String getCode();

    void setCode(String code);

    String getName();

    void setName(String name);

    String getCaption();

    void setCaption(String name);

    String getDescription();

    void setDescription(String description);

    // Replace class
    String getType();

    void setType(String type);
    
    String getClassName();

    void setClassName(String className);

    
    // Special category (example UI)
    String getCategory();
    
    void setCategory(String category);
    
    boolean isEnabled();
    
    void setEnabled(boolean enabled);
    
    
    IPackageConfig getPackageConfig();
    
    void setPackageConfig(IPackageConfig packageConfig);
    
    
    boolean isModify();

    void setModify(boolean modify);
    
    void setModify();
    
    boolean isNormal();

    boolean isModified();

    boolean isDeleted();

    boolean isCreated();


    void setNormal();

    void setModified();

    void setDeleted();

    void setCreated();

    boolean isDitry();
}
