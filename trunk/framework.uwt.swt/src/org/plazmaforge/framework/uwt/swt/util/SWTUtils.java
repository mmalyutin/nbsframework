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

package org.plazmaforge.framework.uwt.swt.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;

public class SWTUtils {

    /**
     * Center <code>Shell</code> in parent
     * @param shell
     */
    public static void centerShell(Shell shell) {

	Rectangle displayArea = null; // area to center in
	try {
	    displayArea = shell.getMonitor().getClientArea();
	} catch (NoSuchMethodError e) {
	    displayArea = shell.getDisplay().getClientArea();
	}

	Rectangle shellRect = shell.getBounds();

	if (shellRect.height > displayArea.height) {
	    shellRect.height = displayArea.height;
	}
	if (shellRect.width > displayArea.width - 50) {
	    shellRect.width = displayArea.width;
	}

	shellRect.x = displayArea.x + (displayArea.width - shellRect.width) / 2;
	shellRect.y = displayArea.y + (displayArea.height - shellRect.height) / 2;

	shell.setBounds(shellRect);

    }
    
    public static Image getImage(InputStream is) {
        Display display = Display.getCurrent();
        ImageData data = new ImageData(is);
        if (data.transparentPixel > 0) {
            return new Image(display, data, data.getTransparencyMask());
        }
        return new Image(display, data);
    }
    
    public static Image getClassImage(String path) {
	if (path != null && path.length() > 0 && path.charAt(0) != '/') {
	    path = "/" + path;
	}
	return getImage(SWTUtils.class, path);
    }
    
    public static Image getFileImage(String path) {
	Image image = null;
	try {
	    FileInputStream fis = new FileInputStream(path);
	    image = getImage(fis);
	    fis.close();
	} catch (Exception e) {
	    image = getMissingImage();
	}
	return image;
    }
    
    public static Image getImage(Class<?> clazz, String path) {
	Image image = null;
	try {
	    if (path.length() > 0 && path.charAt(0) == '/') {
		String newPath = path.substring(1, path.length());
		image = getImage(new BufferedInputStream(clazz.getClassLoader().getResourceAsStream(newPath)));
	    } else {
		image = getImage(clazz.getResourceAsStream(path));
	    }
	} catch (Exception e) {
	    image = getMissingImage();
	}
	return image;
    }
    
    public static Image getMissingImage() {
	//TODO
	return null;
    }
    
    
    /**
     * Update size of <code>CoolItem</code> by size of <code>ToolBar</code>
     * 
     * @param xCoolItem
     * @param xToolBar
     */
    public static void updateSize(CoolItem xCoolItem, ToolBar xToolBar) {
	Point toolBarSize = xToolBar.computeSize(SWT.DEFAULT, SWT.DEFAULT);
	int minHeight = 25; // Fix height
	if (toolBarSize.y < minHeight) {
	    toolBarSize.y = minHeight;
	}
	xToolBar.setSize(toolBarSize);
	Point coolItemSize = xCoolItem.computeSize(toolBarSize.x, toolBarSize.y);
	xCoolItem.setSize(coolItemSize);
    }

    
    /**
     * Return true if <code>TableColumn</code> is sort column of the table
     * @param tableColumn
     * @return
     */
    public static boolean isSortColumn(TableColumn tableColumn) {
	return tableColumn == null ? false : tableColumn.getParent().getSortColumn() == tableColumn; 
    }
    
    
    /**
     * Set sort column in the table by 'asc' flag
     * @param tableColumn
     * @param asc
     */
    public static void setSortColumn(TableColumn tableColumn, boolean asc) {
	setSortColumn(tableColumn, asc ? getAscSortColumnDirection() : getDescSortColumnDirection());
    }
	
    /**
     * Set sort column in the table by direction (UP/DOWN)
     * @param tableColumn
     * @param direction
     */
    public static void setSortColumn(TableColumn tableColumn, int direction) {
	if (tableColumn == null) {
	    return;
	}
	// Fix direction
	if (direction != SWT.UP && direction != SWT.DOWN) {
	    direction = getAscSortColumnDirection(); //TODO: May be SWT.NONE
	}
	Table table = tableColumn.getParent();
	// Set sort column
	table.setSortColumn(tableColumn);
	// Set sort direction
	table.setSortDirection(direction);
    }
    
    public static int getAscSortColumnDirection() {
	return SWT.UP;
    }

    public static int getDescSortColumnDirection() {
	return SWT.DOWN;
    }

    public static int getNoneSortColumnDirection() {
	return SWT.NONE;
    }
    
    /**
     * Inverse sort column
     * @param tableColumn
     */
    public static void inverseSortColumn(TableColumn tableColumn) {
	if (tableColumn == null) {
	    return;
	}
	int direction = getAscSortColumnDirection();
	Table table = tableColumn.getParent();
	if (tableColumn == table.getSortColumn()) {
	    
	    // Inverse: if ASC then DESC 
	    if (table.getSortDirection() == getAscSortColumnDirection()){
		direction = getDescSortColumnDirection();
	    }
	    
	} else {
	    
	    // Set sort column if the table has other sort column
	    table.setSortColumn(tableColumn);
	}
	
	// Set sort direction
	table.setSortDirection(direction);
	
    }
    
    
    public static boolean isAscSortColumn(Table table) {
	if (table == null) {
	    return false;
	}
	return isAscSortColumn(table.getSortDirection());
	
    }
    
    public static boolean isAscSortColumn(int direction) {
	return getDescSortColumnDirection() != direction; // True if DESC or NONE !!!
    }
    
    /**
     * Reset sort column in the table
     * 
     * @param table
     */
    public static void resetSortColumn(Table table) {
	if (table == null) {
	    return;
	}
	table.setSortColumn(null);
	table.setSortDirection(SWT.NONE);
    }
    
    /**
     * Return sort column of the table
     * @param table
     * @return
     */
    public static TableColumn getSortColumn(Table table) {
	return table == null ? null : table.getSortColumn();
    }

    /**
     * Return true if the table has a sort column
     * @param table
     * @return
     */
    public static boolean hasSortColumn(Table table) {
	return getSortColumn(table) != null;
    }

}
