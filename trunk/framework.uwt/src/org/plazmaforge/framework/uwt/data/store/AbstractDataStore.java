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

import org.plazmaforge.framework.core.data.DataList;

/**
 * 
 * @author ohapon
 *
 */
public abstract class AbstractDataStore<T> implements DataStore<T> {

    
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
     * True if data can be sorting and filtering in the store 
     */
    private boolean clientData = true;

    
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
  
    public void initDataList(List<T> dataList) {
	allList = createListWrapper(dataList);
    }
    
    
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
    
    protected void clearData() {
	clearData(true /*!internal*/); // Force clear if not internal
    }

    protected void clearData(boolean force) {
	clearList(allList, force);
	clearList(dataList, force);
	
	allList = null;
	dataList = null;
    }

    protected void clearList(List<?> list, boolean force) {
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
	dataSorter = null;
	clearList(dataFilters, true);
	dataFilters = null;
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

}
