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

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.criteria.Criteria;
import org.plazmaforge.framework.core.data.Callback;
import org.plazmaforge.framework.core.data.DataList;
import org.plazmaforge.framework.core.data.provider.ArrayProvider;
import org.plazmaforge.framework.core.data.provider.CriteriaProvider;
import org.plazmaforge.framework.core.data.provider.CriteriaProviderAsync;
import org.plazmaforge.framework.core.data.provider.DataProvider;
import org.plazmaforge.framework.core.data.provider.DataProviderAsync;
import org.plazmaforge.framework.core.data.provider.ListProvider;
import org.plazmaforge.framework.core.data.provider.ListProviderAsync;
import org.plazmaforge.framework.core.exception.ErrorHandler;
import org.plazmaforge.framework.uwt.widget.Event;
import org.plazmaforge.framework.uwt.widget.Listener;


/**
 * Base DataStore implementation
 * 
 * @author ohapon
 *
 */
//[CORE]
public class BaseDataStore<T> extends AbstractDataStore<T> {

    /**
     * Provider of data
     */
    private DataProvider<T> dataProvider;
    
    
    /**
     * ProviderAsync of data
     */
    private DataProviderAsync<T> dataProviderAsync;
    
    
    
    /**
     * Load flag
     */
    private boolean load; 
    
    
    private boolean lazyLoad = true;
    
    
    ////
    // TODO: Use type of listeners
    private List<Listener> listeners; 
    
    // Use false for internal DataStore
    private boolean enabledListeners = true;
    
    
    private ErrorHandler errorHandler;
    
    public BaseDataStore() {
	
    }
    
    
    public BaseDataStore(DataProvider<T> dataProvider) {
	this.dataProvider = dataProvider;
    }

    public BaseDataStore(DataProvider<T> dataProvider, boolean enabledListeners) {
	this.dataProvider = dataProvider;
	this.enabledListeners = enabledListeners;
    }


    public BaseDataStore(DataProvider<T> dataProvider, DataSorter<T> dataSorter, DataFilter<T> dataFilter) {
	this.dataProvider = dataProvider;
	
	setDataSorter(dataSorter);
	if (dataFilter != null){
	    getDataFilters().add(dataFilter);
	}
    }

    /**
     * Create default DataStore
     * @return
     */
    public static <T> DataStore<T> createDefaultDataStore(List<T> dataList) {
	DataStore<T> dataStore = new BaseDataStore<T>(createDefaultDataProvider(dataList), true);
	dataStore.load();
	return dataStore;
    }

    /**
     * Create default DataProvider
     * @param dataList
     * @return
     */
    public static <T> DataProvider<T> createDefaultDataProvider(List<T> dataList) {
	DataProvider<T> dataProvider = new ArrayProvider<T>(dataList);
	return dataProvider;
    }
    
    
    
    
    @Override
    public DataProvider<T> getDataProvider() {
        return dataProvider;
    }

    @Override
    public void setDataProvider(DataProvider<T> dataProvider) {
	setDataProvider(dataProvider, isForceLoad());
    }
    
    @Override
    public void setDataProvider(DataProvider<T> dataProvider, boolean forceLoad) {
	if (this.dataProvider != null) {
	    //this.dataProvider.dispose();
	}
        this.dataProvider = dataProvider;
        if (forceLoad){
	    load();	    
	}
    }

    @Override
    public boolean hasDataProvider() {
        return dataProvider != null;
    }
    
    
    @Override
    public DataProviderAsync<T> getDataProviderAsync() {
        return dataProviderAsync;
    }


    @Override
    public void setDataProviderAsync(DataProviderAsync<T> dataProviderAsync) {
        setDataProviderAsync(dataProviderAsync, isForceLoad());
    }

    @Override
    public void setDataProviderAsync(DataProviderAsync<T> dataProviderAsync, boolean forceLoad) {
        this.dataProviderAsync = dataProviderAsync;
        if (forceLoad) {
	    load();	    
	}
    }
    
    
    @Override
    public boolean hasDataProviderAsync() {
        return dataProviderAsync != null;
    }



    @Override
    public void load() {
	load((Callback<List<T>>) null);
    }

	
    /**
     * Load (Refresh) Data form DataProvider or DataProviderAsync
     */
    @Override
    public void load(Callback<List<T>> resultCallback) {
	clearData();
	try {
	    loadDataList(resultCallback);
	} finally {
	    /*
	    load = true;
	    if (isEnabledListeners()) {	
		// TODO: Use  event type 
		Event event = new Event();
		event.data = getDataList();
		fireEvent(event);
	    }
	    */
	}
    }
    
    private void fireLoadEvent() {
	load = true;
	// Fire change property PROPERTY_DATA_LIST if not internal
	// if (!internal) {
	if (isEnabledListeners()) {
	    // TODO: Use event type
	    Event event = new Event();
	    event.setData(getDataList());
	    fireEvent(event);
	}
    }

    protected void fireResultCallback(Callback<List<T>> resultCallback) {
	if (resultCallback == null) {
	    return;
	}
	//DISABLE:MIGRATION
	resultCallback.onSuccess(getDataList() /*dataList*/); // TODO: May be clone dataList
    }
    
    
    /**
     * Create Criteria by Filters and Orders
     * @return
     */
    protected Criteria createCriteria() {
	Criteria criteria = new Criteria();
	
	// Populate filter
	StoreUtils.populateCriteriaDataFilters(criteria, getDataFilters());
	
	// Populate sorter
	StoreUtils.populateCriteriaDataSorter(criteria, getDataSorter());

	// Populate paging
	criteria.setOffset(getOffset());
	criteria.setLimit(getLimit());
	
	return criteria;
    }
    
    
    
    /**
     * Load DataList form DataProvider or DataProviderAsync with DataSorter and DataFilter
     * 
     * @return
     */
    protected void loadDataList(final Callback<List<T>> resultCallback) {

	// Analyze DataProvider and DataProviderAsync
   	DataProvider<T> dataProvider = getDataProvider();
   	DataProviderAsync<T> dataProviderAsync = getDataProviderAsync();

   	
   	////////////////////////////////////////////////////////////////////////////////////////
   	// Load from DataProviderAsync
   	////////////////////////////////////////////////////////////////////////////////////////
	if (dataProviderAsync != null) {

	    
	    loadDataProviderAsyncList(new Callback<List<T>>() {

		@Override
		public void onFailure(Throwable error) {
		    handleError(error);
		}

		@Override
		public void onSuccess(List<T> result) {
		    initDataList(result);
		    criteriaDataList();
		    
		    //FIRE LOADED EVENT
		    fireLoadEvent();
		    fireResultCallback(resultCallback);
		}

	    });
	    
	    return;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	// Load from DataProvider
	////////////////////////////////////////////////////////////////////////////////////////
	if (dataProvider != null) {
	    loadDataProviderList();
	    criteriaDataList();
	    
	    //FIRE LOADED EVENT
	    fireLoadEvent();
	    fireResultCallback(resultCallback);
	    return;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	// Load empty data
	////////////////////////////////////////////////////////////////////////////////////////
	initDataList(null);
	criteriaDataList();
	
	//FIRE LOADED EVENT
	fireLoadEvent();
	fireResultCallback(resultCallback);

    }
    
    
    protected void loadDataProviderList() {
	
   	DataProvider<T> dataProvider = getDataProvider();
   	
   	// NO PROVIDER
   	if (dataProvider == null || !(dataProvider instanceof ListProvider)) {
   	    // EMPTY DATA
   	    return;
   	}
   	
   	ListProvider<T> listProvider = (ListProvider<T>) dataProvider;
   	
   	if (isClientData()) {
   	    // CLIENT DATA: LOAD ALL LIST
   	    initDataList(listProvider.getList());
   	} else {
   	    // SERVER DATA: LOAD CRITERIA LIST
   	    if (!(dataProvider instanceof CriteriaProvider)) {
   		//TODO
   		//throw new IllegalArgumentException("DataProvider must be CrioteriaProvider to load data by criteria");
   		return;
   		
   	    }
   	    CriteriaProvider<T> criteriaProvider = (CriteriaProvider<T>) dataProvider;
   	    Criteria criteria = createCriteria();
   	    initDataList(criteriaProvider.getList(criteria));
   	}
    }
    
    protected void loadDataProviderAsyncList(Callback<List<T>> callback) {
	
   	DataProviderAsync<T> dataProviderAsync = getDataProviderAsync();
   	
   	// NO PROVIDER
   	if (dataProviderAsync == null || !(dataProviderAsync instanceof ListProviderAsync)) {
   	    // EMPTY DATA
   	    callback.onSuccess(new ArrayList<T>());
   	    return;
   	}
   	
   	ListProviderAsync<T> listProviderAsync = (ListProviderAsync<T>) dataProviderAsync;
   	
   	if (isClientData()) {
   	    // CLIENT DATA: LOAD ALL LIST
   	    listProviderAsync.getList(callback);
   	} else {
   	    // SERVER DATA: LOAD CRITERIA LIST
   	    if (!(dataProviderAsync instanceof CriteriaProviderAsync)) {
   		//TODO
   		//throw new IllegalArgumentException("DataProviderAsync must be CriteriaProviderAsync to load data by criteria");
   		return;
   		
   	    }
   	    CriteriaProviderAsync<T> criteriaProviderAsync = (CriteriaProviderAsync<T>) dataProviderAsync;
   	    Criteria criteria = createCriteria();
   	    criteriaProviderAsync.getList(criteria, callback);
   	}
    }
    
	
    
    public boolean isLoad() {
	return load;
    }
    
    
    @Override
    public void dispose() {
	super.dispose();
	dataProvider = null;
	dataProviderAsync = null;
	clearList(listeners, true);
	listeners = null;
    }

    

    public List<Listener> getListeners() {
	if (listeners == null) {
	    listeners = new ArrayList<Listener>();
	}
        return listeners;
    }


    public void addListener(Listener listener) {
	getListeners().add(listener);
    }
    
    public void removeListener(Listener listener) {
	getListeners().remove(listener);
    }
    
    
    protected void fireEvent(Event event) {
	if (this.listeners == null) {
	    return;
	}
	List<Listener> listeners = getListeners();
	for (Listener l: listeners) {
	    l.handleEvent(event);
	}
    }
    
    
    public boolean isLazyLoad() {
        return lazyLoad;
    }


    public void setLazyLoad(boolean lazyLoad) {
        this.lazyLoad = lazyLoad;
    }


    public boolean isForceLoad() {
	return !isLazyLoad();
    }


    public boolean isEnabledListeners() {
        return enabledListeners;
    }


    public void setEnabledListeners(boolean enabledListeners) {
        this.enabledListeners = enabledListeners;
    }


   

    ////


    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }


    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }
    
    protected void handleError(Throwable error) {
	if (errorHandler == null) {
	    System.err.println(error);
	    return;
	}
	errorHandler.handleError(error);
    }
    
    
    
}
