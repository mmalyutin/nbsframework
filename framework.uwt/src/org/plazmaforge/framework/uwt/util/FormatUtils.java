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

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.util.StringUtils;

//import org.plazmaforge.framework.core.exception.ApplicationException;


// TODO: MUST ENABLE LAST CODE

//[CORE]
//[NEW] : normalizeDate()

public class FormatUtils {

    
    /**
     * Date patterns
     */
    private static final String[] DATE_PATTERNS = {"dd.MM.yyyy", "dd/MM/yyyy", "d/M/yyyy", "M/d/yyyy", "yyyy-MM-dd"};
    
    
    /**
     * Time patterns
     */
    private static final String[] TIME_PATTERNS = {"HH:mm:ss" , "H:m:s", "hh:mm:ss", "h:m:s"};
    
    /**
     * Integer patterns
     */
    private static final String[] INTEGER_PATTERNS = {"###0", "#,##0"};
    
    
    
    private static final String DEFAULT_DATE_PATTERN = DATE_PATTERNS[0]; // dd.MM.yyyy
    
    
    private static final String DEFAULT_TIME_PATTERN = TIME_PATTERNS[0]; // HH:mm:ss
    
    
    private static final String DEFAULT_INTEGER_PATTERN = INTEGER_PATTERNS[0]; // ###0
    
    
    private static final String DEFAULT_NUMBER_PATTERN = DEFAULT_INTEGER_PATTERN + ".000"; // ###0.000
    
    private static final String DEFAULT_CURRENCY_PATTERN = DEFAULT_INTEGER_PATTERN + ".00"; // ###0.00
    
    private static final String DEFAULT_PERCENT_PATTERN = DEFAULT_INTEGER_PATTERN + ".00"; // ###0.00
    
    private static final String DEFAULT_QUANTITY_PATTERN = DEFAULT_INTEGER_PATTERN + ".000"; // ###0.000
    
    private static final String DEFAULT_COEFFICIENT_PATTERN = DEFAULT_INTEGER_PATTERN + ".000"; // ###0.000
    
    
    
	
    /**
     * Default Date char sequence
     */
    private static final String DEFAULT_DATE_CHAR_SEQUENCE = "dMy./- ";
    
    
    /**
     * Default Time char sequence
     */
    private static final String DEFAULT_TIME_CHAR_SEQUENCE = "hHmst: ";
    
    
    /**
     * Default Number char sequence
     */
    private static final String DEFAULT_NUMBER_CHAR_SEQUENCE = "#0., ";
    
    
    
    
    /**
     * Special Map for number patterns
     * Key is decimal count (1 - '.0', 2 - '.00', 3 - '.000') 
     */
    private static Map<Integer, String[]> numberPatternMap = new HashMap<Integer, String[]>();
    
    
   
    private FormatUtils() {
    }

    public static String[] getDatePatterns() {
	return DATE_PATTERNS.clone();
    }
    
    public static String[] getTimePatterns() {
	return TIME_PATTERNS.clone();
    }
    
    public static String[] getIntegerPatterns() {
	return INTEGER_PATTERNS.clone();
    }

    public static String[] getNumberPatterns() {
	return getNumberPatterns(3); // 3 digits after '.' 
    }
	
    
    public static String[] getCurrencyPatterns() {
	return getNumberPatterns(2); // 2 digits after '.'
    }

    public static String[] getPercentPatterns() {
	return getNumberPatterns(2); // 2 digits after '.'
    }

    public static String[] getQuantityPatterns() {
	return getNumberPatterns(3); // 3 digits after '.'
    }

    public static String[] getCoefficientPatterns() {
	return getNumberPatterns(3); // 2 digits after '.'
    }

    
    
    /**
     * Return Number patterns
     * For example: {"#,##0.###", "#,##0.000" , "###0.###" , "###0.000"}
     * @param decimal
     * @return
     */
    public static String[] getNumberPatterns(int decimal) {
	String[] integerPatterns = getIntegerPatterns();
	if (decimal < 1) {
	    return integerPatterns;
	}
	String[] patterns = numberPatternMap.get(decimal);
	if (patterns != null) {
	    return patterns.clone();
	}
	
	String suffixB = StringUtils.replicate("#", decimal); // blank suffix
	String suffixZ = StringUtils.replicate("0", decimal); // zero suffix
	List<String> patternList = new ArrayList<String>();
	for (String p : integerPatterns) {
	    patternList.add(p + "." + suffixB);
	    patternList.add(p + "." + suffixZ);
	}
	
	patterns = (String[]) patternList.toArray(new String[0]);
	numberPatternMap.put(decimal, patterns);
	
	return patterns.clone();
    }

    
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Fixed year digits (Y2K). Replace 'yy' to 'yyyy'.
     * 
     */
    public static String normalizeYear(String pattern) {
	if (pattern == null) {
	    return null;
	}
	StringBuffer buf = new StringBuffer();
	char[] chars = pattern.toCharArray();
	int yearDigits = 0;
	for (int i = 0; i < chars.length; i++) {
	    if (chars[i] == 'y') {
		yearDigits++;
	    } else {
		if (yearDigits > 0) {
		    buf.append(getYearPattern(buf, yearDigits));
		    yearDigits = 0;
		}

		buf.append(chars[i]);

	    }
	    if (i == chars.length - 1 && yearDigits > 0) {
		buf.append(getYearPattern(buf, yearDigits));
	    }
	}
	return buf.toString();
    }

    /**
     * Normalize day and month. Replace one digit of day or month to two
     * digits. Replace 'M' to 'MM' Replace 'd' to 'dd'
     * 
     * @param parentPattern
     * @return
     */
    public static String normalizeDayAndMonth(String parentPattern) {
	if (parentPattern == null) {
	    return null;
	}
	StringBuffer buf = new StringBuffer();
	char[] chars = parentPattern.toCharArray();
	int dayDigits = 0;
	int monthDigits = 0;
	for (int i = 0; i < chars.length; i++) {
	    if (chars[i] == 'd') {
		dayDigits++;
	    } else if (chars[i] == 'M') {
		monthDigits++;
	    } else {

		if (dayDigits > 0) {
		    buf.append(getDigitPattern("d", dayDigits));
		    dayDigits = 0;
		} else if (monthDigits > 0) {
		    buf.append(getDigitPattern("M", monthDigits));
		    monthDigits = 0;
		}

		buf.append(chars[i]);

	    }
	    if (i == chars.length - 1) {
		if (dayDigits > 0) {
		    buf.append(getDigitPattern("d", dayDigits));
		} else if (monthDigits > 0) {
		    buf.append(getDigitPattern("M", monthDigits));
		}
	    }
	}
	return buf.toString();
    }
    
    /**
     * Normalize Date Pattern
     * - Normalize year (Y2K): 'yy' -> 'yyyy'
     * - Normalize month: 'M' -> 'MM'
     * - Normalize day: 'd' -> 'dd'
     * @param pattern
     * @return
     */
    public static String normalizeDate(String pattern) {
	pattern = normalizeYear(pattern);
	pattern = normalizeDayAndMonth(pattern);
	return pattern;
    }

    private static String getDigitPattern(String pattern, int digits) {
	if (digits < 2) {
	    digits = 2;
	}
	return StringUtils.replicate(pattern, digits);
    }

    private static String getYearPattern(StringBuffer buf, int yearDigits) {
	if (yearDigits == 2) {
	    return "yyyy";
	} else {
	    return StringUtils.replicate("y", yearDigits);
	}
    }
    
    public static boolean isValidDatePattern(String pattern) {
	return isValidDatePattern(pattern, null);
    }
    
    public static boolean isValidDatePattern(String pattern, String charSequence) {
	boolean isValid = isValidPattern(pattern, charSequence);
	if (!isValid) {
	    return isValid;
	}
	isValid = false;
	try {
	    validateDatePattern(pattern);
	    isValid = true;
	} catch (IllegalArgumentException ex) {
	    
	}
	return isValid;
    }

    public static boolean isValidTimePattern(String pattern) {
	return isValidTimePattern(pattern, null);
    }
    
    public static boolean isValidTimePattern(String pattern, String charSequence) {
	boolean isValid = isValidPattern(pattern, charSequence);
	if (!isValid) {
	    return isValid;
	}
	isValid = false;
	try {
	    validateTimePattern(pattern);
	    isValid = true;
	} catch (IllegalArgumentException ex) {
	    
	}
	return isValid;
    }

    public static boolean isValidNumberPattern(String pattern) {
	return isValidNumberPattern(pattern, null);
    }
    
    public static boolean isValidNumberPattern(String pattern, String charSequence) {
	boolean isValid = isValidPattern(pattern, charSequence);
	if (!isValid) {
	    return isValid;
	}
	isValid = false;
	try {
	    validateNumberPattern(pattern);
	    isValid = true;
	} catch (IllegalArgumentException ex) {
	    
	}
	return isValid;
    }
    
    public static void validateDatePattern(String pattern) {
	new SimpleDateFormat(pattern);
    }
    
    public static void validateTimePattern(String pattern) {
	new SimpleDateFormat(pattern);
    }

    public static void validateNumberPattern(String pattern) {
	new DecimalFormat(pattern);
    }

    public static boolean isValidPattern(String pattern) {
	return isValidPattern(pattern, null);
    }
    
    public static boolean isValidPattern(String pattern, String charSequence) {
	if (pattern == null || pattern.trim().length() == 0) {
	    return false;
	}
	if (charSequence != null && charSequence.trim().length() != 0) {
	    char[] patternArray = pattern.toCharArray();
	    char[] charArray = charSequence.toCharArray();
	    for (int i = 0; i < patternArray.length; i++) {
		boolean b = false;
		for (int j = 0; j < charArray.length; j++) {
		    if (patternArray[i] == charArray[j]) {
			b = true;
			break;
		    }
		}
		if (!b) {
		    return false;
		}
	    }
	    
	}
	return true;
    }
    

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    public static String getDefaultDateCharSequence() {
	return DEFAULT_DATE_CHAR_SEQUENCE;
    }
    
    public static String getDefaultTimeCharSequence() {
	return DEFAULT_TIME_CHAR_SEQUENCE;
    }

    
    public static String getDefaultNumberCharSequence() {
	return DEFAULT_NUMBER_CHAR_SEQUENCE;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static String getDefaultDatePattern() {
	return DEFAULT_DATE_PATTERN;
    }
    
    public static String getDefaultTimePattern() {
	return DEFAULT_TIME_PATTERN;
    }
    
    public static String getDefaultDateTimePattern() {
	return "" + getDefaultDatePattern() + " " + getDefaultTimePattern();
    }
    
    ////

    public static String getDefaultIntegerPattern() {
	return DEFAULT_INTEGER_PATTERN;
    }

    public static String getDefaultNumberPattern() {
	return DEFAULT_NUMBER_PATTERN;
    }

    public static String getDefaultCurrencyPattern() {
	return DEFAULT_CURRENCY_PATTERN;
    }
    
    public static String getDefaultPercentPattern() {
	return DEFAULT_PERCENT_PATTERN;
    }
    
    public static String getDefaultQuantityPattern() {
	return DEFAULT_QUANTITY_PATTERN;
    }
    
    public static String getDefaultCoefficientPattern() {
	return DEFAULT_COEFFICIENT_PATTERN;
    }
    


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static String formatExampleDate(String pattern) {
	String result = "";
	try {
	    SimpleDateFormat f = new SimpleDateFormat(pattern);
	    result = f.format(new Date());
	} catch (Exception ex) {
	    
	}
	return result;
    }
    
    public static String formatExampleNumber(String pattern) {
	String result = "";
	try {
	    DecimalFormat f = new DecimalFormat(pattern);
	    result = f.format(1234567.890);
	} catch (Exception ex) {
	    
	}
	return result;
    
    }    
    
    public static String formatTimeSheet(int days, int hours, int minutes) {
	return formatTimeSheet(days, hours, minutes, true);
    }
    
    public static String formatTimeSheet(int days, int hours, int minutes, boolean useEmpty) {
	days = pozitiveInt(days);
	hours = pozitiveInt(hours);
	minutes = pozitiveInt(minutes);
	
	if (useEmpty && days == 0 && hours == 0 && minutes == 0) {
	    return "";
	}
	StringBuffer buf = new StringBuffer();
	boolean flag = false;
	if (!useEmpty || (useEmpty && days > 0)) {
	    buf.append(days + "d");
	    flag = true;
	}
	if (!useEmpty || (useEmpty && (hours > 0 || flag))) {
	    if (flag) {
		buf.append(" ");
	    }
	    buf.append(hours + "h");
	    flag = true;
	}
	if (!useEmpty || (useEmpty && (minutes > 0 || flag))) {
	    if (flag) {
		buf.append(" ");
	    }
	    buf.append(minutes + "m");
	    flag = true;
	}
	return buf.toString();
    }
    
    private static int pozitiveInt(int value) {
	return value < 0 ? 0 : value;
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
    public static void checkDatePattern(String pattern, String charSequence) throws ApplicationException {
	if (!FormatUtils.isValidDatePattern(pattern, charSequence)) { // "dMy./- "
	    throw new ApplicationException("Date format is invalid");
	}
    }

    public static void checkTimePattern(String pattern, String charSequence) throws ApplicationException {
	if (!FormatUtils.isValidTimePattern(pattern, charSequence)) { // "hHmst: "
	    throw new ApplicationException("Time format is invalid");
	}
    }

    public static void checkNumberPattern(String pattern, String charSequence) throws ApplicationException {
	if (!FormatUtils.isValidNumberPattern(pattern, charSequence)) { // "#0., "
	    throw new ApplicationException("Number format is invalid");
	}
    }
    */
    

}
