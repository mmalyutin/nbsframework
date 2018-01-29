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
package org.plazmaforge.framework.core.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import org.plazmaforge.framework.core.data.object.IData;

/**
 * 
 * @author ohapon
 *
 */
public class DataWriter {

    public static final String CR = "\n";

    public static final String BS = " ";

    private int level;

    private boolean pretty = true;

    private boolean bracketsProperty = true;

    private boolean bracketsString = true;

    private List<String> includeProperties;
    
    private List<String> excludeProperties;
    
    public DataWriter() {
    }

    public boolean isPretty() {
        return pretty;
    }

    public void setPretty(boolean pretty) {
        this.pretty = pretty;
    }

    public boolean isBracketsProperty() {
        return bracketsProperty;
    }

    public void setBracketsProperty(boolean bracketsProperty) {
        this.bracketsProperty = bracketsProperty;
    }

    public boolean isBracketsString() {
        return bracketsString;
    }

    public void setBracketsString(boolean bracketsString) {
        this.bracketsString = bracketsString;
    }

    public List<String> getIncludeProperties() {
        return includeProperties;
    }

    public void addIncludeProperty(String includeProperty) {
	if (includeProperties == null) {
	    includeProperties = new ArrayList<>();
	}
	includeProperties.add(includeProperty);
    }
    
    public void setIncludeProperties(List<String> includeProperties) {
	if (includeProperties == null) {
	    resetIncludeProperties();
	    return;
	}
        this.includeProperties = new ArrayList<>(includeProperties);
    }

    public List<String> getExcludeProperties() {
        return excludeProperties;
    }

    public void addExcludeProperty(String excludeProperty) {
	if (excludeProperties == null) {
	    excludeProperties = new ArrayList<>();
	}
	excludeProperties.add(excludeProperty);
    }
    
    public void setExcludeProperties(List<String> excludeProperties) {
	if (excludeProperties == null) {
	    resetExcludeProperties();
	    return;
	}	
        this.excludeProperties = new ArrayList<>(excludeProperties);
    }

  
    
    public void write(IData data, Appendable buf)  throws IOException {
        reset();
        writeData(data, buf);
    }

    protected void reset() {
        level = 0;
    }

    protected void writeData(IData data, Appendable buf)  throws IOException {
        if (data == null) {
            return;
        }
        //level++;
        writeStartObject(buf);
        writeProperties(data, buf);
        writeEndObject(buf);
        //level--;
    }

    protected List<String> filteredProperties(IData data) {
	if (data == null) {
	    return null;
	}
	List<String> inputProperties = data.getPropertyNames();
	if (isEmpty(inputProperties)) {
	    return null;
	}
	boolean isEmptyIncludes = isEmpty(includeProperties);
	boolean isEmptyExcludes = isEmpty(excludeProperties);
	
	if (isEmptyIncludes && isEmptyExcludes) {
	    return new ArrayList<>(inputProperties);
	}
	List<String> outputProperties = new ArrayList<>();
	for (String property : inputProperties) {
	    
	    if (!isEmptyIncludes) {
		
		// found
		if (includeProperties.contains(property)) {
		    outputProperties.add(property);
		    continue;
		}
	    }
	    
	    if (!isEmptyExcludes) {
		
		// not found
		if (!excludeProperties.contains(property)) {
		    outputProperties.add(property);
		    continue;
		}
	    }	    
	}
	if (isEmpty(outputProperties)) {
	    return null;
	}
	return outputProperties;
    }
    
    protected void writeProperties(IData data, Appendable buf)  throws IOException {
        if (data == null) {
            return;
        }
        List<String> propertyNames = filteredProperties(data);
        if (isEmpty(propertyNames)) {
           return;
        }

        level++;
        Object propertyValue = null;
        int count = 0;
        for (String propertyName : propertyNames) {
            if  (count > 0) {
                writeRaw(",", buf);
                if (!isPretty()) {
                    writeRaw(BS, buf);
                }
            }
            propertyValue = data.get(propertyName);
            writeProperty(data, propertyName, propertyValue, buf);
            count++;
        }
        level--;
    }

    protected void writeArrayElements(Object[] value, Appendable buf)  throws IOException {
        if (value == null || value.length == 0) {
            return;
        }

        level++;
        int count = 0;
        for (int i = 0 ;i < value.length; i++) {
           if  (count > 0) {
                writeRaw(",", buf);
                if (!isPretty()) {
                    writeRaw(BS, buf);
                }
            }
            writeStartPrettyLine(buf);
            writeValue(value[i],buf);
            count++;
        }
        level--;
    }

    protected void writeCollectionElements(Collection<?> value, Appendable buf)  throws IOException {
        if (value == null || value.isEmpty()) {
            return;
        }
        writeArrayElements(value.toArray(), buf);
    }
    
    protected void writeProperty(IData data, String propertyName, Object propertyValue, Appendable buf)  throws IOException {
        if (data == null) {
            return;
        }
        if (propertyName == null) {
            propertyName = "UNKNOWN_PROPERTY";
        }
        writeStartPrettyLine(buf);
        writePropertyName(propertyName, buf);
        writePropertyValue(propertyValue, buf);
    }

    protected void writePropertyName(String propertyName, Appendable buf)  throws IOException {
        if (isBracketsProperty()) {
            propertyName = toBracketsString(propertyName);
        }
        writeRaw( propertyName, buf);
        writeRaw(": ", buf);
    }

    protected void writePropertyValue(Object propertyValue, Appendable buf)  throws IOException {
        writeValue(propertyValue, buf);
    }

    protected void writeValue(Object value, Appendable buf)  throws IOException {
        if (value == null) {
            writeNullValue(buf);
            return;
        }
        if (isComplexValue(value)) {
            writeComplexValue(value, buf);
            return;
        }
        if (value instanceof String) {
            writeStringValue((String) value, buf);
            return;
        }
        String formatValue = format(value);
        writeRaw(formatValue, buf);
    }

    protected void writeNullValue(Appendable buf)  throws IOException {
        writeRaw("null", buf);
    }

    protected void writeStartObject(Appendable buf)  throws IOException {
        //writeStartPrettyLine("{", buf);
        writeRaw("{", buf);
    }

    protected void writeEndObject(Appendable buf)  throws IOException {
        writeStartPrettyLine("}", buf);
    }



    protected void writeStartArray(Appendable buf)  throws IOException {
        writeRaw("[", buf);
    }

    protected void writeEndArray(Appendable buf)  throws IOException {
        writeStartPrettyLine("]", buf);
    }


    protected void writeStartPrettyLine(Appendable buf)  throws IOException {
        writeStartPrettyLine(null, buf);
    }

    protected void writeStartPrettyLine(String value, Appendable buf)  throws IOException {
        if (isPretty()) {
            writeStartLine(level, buf);
            //writeLevelSpace(level, buf);
        }
        writeRaw(value, buf);
    }

    protected void writeStartLine(String value, Appendable buf)  throws IOException {
        writeStartLine(level, buf);
        writeRaw(value, buf);
    }

    protected void writeStartLine(int level, Appendable buf)  throws IOException {
        writeRaw(CR, buf);
        writeLevelSpace(level, buf);
    }

    protected void writeRaw(String value, Appendable buf)  throws IOException {
        if (value == null) {
            return;
        }
        buf.append(value);
    }

    protected void writeLevelSpace(int level, Appendable buf)  throws IOException {
        if (level < 1) {
            return;
        }
        for (int i = 1; i <= level; i++) {
            writeRaw(BS, buf);
        }
    }

    protected String format(Object value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    protected void writeStringValue(String value, Appendable buf)  throws IOException {
        if (value == null) {
            return;
        }
        if (isBracketsString()) {
            value = toBracketsString(value);
        }
        writeRaw(value, buf);
    }

    protected boolean isComplexValue(Object value) {
        if (value instanceof IData) {
            return true;
        }
        if (value.getClass().isArray()) {
            return true;
        }
        if (value instanceof Collection) {
            return true;
        }
        return false;
    }

    protected void writeComplexValue(Object value, Appendable buf)  throws IOException {
        if (value instanceof IData) {
            writeData((IData) value, buf);
            return;
        }
        if (value.getClass().isArray()) {
            writeArray((Object[]) value, buf);
            return;
        }
        if (value instanceof Collection) {
            writeColleaction((Collection<?>) value, buf);
            return;
        }
    }

    protected void writeArray(Object[] value, Appendable buf)  throws IOException {
        writeStartArray(buf);
        writeArrayElements(value, buf);
        writeEndArray(buf);
    }

    protected void writeColleaction(Collection<?> value, Appendable buf)  throws IOException {
        writeStartArray(buf);
        writeCollectionElements(value, buf);
        writeEndArray(buf);
    }
    
    protected String toBracketsString(String value) {
        if (value == null) {
            return null;
        }
        return "\"" + value  + "\"";
    }
    
    protected void resetIncludeProperties() {
	clear(includeProperties);
	includeProperties = null;
    }
    
    protected void resetExcludeProperties() {
 	clear(excludeProperties);
 	excludeProperties = null;
    }
    
    protected void clear(Collection<?> collection) {
	if (collection == null) {
	    return;
	}
	collection.clear();
    }   
    
    protected boolean isEmpty(Collection<?> collection) {
   	return collection == null || collection.isEmpty();
    }
    
}
