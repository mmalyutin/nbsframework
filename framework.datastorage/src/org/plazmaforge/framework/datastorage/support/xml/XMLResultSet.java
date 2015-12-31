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

package org.plazmaforge.framework.datastorage.support.xml;

import java.io.Reader;

import org.plazmaforge.framework.core.datastorage.AbstractXMLResultSet;
import org.plazmaforge.framework.core.datastorage.DSStructuredResultSet;
import org.plazmaforge.framework.core.exception.DSException;

import org.plazmaforge.framework.core.xml.XPathExecuter;
import org.plazmaforge.framework.datastorage.support.xml.support.JaxenXPathExecuter;
import org.plazmaforge.framework.datastorage.support.xml.support.XalanXPathExecuter;
import org.plazmaforge.framework.util.StringUtils;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

/**
 * 
 * @author ohapon
 *
 */
public class XMLResultSet extends AbstractXMLResultSet implements DSStructuredResultSet {

    private boolean init;
    
    public XMLResultSet(Reader reader) throws DSException {
	super();
	this.reader = reader;
	this.document = parse(new InputSource(reader));
	this.xPathExecuter = createXPathExecuter();
    }

    protected XPathExecuter createXPathExecuter() {
	XalanXPathExecuter executer = new XalanXPathExecuter();
	//JaxenXPathExecuter executer = new JaxenXPathExecuter();
	return executer;
    }
    
    protected void initPosition() throws DSException {
	if (init) {
	    return;
	}
	init = true;
	beforeFirst();
    }
    
    public String getSelectExpression() {
	if (StringUtils.isEmpty(selectExpression, true)) {
	    return DEFAULT_SELECT_EXPRESSION;
	}
	return selectExpression;
    }

    public void setSelectExpression(String selectExpression) {
	this.selectExpression = selectExpression;
    }

    public void beforeFirst() throws DSException {
	if (document == null) {
	    throw new DSException("Document cannot be null");
	}
	if (getSelectExpression() == null) {
	    throw new DSException("SelectExpression cannot be null");
	}
	currentNode = null;
	currentNodeIndex = -1;
	nodeListLength = 0;
	nodeList = xPathExecuter.selectNodeList(document, getSelectExpression());
	nodeListLength = nodeList.getLength();
    }

  
    @Override
    public boolean next() throws DSException {
	initPosition();
	if (currentNodeIndex == nodeListLength - 1) {
	    return false;
	}
	currentNode = nodeList.item(++currentNodeIndex);
	return true;
    }

    @Override
    public Object getValue(String name) throws DSException {
	String expression = name; // Name like path expression
	return getStringValue(expression);
    }

    protected String getStringValue(String expression) throws DSException {
	if (currentNode == null)
	    return null;

	if (expression == null || expression.isEmpty()) {
	    return null;
	}
	Object selectedObject = xPathExecuter.selectObject(currentNode, expression);
	return getStringValue(selectedObject);
    }
    

    protected String getStringValue(Object nodeObject) throws DSException {
	if (nodeObject == null) {
	    return null;
	}
	if (nodeObject instanceof Node) {
	    String text = getText((Node) nodeObject);
	    return text;
	}
	return nodeObject.toString();
    }
    
    /*
    protected Object getValue(String expression, String type) throws DSException {

	if (currentNode == null)
	    return null;

	if (expression == null || expression.length() == 0) {
	    return null;
	}

	if (Types.ObjectType.equals(type)) {
	    return null;
	}

	Object value = null;

	Object selectedObject = xPathExecuter.selectObject(currentNode, expression);

	if (selectedObject != null) {
	    if (selectedObject instanceof Node) {
		String text = getText((Node) selectedObject);
		if (text != null) {
		    value = convertString(text, type);
		}
	    } else if (selectedObject instanceof Boolean && type.equals(Types.BooleanType)) {
		value = selectedObject;
	    } else if (selectedObject instanceof Number  && TypeUtils.isLikeNumberType(type)) {
		value = convertNumber((Number) selectedObject, type);
	    } else {
		String text = selectedObject.toString();
		value = convert(text, type);
	    }
	}
	return value;

    }
    */
    
    
           
}
