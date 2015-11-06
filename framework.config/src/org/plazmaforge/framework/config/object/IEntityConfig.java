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

import java.util.List;

/**
 * 
 * @author ohapon
 *
 */
public interface IEntityConfig extends IObjectConfig {

    IConfigIdentifier getEntityTypeIdentifier();

    void setEntityTypeIdentifier(IConfigIdentifier entityTypeIdentifier);

    IEntityTypeConfig getEntityType();

    void setEntityType(IEntityTypeConfig entityTypeConfig);
    
    String getEntityTypeName();
    
    String getEntityClass();

    void setEntityClass(String entityClass);

    String getEntityPackage();

    void setEntityPackage(String entityPackage);

    boolean isMaster();

    void setMaster(boolean master);

    boolean isBean();

    void setBean(boolean bean);

    boolean isForm();

    void setForm(boolean form);

    boolean isSecure();

    void setSecure(boolean secure);

    boolean isSystem();

    void setSystem(boolean system);
    
    boolean isCustom();

    void setCustom(boolean custom);
    
    boolean isCustomAttribute();

    void setCustomAttribute(boolean customAttribute);

    boolean isCustomize();
    
    
    // OnlyRead
    boolean isSupportCache();
    
    

    boolean isCache();
    
    void setCache(boolean cache);
    
    
    long getCacheExpireTime();
    
    void setCacheExpireTime(long cacheExpireTime);
    
    
    List getAttributes();

    void setAttributes(List attributes);

}
