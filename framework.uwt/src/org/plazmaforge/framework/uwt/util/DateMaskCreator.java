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

package org.plazmaforge.framework.uwt.util;

import org.plazmaforge.framework.util.StringUtils;

//[CORE]
public class DateMaskCreator {

    
    /** Number chars */
    private static final char[] numberChars = "ywWDdFHkKhmsS".toCharArray();

    /** Text chars */
    private static final char[] textChars = "GEazZ".toCharArray();

    private static DateMaskCreator instance;

    public synchronized static DateMaskCreator getInstance() {
	if (instance == null) {
	    instance = new DateMaskCreator();
	}
	return instance;
    }

    public static String getDateMaskPattern(String datePattern) {
	return getInstance().createDateMaskPattern(datePattern);
    }

    private String createDateMaskPattern(String datePattern) {
	if (datePattern == null) {
	    return null;
	}
	//return createMaskFromDatePattern(datePattern);
	
	
	String result = datePattern;
	char[] chars = result.toCharArray();
	StringBuffer buf = new StringBuffer();
	StringBuffer tokenBuf = new StringBuffer();
	boolean startString = false;

	for (int i = 0; i < chars.length; i++) {
	    char ch = chars[i];
	    if (ch == '\'') {
		if (!startString) { // previous start string = false
		    buf.append(replaceDateToken(tokenBuf.toString()));
		    tokenBuf = new StringBuffer();
		}
		buf.append(ch);
		startString = !startString;
	    } else {
		if (startString) {
		    buf.append(ch);
		} else {
		    tokenBuf.append(ch);
		}
	    }

	    if (i == chars.length - 1 && !startString) {
		buf.append(replaceDateToken(tokenBuf.toString()));
	    }
	}
	result = buf.toString();
	return result;
	
    }
    
    // SOLUTION 2
    private static String createMaskFromDatePattern(String datePattern) {
	String symbols = "GyMdkHmsSEDFwWahKzZ";
	String mask = "";
	for (int i = 0; i < datePattern.length(); i++) {
	    char ch = datePattern.charAt(i);
	    boolean symbolFound = false;
	    for (int n = 0; n < symbols.length(); n++) {
		if (symbols.charAt(n) == ch) {
		    mask += "#";
		    symbolFound = true;
		    break;
		}
	    }
	    if (!symbolFound) {
		mask += ch;
	    }
	}
	return mask;
    }

    /**
     * Replace date token. Number char -> # Text char -> ? M(2) -> # M(>2) -> ?
     * 
     * @param token
     * @return
     */
    private String replaceDateToken(String token) {
	StringBuffer buf = new StringBuffer();
	char[] chars = token.toCharArray();
	int monthDigits = 0;
	int dayDigits = 0;
	for (int i = 0; i < chars.length; i++) {
	    char ch = chars[i];
	    if (ch == 'M') {
		monthDigits++;
	    } else if (ch == 'd') {
		dayDigits++;
	    } else {
		if (monthDigits > 0) {
		    buf.append(getMonthMask(monthDigits));
		    monthDigits = 0;
		}
		if (dayDigits > 0) {
		    buf.append(getDayMask(dayDigits));
		    dayDigits = 0;
		}

		if (isNumberFormat(ch)) {
		    buf.append("#");
		} else if (isTextFormat(ch)) {
		    buf.append("?");
		} else {
		    buf.append(ch);
		}
	    }

	    if (i == chars.length - 1) {
		if (monthDigits > 0) {
		    buf.append(getMonthMask(monthDigits));
		}
		if (dayDigits > 0) {
		    buf.append(getDayMask(dayDigits));
		}
	    }

	}
	return buf.toString();
    }

    private String getDayMask(int dayDigits) {
	if (dayDigits < 2) {
	    dayDigits = 2;
	}
	return StringUtils.replicate("#", dayDigits);

    }

    private String getMonthMask(int monthDigits) {
	if (monthDigits < 2) {
	    monthDigits = 2;
	}
	return StringUtils.replicate(((monthDigits > 2) ? "?" : "#"), monthDigits);

    }

    private boolean isNumberFormat(char ch) {
	return isFormat(numberChars, ch);
    }

    private boolean isTextFormat(char ch) {
	return isFormat(textChars, ch);
    }

    private boolean isFormat(char[] chars, char ch) {
	for (int i = 0; i < chars.length; i++) {
	    if (chars[i] == ch) {
		return true;
	    }
	}
	return false;
    }

    
}
