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

package org.plazmaforge.framework.datastorage.support.xml.support;

import javax.xml.transform.TransformerException;

import org.apache.xpath.CachedXPathAPI;
import org.apache.xpath.objects.XObject;
import org.plazmaforge.framework.core.exception.DSException;
import org.plazmaforge.framework.core.xml.XPathExecuter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XalanXPathExecuter implements XPathExecuter {

    // XPath API facade
    private CachedXPathAPI xpathAPI = new CachedXPathAPI();

    /**
     * Default constructor.
     */
    public XalanXPathExecuter() {
    }

    public NodeList selectNodeList(Node contextNode, String expression) throws DSException {
	try {
	    return xpathAPI.selectNodeList(contextNode, expression);
	} catch (TransformerException e) {
	    throw new DSException("XPath selection failed. Expression: " + expression, e);
	}
    }

    public Object selectObject(Node contextNode, String expression) throws DSException {
	try {
	    
	    
	    XObject object = xpathAPI.eval(contextNode, expression);
	    
	    //TODO	    
	    return object == null ? null : object.toString();

	    //
	    /*
	    Object value = null; 
	    switch (object.getType()) {
	    case XObject.CLASS_NODESET:
		value = object.nodeset().nextNode();
		break;
	    case XObject.CLASS_BOOLEAN:
		value = object.bool() ? Boolean.TRUE : Boolean.FALSE;
		break;
	    case XObject.CLASS_NUMBER:
		value = new Double(object.num());
		break;
	    default:
		value = object.str();
		break;
	    }
	    return value;
	    */
	} catch (TransformerException e) {
	    throw new DSException("XPath selection failed. Expression: " + expression, e);
	}
    }


}
