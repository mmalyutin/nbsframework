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

import java.io.Serializable;

/**
 * The image entry describes image.
 * 
 * @author ohapon
 * 
 */

public interface ImageEntry extends Serializable {
    
    
    Integer getId();

    void setId(Integer id);
    

    /**
     * Return name of image (it is not file name)
     * @return
     */
    String getName();

    /**
     * Set name of image
     * @param name
     */
    void setName(String name);

    /**
     * Return file name
     * @return
     */
    String getFileName();

    /**
     * Set file name
     * @param fileName
     */
    void setFileName(String fileName);

    /**
     * Return absolute file name
     * @return
     */
    String getAbsoluteFileName();

    /**
     * Set absolute file name
     * @param absoluteFileName
     */
    void setAbsoluteFileName(String absoluteFileName);

    
    /**
     * Return true if image is dirty
     * @return
     */
    boolean isImageDirty();

    /**
     * Set dirty flag of entry
     * @param dirty
     */
    void setImageDirty(boolean dirty);
    

    /**
     * Return binary data of image
     * @return
     */
    byte[] getBinaryData();

    /**
     * Set binary data of image
     * @param binaryData
     */
    void setBinaryData(byte[] binaryData);

    /**
     * Return string representation of image size
     * @return
     */
    String getSizeString();
    
    /**
     * Return site of image
     * @return
     */
    int getSize();

    /**
     * Set size of image
     * @param size
     */
    void setSize(int size);
    
    /**
     * Return true if image was loaded.
     * @return
     */
    boolean isLoadData();    
    
    
    String getFileExt();

    
    void setFileExt(String fileExt);

}
