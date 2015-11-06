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

package org.plazmaforge.framework.database.structure;

/** 
 * @author ohapon
 */

public interface IDBObject {

    String getId();
    
    String getName();

    String getType();
   
    //void generateId();
    
    
    //void setName(String name);

    //void setType(String type);
    
    
    Object getData();

    //void setData(Object data);
    
    Object getData(Object key);
    
    //void setData(Object key, Object data);
    
    
    boolean isCreated();

    boolean isNormal();

    boolean isModified();

    boolean isDeleted();
    
    
    //void setCreated();
    
    //void setNormal();

    //void setModified();

    //void setDeleted();

 
    
}
