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
package org.plazmaforge.framework.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;


/** 
 * @author ohapon
 */

public class BundleUtils {

    
    /** A table of hex digits */
    private static final char[] hexDigit = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

    
    public static String loadConvert(String inputString) {
	if (inputString == null) {
	    return null;
	}
	char[] in = inputString.toCharArray();
	int off = 0;
	int len = in.length;
	char[] convtBuf = new char[1024];
	return loadConvert (in, off, len, convtBuf);
    }
    
    public static String saveConvert(String theString) {
	return saveConvert(theString, false);
    }
    
    /*
     * Converts encoded &#92;uxxxx to unicode chars
     * and changes special saved chars to their original forms
     */
    public static String loadConvert (char[] in, int off, int len, char[] convtBuf) {
        if (convtBuf.length < len) {
            int newLen = len * 2;
            if (newLen < 0) {
	        newLen = Integer.MAX_VALUE;
	    } 
	    convtBuf = new char[newLen];
        }
        char aChar;
        char[] out = convtBuf; 
        int outLen = 0;
        int end = off + len;

        while (off < end) {
            aChar = in[off++];
            if (aChar == '\\') {
                aChar = in[off++];   
                if(aChar == 'u') {
                    // Read the xxxx
                    int value=0;
		    for (int i=0; i<4; i++) {
		        aChar = in[off++];  
		        switch (aChar) {
		          case '0': case '1': case '2': case '3': case '4':
		          case '5': case '6': case '7': case '8': case '9':
		             value = (value << 4) + aChar - '0';
			     break;
			  case 'a': case 'b': case 'c':
                          case 'd': case 'e': case 'f':
			     value = (value << 4) + 10 + aChar - 'a';
			     break;
			  case 'A': case 'B': case 'C':
                          case 'D': case 'E': case 'F':
			     value = (value << 4) + 10 + aChar - 'A';
			     break;
			  default:
                              throw new IllegalArgumentException(
                                           "Malformed \\uxxxx encoding.");
                        }
                     }
                    out[outLen++] = (char)value;
                } else {
                    if (aChar == 't') aChar = '\t'; 
                    else if (aChar == 'r') aChar = '\r';
                    else if (aChar == 'n') aChar = '\n';
                    else if (aChar == 'f') aChar = '\f'; 
                    out[outLen++] = aChar;
                }
            } else {
	        out[outLen++] = (char)aChar;
            }
        }
        return new String (out, 0, outLen);
    }

    /*
     * Converts unicodes to encoded &#92;uxxxx and escapes
     * special characters with a preceding slash
     */
    public static String saveConvert(String theString, boolean escapeSpace) {
	if (theString == null) {
	    return null;
	}
        int len = theString.length();
        int bufLen = len * 2;
        if (bufLen < 0) {
            bufLen = Integer.MAX_VALUE;
        }
        StringBuffer outBuffer = new StringBuffer(bufLen);

        for(int x=0; x<len; x++) {
            char aChar = theString.charAt(x);
            // Handle common case first, selecting largest block that
            // avoids the specials below
            if ((aChar > 61) && (aChar < 127)) {
                if (aChar == '\\') {
                    outBuffer.append('\\'); outBuffer.append('\\');
                    continue;
                }
                outBuffer.append(aChar);
                continue;
            }
            switch(aChar) {
		case ' ':
		    if (x == 0 || escapeSpace) 
			outBuffer.append('\\');
		    outBuffer.append(' ');
		    break;
                case '\t':outBuffer.append('\\'); outBuffer.append('t');
                          break;
                case '\n':outBuffer.append('\\'); outBuffer.append('n');
                          break;
                case '\r':outBuffer.append('\\'); outBuffer.append('r');
                          break;
                case '\f':outBuffer.append('\\'); outBuffer.append('f');
                          break;
                case '=': // Fall through
                case ':': // Fall through
                case '#': // Fall through
                case '!':
                    outBuffer.append('\\'); outBuffer.append(aChar);
                    break;
                default:
                    if ((aChar < 0x0020) || (aChar > 0x007e)) {
                        outBuffer.append('\\');
                        outBuffer.append('u');
                        outBuffer.append(toHex((aChar >> 12) & 0xF));
                        outBuffer.append(toHex((aChar >>  8) & 0xF));
                        outBuffer.append(toHex((aChar >>  4) & 0xF));
                        outBuffer.append(toHex( aChar        & 0xF));
                    } else {
                        outBuffer.append(aChar);
                    }
            }
        }
        return outBuffer.toString();
    }
    
    /**
     * Convert a nibble to a hex character
     * @param	nibble	the nibble to convert.
     */
    private static char toHex(int nibble) {
	return hexDigit[(nibble & 0xF)];
    }


    
    
    
    
    public static String getBundleName(String fileName) {
	// Return simple file name without extension
	return FileUtils.getFileNameWithoutExt(fileName);
//	String bundleName = fileName;
//	int index = fileName.indexOf(".");
//	if (index > -1) {
//	    bundleName = fileName.substring(0, index);
//	}
//	return bundleName;
    }
    
    public static String getLocaleName(String bundleName) {
	String localeName = "";
	int index = indexOfStartLocale(bundleName);
	if (index > -1) {
	    localeName = bundleName.substring(index + 1);
	}
	return localeName;
    }
    
    public static int indexOfStartLocale(String bundleName) {
	return bundleName.indexOf("_"); // TODO: STUB: Must analyze
    }
    
    public static Locale getLocaleByFileName(String fileName) {
	String bundleName = getBundleName(fileName);
	String localeName = getLocaleName(bundleName);
	return getLocaleByName(localeName);
	
    }
    
    public static Locale getLocaleByName(String localeName) {
	Locale currentLocale = null;
        Locale availableLocales[] = Locale.getAvailableLocales();
        for(int i = 0; i < availableLocales.length; i++) {
            Locale locale = availableLocales[i];
            if(locale.toString().equals(localeName)) {
        	currentLocale = locale;
        	break;
            }
        }

        if (currentLocale == null) {
            currentLocale = new Locale(localeName);
        }
        return currentLocale;
    }

    
    public static File[] findPropretiesFiles(String folderName, String baseName) {
	return findPropretiesFiles(folderName, baseName, null);
    }
    
    public static File[] findPropretiesFiles(String folderName, String baseName, String exludeFileName) {
        String filePrefix = baseName;
        String fileExtension = "properties";
        String[] filePrefixList = new String[] {filePrefix + ".", filePrefix + "_"};
        
        File[] files = null;
        if (folderName != null && !folderName.equals("")) {
            File folder = new File(folderName);
            files = folder.listFiles(new FileFilter(filePrefixList, fileExtension, exludeFileName));
        }
        return files;
    }

    public static List<Locale> findLocales(String folderName, String baseName, String exludeFileName) {
	
	List<Locale> localeList = new ArrayList<Locale>();
	
        File[] files = findPropretiesFiles(folderName, baseName, exludeFileName);
        
        if (files == null || files.length == 0) {
            return localeList;
        }
        
        for (int i = 0; i < files.length; i++) {
	    File f = files[i];
	    
	    String fn = f.getName();
	    Locale l = getLocaleByFileName(fn);
	    localeList.add(l);
	}
        return localeList;
        
    }
    
    public static String[] getLocaleCombinations(Locale locale) {
	if (locale == null) {
	    return new String[0];
	}
	String country = locale.getCountry();
	String language = locale.getLanguage();
	//String variant = locale.getVariant(); // TODO: variant ? 
	
	List<String> combinations = new ArrayList<String>(); 
	if (!isEmpty(language)) {
	    if (!isEmpty(country)) {
		/// en_US
		combinations.add(language + "_" + country);
	    }
	    /// en
	    combinations.add(language);
	} else {
	    if (!isEmpty(country)) {
		/// US
		combinations.add(country);
	    }
	}
	
	if (combinations.isEmpty()) {
	    combinations.add("");
	}
	
	return combinations.toArray(new String[0]);
	
    }

    public static String generateFileName(String baseName, Locale locale) {
	String localeStr = null;
	if (locale != null) {
	    localeStr = locale.toString();
	    if (localeStr.length() == 0) {
		localeStr = null;
	    }
	}
	if (localeStr == null ) {
	    return baseName + ".properties";
	}
	return baseName + "_" + localeStr + ".properties"; 
    }

    public static String generateAbsoluteFileName(String folderName, String fileName) {
	return folderName + File.separator + fileName; 
    }
    
    
    public static String generateAbsoluteFileName(String folderName, String baseName, Locale locale) {
	String fileName = generateFileName(baseName, locale);
	return generateAbsoluteFileName(folderName, fileName);
    }
    
    
    static class FileFilter implements FilenameFilter {
	
	private String extension;
	
	private String[] prefixList;
	
	private String exludeFileName;
	
	
	
	public FileFilter(String prefix, String extension, String exludeFileName) {
	    this(new String[] {prefix}, extension, exludeFileName);
	}
	
	public FileFilter(String[] prefixList, String extension, String exludeFileName) {
	    super();
	    this.prefixList = prefixList;
	    this.extension = extension;
	    this.exludeFileName = exludeFileName;
	}
	
	
	
	public boolean accept(File dir, String name) {
	    if (name == null || (exludeFileName != null && exludeFileName.equals(name))) {
		return false;
	    }
	    int len = name.length();
	    String ext = null;
	    int index = name.indexOf(".");
	    if (index > -1 && index + 1 < len) {
		ext = name.substring(index + 1);
	    }
	    
	    if (prefixList != null && prefixList.length > 0) {
		for (int i = 0; i < prefixList.length; i++) {
		    String prefix = prefixList[i];
		    if (prefix == null) {
			continue;
		    }
		    if (name.startsWith(prefix) && extension.equals(ext)) {
			return true;
		    }
		}
		return false;
	    }
	    
	    //return name.startsWith(prefix) && extension.equals(ext);
	    return extension.equals(ext);
	    
	}
    }
    
    
    private static boolean isEmpty(String str) {
	return StringUtils.isEmpty(str);
    }
    
    
    public static void sortLocales(List<Locale> locales){
	Collections.sort(locales, new Comparator<Locale>() {
            public int compare(Locale l1, Locale l2) {
                return l1.toString().compareTo(l2.toString());
            }
        });
    }
}
