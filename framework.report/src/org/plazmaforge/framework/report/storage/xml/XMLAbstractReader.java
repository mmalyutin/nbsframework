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

/**
 * 
 */
package org.plazmaforge.framework.report.storage.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.plazmaforge.framework.core.data.LocalizedIdentifier;
import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.model.base.Border;
import org.plazmaforge.framework.report.model.base.Insets;
import org.plazmaforge.framework.report.model.base.Margin;
import org.plazmaforge.framework.report.model.base.Padding;
import org.plazmaforge.framework.report.model.base.Pen;
import org.plazmaforge.framework.report.model.base.Size;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;

/**
 * @author ohapon
 *
 */
public class XMLAbstractReader extends XMLWorker implements XMLInfo  {

    
    //// COMMON-BEGIN
    
    protected Document readXMLDocument(String fileName) throws RTException {
	if (fileName == null) {
	    throw new RTException("Can't read document. File name is null.");
	}
	fileName = normalizeString(fileName);
	if (fileName == null) {
	    throw new RTException("Can't read document. File name is empty.");
	}
	return readXMLDocument(new File(fileName));
    }

    protected Document readXMLDocument(File file) throws RTException {
	if (file == null) {
	    throw new RTException("Can't read document. File is null.");
	}
	try {
	    return readXMLDocument(new FileInputStream(file));
	} catch (FileNotFoundException ex) {
	    throw new RTException(ex);
	}
    }

    protected Document readXMLDocument(InputStream is) throws RTException {
	if (is == null) {
	    throw new RTException("Can't read document. InputStream is null.");
	}
	try {
	    SAXBuilder builder = new SAXBuilder();
	    // builder.setValidation(false);
	    return builder.build(is);
	} catch (Exception ex) {
	    throw new RTException(ex);
	}
    }
    
    protected org.jdom.Document readXMLDocument(Reader reader) throws RTException {
	if (reader == null) {
	    throw new RTException("Can't read document. Reader is null.");
	}
	try {
	    SAXBuilder builder = new SAXBuilder();
	    // builder.setValidation(false);
	    return builder.build(reader);
	} catch (Exception ex) {
	    throw new RTException(ex);
	}
    }    

    ////

    protected Element getChild(Element parent, String name) {
	return parent.getChild(name, parent.getNamespace());	
    }

    protected List getChildren(Element parent, String name) {
	return parent.getChildren(name, parent.getNamespace());	
    }

    protected List getNodeChildren(Element parent, String nodeName, String name) {
	Element node = getChild(parent, nodeName);
	if (node == null) {
	    return null;
	}
	return getChildren(node, name);
    }
    
    protected boolean useNamespace() {
	return true;
    }
    
    //// COMMON-END
    
    
    protected void readElementAttributes(Element node, org.plazmaforge.framework.report.model.base.Element element)  {
  	String sValue = null;
  	Integer iValue = null;

  	// x
  	iValue = getIntegerValue(node, XML_ATTR_X);
  	if (iValue != null) {
  	    element.setX(iValue);
  	}

  	// y
  	iValue = getIntegerValue(node, XML_ATTR_Y);
  	if (iValue != null) {
  	    element.setY(iValue);
  	}
  	
  	// width
  	iValue = getIntegerValue(node, XML_ATTR_WIDTH);
  	if (iValue != null) {
  	    element.setWidth(iValue);
  	}
  	
  	// height
  	iValue = getIntegerValue(node, XML_ATTR_HEIGHT);
  	if (iValue != null) {
  	    element.setHeight(iValue);
  	}

  	// background
  	Color background = getColor(node, XML_ATTR_BACKGROUND);
  	if  (background != null) {
  	    element.setBackground(background);
  	}
  	    
  	// foreground
  	Color foreground = getColor(node, XML_ATTR_FOREGROUND);
  	if (foreground != null) {
  	    element.setForeground(foreground);
  	}

  	// font
  	Font font = getFont(node, XML_ATTR_FONT);
  	if (font != null) {
  	    element.setFont(font);
  	}
  	
    }
    

    ////
    
    /**
     * Get Size by 'width, 'height' attributes
     * @param node
     * @return
     */
    protected Size getSizeByAttributes(Element node) {
	if(node == null) {
	    return null;
	}
	
	Size size = null;
	
	// width
	Integer iValue = getIntegerValue(node, XML_ATTR_WIDTH);
	if (iValue != null) {
	    size = new Size();
	    size.setWidth(iValue);
	}
	
	// height
	iValue = getIntegerValue(node, XML_ATTR_HEIGHT);
	if (iValue != null) {
	    if (size == null) {
		size = new Size();
	    }
	    size.setHeight(iValue);
	}
	
	return size;

    }
    
  
 

    /**
     * Get margin by element attributes
     * @param node
     * @return
     */
    protected Margin getMarginByAttributes(Element node) {
	if(node == null) {
	    return null;
	}
	Margin margin = new Margin();
	Integer iValue = null;

	// margin
	iValue = getIntegerValue(node, XML_ATTR_MARGIN);
	if (iValue != null) {
	    margin.setValue(iValue);
	}
	
	 // left
	iValue = getIntegerValue(node, XML_ATTR_MARGIN_LEFT);
	if (iValue != null) {
	    margin.setLeft(iValue);
	}
	
	// top
	iValue = getIntegerValue(node, XML_ATTR_MARGIN_TOP);
	if (iValue != null) {
	    margin.setTop(iValue);
	}
	
	// right
	iValue = getIntegerValue(node, XML_ATTR_MARGIN_RIGHT);
	if (iValue != null) {
	    margin.setRight(iValue);
	}
	
	// bottom
	iValue = getIntegerValue(node, XML_ATTR_MARGIN_BOTTOM);
	if (iValue != null) {
	    margin.setBottom(iValue);
	}
	    
	return margin.isEmpty() ? null : margin;
    }
    
    protected Margin getMarginByNode(Element node) {
	if(node == null) {
	    return null;
	}
	Margin margin = new Margin();
	readInsets(node, margin);
	return margin.isEmpty() ? null : margin;
    }
	
    
    protected Padding getPaddingByAttributes(Element node) {
   	if(node == null) {
   	    return null;
   	}
   	Padding padding = new Padding();
   	Integer iValue = null;

   	// margin
   	iValue = getIntegerValue(node, XML_ATTR_PADDING);
   	if (iValue != null) {
   	    padding.setValue(iValue);
   	}
   	
   	 // left
   	iValue = getIntegerValue(node, XML_ATTR_PADDING_LEFT);
   	if (iValue != null) {
   	    padding.setLeft(iValue);
   	}
   	
   	// top
   	iValue = getIntegerValue(node, XML_ATTR_PADDING_TOP);
   	if (iValue != null) {
   	    padding.setTop(iValue);
   	}
   	
   	// right
   	iValue = getIntegerValue(node, XML_ATTR_PADDING_RIGHT);
   	if (iValue != null) {
   	    padding.setRight(iValue);
   	}
   	
   	// bottom
   	iValue = getIntegerValue(node, XML_ATTR_PADDING_BOTTOM);
   	if (iValue != null) {
   	    padding.setBottom(iValue);
   	}
   	    
   	return padding.isEmpty() ? null : padding;
       }    

    protected Padding getPaddingByNode(Element node) {
	if(node == null) {
	    return null;
	}
	Padding padding = new Padding();
	readInsets(node, padding);
	return padding.isEmpty() ? null : padding;
    }
    
    protected Pen getBorderPenByAttributes(Element node, String borderAttribute) {
	if (node == null) {
	    return null;
	}
	if (borderAttribute == null) {
	    borderAttribute = XML_ATTR_BORDER;
	}
	return getPenByAttributes(node, borderAttribute);
    }

    protected Pen getPenByAttributes(Element node, String penAttribute) {
	if (node == null) {
	    return null;
	}
	String value = getStringValue(node, penAttribute);
	if (isNone(value)) {
	    return Pen.NONE;
	}

	// width
	Float width = getFloatValue(value);

	// style
	String style = getStringValue(node, penAttribute + "-style");

	// color
	Color color = getColor(node, penAttribute + "-color");

	if (width == null && style == null && color == null) {
	    return null;
	}
	
	Pen pen = new Pen();
	if (width != null) {
	    pen.setLineWidth(width);
	}

	if (style != null) {
	    byte lineStyle = parseLineStyle(style);
	    pen.setLineStyle(lineStyle);
	}

	if (color != null) {
	    pen.setLineColor(color);
	}

	// Normalize pen
	return pen.isEmpty() ? null : pen;
    }
    
    protected Border getBorderByAttributes(Element node) {
	return getBorderByAttributes(node, null);
    }
    
    protected Border getBorderByAttributes(Element node, String prefixAttribute) {
	prefixAttribute = prefixAttribute == null ? "" : (prefixAttribute  + "-");

	Pen pen = null;
	Pen leftPen = null;
	Pen topPen = null;
	Pen rightPen = null;
	Pen bottomPen = null;

	// general
	pen = getBorderPenByAttributes(node, prefixAttribute + XML_ATTR_BORDER);
	
	// left
	leftPen = getBorderPenByAttributes(node, prefixAttribute + XML_ATTR_BORDER_LEFT);
	
	// top
	topPen = getBorderPenByAttributes(node, prefixAttribute + XML_ATTR_BORDER_TOP);

	// right
	rightPen = getBorderPenByAttributes(node, prefixAttribute + XML_ATTR_BORDER_RIGHT);
	
	// bottom
	bottomPen = getBorderPenByAttributes(node, prefixAttribute + XML_ATTR_BORDER_BOTTOM);
	
	
	if (pen == null && leftPen == null && topPen == null && rightPen == null && bottomPen == null) {
	    return null;
	}

	if (pen == Pen.NONE && leftPen == null && topPen == null && rightPen == null && bottomPen == null) {
	    return Border.NONE;
	}

	if (leftPen == Pen.NONE	&& topPen == Pen.NONE && rightPen == Pen.NONE && bottomPen == Pen.NONE) {
	    return Border.NONE;
	}
	
	if (pen != null) {
	    if (leftPen == null) {
		leftPen = Border.clonePen(pen);
	    }
	    if (topPen == null) {
		topPen = Border.clonePen(pen);
	    }
	    if (rightPen == null) {
		rightPen = Border.clonePen(pen);
	    }
	    if (bottomPen == null) {
		bottomPen = Border.clonePen(pen);
	    }
	}
	
	if (leftPen == Pen.NONE	&& topPen == Pen.NONE && rightPen == Pen.NONE && bottomPen == Pen.NONE) {
	    return Border.NONE;
	}
	
	Border border = new Border();
	border.setLeft(leftPen);
	border.setTop(topPen);
	border.setRight(rightPen);
	border.setBottom(bottomPen);
		
	return border;
    }
    
    protected Border getBorder(Element node) {
	return getBorder(node, null);
    }
    
    protected Border getBorder(Element node, String prefixAttribute) {
	return getBorderByAttributes(node, prefixAttribute);
    }
    
    protected void readInsets(Element node, Insets margin) {
	if(node == null) {
	    return;
	}
	
	Integer iValue = null;

	 // left
	iValue = getIntegerValue(node, XML_ATTR_LEFT);
	if (iValue != null) {
	    margin.setLeft(iValue);
	}
	
	// top
	iValue = getIntegerValue(node, XML_ATTR_TOP);
	if (iValue != null) {
	    margin.setTop(iValue);
	}
	
	// right
	iValue = getIntegerValue(node, XML_ATTR_RIGHT);
	if (iValue != null) {
	    margin.setRight(iValue);
	}
	
	// bottom
	iValue = getIntegerValue(node, XML_ATTR_BOTTOM);
	if (iValue != null) {
	    margin.setBottom(iValue);
	}
	    
    }
    
    
    /**
     * Get margin by 'margin' node
     * @param node
     * @return
     */
    protected Margin getMargin(Element node) {
	if(node == null) {
	    return null;
	}
	// Get margin by attributes
	Margin margin = getMarginByAttributes(node);
	
	// Get margin by node
	Margin marginByNode = getMarginByNode(node.getChild(XML_MARGIN));
	if (marginByNode == null) {
	    return margin;
	}
	if (margin == null){
	    return marginByNode;
	}
	margin.merge(marginByNode);
	return margin.isEmpty() ? null : margin;
    }

    protected Padding getPadding(Element node) {
	if(node == null) {
	    return null;
	}
	// Get padding by attributes
	Padding padding = getPaddingByAttributes(node);
	
	// Get padding by node
	Padding paddingByNode = getPaddingByNode(node.getChild(XML_PADDING));
	if (paddingByNode == null) {
	    return padding;
	}
	if (padding == null){
	    return paddingByNode;
	}
	padding.merge(paddingByNode);
	return padding.isEmpty() ? null : padding;
    }
    
    ////
    
    
    protected DSExpression getExpression(Element node) {
   	return getExpression(node, true);
    }

    protected DSExpression getExpression(Element node, boolean isUseDataType) {
   	if (node == null) {
   	    return null;
   	}
   	DSExpression expression = new DSExpression();
   	String sValue = getContentValue(node);
   	if (sValue != null) {
   	    expression.setText(sValue);
   	}
   	if (!isUseDataType) {
   	    return expression;
   	}
   	sValue = getStringValue(node, XML_ATTR_DATA_TYPE);
   	if (sValue != null) {
   	    expression.setDataType(sValue);
   	}
   	return expression;
    }
    
    ////
    
    protected void readIdentifier(Element node, LocalizedIdentifier identifier) {

	String sValue = null;

   	// id
   	sValue = getStringValue(node, XML_ATTR_ID);
   	if (sValue != null) {
   	    identifier.setId(sValue);
   	}
	
   	// name
   	sValue = getStringValue(node, XML_ATTR_NAME);
   	if (sValue != null) {
   	    identifier.setName(sValue);
   	}
   	
   	// caption
   	sValue = getStringValue(node, XML_ATTR_CAPTION);
   	if (sValue != null) {
   	    identifier.setCaption(sValue);
   	}
   	
   	// description
   	sValue = getStringValue(node, XML_ATTR_DESCRIPTION);
   	if (sValue != null) {
   	    identifier.setDescription(sValue);
   	}
   	
    }
       
}
