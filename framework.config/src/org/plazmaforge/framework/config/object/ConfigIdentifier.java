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


package org.plazmaforge.framework.config.object;


/**
 * 
 * @author ohapon
 *
 */
public class ConfigIdentifier implements IConfigIdentifier {

    private static final long serialVersionUID = -6687147241591309808L;

    private String configId;

    private String configName;

    public String getConfigId() {
	return configId;
    }

    public void setConfigId(String configId) {
	this.configId = configId;
    }
    
    public String getConfigName() {
	return configName;
    }

    public void setConfigName(String configName) {
	this.configName = configName;
    }

    public boolean equals(Object o) {

	if (o == null) {
	    return false;
	}

	if (this == o) {
	    return true;
	}

	if (!(o instanceof ConfigIdentifier)) {
	    return false;
	}
	ConfigIdentifier c = (ConfigIdentifier) o;

	if (!equals(c.getConfigId(), getConfigId())) {
	    return false;
	}
	if (!equals(c.getConfigName(), getConfigName())) {
	    return false;
	}

	return true;
    }
    
    protected boolean equals(Object o1, Object o2) {
	if (o1 == null && o2 == null) {
	    return true;
	}
	if (o1 == null || o2 == null) {
	    return false;
	}
	return o1.equals(o2);
    }
}
