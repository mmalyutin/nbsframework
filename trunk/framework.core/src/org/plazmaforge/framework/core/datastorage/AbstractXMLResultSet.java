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

package org.plazmaforge.framework.core.datastorage;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.plazmaforge.framework.core.exception.DSException;
import org.plazmaforge.framework.core.xml.XPathExecuter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * 
 * @author ohapon
 *
 */
public abstract class AbstractXMLResultSet extends AbstractTextFileResultSet {


    public static final String DEFAULT_SELECT_EXPRESSION = ".";
    
    // the xml document
    protected Document document;

    // the XPath select expression that gives the nodes to iterate
    protected String selectExpression;

    // the node list
    protected NodeList nodeList;

    // the node list length
    protected int nodeListLength;

    // the current node
    protected Node currentNode;

    // current node index
    protected int currentNodeIndex = -1;

    protected XPathExecuter xPathExecuter;
 
    
    
    private boolean init;
    
    
    protected DocumentBuilder createDocumentBuilder() throws DSException {
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	dbf.setValidating(false);
	dbf.setIgnoringComments(true);

	try {
	    return dbf.newDocumentBuilder();
	} catch (ParserConfigurationException e) {
	    throw new DSException("Failed to create a document builder factory", e);
	}
    }

    protected Document parse(InputSource is) throws DSException {
	try {
	    return createDocumentBuilder().parse(is);
	} catch (SAXException e) {
	    throw new DSException("Failed to parse the xml document", e);
	} catch (IOException e) {
	    throw new DSException("Failed to parse the xml document", e);
	}
    }
    
    protected void initPosition() throws DSException {
	if (init) {
	    return;
	}
	init = true;
	beforeFirst();
    }
    
    public String getSelectExpression() {
	if (isEmpty(selectExpression)) {
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
    
    
    /**
     * Return the text that a node contains. This routine:
     * <ul>
     * <li>Ignores comments and processing instructions.
     * <li>Concatenates TEXT nodes, CDATA nodes, and the results of recursively
     * processing EntityRef nodes.
     * <li>Ignores any element nodes in the sublist. (Other possible options are
     * to recurse into element sublists or throw an exception.)
     * </ul>
     * 
     * @param node a DOM node
     * @return a String representing node contents or null
     */
    protected String getText(Node node) {
	
	if (!node.hasChildNodes()) {
	    return node.getNodeValue();
	}
	    
	StringBuffer result = new StringBuffer();

	NodeList list = node.getChildNodes();
	for (int i = 0; i < list.getLength(); i++) {
	    Node subnode = list.item(i);
	    if (subnode.getNodeType() == Node.TEXT_NODE) {
		String value = subnode.getNodeValue();
		if (value != null)
		    result.append(value);
	    } else if (subnode.getNodeType() == Node.CDATA_SECTION_NODE) {
		String value = subnode.getNodeValue();
		if (value != null)
		    result.append(value);
	    } else if (subnode.getNodeType() == Node.ENTITY_REFERENCE_NODE) {
		// Recurse into the subtree for text
		// (and ignore comments)
		String value = getText(subnode);
		if (value != null)
		    result.append(value);
	    }
	}

	return result.toString();
    }	
    

}
