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
import org.plazmaforge.framework.config.configurer.FormConfigurer;
import org.plazmaforge.framework.config.object.ActionDescriptorConfig;
import org.plazmaforge.framework.config.object.FormConfig;
import org.plazmaforge.framework.config.object.ReportDescriptorConfig;
import org.plazmaforge.framework.config.object.IActionDescriptorConfig;
import org.plazmaforge.framework.config.object.IFormConfig;
import org.plazmaforge.framework.config.object.IReportDescriptorConfig;
import org.plazmaforge.framework.core.exception.ApplicationException;

/**
 * 
 * @author ohapon
 *
 */
public class XMLFormConfigurer extends XMLObjectConfigurer<IFormConfig> implements FormConfigurer {

    public static String NAME = "XMLFormConfigurer";

    public static final String XML_ROOT = "form-config";
    
    public static final String DEFAULT_CONFIG_FILE = XML_ROOT + ".xml";

    public static final String DEFAULT_CONFIG_PROPERTIES = XML_ROOT;

    public static final String XML_FORM = "form";

    public static final String XML_ACTIONS = "actions";

    public static final String XML_ACTION = "action";

    public static final String XML_REPORTS = "reports";

    public static final String XML_REPORT = "report";
    
    
    public static final String XML_CONTROL_TYPE = "control-type";
    
    public static final String XML_CONTROL_POSITION = "control-position";
    
    public static final String XML_CONTROL_RELATIVE = "control-relative";
    

    public XMLFormConfigurer(AppConfiguration configuration) {
	super(configuration);
	setConfigFileName(createUIConfigFileName(DEFAULT_CONFIG_FILE));
	setPropertiesBaseName(createUIConfigFileName(DEFAULT_CONFIG_PROPERTIES));
    }
  
    /**
     * Load forms from file
     */
    protected void doLoadObjects() throws Exception {
	Element rootElement = getRootElement(getConfigInputStream());
	List list = getChildren(rootElement, XML_FORM);
	if (list == null) {
	    return;
	}
	Iterator i = list.iterator();
	IFormConfig form = null;
	Element formElement = null;
	while (i.hasNext()) {
	    formElement = ((Element) i.next());

	    String id = getAttributeId(formElement);
	    String name = formElement.getAttributeValue(XML_NAME);
	    String type = formElement.getAttributeValue(XML_TYPE);
	    String klass = formElement.getAttributeValue(XML_CLASS);	    
	    String uiType = formElement.getAttributeValue(XML_UI_TYPE);

	    form = new FormConfig();
	    form.setId(id);
	    form.setName(name);
	    form.setType(type);
	    form.setClassName(klass);
	    form.setUiType(uiType);

	    if (store != null) {
		loadNLSCaption(store, form);
		loadNLSDescription(store, form);
	    }

	    loadActions(form, formElement);
	    loadReports(form, formElement);

	    addObject(form);
	}

    }
    
    /**
     * Store forms to file
     */
    protected void doStoreObjects() throws Exception {
	Element root = createRootElement(XML_ROOT);
	Document doc = new Document(root);
	List<IFormConfig> forms = getObjects();
	if (forms == null) {
	    storeWithNLS(doc, store);
	    return;
	}
	Element formElement = null;
	List<Element> children = new ArrayList<Element>();
	for (IFormConfig form : forms) {
	    formElement = new Element(XML_FORM);

	    children.add(formElement);

	    setAttributeId(formElement, form.getId());
	    setAttribute(formElement, XML_NAME, form.getName());
	    setAttribute(formElement, XML_TYPE, form.getType());
	    setAttribute(formElement, XML_CLASS, form.getClassName());
	    setAttribute(formElement, XML_UI_TYPE, form.getUiType());

	    storeActions(form, formElement);
	    storeReports(form, formElement);
	    
	    if (store != null) {
		storeNLSCaption(store, form);
		storeNLSDescription(store, form);
	    }
	    
	}
	root.setContent(children);
	storeWithNLS(doc, store);
    }


    protected void loadActions(IFormConfig form, Element formElement) throws ApplicationException {
	Element rootActions = formElement.getChild(XML_ACTIONS);
	if (rootActions == null) {
	    return;
	}
	List children = rootActions.getChildren(XML_ACTION);
	Iterator i = children.iterator();
	String actionName = null;
	while (i.hasNext()) {
	    Element element = (Element) i.next();
	    actionName = element.getAttributeValue(XML_REF);
	    if (actionName == null) {
		continue;
	    }
	    
	    IActionDescriptorConfig actionDescriptor = new ActionDescriptorConfig();
	    actionDescriptor.setRef(actionName);
	    
	    actionDescriptor.setControlType(element.getAttributeValue(XML_CONTROL_TYPE));
	    actionDescriptor.setControlPosition(element.getAttributeValue(XML_CONTROL_POSITION));
	    actionDescriptor.setControlRelative(element.getAttributeValue(XML_CONTROL_RELATIVE));
	    
	    form.addActionDescriptor(actionDescriptor);
	}
    }

    protected void loadReports(IFormConfig form, Element formElement) throws ApplicationException {
	Element rootReports = formElement.getChild(XML_REPORTS);
	if (rootReports == null) {
	    return;
	}
	List children = rootReports.getChildren(XML_REPORT);
	Iterator i = children.iterator();
	String reportName = null;
	while (i.hasNext()) {
	    Element element = (Element) i.next();
	    reportName = element.getAttributeValue(XML_REF);
	    if (reportName == null) {
		continue;
	    }
	    IReportDescriptorConfig reportDescriptor = new ReportDescriptorConfig();
	    reportDescriptor.setRef(reportName);
	    form.addReportDescriptor(reportDescriptor);
	}
    }
    
    
    ////
    
    
    protected void storeActions(IFormConfig form, Element formElement) throws ApplicationException {
	List<IActionDescriptorConfig> actionDescriptors = form.getActionDescriptors();
	if (actionDescriptors == null || actionDescriptors.isEmpty()) {
	    return;
	}
	Element rootActions = new Element(XML_ACTIONS);
	formElement.getChildren().add(rootActions);
	    
	for (IActionDescriptorConfig actionDescriptor: actionDescriptors) {
	    String actionName = actionDescriptor.getRef();
	    if (actionName == null) {
		continue;
	    }
	    actionName = actionName.trim();
	    if (actionName.isEmpty()) {
		continue;
	    }
	    Element actionElement = new Element(XML_ACTION);
	    actionElement.setAttribute(XML_REF, actionName);
	    
	    setAttribute(actionElement, XML_CONTROL_TYPE, actionDescriptor.getControlType());
	    setAttribute(actionElement, XML_CONTROL_POSITION, actionDescriptor.getControlPosition());
	    setAttribute(actionElement, XML_CONTROL_RELATIVE, actionDescriptor.getControlRelative());
	    
	    rootActions.getChildren().add(actionElement);
	}
    }
    
    protected void storeReports(IFormConfig form, Element formElement) throws ApplicationException {
	List<IReportDescriptorConfig> reportDescriptors = form.getReportDescriptors();
	if (reportDescriptors == null || reportDescriptors.size() == 0) {
	    return;
	}
	Element rootReports = new Element(XML_REPORTS);
	formElement.getChildren().add(rootReports);
	for (IReportDescriptorConfig reportDescriptor: reportDescriptors) {
	    String reportName = reportDescriptor.getRef();
	    if (reportName == null) {
		continue;
	    }
	    reportName = reportName.trim();
	    if (reportName.isEmpty()) {
		continue;
	    }
	    Element reportElement = new Element(XML_REPORT);
	    reportElement.setAttribute(XML_REF, reportName);
	    rootReports.getChildren().add(reportElement);
	}
    }

}
