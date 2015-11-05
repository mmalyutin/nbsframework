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

/* 
 * Created on 24.08.2007
 *
 */

package org.plazmaforge.framework.core.datastorage;

import java.util.List;

import org.plazmaforge.framework.core.data.LocalizedIdentifier;
import org.plazmaforge.framework.core.exception.DSException;

/** 
 * 
 * DataSet is ResultSet with fields
 * 
 * @author ohapon
 * 
 */

public interface DSDataSet extends DSStructuredResultSet, LocalizedIdentifier {
    
    /**
     * Return DataSource name
     * @return
     */
    String getDataSourceName();

    /**
     * Set DataSource name
     * @param dataSourceName
     */
    void setDataSourceName(String dataSourceName);

    /**
     * Return count of fields
     * @return
     */
    int getFieldCount();
    
    /**
     * Return fields
     * @return
     */
    List<DSField> getFields();
    
    /**
     * Return field by index
     * @param index
     * @return
     */
    DSField getField(int index);

    /**
     * Return field by name
     * @param name of field
     * @return
     */
    DSField getField(String name);

    /**
     * Return name of field by index
     * @param index
     * @return
     */
    String getFieldName(int index);

    /**
     * Return type of filed by index 
     * @param index
     * @return
     */
    String getFieldType(int index);

    
    /**
     * Return value of current record by index
     * @param index
     * @return
     * @throws DSException
     */
    Object getValue(int index) throws DSException;
    
    /**
     * Return value of current record by field
     * @param field
     * @return
     * @throws DSException 
     */
    Object getValue(DSField field) throws DSException;
    
    /**
     * Get all values of current record
     * 
     * @param rowIndex
     * @return
     * @throws DSException
     */
    Object[] getRecord() throws DSException;

}
