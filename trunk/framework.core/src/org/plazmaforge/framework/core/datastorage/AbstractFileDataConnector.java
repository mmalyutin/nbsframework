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
package org.plazmaforge.framework.core.datastorage;

/**
 * @author ohapon
 *
 */
public abstract class AbstractFileDataConnector extends AbstractDataConnector {

    public static final String PROPERTY_FOLDER = "folder";
    
    public static final String PROPERTY_FILE = "file";

    public static final String PROPERTY_ENCODING = "encoding";
    
    private String folder;
    
    private String file;

    private String encoding;
    

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result
		+ ((encoding == null) ? 0 : encoding.hashCode());
	result = prime * result + ((file == null) ? 0 : file.hashCode());
	result = prime * result + ((folder == null) ? 0 : folder.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	AbstractFileDataConnector other = (AbstractFileDataConnector) obj;
	if (encoding == null) {
	    if (other.encoding != null)
		return false;
	} else if (!encoding.equals(other.encoding))
	    return false;
	if (file == null) {
	    if (other.file != null)
		return false;
	} else if (!file.equals(other.file))
	    return false;
	if (folder == null) {
	    if (other.folder != null)
		return false;
	} else if (!folder.equals(other.folder))
	    return false;
	return true;
    }

  
    
}
