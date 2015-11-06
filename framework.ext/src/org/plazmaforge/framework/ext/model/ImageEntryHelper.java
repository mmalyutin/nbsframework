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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.plazmaforge.framework.core.exception.ApplicationException;
import org.plazmaforge.framework.util.StringUtils;

/**
 * 
 * @author ohapon
 *
 */
public class ImageEntryHelper {


    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    
    /**
     * Return holder of entity
     * @param entity
     * @return
     */
    public static ImageEntryHolder getImageEntryHolder(Object entity) {
	if (entity instanceof ImageEntryHolder) {
	    return (ImageEntryHolder) entity;
	}
	return null;
    }

    /**
     * Return entry of entity
     * @param entity
     * @return
     */
    public static ImageEntry getImageEntry(Object entity) {
	ImageEntryHolder holder = getImageEntryHolder(entity);
	if (holder == null) {
	    return null;
	}
	return holder.getImageEntry();
    }

    
    
    public static byte[] loadBinaryDataFromFS(String filePath, String imageTable, String imageField, String fileExt, Integer id) throws FileNotFoundException, IOException {
	String fileName = getImageFileName(filePath, imageTable, imageField, fileExt, id);
	return loadBinaryDataFromFS(fileName);
    }
       
    public static byte[] loadBinaryDataFromFS(String fileName, boolean checkFile) throws FileNotFoundException, IOException {
	if (checkFile) {
	    File file = new File(fileName);
	    if (!file.exists()) {
		return null;
	    }
	}
	FileInputStream fis = null;
	try {
	    fis = new FileInputStream(fileName);
	    byte[] data = getBytes(fis);
	    return data;
	} finally {
	    if (fis != null) {
		fis.close();
	    }
	}
    }
       
    public static byte[] loadBinaryDataFromFS(String fileName) throws FileNotFoundException, IOException {
	return loadBinaryDataFromFS(fileName, true);
    }

    public static String getImageFileName(String storagePath, String imageTable, String imageField, String fileExt, Integer id) {
	return getImageFilePath(storagePath, imageTable) + FILE_SEPARATOR + getImageFileName(imageField, fileExt, id);
    }

    public static String getImageFilePath(String storagePath, String imageTable) {
	return storagePath + FILE_SEPARATOR + imageTable.toLowerCase();
    }

    public static String getImageFileName(String imageField, String fileExt, Integer id) {
	return imageField + id + "." + fileExt;
    }   

    public static String getEntityImageFileName(String imageField, ImageEntry imageEntry, Integer id) {
	String fileExt = "dat";
	if (imageEntry != null && !isEmpty(imageEntry.getFileExt())) {
	    fileExt = imageEntry.getFileExt().trim().toLowerCase();
	}
	return getImageFileName(imageField, fileExt, id);
    }           
    
    /**
     * Return byte array from InputStream
     * @param is
     * @return
     * @throws IOException
     */
    public static byte[] getBytes(InputStream is) throws IOException {
	byte[] data = new byte[is.available()];
	is.read(data);
	return data;
    }    
    
    
    private static boolean isEmpty(String str) {
	return StringUtils.isEmpty(str);
    }
    
    
    public static String getAbsoluteFileName(String filePath, String fileName) {
	return filePath + FILE_SEPARATOR + fileName;
    }

    public static void deleteFile(String filePath, String fileName) {
	deleteFile(getAbsoluteFileName(filePath, fileName));
    }

    public static void deleteFile(String absoluetFileName) {
	File file = new File(absoluetFileName);
	if (!file.exists()) {
	    return;
	}
	boolean f = file.delete();
	System.out.print("File: [" + absoluetFileName + "] delete: [" + f + "]");
    }
    
    ////
    

    public static byte[] loadBinaryDataFromFS(String filePath, String fileName) throws FileNotFoundException, IOException {
	return ImageEntryHelper.loadBinaryDataFromFS(filePath + FILE_SEPARATOR +  fileName);
   }


    public static void storeBinaryDataToFS(String filePath, String imageTable, String imageField, String fileExt, Integer id, byte[] data) throws IOException {
	String fileName = ImageEntryHelper.getImageFileName(filePath, imageTable, imageField, fileExt, id);
	storeBinaryDataToFS(fileName, data);
    }
    
    public static void storeBinaryDataToFS(String filePath, String fileName, byte[] data) throws FileNotFoundException, IOException {
	String absoluteFileName = fileName;
	if (!isEmpty(filePath)) {
	    File folder = new File(filePath);
	    if (!folder.exists()) {
		folder.mkdir();
	    }
	    absoluteFileName = filePath + FILE_SEPARATOR + fileName;
	}
	storeBinaryDataToFS(absoluteFileName, data);
    }
    
    
    public static void storeBinaryDataToFS(String fileName, byte[] data) throws FileNotFoundException, IOException {
	FileOutputStream fos = null;
	try{
	    fos = new FileOutputStream(fileName);
	    fos.write(data);
	    fos.flush();
	} finally {
	    if (fos != null) {
		fos.close();
	    }
	}
   }
    
    ////
    
    
    public static void loadEntityImageFS(Object entity, String storagePath, String imageTable) throws ApplicationException {
	
	////
	ImageEntryHolder holder = getImageEntryHolder(entity);
	if (holder == null) {
	    return;
	}
	ImageEntry imageEntry = holder.getImageEntry();
	if (imageEntry == null) {
	    return;
	}
	////
	
	String fileName = imageEntry.getFileName();
	if (isEmpty(fileName)) {
	    return;
	}
	try {
	    String filePath = getImageFilePath(storagePath, imageTable);
	    byte[] data = loadBinaryDataFromFS(filePath, fileName);
	    imageEntry.setBinaryData(data);
	} catch (IOException e) {
	    throw new ApplicationException(e);
	}

    }
    
    
    public static void deleteEntityImageFS(Object entity, String storagePath, String imageTable) throws ApplicationException {

	////
	ImageEntryHolder holder = getImageEntryHolder(entity);
	if (holder == null) {
	    return;
	}
	ImageEntry imageEntry = holder.getImageEntry();
	if (imageEntry == null) {
	    return;
	}
	////

	String filePath = getImageFilePath(storagePath, imageTable);
	String fileName = imageEntry.getFileName();
	deleteFile(filePath, fileName);

    }
    
    public static void insertEntityImageFS(Object entity, String storagePath, String imageTable, String imageField) throws ApplicationException {
	
	ImageEntryHolder holder = getImageEntryHolder(entity);
	if (holder == null) {
	    return;
	}
	ImageEntry imageEntry = holder.getImageEntry();
	if (imageEntry == null) {
	    return;
	}
	byte[] data = imageEntry.getBinaryData();
	if (data == null || data.length == 0) {
	    return;
	}
	////
	
	//
	String fileName = getEntityImageFileName(imageField, imageEntry, holder.getId()); // generate new file name
	imageEntry.setFileName(fileName); // May be execute store
	
	String filePath = getImageFilePath(storagePath, imageTable);
	//
	
	try {
	    storeBinaryDataToFS(filePath, fileName, data);
	} catch (IOException e) {
	    throw new ApplicationException(e);
	}
	
    }

    
    
    public static void updateEntityImageFS(Object entity, String storagePath, String imageTable, String imageField) throws ApplicationException {
	
	////
	ImageEntryHolder holder = getImageEntryHolder(entity);
	if (holder == null) {
	    return;
	}
	ImageEntry imageEntry = holder.getImageEntry();
	if (imageEntry == null) {
	    return;
	}
	////
	
	byte[] data = imageEntry.getBinaryData();
	String filePath = getImageFilePath(storagePath, imageTable);
	String fileName = imageEntry.getFileName();
	boolean isEmptyData = (data == null || data.length == 0);
	
	
	if (isEmpty(fileName)) {
	    if (isEmptyData) {
		return;
	    }
	    fileName = getEntityImageFileName(imageField, imageEntry, holder.getId()); // generate new file name
	    imageEntry.setFileName(fileName); // May be execute store
	} else {
	    if (isEmptyData) {
		imageEntry.setFileName(null); // Reset file name. // May be execute store
	    }
	}

	
	if (!isEmpty(fileName) && isEmptyData) {
	    deleteFile(filePath, fileName);
	    return;
	}
	
	
	try {
	    storeBinaryDataToFS(filePath, fileName, data);
	} catch (IOException e) {
	    throw new ApplicationException(e);
	}
    }    

}
