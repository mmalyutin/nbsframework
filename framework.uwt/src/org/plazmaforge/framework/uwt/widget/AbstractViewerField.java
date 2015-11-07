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

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.data.provider.DataProvider;
import org.plazmaforge.framework.core.data.provider.DataProviderAsync;
import org.plazmaforge.framework.core.data.provider.MutableListProvider;
import org.plazmaforge.framework.uwt.data.store.BaseDataStore;
import org.plazmaforge.framework.uwt.data.store.DataFilter;
import org.plazmaforge.framework.uwt.data.store.DataSorter;
import org.plazmaforge.framework.uwt.data.store.DataStore;
import org.plazmaforge.framework.uwt.event.SelectionListener;

/**
 * Abstract Viewer Field
 * 
 * 
 * See <code>Viewer</code>
 *  
 * @author ohapon
 *
 * @param <T>
 */
public abstract class AbstractViewerField<T> extends AbstractField<T> implements IViewer<T> {

    
    /**
     * DataStore
     */
    private DataStore<T> dataStore;

    /**
     * Own DataProvider
     * Only for indicate that viewer has own DataProvider
     */
    private boolean ownDataProvider;
    

    /**
     * PropertyProvider
     */
    private PropertyProvider<T> propertyProvider;

    
    
    
    /**
     * Create default DataProvider
     * @return
     */
    protected abstract DataProvider<T> createDefaultDataProvider();

    
    /**
     * Create DataProvider by list of data
     * @param dataList
     * @return
     */
    protected abstract DataProvider<T> createDataListProvider(List<T> dataList);
    
    /**
     * Create default DataStore
     * @return
     */
    protected DataStore<T> createDefaultDataStore() {
	DataStore<T> dataStore = new BaseDataStore<T>(createDefaultDataProvider(), true);
	dataStore.load();
	dataStore.addListener(new Listener() {

	    @Override
	    public void handleEvent(Event event) {
		fireChangeProperty(PROPERTY_DATA_LIST, event.getData());
	    }
	    
	});
	return dataStore;
    }


    protected DataStore<T> getDataStore() {
        return dataStore;
    }

    protected void setDataStore(DataStore<T> dataStore) {
        this.dataStore = dataStore;
    }

    ////

    public DataProvider<T> getDataProvider() {
	return getDataStore().getDataProvider();
    }

    public void setDataProvider(DataProvider<T> dataProvider) {
	setDataProvider(dataProvider, isForceLoad());
    }

    public void setDataProvider(DataProvider<T> dataProvider, boolean forceLoad) {
	checkDataProvider(dataProvider);
	getDataStore().setEnabledListeners(true); // Enable listeners for external DataProvider
	getDataStore().setDataProvider(dataProvider, forceLoad); //TD-FX
	ownDataProvider = dataProvider != null;
    }

    public boolean hasDataProvider() {
	return getDataStore().hasDataProvider();
    }

    public boolean hasOwnDataProvider() {
	return ownDataProvider;
    }

    protected void checkDataProvider(DataProvider<T> dataProvider) {
	
    }

    ////

    public DataProviderAsync<T> getDataProviderAsync() {
	return getDataStore().getDataProviderAsync();
    }

    public void setDataProviderAsync(DataProviderAsync<T> dataProviderAsync) {
	setDataProviderAsync(dataProviderAsync, isForceLoad());
    }

    public void setDataProviderAsync(DataProviderAsync<T> dataProviderAsync, boolean forceLoad) {
	checkDataProviderAsync(dataProviderAsync);
	getDataStore().setEnabledListeners(true); // Enable listeners for external DataProvider
	getDataStore().setDataProviderAsync(dataProviderAsync, forceLoad); //TD-FX
    }

    public boolean hasDataProviderAsync() {
	return getDataStore().hasDataProviderAsync();
    }

    protected void checkDataProviderAsync(DataProviderAsync<T> dataProviderAsync) {
	
    }

    
    ////
    
    @Override
    public DataSorter<T> getSorter() {
        return getDataStore().getDataSorter();
    }

    @Override
    public void setSorter(DataSorter<T> sorter) {
	getDataStore().setDataSorter(sorter);
    }

    @Override
    public boolean hasSorter() {
   	return getDataStore().hasDataSorter();
    }
    
    @Override
    public void resetSorter() {
	getDataStore().resetDataSorter();
    }
    
    @Override
    public List<DataFilter<T>> getFilters() {
	return getDataStore().getDataFilters();
    }

    @Override
    public void setFilters(List<DataFilter<T>> filters) {
        getDataStore().setDataFilters(filters);
    }

    @Override
    public boolean hasFilters() {
   	return getDataStore().hasDataFilters();
    }

    @Override
    public void resetFilters() {
	getDataStore().resetDataFilters();
    }

    ////
    
    @Override
    public List<T> getItems() {
	return getDataStore().getDataList();
    }
    
    @Override
    public void setItems(List<T> items) {
	DataProvider<T> dataProvider = getDataProvider();
	
	// Update data in current data provider if provider is mutable
	if (dataProvider != null) {
	    if (dataProvider instanceof MutableListProvider ){
		((MutableListProvider<T>) dataProvider).setList(items);
		getDataStore().load();
		return;
	    }
	}
	
	setDataProvider(createDataListProvider(items), true);
    }
    
    //@Override
    public void setItems(T[] items) {
	List<T> list = new ArrayList<T>();
	if (items != null) {
	    for (T item: items) {
		list.add(item);
	    }
	}
	setItems(list);
    }
    
    @Override
    public int getItemCount() {
	return getDataStore().getSize();
    }

    /**
     * Check index of item
     * Return false if outside
     * @param index
     * @return
     */
    protected boolean checkIndex(int index) {
	return index >= 0 && index <= getItemCount();
    }
    
    /**
     * Get item by index
     * @return
     */
    protected T getItem(int index) {
	return getItems().get(index);
    }
    
    /**
     * Set item by index
     * @param item
     */
    public void setItem(int index, T item) {
	getItems().set(index, item);
    }
    
    protected int indexOfItem(T item) {
	return getItems().indexOf(item);
    }
    
    public void resetSelection() {
	// TODO: User resetSelection in delegate
	//setSelectionIndex(-1);
    }
    
    
    /**
     * Get selection item
     * @return
     */
    public T getSelectionItem() {
	return getSelectionValue();
    }
    
    /**
     * Set selection item
     * @param selectionValue
     */
    public void setSelectionItem(T item) {
	setSelectionValue(item);
    }
    
    
    public boolean hasSelection() {
  	return getSelectionItem() != null;
    }
    
    public boolean isClientData() {
        return getDataStore().isClientData();
    }


    public void setClientData(boolean clientData) {
	getDataStore().setClientData(clientData);
    }

    public boolean isServerData() {
        return getDataStore().isServerData();
    }
    
    ////
    
    public boolean isForceLoad() {
	return true;
    }
    
    public void refresh() {
	loadData();
	//layout();
    }
    
    /**
     * Load data from DataStore
     */
    protected void loadData() {
	DataStore<T> dataStore = getDataStore();
	if (dataStore == null) {
	    return;
	}
	dataStore.load();
    }

    public PropertyProvider<T> getPropertyProvider() {
        return propertyProvider;
    }

    public void setPropertyProvider(PropertyProvider<T> propertyProvider) {
        this.propertyProvider = propertyProvider;
    }
    

    public boolean hasPropertyProvider() {
	return propertyProvider != null;
    }
    
    ////
    
    public void addSelectionListener(SelectionListener listener) {
	addSelectionListener(this, listener);
    }

    public void removeSelectionListener(SelectionListener listener) {
	removeSelectionListener(this, listener);
    }

     ////
    
    public void addStoreListener(Listener listener) {
	getDataStore().addListener(listener);
    }

    public void removeStoreListener(Listener listener) {
	getDataStore().removeListener(listener);
    }
}
