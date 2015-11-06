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

package org.plazmaforge.framework.config.configurer.xml;

import org.plazmaforge.framework.config.AppConfiguration;
import org.plazmaforge.framework.config.configurer.ModuleConfigurer;
import org.plazmaforge.framework.config.object.IModuleConfig;
import org.plazmaforge.framework.core.exception.ApplicationException;

public class XMLModuleConfigurer extends XMLObjectConfigurer<IModuleConfig> implements ModuleConfigurer {


    public XMLModuleConfigurer(AppConfiguration configuration) {
	super(configuration);
    }


    @Override
    protected boolean isSupportNLSStore() {
	return false;
    }
    
    
    @Override
    public void loadObjects() throws ApplicationException {
    }

    @Override
    public void storeObjects() throws ApplicationException {
    }
    
    @Override
    protected void doLoadObjects() throws Exception {
    }

    @Override
    protected void doStoreObjects() throws Exception {
    }


}
