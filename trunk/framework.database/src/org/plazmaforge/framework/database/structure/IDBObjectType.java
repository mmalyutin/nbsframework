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

public interface IDBObjectType extends IDBObject {

    // External/base folder (group/category) type
    
    String CATALOG_FOLDER = "CATALOG_FOLDER";
    
    String SCHEMA_FOLDER = "SCHEMA_FOLDER";
    
    

    // Table folders
    String TABLE_FOLDER = "TABLE_FOLDER";
    
    String VIEW_FOLDER = "VIEW_FOLDER";
    
    String SYSTEM_TABLE_FOLDER = "SYSTEM_TABLE_FOLDER";
    
    String GLOBAL_TEMPORARY_FOLDER = "GLOBAL_TEMPORARY_FOLDER";
    
    String LOCAL_TEMPORARY_FOLDER = "LOCAL_TEMPORARY_FOLDER";
    
    
    
    // Internal/children folder type
    String COLUMN_FOLDER = "COLUMN_FOLDER";
    
    String INDEX_FOLDER = "INDEX_FOLDER";
    
    
    // Concrete type (original db object type)
    
    String CATALOG = "CATALOG";
    
    String SCHEMA = "SCHEMA";
    
    
    // Special table types
    
    String TABLE = "TABLE";
    
    String VIEW = "VIEW";
    
    String SYSTEM_TABLE = "SYSTEM TABLE";
    
    String GLOBAL_TEMPORARY = "GLOBAL TEMPORARY";
    
    String LOCAL_TEMPORARY = "LOCAL TEMPORARY";
    
    String ALIAS = "ALIAS";
    
    String SYNONIM = "SYNONIM";
    
    
    // Other types
    
    String PROCEDURE = "PROCEDURE";
    
    String FUNCTION = "FUNCTION";
    
    String SEQUENCE = "SEQUENCE";
    
    String GENERATOR = "GENERATOR";
    
    String UDF = "UDF";
    
    
    String INDEX = "INDEX";
    
    String TRIGGER = "TRIGGER";
    
    
    String COLUMN = "COLUMN";
    
    String PK_COLUMN = "PK_COLUMN";
    
    String FK_COLUMN = "FK_COLUMN";
    
    String PK_FK_COLUMN = "PK_FK_COLUMN";
    
    
}
