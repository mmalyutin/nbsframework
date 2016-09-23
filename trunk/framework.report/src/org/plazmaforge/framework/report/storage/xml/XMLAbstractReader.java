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
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.plazmaforge.framework.core.data.LocalizedIdentifier;
import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.model.base.Insets;
import org.plazmaforge.framework.report.model.base.Margin;
import org.plazmaforge.framework.report.model.base.Size;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;

/**
 * @author ohapon
 *
 */
public class XMLAbstractReader extends XMLWorker implements XMLInfo  {

    
    
    protected Document readXMLDocument(String fileName) throws RTException {
	if (fileName == null) {
	    throw new RTException("Can't read report. File name is null.");
	}
	fileName = normalizeString(fileName);
	if (fileName == null) {
	    throw new RTException("Can't read report. File name is empty.");
	}
	return readXMLDocument(new File(fileName));
    }

    protected Document readXMLDocument(File file) throws RTException {
	if (file == null) {
	    throw new RTException("Can't read report. File is null.");
	}
	try {
	    return readXMLDocument(new FileInputStream(file));
	} catch (FileNotFoundException ex) {
	    throw new RTException(ex);
	}
    }

    protected Document readXMLDocument(InputStream is) throws RTException {
	if (is == null) {
	    throw new RTException("Can't read report. InputStream is null.");
	}
	try {
	    SAXBuilder builder = new SAXBuilder();
	    // builder.setValidation(false);
	    return builder.build(is);
	} catch (Exception ex) {
	    throw new RTException(ex);
	}
    }
    
    ////
    
    protected void readElementAttributes(Element xmlElement, org.plazmaforge.framework.report.model.base.Element element)  {
  	String sValue = null;
  	Integer iValue = null;

  	// x
  	iValue = getIntegerValue(xmlElement, XML_ATTR_X);
  	if (iValue != null) {
  	    element.setX(iValue);
  	}

  	// y
  	iValue = getIntegerValue(xmlElement, XML_ATTR_Y);
  	if (iValue != null) {
  	    element.setY(iValue);
  	}
  	
  	// width
  	iValue = getIntegerValue(xmlElement, XML_ATTR_WIDTH);
  	if (iValue != null) {
  	    element.setWidth(iValue);
  	}
  	
  	// height
  	iValue = getIntegerValue(xmlElement, XML_ATTR_HEIGHT);
  	if (iValue != null) {
  	    element.setHeight(iValue);
  	}

  	// background
  	Color background = getColor(xmlElement, XML_ATTR_BACKGROUND);
  	if  (background != null) {
  	    element.setBackground(background);
  	}
  	    
  	// foreground
  	Color foreground = getColor(xmlElement, XML_ATTR_FOREGROUND);
  	if (foreground != null) {
  	    element.setForeground(foreground);
  	}

  	// font
  	Font font = getFont(xmlElement, XML_ATTR_FONT);
  	if (font != null) {
  	    element.setFont(font);
  	}
  	
    }
    
   //

    protected Element getChild(Element parent, String name) {
	return parent.getChild(name, parent.getNamespace());	
    }

    protected List getChildren(Element parent, String name) {
	return parent.getChildren(name, parent.getNamespace());	
    }

    protected boolean useNamespace() {
	return true;
    }

    ////
    
    /**
     * Get Size by 'width, 'height' attributes
     * @param element
     * @return
     */
    protected Size getSizeByAttributes(Element element) {
	if(element == null) {
	    return null;
	}
	
	Size size = null;
	
	// width
	Integer iValue = getIntegerValue(element, XML_ATTR_WIDTH);
	if (iValue != null) {
	    size = new Size();
	    size.setWidth(iValue);
	}
	
	// height
	iValue = getIntegerValue(element, XML_ATTR_HEIGHT);
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
     * @param element
     * @return
     */
    protected Margin getMarginByAttributes(Element element) {
	if(element == null) {
	    return null;
	}
	Margin margin = new Margin();
	Integer iValue = null;

	// margin
	iValue = getIntegerValue(element, XML_ATTR_MARGIN);
	if (iValue != null) {
	    margin.setValue(iValue);
	}
	
	 // left
	iValue = getIntegerValue(element, XML_ATTR_MARGIN_LEFT);
	if (iValue != null) {
	    margin.setLeft(iValue);
	}
	
	// top
	iValue = getIntegerValue(element, XML_ATTR_MARGIN_TOP);
	if (iValue != null) {
	    margin.setTop(iValue);
	}
	
	// right
	iValue = getIntegerValue(element, XML_ATTR_MARGIN_RIGHT);
	if (iValue != null) {
	    margin.setRight(iValue);
	}
	
	// bottom
	iValue = getIntegerValue(element, XML_ATTR_MARGIN_BOTTOM);
	if (iValue != null) {
	    margin.setBottom(iValue);
	}
	    
	return margin.isEmpty() ? null : margin;
    }
    
    protected Margin getMarginByNode(Element element) {
	if(element == null) {
	    return null;
	}
	Margin margin = new Margin();
	readInsets(element, margin);
	return margin.isEmpty() ? null : margin;
    }
	
    
    protected void readInsets(Element element, Insets margin) {
	if(element == null) {
	    return;
	}
	
	Integer iValue = null;

	 // left
	iValue = getIntegerValue(element, XML_ATTR_LEFT);
	if (iValue != null) {
	    margin.setLeft(iValue);
	}
	
	// top
	iValue = getIntegerValue(element, XML_ATTR_TOP);
	if (iValue != null) {
	    margin.setTop(iValue);
	}
	
	// right
	iValue = getIntegerValue(element, XML_ATTR_RIGHT);
	if (iValue != null) {
	    margin.setRight(iValue);
	}
	
	// bottom
	iValue = getIntegerValue(element, XML_ATTR_BOTTOM);
	if (iValue != null) {
	    margin.setBottom(iValue);
	}
	    
    }
    
    
    /**
     * Get margin by 'margin' node
     * @param element
     * @return
     */
    protected Margin getMargin(Element element) {
	if(element == null) {
	    return null;
	}
	// Get margin by attributes
	Margin margin = getMarginByAttributes(element);
	
	// Get margin by node
	Margin marginByNode = getMarginByNode(element.getChild(XML_MARGIN));
	if (marginByNode == null) {
	    return margin;
	}
	if (margin == null){
	    return marginByNode;
	}
	margin.merge(marginByNode);
	return margin.isEmpty() ? null : margin;
    }

    ////
    
    
    protected DSExpression getExpression(Element element) {
   	return getExpression(element, true);
    }

    protected DSExpression getExpression(Element element, boolean isUseDataType) {
   	if (element == null) {
   	    return null;
   	}
   	DSExpression expression = new DSExpression();
   	String sValue = getContentValue(element);
   	if (sValue != null) {
   	    expression.setText(sValue);
   	}
   	if (!isUseDataType) {
   	    return expression;
   	}
   	sValue = getStringValue(element, XML_ATTR_DATA_TYPE);
   	if (sValue != null) {
   	    expression.setDataType(sValue);
   	}
   	return expression;
    }
    
    ////
    
    protected void readIdentifier(Element element, LocalizedIdentifier identifier) {

	String sValue = null;

   	// id
   	//sValue = getStringValue(element, XML_ATTR_ID);
   	//if (sValue != null) {
   	//    identifier.setId(sValue);
   	//}
	
   	// name
   	sValue = getStringValue(element, XML_ATTR_NAME);
   	if (sValue != null) {
   	    identifier.setName(sValue);
   	}
   	
   	// caption
   	sValue = getStringValue(element, XML_ATTR_CAPTION);
   	if (sValue != null) {
   	    identifier.setCaption(sValue);
   	}
   	
   	// description
   	sValue = getStringValue(element, XML_ATTR_DESCRIPTION);
   	if (sValue != null) {
   	    identifier.setDescription(sValue);
   	}
   	
    }
       
}
