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
package org.plazmaforge.framework.core.datastorage;

import org.plazmaforge.framework.core.data.BaseLocalizedIdentifier;

/**
 * @author ohapon
 *
 */
public abstract class AbstractDataConnector extends BaseLocalizedIdentifier implements DSDataConnector {

    public static final String PROPERTY_USERNAME = "username";
    
    public static final String PROPERTY_PASSWORD = "password";
    
    public static final String PROPERTY_QUERY = "query";

    public static final String PROPERTY_DATE_FROMAT = "dateFormat";
    
    public static final String PROPERTY_NUMBER_FROMAT = "numberFormat";
    
    
    
    private String username;
    
    private String password;
    
    private String query;
    
    private String dateFormat;
    
    private String numberFormat;
    

    public AbstractDataConnector() {
	super();
    }

    public abstract String getType();


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

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getNumberFormat() {
        return numberFormat;
    }

    public void setNumberFormat(String numberFormat) {
        this.numberFormat = numberFormat;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result
		+ ((dateFormat == null) ? 0 : dateFormat.hashCode());
	result = prime * result
		+ ((numberFormat == null) ? 0 : numberFormat.hashCode());
	result = prime * result
		+ ((password == null) ? 0 : password.hashCode());
	result = prime * result + ((query == null) ? 0 : query.hashCode());
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
	AbstractDataConnector other = (AbstractDataConnector) obj;
	if (dateFormat == null) {
	    if (other.dateFormat != null)
		return false;
	} else if (!dateFormat.equals(other.dateFormat))
	    return false;
	if (numberFormat == null) {
	    if (other.numberFormat != null)
		return false;
	} else if (!numberFormat.equals(other.numberFormat))
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
	if (username == null) {
	    if (other.username != null)
		return false;
	} else if (!username.equals(other.username))
	    return false;
	return true;
    }

    
}
