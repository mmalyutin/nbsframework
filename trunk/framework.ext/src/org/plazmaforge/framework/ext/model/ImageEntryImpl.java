/*
 * Copyright (C) 2005-2010 Oleh Hapon ohapon@users.sourceforge.net
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

package org.plazmaforge.framework.ext.model;


/**
 * 
 * @author ohapon
 *
 */
public class ImageEntryImpl implements ImageEntry {


    private static final long serialVersionUID = 2730484118992021741L;


    private Integer id;
    
    private String name;
    
    private String fileName;
    
    private String absoluteFileName;
    
    private String fileExt;
    
    private int size;
    
    private String sizeString;
    
    private boolean imageDirty;
    
    private byte[] binaryData;
    
    private boolean loadData;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAbsoluteFileName() {
        return absoluteFileName;
    }

    public void setAbsoluteFileName(String absoluteFileName) {
        this.absoluteFileName = absoluteFileName;
    }

    public boolean isImageDirty() {
        return imageDirty;
    }

    public void setImageDirty(boolean dirty) {
        this.imageDirty = dirty;
    }

    public byte[] getBinaryData() {
        return binaryData;
    }

    public void setBinaryData(byte[] binaryData) {
        this.binaryData = binaryData;
        setSize((binaryData == null) ? 0 : binaryData.length);
        loadData = true;
    }
    
    public String getSizeString() {
	if (sizeString == null) {
	    if (size < 1024) {
		sizeString = size + " bytes"; 
	    } else {
		//sizeString = NUMBER_FORMAT.format((double) size / 1024) + " KB";
		sizeString = ((double) size / 1024) + " KB"; // TODO: Must format file size
	    }
	    
	}
	return sizeString;
    }
    
    public String toString() {
	return name == null ? "" : name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        this.sizeString = null; // reset
    }

    public boolean isLoadData() {
        return loadData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    
    
}
