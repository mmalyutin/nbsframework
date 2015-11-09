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

package org.plazmaforge.framework.uwt.swt.adapter.viewer;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.plazmaforge.framework.core.data.provider.DataProvider;

public class SWTStructuredContentProvider implements IStructuredContentProvider {

    private List<Object> dataList;
    
    private DataProvider dataProvider;
    
    public SWTStructuredContentProvider(DataProvider dataProvider) {
	//if (dataProvider == null) {
	//    throw new IllegalArgumentException("DataProvider must be not null");
	//}
	this.dataProvider = dataProvider;
    }

    public List<Object> getDataList() {
        return dataList;
    }

    public void setDataList(List<Object> dataList) {
        this.dataList = dataList;
    }

    
    public DataProvider getDataProvider() {
        return dataProvider;
    }

    @Override
    public void dispose() {
	dataList = null;
    }

    @Override
    public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
    }

    @Override
    public Object[] getElements(Object arg0) {
	return dataList == null ? new Object[0] : dataList.toArray(new Object[0]);
    }
}
