/*
 * Copyright (C) 2012-2015 Oleh Hapon ohapon@users.sourceforge.net
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

/**
 * 
 */
package org.plazmaforge.framework.core.datastorage;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.exception.DSException;

/**
 * @author ohapon
 *
 */
public class DSEmptyDataSet extends DSEmptyResultSet implements DSDataSet  {

    private static final long serialVersionUID = -9152468755725124465L;

    @Override
    public String getDataSourceName() {
	return null;
    }

    @Override
    public void setDataSourceName(String dataSourceName) {
    }
    
    @Override
    public List<String> getFieldNames() {
	return new ArrayList<String>();
    }

    @Override
    public String getDisplayName() {
	return null;
    }

    @Override
    public void setCaption(String caption) {
    }

    @Override
    public String getCaption() {
	return null;
    }

    @Override
    public void setDescription(String description) {
    }

    @Override
    public String getDescription() {
	return null;
    }

    @Override
    public String getId() {
	return null;
    }

    @Override
    public void setId(String id) {
    }

    @Override
    public String getName() {
	return null;
    }

    @Override
    public void setName(String name) {
    }

    @Override
    public int getFieldCount() {
	return 0;
    }

    @Override
    public List<DSField> getFields() {
	return new ArrayList<DSField>();
    }

    @Override
    public DSField getField(int index) {
	return null;
    }

    @Override
    public DSField getField(String name) {
	return null;
    }

    @Override
    public String getFieldName(int index) {
	return null;
    }

    @Override
    public String getFieldType(int index) {
	return null;
    }

    @Override
    public Object getValue(DSField field) throws DSException {
	return null;
    }

    @Override
    public Object getValue(int index) throws DSException {
	return null;
    }

    @Override
    public Object[] getRecord() throws DSException {
	return null;
    }


    
    

}
