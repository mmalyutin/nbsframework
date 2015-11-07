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
public class BaseDataStore<T> implements DataStore<T> {

    /**
     * Provider of data
     */
    private DataProvider<T> dataProvider;
    
    
    /**
     * ProviderAsync of data
     */
    private DataProviderAsync<T> dataProviderAsync;
    
    
    /**
     * Sorter of data
     */
    private DataSorter<T> dataSorter;
    
    
    /**
     * Filter list of data 
     */
    private List<DataFilter<T>> dataFilters;

    ////
    
    /**
     * All data list
     */
    private List<T> allList;

    
    /**
     * Sorted and filtered paging data list (for view)
     */
    private List<T> dataList;
    
    
    /**
     * Total row count
     */
    private int total;
    

    /**
     * Row limit
     */
    private int limit;
    
    /**
     * Row offset. Start with 0.
     */
    private int offset;

    ////
    
    /**
     * Load flag
     */
    private boolean load; 
    
    
    private boolean lazyLoad = true;
    
    /**
     * True if data can be sorting and filtering in the store 
     */
    private boolean clientData = true;
    
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
	this.dataSorter = dataSorter;
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
    public DataSorter<T> getDataSorter() {
        return dataSorter;
    }

    @Override
    public void setDataSorter(DataSorter<T> dataSorter) {
        this.dataSorter = dataSorter;
    }
    
    @Override
    public boolean hasDataSorter()  {
	return dataSorter != null;
    }
    
    @Override
    public void resetDataSorter()  {
	this.dataSorter = null;
    }
    

    @Override
    public List<DataFilter<T>> getDataFilters() {
	if (dataFilters == null) {
	    dataFilters = new ArrayList<DataFilter<T>>();
	}
        return dataFilters;
    }

    @Override
    public void setDataFilters(List<DataFilter<T>> dataFilters) {
        this.dataFilters = dataFilters;
    }

    @Override
    public boolean hasDataFilters()  {
	return dataFilters != null && !dataFilters.isEmpty();
    }

    /**
     * Add DataFilter to DataStore
     * @param dataFilter
     */
    @Override
    public void addDataFilter(DataFilter<T> dataFilter) {
	getDataFilters().add(dataFilter);
    }
    
    /**
     * Remove DataFilter from DataStore
     * @param dataFilter
     */
    @Override
    public void removeDataFilter(DataFilter<T> dataFilter) {
	getDataFilters().remove(dataFilter);
    }

    
    @Override
    public void resetDataFilters() {
	clearList(dataFilters, true);
    }
    
    ////

    @Override
    public List<T> getAllList() {
        return createListWrapper(allList);
    }

    @Override
    public List<T> getDataList() {
	return getRangeList();
    }

    @Override
    public int getSize() {
	return getRangeSize();
    }
    
    
    public List<T> getRangeList() {
	
	int offset = getOffset();
	int limit = getLimit();
	
	if (isIgnoreCriteria()) {
	    // Reset if ignore criteria
	    offset = 0;
	    limit = 0;
	}
	
	return getRangeList(offset, limit);
    }
    
    
    public int getRangeSize() {
	int offset = getOffset();
	int limit = getLimit();
	
	if (isIgnoreCriteria()) {
	    // Reset if ignore criteria
	    offset = 0;
	    limit = 0;
	}

	return getRangeSize(offset, limit);
    }

    
    
    public List<T> getRangeList(int offset, int limit) {
	if (dataList == null || dataList.isEmpty()) {
	    return createListWrapper(dataList);
	}
	
	if (offset < 0) {
	    offset = 0;
	}
	if (limit < 1) {
	    limit = 0;
	}
	
	List<T> list = dataList;
	
	if (limit > 0) {
	    
	    // Paging
	    int size = dataList.size();
	    
	    if (offset >= size) {
		// Out range
		list = null;
	    } else {
		
		/////////////////////////////////////
		int start = offset;
		int end = offset + limit - 1;

		if (end >= size) {
		    end = size - 1;
		}
		/////////////////////////////////////
		
		// Create range list
		list = new ArrayList<T>();
		for (int i = start; i <= end; i++) {
		    list.add(dataList.get(i));
		}
		
		// TODO: Must analyze
		
		// Convert to DataList
		int total = size;
		if (dataList instanceof DataList) {
		    total = ((DataList<T>) dataList).getTotal();
		}
		list = new DataList<T>(list, total);
		return list;
	    }
	    
	}
        return createListWrapper(list);
    }

    
    
    public int getRangeSize(int offset, int limit) {
	if (dataList == null || dataList.isEmpty()) {
	    return 0;
	}
	
	if (offset < 0) {
	    offset = 0;
	}
	if (limit < 1) {
	    limit = 0;
	}
	
	int size = dataList.size();
	
	if (limit > 0) {
	    
	    // Paging
	    if (offset >= size) {
		// Out range
		size = 0;
	    } else {
		
		/////////////////////////////////////
		int start = offset;
		int end = offset + limit - 1;

		if (end >= size) {
		    end = size - 1;
		}
		size = end - start;
		/////////////////////////////////////
	    }
	}
	
        return size;
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
	resultCallback.onSuccess(dataList); // TODO: May be clone dataList
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
		    allList = createListWrapper(result);
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
	allList = createListWrapper(null);
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
   	    allList = createListWrapper(listProvider.getList());
   	} else {
   	    // SERVER DATA: LOAD CRITERIA LIST
   	    if (!(dataProvider instanceof CriteriaProvider)) {
   		//TODO
   		//throw new IllegalArgumentException("DataProvider must be CrioteriaProvider to load data by criteria");
   		return;
   		
   	    }
   	    CriteriaProvider<T> criteriaProvider = (CriteriaProvider<T>) dataProvider;
   	    Criteria criteria = createCriteria();
   	    allList = createListWrapper(criteriaProvider.getList(criteria));
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
    
    protected List<T> createListWrapper(List<T> list) {
	if (list == null ) {
	    return new ArrayList<T>();
	}
	
	// DataList (paging)
	if (list instanceof DataList) {
	    return new DataList<T>(new ArrayList<T>(list), ((DataList<T>) list).getTotal());
	}
	
	// List
	return new ArrayList<T>(list);
    }
    
    protected void criteriaDataList() {
	// Get data from original provider
	List<T> input = this.allList;
	
	// Recreate list (wrapper)
	input = createListWrapper(input);

	
	// IGNORE CRITERIA: SERVER MODE
	if (isIgnoreCriteria()) {
	    this.dataList = input;
	    return;
	}
	
	// Filter
	if (hasDataFilters()) {
	    input = filterDataList(null, input);
	}

	// Sorter
	if (hasDataSorter()) {
	    getDataSorter().sort(input);
	}

	this.dataList = input;
    }
    
    protected List<T> filterDataList(T parent, List<T> input) {
	List<DataFilter<T>> filters = getDataFilters();
	int count = filters.size();
	if (count == 0) {
	    return input;
	}
	ArrayList<T> output = new ArrayList<T>();
	int flags = 0;
	for (T element : input) {
	    flags = 0; // Reset flags
	    for (DataFilter<T> filter : filters) {
		if (!filter.select(parent, element)) {
		    break; // If filter not selected then break
		}
		flags++; // If filter selected then count flags
	    }
	    if (count == flags) {
		output.add(element);
	    }
	}
	return output;
    }
	
    
    public boolean isLoad() {
	return load;
    }
    
    private void clearData() {
	clearData(true /*!internal*/); // Force clear if not internal
    }

    private void clearData(boolean force) {
	clearList(allList, force);
	clearList(dataList, force);
	
	allList = null;
	dataList = null;
	
    }

    private void clearList(List list, boolean force) {
	if (list == null) {
	    return;
	}
	if (force) {
	    list.clear();
	}
    }
    
    @Override
    public void dispose() {
	clearData(true); // Force clear
	
	dataProvider = null;
	dataSorter = null;
	clearList(dataFilters, true);
	clearList(listeners, true);
	listeners = null;
    }

    
    @Override
    public boolean isClientData() {
        return clientData;
    }


    @Override
    public void setClientData(boolean clientData) {
        this.clientData = clientData;
    }

    @Override
    public boolean isServerData() {
        return !isClientData();
    }
    
    protected boolean isIgnoreCriteria() {
	return isServerData();
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
    
    
    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
	if (limit < 0) {
	    throw new IllegalArgumentException("Limit must be >= 0");
	}
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
	if (limit < 0) {
	    throw new IllegalArgumentException("Offset must be >= 0");
	}
        this.offset = offset;
    }


    public boolean isPaging() {
	return limit > 0;
    }

    public int getTotal() {
        return total;
    }


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
