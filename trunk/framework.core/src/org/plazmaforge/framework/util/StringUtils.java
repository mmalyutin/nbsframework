package org.plazmaforge.framework.util;

//NON-JS IMPORT
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.StringReader;

import java.util.ArrayList;
import java.util.List;
//import java.util.StringTokenizer;

public class StringUtils {

    
    public static String ELLIPSIS = "...";
    
    
    public static String DEFAULT_LIKE_ANY_CH = "%";
    
    public static String DEFAULT_LIKE_ONE_CH = "?";
    
    
    public static String LIKE_ANY_STR = "%*";
    
    public static String LIKE_ONE_STR = "?_";

    public static String REGEX_STR = "[](){}.*+?$^|#\\";

    
    
    private static int DEFAULT_COLS = 100;

    private static int DEFAULT_ROWS = 0;

    private static int DEFAULT_ERROR_ROWS = 10;
    
    
    /////////////////////////////////////////////////////////////////////////////////////
    //
    // Utilities methods
    //
    //////////////////////////////////////////////////////////////////////////////////////

    private StringUtils() {
    }

    /**
     * Return true if <code>str</code> is empty
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
	return isEmpty(str, false);
    }

    /**
     * Return true if <code>str</code> is empty
     * If <code>trim</code> is true then trim string before analyze
     * @param str
     * @param trim
     * @return
     */
    public static boolean isEmpty(String str, boolean trim) {
	if (str == null || str.isEmpty()) {
	    return true;
	}
	if (trim) {
	    str = str.trim();
	}
	return str.isEmpty();
    }
    
    /**
     * Return <code>true</code> if array is null or array.length == 0
     * @param array
     * @return
     */
    public static boolean isEmpty(String[] array) {
	return array == null || array.length == 0;
    }

    
    /**
     * Returns <code>true</code> if all elements is empty
     * 
     * @param array
     * @return result
     */
    public static boolean isEmptyAll(String[] array) {
	if (isEmpty(array)) {
	    return true;
	}
	for (int i = 0; i < array.length; i++) {
	    if (!isEmpty(array[i]))
		return false;
	}
	return true;
    }
    
    //NON_JS
    /*
    public static String cleanString(String str) {
	StringBuffer buf = new StringBuffer(str.length());
	char lastCh = ' ';

	for (int i = 0, limit = str.length(); i < limit; ++i) {
	    char ch = str.charAt(i);

	    if (Character.isWhitespace(ch)) {
		if (!Character.isWhitespace(lastCh)) {
		    buf.append(' ');
		}
	    } else {
		buf.append(ch);
	    }
	    lastCh = ch;
	}

	return buf.toString();
    }
    */

    public static String lpad(String str, int len, String pad) {
	if (str == null || pad == null) {
	    return null;
	}
	int strLen = str.length();
	int padLen = pad.length();
	if (len <= strLen || padLen == 0) {
	    return str;
	}
	int ln = len - strLen;
	ln = (int) Math.ceil((double) ln / padLen);
	String s = replicate(pad, ln) + str;
	return s.substring(s.length() - len);
    }

    public static String lpad(String str, int len, char pad) {
	char[] ch = new char[1];
	ch[0] = pad;
	return lpad(str, len, new String(ch));
    }

    public static String lpad(String str, int len) {
	return lpad(str, len, " ");
    }

    public static String rpad(String str, int len, String pad) {
	if (str == null || pad == null) {
	    return null;
	}
	int strLen = str.length();
	int padLen = pad.length();
	if (len <= strLen || padLen == 0) {
	    return str;
	}
	int ln = len - strLen;
	ln = (int) Math.ceil((double) ln / padLen);
	String s = str + replicate(pad, ln);
	return s.substring(0, len);
    }

    public static String rpad(String str, int len, char pad) {
	char[] ch = new char[1];
	ch[0] = pad;
	return rpad(str, len, new String(ch));
    }

    public static String rpad(String str, int len) {
	return rpad(str, len, " ");
    }

    public static String shortString(String str, int count) {
 	return trunc(str, count, true, true);
     }

     public static String trunc(String str, int count) {
 	return trunc(str, count, true, false);
     }
     
     public static String trunc(String str, int count, boolean useTrim, boolean useEllipsis) {
 	 if (str == null) {
 	     return null;
 	 }
 	 if (count < 1) {
 	     return str;
 	 }
 	 if (useTrim) {
 	     str = str.trim();
 	 }
 	 if (str.length() <= count) {
 	     return str;
 	 }
 	 if (useEllipsis) {
 	     int ELLIPSIS_LEN = 3; // "...".length() = 3
 	     if (count <= ELLIPSIS_LEN) { 
 		 return str.substring(0, count);
 	     }
 	     return str.substring(0, count - ELLIPSIS_LEN) + ELLIPSIS;
 	 } 
 	 return str.substring(0, count);
     }

    //NON-JS
    /*
    public static String trimLine(String text) {
	if (text == null) {
	    return "";
	}

	StringBuffer buf = new StringBuffer();
	try {
	    BufferedReader r = new BufferedReader(new StringReader(text.trim()));
	    int c = 0;
	    String s = r.readLine();
	    while (s != null) {
		if (c++ > 0)
		    buf.append('\n');
		buf.append(s.trim());
		s = r.readLine();
	    }
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
	return buf.toString();
    }
    */

    public static String trimString(String str) {
	return emptyIfNull(str).trim();
    }
    
    
    /**
     * Return "" if <code>str</code> is null
     * @param str
     * @return
     */
    public static String emptyIfNull(String str) {
 	if (str == null) {
 	    return "";
 	}
 	return str;
     }

     /**
      * Return <code/>defStr</code> if <code>str</code> is null
      * @param str
      * @param defStr
      * @return
      */
     public static String strIfNull(String str, String defStr) {
 	if (str == null) {
 	    return defStr;
 	}
 	return str;
     }    

    /**
     * Return null if <code>str</code> is empty
     * @param str
     * @return
     */
    public static String nullIfEmpty(String str) {
	return nullIfEmpty(str, false);
    }
    
    /**
     * Return null if <code>str</code> is empty
     * If <code>trim</code> is true then trim string before analyze
     * @param str
     * @param trim
     * @return null or <code>str</code>
     */
    public static String nullIfEmpty(String str, boolean trim) {
	if (str != null && trim) {
	    str = str.trim();
	}
	return isEmpty(str) ? null : str;
    }

    public static String[] split(String str, String seperators) {
	return split(str, seperators, false);

    }

    public static String[] split(String str, String seperators, boolean include) {
	if (str == null) {
	    return null;
	}
	if (seperators == null) {
	    return new String[] {str};
	}
	StringTokenizer tokens = new StringTokenizer(str, seperators, include);
	String[] result = new String[tokens.countTokens()];
	int i = 0;
	while (tokens.hasMoreTokens()) {
	    result[i++] = tokens.nextToken();
	}
	return result;
    }

    public static String[] splitLines(String str) {
	if (str == null || str.length() == 0) {
	    return new String[0];
	}
	return str.split("[\\r]?\\n");
    }
    
    public static String[] splitScriptLines(String script) {
	if (script == null || script.length() == 0) {
	    return new String[0];
	}
	String[] input = splitLines(script);
	List<String> output = new ArrayList<String>();
	for (String line : input) {
	    
	    // Ignore empty line
	    if (line.length() == 0) {
		continue;
	    }

	    char[] chars = line.toCharArray();
	    int count = chars.length;
	    int len = count;
	    int st = 0;
	    
	    while (st < len && (chars[st] == ' ' || chars[st] == '\t')) {
		st++;
	    }
	    while (st < len && (chars[len - 1] == ' ' || chars[len - 1] == '\t')) {
		len--;
	    }

	    line = ((st > 0) || (len < count)) ? line.substring(st, len) : line;
	    
	    if (line.length() == 0) {
		continue;
	    }
	    
	    // WARNING !
	    // We add ' ' before/after line when input string has whitespace chars (' \t  \t') before/after 
	    // because one command of script can use more lines
	    if (st > 0 || len < count) {
		line = ((st > 0) ? " " : "") + line + ((len < count) ? " " : ""); 
	    }
	    output.add(line);
	    
	    //output.add(st > 0 ? (" " + line) : line); 
	}
	if (output.isEmpty()) {
	    return new String[0];
	}
	return output.toArray(new String[0]);
    }
    
    /**
     * Tests if this string starts with the specified prefix.
     * @param str
     * @param prefix
     * @return
     */
    public static boolean startsWith(String str, String prefix) {
	return str != null && str.startsWith(prefix);
    }

    /**
     * Tests if this string ends with the specified suffix.
     * @param str
     * @param suffix
     * @return
     */
    public static boolean endsWith(String str, String suffix) {
	return str != null && str.endsWith(suffix);
    }

    public static String replicate(String s, int n) {
   	if (s == null || n < 1) {
   	 return "";
   	}
   	StringBuffer buff = new StringBuffer();
   	for (int i = 0; i < n; i++) {
   	    buff.append(s);
   	}
   	return buff.toString();
    }
        
    
    /**
     * Return true if <code>str</code> starts with <code>prefix</code> (ignore case)
     * @param str
     * @param prefix
     * @return
     */
    public static boolean startsWithIgnoreCase(String str, String prefix) {
	if (str == null || prefix == null ) {
	    return false;
	}
	return str.toUpperCase().startsWith(prefix.toUpperCase());
    }
    
    
    /**
     * Return true if <code>str</code> ends with <code>suffix</code> (ignore case)
     * @param str
     * @param suffix
     * @return
     */
    public static boolean endsWithIgnoreCase(String str, String suffix) {
	if (str == null || suffix == null ) {
	    return false;
	}
	return str.toUpperCase().endsWith(suffix.toUpperCase());
    }

    public static boolean bracketsWithIgnoreCase(String str, String prefix, String suffix) {
	if (str == null || prefix == null || suffix == null) {
	    return false;
	}
	str = str.toUpperCase();
	return str.startsWith(prefix.toUpperCase()) && str.endsWith(suffix.toUpperCase());
    }
    
    
    public static String replaceAll(String str, String s1, String s2) {
	if (str == null) {
	    return null;
	}
	if (s1 == null || s2 == null) {
	    return str;
	}
	StringBuffer sbuf = new StringBuffer();
	int pos = 0;
	int index = str.indexOf(s1);
	int patLen = s1.length();
	while (index >= 0) {
	    sbuf.append(str.substring(pos, index));
	    sbuf.append(s2);
	    pos = index + patLen;
	    index = str.indexOf(s1, pos);
	}
	sbuf.append(str.substring(pos));
	return sbuf.toString();
    }
    
    
    /**
     * Convert string to HTML representation.
     * For example, replace \'n' to '<br>'
     * @param str
     * @return
     */
    public static String toHtml(String str) {
	if (str == null) {
	    return str;
	}
	//TODO: Temp solution. Must analyze \r \t
	return replaceAll(str, "\n", "<br>");
    }

    
    
    // NEW-OLD
    
    public static boolean equals(String s1, String s2) {
	if (s1 == null && s2 == null) {
	    return true;
	}
	if (s1 != null) {
	    return s1.equals(s2);
	}
	return s2.equals(s1);
    }
    
    public static String replaceCharTable(String str, String table1, String table2) {
	if (str == null || str.length() == 0) {
	    return str;
	}
	if (table1 == null || table2 == null) {
	    return str;
	}
	table1 = table1.trim();
	table2 = table2.trim();
	int len1 = table1.length();
	int len2 = table2.length();
	if (len1 == 0 || len2 == 0) {
	    return str;
	}
	int len = Math.min(len1, len2);
	char ch = 0;
	char ch1 = 0;
	char ch2 = 0;
	StringBuffer sbuf = new StringBuffer();
	for (int index = 0; index < str.length(); index++) {
	    ch = str.charAt(index);
	    for (int i = 0; i < len; i++) {
		ch1 = table1.charAt(i);
		ch2 = table2.charAt(i);
		if (ch == ch1) {
		    ch = ch2;
		    break;
		}
	    }
	    sbuf.append(ch);
	}
	return sbuf.toString();
    }

    public static String xmlEncode(String text) {
	int length = text.length();
	if (text != null && length > 0) {
	    StringBuffer ret = new StringBuffer(length * 12 / 10);

	    int last = 0;
	    for (int i = 0; i < length; i++) {
		char c = text.charAt(i);
		switch (c) {
		//case ' ' : ret.append("&nbsp;"); break;
		case '&':
		    if (last < i) {
			ret.append(text.substring(last, i));
		    }
		    last = i + 1;

		    ret.append("&amp;");
		    break;
		case '>':
		    if (last < i) {
			ret.append(text.substring(last, i));
		    }
		    last = i + 1;

		    ret.append("&gt;");
		    break;
		case '<':
		    if (last < i) {
			ret.append(text.substring(last, i));
		    }
		    last = i + 1;

		    ret.append("&lt;");
		    break;
		case '\"':
		    if (last < i) {
			ret.append(text.substring(last, i));
		    }
		    last = i + 1;

		    ret.append("&quot;");
		    break;
		case '\'':
		    if (last < i) {
			ret.append(text.substring(last, i));
		    }
		    last = i + 1;

		    ret.append("&apos;");
		    break;

		default:
		    break;
		}
	    }

	    if (last < length) {
		ret.append(text.substring(last));
	    }

	    return ret.toString();
	}

	return text;
    }

    public static String htmlEncode(String text) {
	int length = text.length();
	if (text != null && length > 0) {
	    StringBuffer ret = new StringBuffer(length * 12 / 10);

	    boolean isEncodeSpace = true;
	    int last = 0;
	    for (int i = 0; i < length; i++) {
		char c = text.charAt(i);
		switch (c) {
		case ' ':
		    if (isEncodeSpace) {
			if (last < i) {
			    ret.append(text.substring(last, i));
			}
			last = i + 1;

			ret.append("&nbsp;");
			isEncodeSpace = false;
		    } else {
			isEncodeSpace = true;
		    }
		    break;
		case '&':
		    if (last < i) {
			ret.append(text.substring(last, i));
		    }
		    last = i + 1;

		    ret.append("&amp;");
		    isEncodeSpace = false;
		    break;
		case '>':
		    if (last < i) {
			ret.append(text.substring(last, i));
		    }
		    last = i + 1;

		    ret.append("&gt;");
		    isEncodeSpace = false;
		    break;
		case '<':
		    if (last < i) {
			ret.append(text.substring(last, i));
		    }
		    last = i + 1;

		    ret.append("&lt;");
		    isEncodeSpace = false;
		    break;
		case '\"':
		    if (last < i) {
			ret.append(text.substring(last, i));
		    }
		    last = i + 1;

		    ret.append("&quot;");
		    isEncodeSpace = false;
		    break;
		// it does not work in IE
		//					case '\'' :
		//						if (last < i)
		//						{
		//							ret.append(text.substring(last, i));
		//						}
		//						last = i + 1;
		//						
		//						ret.append("&apos;");
		//						isEncodeSpace = false;
		//						break;
		case '\n':
		    if (last < i) {
			ret.append(text.substring(last, i));
		    }
		    last = i + 1;

		    ret.append("<br/>");
		    isEncodeSpace = false;
		    break;

		default:
		    isEncodeSpace = false;
		    break;
		}
	    }

	    if (last < length) {
		ret.append(text.substring(last));
	    }

	    return ret.toString();
	}

	return text;
    }

    
    /**
     * Return string to upper case
     * @param str
     * @return
     */
    public static String getUpperCaseString(String str) {
	if (str == null) {
	    return null;
	}
	StringBuffer buf = new StringBuffer();
	int length = str.length();
	for (int i = 0; i < length; i++) {
	    char ch = str.charAt(i);
	    if (Character.isUpperCase(ch)) {
		buf.append(ch);
	    } else {
		buf.append(Character.toUpperCase(ch));
	    }
	}
	return buf.toString(); 
    }
    
    
    /**
     * Return string to lower case
     * @param str
     * @return
     */
    public static String getLowerCaseString(String str) {
	if (str == null) {
	    return null;
	}
	StringBuffer buf = new StringBuffer();
	int length = str.length();
	for (int i = 0; i < length; i++) {
	    char ch = str.charAt(i);
	    if (Character.isLowerCase(ch)) {
		buf.append(ch);
	    } else {
		buf.append(Character.toLowerCase(ch));
	    }
	}
	return buf.toString(); 
    }
    
    /**
     * Concatenate string elements
     * 
     *  @param elements
     *  list of elements.
     *  @return the string.
     *  
     **/
    public static String buildString(String... elements) {
	StringBuilder stringBuffer = new StringBuilder(70);
	for (int i = 0; i < elements.length; i++) {
	    stringBuffer.append(elements[i]);
	}
	return stringBuffer.toString();
    }


    public static String[] getTwoLines(String str, int firstLineLimit) {
	String[] lines = new String[2];
	if (isEmpty(str)) {
	    return lines;
	}
	str = str.trim();
	if (firstLineLimit <=0 || str.length() <= firstLineLimit) {
	    lines[0] = str;
	    return lines;
	}
	lines[0] = str.substring(0, firstLineLimit);
	int index = getMaxLastIndex(lines[0], " .,;-/:");
	if (index > -1) {
	    index++;
	    lines[0] = str.substring(0, index);
	} else {
	    index = firstLineLimit;
	}
	lines[1] = str.substring(index);
	
	
	lines[0] = lines[0].trim();
	lines[1] = lines[1].trim();
	return lines;
    }
    
    public static int getMaxLastIndex(String str, String chars) {
	int index = -1;
	if (str == null || str.length() == 0 || chars == null || chars.length() == 0) {
	    return index;
	}
	for (int i = 0; i < chars.length(); i++) {
	    index = Math.max(index, str.lastIndexOf(chars.charAt(i)));
	}
	return index;
    }
    
    public static String normalizeString(String str) {
	return nullIfEmpty(str, true);
    }
    
    public static String[] normalizeArray(String[] array) {
	List<String> list = new ArrayList<String>();
	if (array == null || array.length == 0) {
	    return array;
	}
	for (String s: array) {
	    // Not trim string
	    if (nullIfEmpty(s, false) == null) {
		continue;
	    }
	    list.add(s);
	}
	return (String[]) list.toArray(new String[0]);
    }
    
    
    public static String getLastWords(String str, int n) {
	if (isEmpty(str) || n <= 0) {
	    return str;
	}
	//str = str.trim();
	String[] words = str.split(" ");
	
	//TODO: Must analyze: We remove more blank chars between words 
	words = normalizeArray(words);
	if (words.length <= n) {
	    return str;
	}    
	StringBuffer buf = new StringBuffer();
	boolean first = true;

	for (int i = words.length - n; i < words.length; i++) {
	    if (!first) {
		buf.append(" ");
	    }
	    buf.append(words[i]);
	    first = false;
	}
	return buf.toString();
    }
    

    public static String[] getWords(String str) {
	if (isEmpty(str)) {
	    return new String[0];
	}
	//str = str.trim();
	String[] words = str.split(" ");
	
	//TODO: Must analyze: We remove more blank chars between words 
	words = normalizeArray(words);
	return words;
	
    }
    
    public static boolean like(String str, String expr) {
	return doLike(str, expr, null, null, false);
    }

    public static boolean like(String str, String expr, String likeAnyCh, String likeOneCh) {
	return doLike(str, expr, likeAnyCh, likeOneCh, false);
    }

    public static boolean like(String str, String expr, String likeAnyCh, String likeOneCh, boolean isAutoFix) {
	return doLike(str, expr, likeAnyCh, likeOneCh, isAutoFix);
    }

    ////
    
    public static boolean like(String str, String expr, boolean isAutoFix) {
	return doLike(str, expr, null, null, isAutoFix);
    }

    ////
    
    public static boolean likeRegex(String str, String expr) {
	return doLikeRegex(str, expr);
    }
    
    ////
	
    private static boolean doLike(String str, String expr, String likeAnyCh, String likeOneCh, boolean isAutoFix) {
	if (str == null || expr == null) {
	    return false;
	}
	likeAnyCh = normalizeLikeAnyCh(likeAnyCh);
	likeOneCh = normalizeLikeOneCh(likeOneCh);

	expr = doNormalizeLike(expr, likeAnyCh, likeOneCh, ".*", ".", isAutoFix);
	
	return doLikeRegex(str, expr);
    }

    private static boolean doLikeRegex(String str, String expr) {
	if (str == null || expr == null) {
	    return false;
	}
	return str.matches(expr);
    }
    
    ////
    
    public static String normalizeLike(String expr, String likeAnyCh, String likeOneCh) {
	return doNormalizeLike(expr, likeAnyCh, likeOneCh, null, null, false);
    }

    public static String normalizeLike(String expr, String likeAnyCh, String likeOneCh, boolean isAutoFix) {
	return doNormalizeLike(expr, likeAnyCh, likeOneCh, null, null, isAutoFix);
    }
    
    public static String normalizeLike(String expr, String likeAnyCh, String likeOneCh, String likeAnyChNew, String likeOneChNew, boolean isAutoFix) {
	return doNormalizeLike(expr, likeAnyCh, likeOneCh, likeAnyChNew, likeOneChNew, isAutoFix);
    }
    
    ////

    public static String normalizeLikeRegex(String expr, String likeAnyCh, String likeOneCh) {
	return doNormalizeLike(expr, likeAnyCh, likeOneCh, ".*", ".", false);
    }

    public static String normalizeLikeRegex(String expr, String likeAnyCh, String likeOneCh, boolean isAutoFix) {
	return doNormalizeLike(expr, likeAnyCh, likeOneCh, ".*", ".", isAutoFix);
    }
    
    ////
    
    private static String doNormalizeLike(String expr, String likeAnyCh, String likeOneCh, String likeAnyChNew, String likeOneChNew, boolean isAutoFix) {
	if (expr == null || expr.length() == 0) {
	    return expr;
	}
	
	//
	likeAnyCh = normalizeLikeAnyCh(likeAnyCh);
	likeOneCh = normalizeLikeOneCh(likeOneCh);
	
	// expr =  "%" + expr + "%"
	if (isAutoFix) {
	    if (!hasLikeChars(expr, likeAnyCh, likeOneCh)) {
		expr = likeAnyCh + expr + likeAnyCh;
	    }
	}
	
	// replace for example: "?" -> "."
	if (likeOneCh != null && !isEmpty(likeOneChNew)) {
	    expr = expr.replace(likeOneCh, likeOneChNew);
	}
	
	// replace for example "%" -> ".*"
	if (!isEmpty(likeAnyChNew)) {
	    expr = expr.replace(likeAnyCh, likeAnyChNew);
	}
	
	//if (isNormRegexpChars) {
	//    expr = normalizeRegexpChars(expr);
	//}
	return expr;
    }
    
    private static boolean hasLikeChars(String expr, String likeAnyCh, String likeOneCh) {
	if (expr == null) {
	    return false;
	}
	return expr.contains(likeAnyCh) || (likeOneCh != null && expr.contains(likeOneCh));
    }
    
    public static String normalizeRegexChars(String str) {
      if (str == null ) {
	  return null;
      }

      int len = str.length();
      if (len == 0) {
	  return "";
      }

      StringBuilder sb = new StringBuilder(len * 2);
      for (int i = 0; i < len; i++) {
        char c = str.charAt(i);
        if (REGEX_STR.indexOf(c) != -1) {
          sb.append("\\");
        }
        sb.append(c);
      }
      return sb.toString();
    }
    
    private static String normalizeLikeAnyCh(String likeAnyCh) {
	if (likeAnyCh == null) {
	    return DEFAULT_LIKE_ANY_CH;
	}
	likeAnyCh = likeAnyCh.trim();
	if (likeAnyCh.length() == 0) {
	    return DEFAULT_LIKE_ANY_CH;
	}
	likeAnyCh = likeAnyCh.substring(0, 1);
	if (LIKE_ANY_STR.contains(likeAnyCh)) {
	    return likeAnyCh; 
	}
	return DEFAULT_LIKE_ANY_CH;
    }
    
    private static String normalizeLikeOneCh(String likeOneCh) {
	if (likeOneCh == null) {
	    //return DEFAULT_LIKE_ONE_CH;
	    return null;
	}
	likeOneCh = likeOneCh.trim();
	if (likeOneCh.length() == 0) {
	    //return DEFAULT_LIKE_ONE_CH;
	    return null;
	}
	likeOneCh = likeOneCh.substring(0, 1);
	if (LIKE_ONE_STR.contains(likeOneCh)) {
	    return likeOneCh; 
	}
	//return DEFAULT_LIKE_ONE_CH;
	return null;
    }
    
    
    
    
    
    public static String textFormat(Object obj, int cols, int rows) {
 	if (obj == null) {
 	   return "";
 	}
 	String st = obj.toString().trim();
 	StringTokenizer t = new StringTokenizer(st, " \t\r\f");
 	StringBuffer buf = new StringBuffer();
 	int len = 0;
 	int rowNumber = 0;
 	int tokenNumber = 0;
 	while (t.hasMoreTokens()) {
 	    String token = t.nextToken();
 	    if (len + token.length() > cols) {
 		buf.append('\n');
 		rowNumber++;
 		len = 0;
 	    } else {
 		if (tokenNumber > 0)
 		    buf.append(' ');
 		len += token.length();
 	    }

 	    if (rows > 0 && rowNumber > rows) {
 		buf.append(token + ELLIPSIS);
 		break;
 	    } else {
 		buf.append(token);
 	    }
 	    tokenNumber++;
 	}
 	return buf.toString();
     }

     public static String textFormat(Object obj, int cols) {
 	return textFormat(obj, cols, DEFAULT_ROWS);
     }

     public static String textFormat(Object obj) {
 	return textFormat(obj, DEFAULT_COLS);
     }

     public static String textErrorFormat(Object obj) {
 	if (obj instanceof StackTraceElement[]) {
 	    StringBuffer buf = new StringBuffer();

 	    StackTraceElement[] trace = (StackTraceElement[]) obj;
 	    for (int i = 0; i < trace.length; i++) {
 		buf.append("\tat " + trace[i]);
 	    }
 	    obj = buf.toString();
 	}
 	return textFormat(obj, DEFAULT_COLS, DEFAULT_ERROR_ROWS);
     }
    
    public static boolean isJavaIdentifier(String str) {
	str = normalizeString(str);
	if (str == null) {
	    return false;
	}
	boolean start = true;
	boolean valid = true;
	for (char b : str.toCharArray()) {
	    if (start) {
		valid = valid && Character.isJavaIdentifierStart(b);
		start = false;
	    } else {
		valid = valid && Character.isJavaIdentifierPart(b);
	    }
	    if (!valid) {
		return false;
	    }
	}
	return true;
    }
}
