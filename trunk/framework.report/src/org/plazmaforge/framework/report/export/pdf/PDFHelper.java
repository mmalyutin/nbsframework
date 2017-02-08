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

package org.plazmaforge.framework.report.export.pdf;

import java.awt.GraphicsEnvironment;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

//import sun.font.FontManager;

/**
 * 
 * @author ohapon
 *
 */
public class PDFHelper {

    private static final String FONT_FILE_NONE = "-";
    
    private static Map<String, String> fontFiles;
    
    private static String defaultFontName;
    
    
    
    
    public static String getFontFileName(String fontName) {
	
	// Font not available 
	if (!isAvailable(fontName)) {
	    return null;
	}
	
	// Get font file from storage
	String fontFile = fontFiles.get(fontName);
	
	// Font file not found in storage (marker)
	if (fontFile != null && FONT_FILE_NONE.equals(fontFile)) {
	    return null;
	}
	
	// Try find font file
	if (fontFile == null) {
	    fontFile = findFontFileName(fontName);
	    if (fontFile == null) {
		// Font file not found in system
		fontFiles.put(fontName, FONT_FILE_NONE);
		System.out.println("Font file not found: font='" + fontName + "'");
	    } else {
		//fontFile = FontManager.getFontPath(true) + "/" +  fontFile;
		fontFiles.put(fontName, fontFile);
	    }
	}
	
	return fontFile;
    }
    
    public static boolean isAvailable(String fontName) {
	if (fontName == null || fontName.isEmpty()) {
	    return false;
	}
	return fontFiles.containsKey(fontName);
    }
    
    public static String getAvailableDefaultFont() {
	return defaultFontName;
    }

    public static String findFontFileName(String fontName) {
	if (fontName == null) {
	    return null;
	}
	
	//fontName = "222";
	
	
	/*
	// Java 1.6
	String fontFile = FontManager.getFileNameForFontName(fontName);
	//return fontFile;
	if (fontFile == null) {
	    return null;
	}
	return FontManager.getFontPath(true) + "/" +  fontFile;
	*/

	
	// TODO: Must optimize
	// Java 1.7+ FontManagerFactory.getInstance()
	String fontFile = null;
	try {
		java.awt.Font font = new java.awt.Font(fontName, java.awt.Font.PLAIN, 12); 
		Object font2D;
		try {
		    // Java 1.7+.
		    font2D = Class.forName("sun.font.FontUtilities").getDeclaredMethod("getFont2D", new Class[] {java.awt.Font.class}).invoke(null, new Object[] {font});
		} catch (Throwable ignored) {
		    font2D = Class.forName("sun.font.FontManager").getDeclaredMethod("getFont2D", new Class[] {java.awt.Font.class}).invoke(null, new Object[] {font});
		}
		Field platNameField = Class.forName("sun.font.PhysicalFont").getDeclaredField("platName");
		platNameField.setAccessible(true);
		fontFile = (String)platNameField.get(font2D);
	} catch (Exception ex) {
	    System.err.println(ex);
	}
	
	return fontFile;


	/*
	try {
            String fmClassName = "sun.font.fontmanager", DEFAULT_CLASS);
            ClassLoader cl = ClassLoader.getSystemClassLoader();
            Class fmClass = Class.forName(fmClassName, true, cl);
            instance = (FontManager) fmClass.newInstance();
        } catch (ClassNotFoundException |
                 InstantiationException |
                 IllegalAccessException ex) {
            throw new InternalError(ex);

        }
        */
	
	
    }
    
    static {
	GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	fontFiles = new HashMap<String, String>();
	String[] names = env.getAvailableFontFamilyNames();
	for (String name: names) {
	    fontFiles.put(name, null);
	    if (defaultFontName == null 
		    && ("Helvetica".equals(name) || "Arial".equals(name)  || "Verdana".equals(name))) {
		defaultFontName = name;
	    }
	    //System.out.println(name);
	}
    }
    
}
