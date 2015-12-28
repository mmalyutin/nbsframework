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

package org.plazmaforge.framework.core.xml;

import org.plazmaforge.framework.core.exception.DSException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface XPathExecuter {

    /**
     * Selects a node list by evaluating an XPath expression on a context node.
     * 
     * @param contextNode the context node (a document can also be used)
     * @param expression the XPath expression
     * @return the selected node list
     * @throws DSException if the XPath evaluation failed
     */
    NodeList selectNodeList(Node contextNode, String expression) throws DSException;

    /**
     * Selects an object by evaluating an XPath expression on a context node.
     * <p/>
     * If the expression evaluates to a node list, the first node in the list
     * should be returned. Otherwise, the primitive value resulted from the
     * evaluation should be returned as a <code>java.lang.String</code>,
     * <code>java.lang.Number</code> or <code>java.lang.Boolean</code>.
     * 
     * @param contextNode the context node (a document can also be used)
     * @param expression the XPath expression
     * @return the selected node or value
     * @throws DSException if the XPath evaluation failed
     */
    Object selectObject(Node contextNode, String expression) throws DSException;
}
