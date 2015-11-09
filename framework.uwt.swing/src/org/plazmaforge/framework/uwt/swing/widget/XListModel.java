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

package org.plazmaforge.framework.uwt.swing.widget;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ListModel;

public class XListModel extends AbstractListModel implements ListModel {

    public static final String EMPTY_STRING = "";
    
    
    private List dataList;
    
    public XListModel() {
        super();
    }
    
    public XListModel(List dataList) {
        this.dataList = dataList;
    }

    public int getSize() {
        return dataList == null ? 0 : dataList.size();
    }

    public Object getElementAt(int i) {
        return dataList.get(i);
    }

    protected List getDataList() {
        return dataList;
    }
    
    public void setDataList(List dataList) {
        this.dataList = dataList;
        this.fireContentsChanged(this, 0, dataList.size());
    }

    public void clearData() {
	setDataList(null);
    }

}
