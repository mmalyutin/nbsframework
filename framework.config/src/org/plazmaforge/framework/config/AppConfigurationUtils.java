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

/**
 * 
 */
package org.plazmaforge.framework.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Locale;

import org.plazmaforge.framework.util.FileUtils;
import org.plazmaforge.framework.util.IPropertiesStore;
import org.plazmaforge.framework.util.PropertiesStore;

/**
 * @author ohapon
 *
 */
public class AppConfigurationUtils {
    
    private static SecureRandom seeder;
    
    static {
   	seeder = new SecureRandom();
    }

    public static String getRootConfigFileName(String rootConfigDir, String name) {
        return FileUtils.getPath(rootConfigDir, name);
    }
    
    public static FileInputStream getPropertiesFileInputStream(String name,
	    String language, String country) throws IOException {
	String fileName = findPropertiesFileName(name, language, country);
	if (fileName == null) {
	    return null;
	}
	return new FileInputStream(fileName);
    }

    public static String findPropertiesFileName(String configName, Locale locale) {
	if (locale == null) {
	    locale = Locale.getDefault();
	}
	// We use only language and country of locale
	String language = locale.getLanguage();
	String country = locale.getCountry();
	return findPropertiesFileName(configName, language, country);
    }

    public static String findPropertiesFileName(String name, String language,
	    String country) {

	if (name == null) {
	    return null;
	}

	String fileName = createPropertiesFileName(name, language, country); // file_en_US.properties
	boolean isExist = false;

	if (isFileExist(fileName)) {
	    isExist = true;
	} else {
	    fileName = createPropertiesFileName(name, language, null); // file_en.properties
	    if (isFileExist(fileName)) {
		isExist = true;
	    } else {
		fileName = createPropertiesFileName(name, null, null); // file.properties
		if (isFileExist(fileName)) {
		    isExist = true;
		}
	    }
	}
	if (isExist) {
	    return fileName;
	}
	return null;
    }

    private static String normalizePropertiesFileName(String fileName) {
	return fileName + AppConfiguration.PROPERTIES_FILE_EXT;
    }

    public static String createPropertiesFileName(String name, Locale locale) {
	if (locale == null) {
	    locale = Locale.getDefault();
	}
	// We use only language and country of locale
	String language = locale.getLanguage();
	String country = locale.getCountry();
	return createPropertiesFileName(name, language, country);
    }

    public static String createPropertiesFileName(String name, String language, String country) {
	if (name == null) {
	    return null;
	}
	if (language == null) {
	    return normalizePropertiesFileName(name);
	}
	if (country == null) {
	    return normalizePropertiesFileName(name + "_" + language);
	}
	return normalizePropertiesFileName(name + "_" + language + "_"	+ country);
    }

    private static boolean isFileExist(String fileName) {
	return FileUtils.exists(fileName);
    }
    
    
    ///
    
    public static IPropertiesStore getConfigPropertiesStoreByFile(String fileName) throws IOException {
	if (fileName == null) {
	    return null;
	}
	PropertiesStore store = new PropertiesStore();
	store.initLocales(fileName, true);
	store.setAutoAddLocale(true);
	store.load();
	store.resetLocale();
	return store;
    }
    
    public static InputStream getConfigInputStream(String configName) throws IOException {
	return FileUtils.getResource(configName);
    }
    
    private static String hexFormat(int inpInt, int nChars) {
	String tmpStr = Integer.toHexString(inpInt);
	// return string prepended with 0s if the length is less than 8 chars
	return ((tmpStr.length() == 8) ? tmpStr : ((new String("00000000")).substring(tmpStr.length()) + tmpStr));
    }

    /**
     * generate 8 digits ID
     * 
     * @return
     */
    public static String generateID() {
	// get next random value
	int node = seeder.nextInt();
	return hexFormat(node, 8);
    }
}
