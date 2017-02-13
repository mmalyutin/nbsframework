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

package org.plazmaforge.framework.datastorage;

import org.plazmaforge.framework.core.datastorage.DataManager;
import org.plazmaforge.framework.datastorage.support.csv.CSVDataConnector;
import org.plazmaforge.framework.datastorage.support.csv.CSVDataProducerFactory;
import org.plazmaforge.framework.datastorage.support.json.JSONDataConnector;
import org.plazmaforge.framework.datastorage.support.json.JSONDataProducerFactory;
import org.plazmaforge.framework.datastorage.support.xls.XLSDataConnector;
import org.plazmaforge.framework.datastorage.support.xls.XLSDataProducerFactory;
import org.plazmaforge.framework.datastorage.support.xlsx.XLSXDataConnector;
import org.plazmaforge.framework.datastorage.support.xlsx.XLSXDataProducerFactory;
import org.plazmaforge.framework.datastorage.support.xml.XMLDataConnector;
import org.plazmaforge.framework.datastorage.support.xml.XMLDataProducerFactory;



/**
 * 
 * @author ohapon
 *
 */
public class DataStorage {

    
//    private static DataStorage instance; 
    
    private DataStorage() {
    }
    
//    private static DataStorage getInstance() {
//	if (instance == null) {
//	    instance = new DataStorage();
//	    instance.init();
//	}
//	return instance;
//    }    
    private static boolean init;
    
    public static void init() {
	if (init) {
	    return;
	}
	init = true;
	DataManager.registerDataProducerFactory(CSVDataConnector.TYPE, new CSVDataProducerFactory());
	DataManager.registerDataProducerFactory(XMLDataConnector.TYPE, new XMLDataProducerFactory());
	DataManager.registerDataProducerFactory(JSONDataConnector.TYPE, new JSONDataProducerFactory());
	DataManager.registerDataProducerFactory(XLSDataConnector.TYPE, new XLSDataProducerFactory());
	DataManager.registerDataProducerFactory(XLSXDataConnector.TYPE, new XLSXDataProducerFactory());
    }
}
