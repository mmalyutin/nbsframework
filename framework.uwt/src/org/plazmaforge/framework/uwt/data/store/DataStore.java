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

package org.plazmaforge.framework.uwt.data.store;

import java.util.List;

import org.plazmaforge.framework.core.data.Callback;
import org.plazmaforge.framework.core.data.provider.DataProvider;
import org.plazmaforge.framework.core.data.provider.DataProviderAsync;
import org.plazmaforge.framework.core.exception.ErrorHandler;
import org.plazmaforge.framework.uwt.widget.Listener;

//[CORE]
public interface DataStore<T> {

    /**
     * Return DataProvider of DataStore
     * 
     * @return
     */
    DataProvider<T> getDataProvider();

    
    /**
     * Set DataProvider to DataStore
     * 
     * @param dataProvider
     */
    void setDataProvider(DataProvider<T> dataProvider);
    
    /**
     * Set DataProvider to DataStore with 'forceLoad' parameter
     * If 'forceLoad' is true then load data
     * 
     * @param dataProvider
     * @param forceLoad
     */
    void setDataProvider(DataProvider<T> dataProvider, boolean forceLoad);

    
    /**
     * Return true if DataStore has DataProvider
     * @return
     */
    boolean hasDataProvider();
    
    
    /**
     * Return DataProviderAsync of DataStore
     * @return
     */
    DataProviderAsync<T> getDataProviderAsync();

    

    /**
     * Set ProviderAsync of DataStore
     * @param dataProviderAsync
     */
    void setDataProviderAsync(DataProviderAsync<T> dataProviderAsync);

    /**
     * Set ProviderAsync of DataStore with 'forceLoad' parameter
     * If 'forceLoad' is true then load data
     * @param dataProviderAsync
     * @param forceLoad
     */
    void setDataProviderAsync(DataProviderAsync<T> dataProviderAsync, boolean forceLoad);
    
    /**
     * Return true if DataStore has DataProviderAsync
     * @return
     */
    boolean hasDataProviderAsync();
    
    
    /**
     * Return DataSorter of DataStore
     * 
     * @return
     */
    DataSorter<T> getDataSorter();

    /**
     * Set DataSorter to DataStore
     * 
     * @param dataSorter
     */
    void setDataSorter(DataSorter<T> dataSorter);

    
    /**
     * Return true if DataStore has DataSorter
     * @return
     */
    boolean hasDataSorter();
    
    /**
     * Reset/clear DataSorter
     */
    void resetDataSorter();


    /**
     * Return List of DataFilter of DataStore
     *  
     * @return
     */
    List<DataFilter<T>> getDataFilters();

    /**
     * Set List of DataFilter to DataStore
     * 
     * @param dataFilter
     */
    void setDataFilters(List<DataFilter<T>> dataFilters);
 
    /**
     * Add DataFilter to DataStore
     * @param dataFilter
     */
    void addDataFilter(DataFilter<T> dataFilter);
    
    /**
     * Remove DataFilter from DataStore
     * @param dataFilter
     */
    void removeDataFilter(DataFilter<T> dataFilter);
    
    /**
     * Return true if DataStore has list of DataFilter
     * @return
     */
    boolean hasDataFilters();
    
    
    /**
     * Reset/clear list of DataFilters
     */
    void resetDataFilters();
    
    /**
     * Get all data list
     * @return
     */
    List<T> getAllList();


    /**
     * Get sorted and filtered data list
     * @return
     */
    List<T> getDataList();

    /**
     * Get size of sorted and filtered data list 
     * @return
     */
    int getSize();
    
    
    
    int getLimit();

    void setLimit(int limit);

    int getOffset();

    void setOffset(int offset);


    boolean isPaging();
    

    /**
     * Load (Refresh) Data form DataProvider
     */
    void load();

    /**
     * Load (Refresh) Data form DataProvider with callback
     */
    void load(Callback<List<T>> resultCallback);
    
    /**
     * Dispose DataStore
     */
    void dispose();


    /**
     * Add listener
     * 
     * @param listener
     */
    void addListener(Listener listener);
    
    /**
     * Remove listener
     * 
     * @param listener
     */
    void removeListener(Listener listener);
    
    
    boolean isEnabledListeners();


    void setEnabledListeners(boolean enabledListeners);

    
    boolean isClientData();


    void setClientData(boolean clientData);

    
    boolean isServerData();
    
    
    void setErrorHandler(ErrorHandler errorHandler);
    
    ErrorHandler getErrorHandler();
}
