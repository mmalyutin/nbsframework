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

/**
 * 
 */
package org.plazmaforge.framework.datastorage.support.sql;

import org.plazmaforge.framework.core.datastorage.AbstractDataConnector;

/**
 * @author ohapon
 *
 */
public class SQLDataConnector extends AbstractDataConnector {

    public static final String TYPE = "SQL";

    
    public static final String PROPERTY_URL = "url";
    public static final String PROPERTY_DRIVER = "driver";    
    

    private String driver;

    private String url;
    
    private String username;
    
    private String password;
    
    private String query; // experimental
    
    public SQLDataConnector() {
    }

    @Override
    public String getType() {
	return TYPE;
    }
    
    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((driver == null) ? 0 : driver.hashCode());
	result = prime * result
		+ ((password == null) ? 0 : password.hashCode());
	result = prime * result + ((query == null) ? 0 : query.hashCode());
	result = prime * result + ((url == null) ? 0 : url.hashCode());
	result = prime * result
		+ ((username == null) ? 0 : username.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	SQLDataConnector other = (SQLDataConnector) obj;
	if (driver == null) {
	    if (other.driver != null)
		return false;
	} else if (!driver.equals(other.driver))
	    return false;
	if (password == null) {
	    if (other.password != null)
		return false;
	} else if (!password.equals(other.password))
	    return false;
	if (query == null) {
	    if (other.query != null)
		return false;
	} else if (!query.equals(other.query))
	    return false;
	if (url == null) {
	    if (other.url != null)
		return false;
	} else if (!url.equals(other.url))
	    return false;
	if (username == null) {
	    if (other.username != null)
		return false;
	} else if (!username.equals(other.username))
	    return false;
	return true;
    }

    

}
