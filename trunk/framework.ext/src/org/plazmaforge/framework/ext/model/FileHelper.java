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

import java.util.List;

import org.plazmaforge.framework.config.object.IEntityConfig;
import org.plazmaforge.framework.core.exception.ApplicationException;

/**
 * 
 * @author ohapon
 *
 */
public class FileHelper {

    public static interface FileManager {
	
	List<File> doFindByOwner(IEntityConfig entity, Integer ownerId) throws ApplicationException;
	
	void doInsertFile(File file) throws ApplicationException;
	    
	void doUpdateFile(File file) throws ApplicationException;
	    
	void doDeleteFile(File file) throws ApplicationException;
   }

   private FileManager fileManager;

   public FileHelper(FileManager fileManager) {
	super();
	this.fileManager = fileManager;
   }

    public void loadFiles(IFileHolder holder) throws ApplicationException {
	if (holder == null) {
	    return;
	}
	IEntityConfig entity = holder.getFileEntity();
	Integer ownerId = holder.getFileOwnerId();
	if (entity == null || ownerId == null) {
	    return;
	}
	List<File> files = fileManager.doFindByOwner(entity, ownerId);
	holder.setFiles(files);
    }
    
    public List<File> getFiles(IFileHolder holder) throws ApplicationException {
	if (holder == null) {
	    return null;
	}
	IEntityConfig entity = holder.getFileEntity();
	Integer ownerId = holder.getFileOwnerId();
	if (entity == null || ownerId == null) {
	    return null;
	}
	List<File> files = fileManager.doFindByOwner(entity, ownerId);
	return files;
    }
    
    public void saveFiles(IFileHolder holder) throws ApplicationException {
	if (holder == null) {
	    return;
	}
	List<File> files = holder.getFiles();
	for (File file : files) {
	    Integer id = file.getId();
	    if (id == null) {
		if (file.isCreated()) {
		    file.setOwnerId(holder.getFileOwnerId());
		    file.setEntity(holder.getFileEntity());
		    fileManager.doInsertFile(file);
		}
	    } else {
		if (file.isDeleted()) {
		    fileManager.doDeleteFile(file);
		} else {
		    fileManager.doUpdateFile(file);
		}
	    }
	}
	
	List<File> dbFiles = getFiles(holder);
	if (dbFiles == null) {
	    return;
	}
	for (File dbFile : dbFiles) {
	    Integer id = dbFile.getId();
	    boolean found = false;
	    for (File file : files) {
		if (id.equals(file.getId())) {
		    found = true;
		}
	    }
	    if (!found) {
		fileManager.doDeleteFile(dbFile);
	    }
	}
	
    }

    public void deleteFiles(IFileHolder holder) throws ApplicationException {
	if (holder == null) {
	    return;
	}
	List<File> files = getFiles(holder);
	if (files == null || files.size() == 0) {
	    return;
	}
	for (File file : files) {
	    fileManager.doDeleteFile(file);
	}
    }

  

}
