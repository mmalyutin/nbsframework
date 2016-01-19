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

package org.plazmaforge.framework.datastorage.support.xls;

import java.io.InputStream;
import java.util.List;

import org.plazmaforge.framework.core.datastorage.AbstractStreamFileResultSet;
import org.plazmaforge.framework.core.datastorage.DSStructuredResultSet;
import org.plazmaforge.framework.core.exception.DSException;

/**
 * 
 * @author ohapon
 *
 */
public abstract class AbstractXLSResultSet extends AbstractStreamFileResultSet implements DSStructuredResultSet  {

    private String sheetExpression;
    
    protected int sheetIndex = -1;
    protected int recordIndex = -1;
    
    private boolean firstRowHeader;
    
    public AbstractXLSResultSet(InputStream is) throws DSException {
	this.inputStream = is;
    }
	
    public AbstractXLSResultSet(List<String> fieldNames, InputStream inputStream) {
	super(fieldNames, inputStream);
    }

    public AbstractXLSResultSet(List<String> fieldNames) {
	super(fieldNames);
    }

    public boolean isFirstRowHeader() {
        return firstRowHeader;
    }

    public void setFirstRowHeader(boolean firstRowHeader) {
	checkProcessing();
        this.firstRowHeader = firstRowHeader;
    }

    //@Override
    public void beforeFirst() throws DSException {
	this.recordIndex = -1;
	this.sheetIndex = -1;
    }

    public String getSheetExpression() {
        return sheetExpression;
    }

    public void setSheetExpression(String sheetExpression) {
	checkProcessing();
        this.sheetExpression = sheetExpression;
    }
    
    
}
