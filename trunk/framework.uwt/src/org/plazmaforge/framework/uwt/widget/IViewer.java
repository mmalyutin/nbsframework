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

package org.plazmaforge.framework.uwt.widget;


import java.util.List;

import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.data.provider.DataProvider;
import org.plazmaforge.framework.core.data.provider.DataProviderAsync;
import org.plazmaforge.framework.uwt.data.store.DataFilter;
import org.plazmaforge.framework.uwt.data.store.DataSorter;

public interface IViewer<T> extends HasDataItems<T>, HasSelection<T> {

    DataProvider<T> getDataProvider();
    
    void setDataProvider(DataProvider<T> dataProvider);
    
    void setDataProvider(DataProvider<T> dataProvider, boolean forceLoad);
    
    boolean hasDataProvider();
    
    boolean hasOwnDataProvider();
    
    
    DataProviderAsync<T> getDataProviderAsync();
    
    void setDataProviderAsync(DataProviderAsync<T> dataProviderAsync);

    void setDataProviderAsync(DataProviderAsync<T> dataProviderAsync, boolean forceLoad);

    boolean hasDataProviderAsync();
    

    PropertyProvider<T> getPropertyProvider();

    void setPropertyProvider(PropertyProvider<T> propertyProvider);

    boolean hasPropertyProvider();
    
    
    
    boolean isClientData();

    void setClientData(boolean clientData);

    boolean isServerData();
    
    
    void addStoreListener(Listener listener);
      
    void removeStoreListener(Listener listener);
    
    
    DataSorter<T> getSorter();

    void setSorter(DataSorter<T> sorter);

    boolean hasSorter();
    
    void resetSorter();
    
    List<DataFilter<T>> getFilters();

    void setFilters(List<DataFilter<T>> filters);

    boolean hasFilters();

    void resetFilters();
    
    Object getData();

    void setData(Object data);
    
    Object getData(String key);

    void setData(String key, Object data);


}
