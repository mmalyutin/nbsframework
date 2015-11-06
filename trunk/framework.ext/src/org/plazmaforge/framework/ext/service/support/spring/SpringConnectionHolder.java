/*
 * Copyright (C) 2005-2010 Oleh Hapon ohapon@users.sourceforge.net
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
package org.plazmaforge.framework.ext.service.support.spring;




import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;


//import org.plazmaforge.framework.platform.PlatformEnvironment;
//import org.plazmaforge.framework.platform.structure.impl.spring.SpringContext;
//import org.plazmaforge.framework.platform.structure.impl.spring.SpringFramework;

import org.plazmaforge.framework.sql.ConnectionHolder;
import org.springframework.context.ApplicationContext;


public class SpringConnectionHolder implements ConnectionHolder {

    private String dataSourceName;

    private ApplicationContext applicationContext;

    public Connection getConnection() throws SQLException {
	return ((DataSource) getApplicationContext().getBean(getDataSourceName())).getConnection();
    }

    public ApplicationContext getApplicationContext() {
	return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
	this.applicationContext = applicationContext;
    }

    public String getDataSourceName() {
	if (dataSourceName == null) {
	    dataSourceName = createDataSourceName();
	}
	return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
	this.dataSourceName = dataSourceName;
    }

    protected String createDataSourceName() {
	//TODO: STUB
	return "dataSource";
	
    }

}
