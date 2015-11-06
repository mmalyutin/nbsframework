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

package org.plazmaforge.framework.config.configurer;

import org.plazmaforge.framework.config.AppConfiguration;
import org.plazmaforge.framework.config.object.IObjectConfig;
import org.plazmaforge.framework.core.exception.ApplicationException;
import org.plazmaforge.framework.util.IPropertiesStore;

/**
 * 
 * @author ohapon
 *
 * @param <T>
 */
public class DefaultObjectConfigurer<T extends IObjectConfig> extends AbstractObjectConfigurer<T> implements ObjectConfigurer<T> {

    public DefaultObjectConfigurer(AppConfiguration configuration) {
	super(configuration);
    }

    public void loadObjects() throws ApplicationException {
    }

    public void storeObjects() throws ApplicationException {
    }
    
    public IPropertiesStore getStore() {
	return null;
    }

}
