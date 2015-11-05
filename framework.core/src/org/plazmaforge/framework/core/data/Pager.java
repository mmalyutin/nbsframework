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

package org.plazmaforge.framework.core.data;

/**
 * Pager encapsulates navigation information about data list
 * 
 * - Row count
 * - Total Row count
 * - Row limit
 * - Page count
 * - Current page index
 * 
 * @author ohapon
 *
 */
public class Pager {

    public static final int START_PAGE_INDEX = 0;
    
    public static final int START_ROW_INDEX = 0;
    
    
    
    private int totalRowCount;
    
    private int rowLimit;
    
    private int fromRow;
    
    private int rowCount;
    
    private int pageCount;
    
    private int currentPageIndex;

    private boolean navigationState;
    
    
    
    public int getTotalRowCount() {
        return totalRowCount < 0 ? 0 : totalRowCount;
    }

    public void setTotalRowCount(int totalRowCount) {
        this.totalRowCount = totalRowCount;
    }

    /**
     * RowCount is count of row of current page.
     * It is not RowLimit because for example in last page we can have RowCount < RowLimit
     * @return
     */
    public int getRowCount() {
	return rowCount < 0 ? 0 : rowCount;
    }
    
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getRowLimit() {
        return rowLimit < 0 ? 0 : rowLimit;
    }

    public void setRowLimit(int rowLimit) {
        this.rowLimit = rowLimit;
    }

    public int getFromRow() {
        return fromRow < START_ROW_INDEX ? START_ROW_INDEX : fromRow;
    }

    public void setFromRow(int fromRow) {
        this.fromRow = fromRow;
    }


    public int getPageCount() {
        return pageCount < 0 ? 0 : pageCount;
    }

    /*
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    */

    public int getCurrentPageIndex() {
	if (getPageCount() < 1) {
	    return -1;
	}
        return currentPageIndex;
    }

    /*
    public void setCurrentPageIndex(int currentPageIndex) {
        this.currentPageIndex = currentPageIndex;
    }
    */
    
    public int getFirstPageIndex() {
        return isDisablePager() ? -1 : START_PAGE_INDEX;
    }

    public int getLastPageIndex() {
        return isDisablePager() ? -1 : getPageCount() - 1;
    }

    public boolean isEmpty() {
	return pageCount < 1;
    }
    
    public boolean isEnableFirstPage() {
	if (isDisablePager()) {
	    return false;
	}
	return (getCurrentPageIndex() > START_PAGE_INDEX);
    }

    public boolean isEnablePrevPage() {
	if (isDisablePager()) {
	    return false;
	}
	return getCurrentPageIndex() > START_PAGE_INDEX;
    }

    public boolean isEnableNextPage() {
	if (isDisablePager()) {
	    return false;
	}
	return getCurrentPageIndex() < getLastPageIndex();
    }
    
    public boolean isEnableLastPage() {
	if (isDisablePager()) {
	    return false;
	}
	return getCurrentPageIndex() < getLastPageIndex();
    }
    
    public boolean isDisablePager() {
	return isEmpty() || getCurrentPageIndex() < START_PAGE_INDEX;
    }
    
    public void recalculate() {
	int totalRowCount = getTotalRowCount();
	int rowLimit = getRowLimit();
	int fromRow = getFromRow();
	
	if (rowLimit == 0) {
	    pageCount = 0;
	} else {
	    float f = (float) totalRowCount / rowLimit;
	    int i = totalRowCount / rowLimit;
	    if (f - i > 0) {
		i++;
	    }
	    pageCount = i;
	}
	
	if (pageCount <= 0) {
	    currentPageIndex = -1;
	    return;
	}
	
	if (fromRow > totalRowCount) {
	    currentPageIndex = pageCount - 1;
	} else if (fromRow <= 0 ) {
	    currentPageIndex = START_PAGE_INDEX;
	} else {
	    
	    
	    int realFromRow = fromRow + 1; // Real from row start with 1
	    float f = (float) realFromRow / rowLimit;
	    int i = realFromRow / rowLimit;
	    
	    if (f - i > 0) {
		i++;
	    }
	    currentPageIndex = i - 1; // because start from 0
	    if (currentPageIndex < START_PAGE_INDEX) {
		currentPageIndex = START_PAGE_INDEX;
	    }
	    
	}
    }
    
    public boolean firstPage() {
	if (!isEnableFirstPage()) {
	    return false;
	}
	fromRow = START_ROW_INDEX;
	recalculate();
	return true;
    }

    public boolean prevPage() {
	if (!isEnablePrevPage()) {
	    return false;
	}
	
	int rowLimit = getRowLimit();
	if (rowLimit == 0) {
	    return false;
	}
	int totalRowCount = getTotalRowCount();
	if (totalRowCount == 0) {
	    return false;
	}
	
	int currRow = fromRow - rowLimit;
	if (currRow < START_ROW_INDEX) {
	    return firstPage();
	}
	fromRow = currRow;
	
	recalculate();
	return true;
    }

    public boolean nextPage() {
	if (!isEnableNextPage()) {
	    return false;
	}
	int rowLimit = getRowLimit();
	if (rowLimit == 0) {
	    return false;
	}
	int totalRowCount = getTotalRowCount();
	if (totalRowCount == 0) {
	    return false;
	}
	int lastRow = totalRowCount - 1;
	int currRow = fromRow + rowLimit;
	
	
	if (currRow > lastRow) {
	    return lastPage();
	}
	fromRow = currRow;
	
	recalculate();
	return true;
    }
    
    public boolean lastPage() {
	if (!isEnableLastPage()) {
	    return false;
	}
	int rowLimit = getRowLimit();
	if (rowLimit == 0) {
	    fromRow = START_ROW_INDEX;
	    recalculate();
	    return false;
	}
	int totalRowCount = getTotalRowCount();
	if (totalRowCount == 0) {
	    return false;
	}
	float f = (float) totalRowCount / rowLimit;
	int i = totalRowCount / rowLimit;
	if (i == 0) {
	    fromRow = START_ROW_INDEX;
	} else {
	    if (f - i > 0) {
		fromRow  = rowLimit * i;
	    } else {
		fromRow  = rowLimit * i - rowLimit; 
	    }
	}
	recalculate();
	return true;
    }

    public boolean isNavigationState() {
        return navigationState;
    }

    public void setNavigationState(boolean navigationState) {
        this.navigationState = navigationState;
    }
    
}
