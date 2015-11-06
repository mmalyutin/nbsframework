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

import java.util.Iterator;
import java.util.List;

import org.jdom.Element;
import org.plazmaforge.framework.config.AppConfiguration;
import org.plazmaforge.framework.config.configurer.DialogConfigurer;
import org.plazmaforge.framework.config.object.IDialogConfig;
import org.plazmaforge.framework.config.object.DialogConfig;

/**
 * 
 * @author ohapon
 *
 */
public class XMLDialogConfigurer extends XMLObjectConfigurer<IDialogConfig> implements DialogConfigurer {

    public static String NAME = "XMLDialogConfigurer";

    public static final String DEFAULT_CONFIG_FILE = "report-dialog-config.xml";

    public static final String DEFAULT_CONFIG_PROPERTIES = "report-dialog-config";

    public static final String XML_DIALOG = "dialog";

    public XMLDialogConfigurer(AppConfiguration configuration) {
	super(configuration);
	setConfigFileName(DEFAULT_CONFIG_FILE);
	setPropertiesBaseName(DEFAULT_CONFIG_PROPERTIES);
    }


    /**
     * Load report dialogs from file
     */
    protected void doLoadObjects() throws Exception {

	Element rootElement = getRootElement(getConfigInputStream());
	List list = getChildren(rootElement, XML_DIALOG);
	if (list == null) {
	    return;
	}
	Iterator i = list.iterator();
	IDialogConfig reportDialog = null;
	Element reportElement = null;
	while (i.hasNext()) {
	    reportElement = ((Element) i.next());
	    String id = getAttributeId(reportElement);
	    String name = reportElement.getAttributeValue(XML_NAME);
	    String klass = reportElement.getAttributeValue(XML_CLASS);

	    reportDialog = new DialogConfig();
	    reportDialog.setId(id);
	    reportDialog.setName(name);
	    reportDialog.setClassName(klass);

	    addObject(reportDialog);
	}
    }
    
    /**
     * Store report dialogs to file
     */
    protected void doStoreObjects() throws Exception {
	// STUB
    }


}
