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
import org.plazmaforge.framework.core.datastorage.DSDataConnector;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.datastorage.DSSession;
import org.plazmaforge.framework.core.datastorage.DataManager;
import org.plazmaforge.framework.core.exception.DSException;


/**
 * 
 * @author ohapon
 *
 */
public class XLSDataProducer extends AbstractXLSDataProducer {

    protected AbstractXLSSession createXLSSession(InputStream inputStream) throws DSException {
	return new XLSSession(inputStream);
    }
    
    protected AbstractXLSResultSet createXLSResultSet(InputStream inputStream) throws DSException {
	return new XLSResultSet(inputStream);
    }
    
    protected AbstractXLSDataSet createXLSDataSet(List<DSField> fields, InputStream inputStream) throws DSException {
	return new XLSDataSet(fields, inputStream);
    }
    
    protected void checkDataConnector(DSDataConnector dataConnector) throws DSException {
	super.checkDataConnector(dataConnector);
	if (!(dataConnector instanceof XLSDataConnector)) {
	    handleContextException(DataManager.CONTEXT_SESSION, "DataConnector must be XLSDataConnector");
	}	
    }
    
    protected void checkSession(DSSession session) throws DSException {
	super.checkSession(session);
	if (!(session instanceof XLSSession)) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "Session must be XLSSession");
	}
    }

    public DSDataConnector createDataConnector() {
	return new XLSDataConnector();
    }

    public boolean supportsSingleDataSource() {
	return true;
    }
    
}
