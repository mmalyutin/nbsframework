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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.plazmaforge.framework.config.AppConfiguration;
import org.plazmaforge.framework.config.configurer.ReportConfigurer;
import org.plazmaforge.framework.config.object.IReportConfig;
import org.plazmaforge.framework.config.object.ReportConfig;

/**
 * 
 * @author ohapon
 *
 */
public class XMLReportConfigurer extends XMLObjectConfigurer<IReportConfig> implements ReportConfigurer {

    public static String NAME = "XMLReportConfigurer";

    public static final String XML_ROOT = "report-config";
    
    public static final String DEFAULT_CONFIG_FILE = XML_ROOT + ".xml";

    public static final String DEFAULT_CONFIG_PROPERTIES = XML_ROOT;

    public static final String XML_REPORT = "report";

    public static final String XML_PATH = "path";

    public static final String XML_FILE = "file";

    public static final String XML_REGION = "region";

    public static final String XML_COUNTRY = "country";

    public static final String XML_LANGUAGE = "language";

    public static final String XML_ACCEPTOR = "acceptor";
    
    public static final String XML_IS_SINGLE = "single";
    
    public XMLReportConfigurer(AppConfiguration configuration) {
	super(configuration);
	setConfigFileName(DEFAULT_CONFIG_FILE);
	setPropertiesBaseName(DEFAULT_CONFIG_PROPERTIES);
    }

    

    /**
     * Load reports from file
     */
    protected void doLoadObjects() throws Exception {

	Element root = getRootElement(getConfigInputStream());
	List list = getChildren(root, XML_REPORT);
	if (list == null) {
	    return;
	}
	Iterator i = list.iterator();
	IReportConfig report = null;
	Element reportElement = null;
	while (i.hasNext()) {
	    reportElement = ((Element) i.next());

	    String id = getAttributeId(reportElement);
	    String name = reportElement.getAttributeValue(XML_NAME);
	    String type = reportElement.getAttributeValue(XML_TYPE);
	    String klass = reportElement.getAttributeValue(XML_CLASS);
	    String path = reportElement.getAttributeValue(XML_PATH);
	    String file = reportElement.getAttributeValue(XML_FILE);
	    
	    String region = reportElement.getAttributeValue(XML_REGION);
	    String country = reportElement.getAttributeValue(XML_COUNTRY);
	    String language = reportElement.getAttributeValue(XML_LANGUAGE);
	    
	    String acceptor = reportElement.getAttributeValue(XML_ACCEPTOR);
	    
	    String client = reportElement.getAttributeValue(XML_UI_TYPE);
	    String single = reportElement.getAttributeValue(XML_IS_SINGLE);
	    String custom = reportElement.getAttributeValue(XML_IS_CUSTOM);

	    report = new ReportConfig();
	    report.setId(id);
	    report.setName(name);
	    report.setType(type);
	    report.setClassName(klass);
	    report.setPath(path);
	    report.setFileName(file);
	    
	    report.setAcceptor(acceptor);

	    // Locale init
	    report.setRegion(region);
	    report.setCountry(country);
	    report.setLanguage(language);

	    report.setUiType(client);
	    report.setSingle(getBoolean(single));
	    report.setCustom(getBoolean(custom));

	    //NLS
	    if (store != null) {
		loadNLSCaption(store, report);
		loadNLSDescription(store, report);
	    }
	    
	    addObject(report);
	}
    }

    
    /**
     * Store reports to file
     */
    protected void doStoreObjects() throws Exception {
	Element root = createRootElement(XML_ROOT);
	Document doc = new Document(root);
	List<IReportConfig> reports = getObjects();
	if (reports == null) {
	    storeWithNLS(doc, store);
	    return;
	}
	Element reportElement = null;
	List<Element> children = new ArrayList<Element>();
	for (IReportConfig report : reports) {
	    reportElement = new Element(XML_REPORT);

	    children.add(reportElement);

	    setAttributeId(reportElement, report.getId());
	    
	    setAttribute(reportElement, XML_NAME, report.getName());
	    setAttribute(reportElement, XML_TYPE, report.getType());
	    setAttribute(reportElement, XML_CLASS, report.getClassName());
	    setAttribute(reportElement, XML_PATH, report.getPath());
	    setAttribute(reportElement, XML_FILE, report.getFileName());

	    // Locale init
	    setAttribute(reportElement, XML_REGION, report.getRegion());
	    setAttribute(reportElement, XML_COUNTRY, report.getCountry());
	    setAttribute(reportElement, XML_LANGUAGE, report.getLanguage());

	    setAttribute(reportElement, XML_ACCEPTOR, report.getAcceptor());
	    
	    setAttribute(reportElement, XML_UI_TYPE, report.getUiType());
	    setAttribute(reportElement, XML_IS_SINGLE, getBooleanStringNullIfFalse(report.isSingle()));
	    setAttribute(reportElement, XML_IS_CUSTOM, getBooleanStringNullIfFalse(report.isCustom()));

	    // NLS
	    if (store != null) {
		storeNLSCaption(store, report);
		storeNLSDescription(store, report);
	    }
	    
	    
	}
	root.setContent(children);
	storeWithNLS(doc, store);
    }


}
