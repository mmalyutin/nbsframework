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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.plazmaforge.framework.core.exception.DSException;

/**
 * 
 * @author ohapon
 *
 */
public class XLSResultSet extends AbstractPOIResultSet {

    public XLSResultSet(InputStream inputStream) throws DSException {
	super(inputStream);
    }

    public XLSResultSet(List<String> fieldNames, InputStream inputStream) throws DSException {
	super(fieldNames, inputStream);
    }

    @Override
    protected Workbook loadWorkbook(InputStream inputStream) throws DSException {
	try {
	    return new HSSFWorkbook(inputStream);    
	} catch (IOException e) {
	    throw new DSException(e);
	}
    }

    
}
