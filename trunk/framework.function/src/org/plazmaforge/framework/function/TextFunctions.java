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

package org.plazmaforge.framework.function;

public class TextFunctions {

    private TextFunctions() {
    }
    
    
    public static String CHAR(Integer number) {
	if (number == null || number.intValue() < 1 || number.intValue() > 255) {
	    return null;
	}
	return Character.toString((char) number.intValue());
    }

    ////

    public static Integer LEN(String text) {
	if (text == null) {
	    return null;
	}
	return text.length();
    }

    ////
    
    public static String TRIM(String text) {
	if (text == null) {
	    return null;
	}
	return text.trim();
    }


    public static String LTRIM(String text) {
        if(text == null) {
            return null;
        }
        return text.replaceAll("^\\s+", "");
    }
    

    public static String RTRIM(String text) {
	if (text == null) {
	    return null;
	}
	return text.replaceAll("\\s+$", "");
    }    

    ////
    
    public static String LEFT(String text) {
	return LEFT(text, 1);
    }

    public static String LEFT(String text, Integer charactersNum) {
	if (text == null || charactersNum == null) {
	    return null;
	}
	return text.substring(0, charactersNum);
    }
    
    public static String RIGHT(String text) {
        return RIGHT(text, Integer.valueOf(1));
    }

    public static String RIGHT(String text, Integer charactersNum) {
	if (text == null || charactersNum == null) {
	    return null;
	} else {
	    int length = text.length();
	    return text.substring(length - charactersNum.intValue(), length);
	}
    }    

    
    ////
    
    public static String LOWER(String text) {
        if(text == null) {
            return null;
        }
        return text.toLowerCase();
    }
    
    public static String UPPER(String text) {
	if (text == null) {
	    return null;
	}
	return text.toUpperCase();
    }

    
    //REPLACE(String, Integer, Integer, String)
    
    //TODO: BAD FUNCTIONAL. REPLACE(String text, String oldText, String newText)
    //[Index-Number]
    public static String REPLACE(String originalText, Integer startPosition, Integer charsNum, String newText) {
	if (originalText == null || startPosition == null || charsNum == null || newText == null) {
	    return null;
	}
	StringBuffer output = new StringBuffer();
	output.append(originalText.substring(0,	toJPosition(startPosition.intValue())));
	output.append(newText);
	output.append(originalText.substring((toJPosition(startPosition.intValue() + charsNum.intValue())), originalText.length()));
	return output.toString();
	
    }
    
    //[Index: start with 0]
    public static String IREPLACE(String originalText, Integer startPosition, Integer charsNum, String newText) {
	if (originalText == null || startPosition == null || charsNum == null || newText == null) {
	    return null;
	}
	return REPLACE(originalText, toJPosition(startPosition), charsNum, newText);
    }

    //[Number: start with 1]
    public static String NREPLACE(String originalText, Integer startPosition, Integer charsNum, String newText) {
	return REPLACE(originalText, toJPosition(startPosition), charsNum, newText);
    }


    public static String REPEAT(String originalText, Integer numberOfTimes) {
	if (originalText == null || numberOfTimes == null) {
	    return null;
	}
	StringBuffer output = new StringBuffer();
	for (int i = 0; i < numberOfTimes.intValue(); i++) {
	    output.append(originalText);
	}
	return output.toString();
    }

 

    

    // POSITION(String, String)
    
    //[Index-Number]
    public static Integer POSITION(String findText, String textToSearch) {
	return POSITION(findText, textToSearch, 1);
    }

    //[Index: start with 0]
    public static Integer IPOSITION(String findText, String textToSearch) {
	return IPOSITION(findText, textToSearch, 0);
    }

    //[Number: start with 1]
    public static Integer NPOSITION(String findText, String textToSearch) {
	return POSITION(findText, textToSearch, 1);
    }
    
    
    // POSITION(String, String, Integer)
    
    //[Index-Number]
    public static Integer POSITION(String findText, String textToSearch, Integer startPosition) {
	if (findText == null || textToSearch == null || startPosition == null) {
	    return null;
	}
	return Integer.valueOf(textToSearch.indexOf(findText, toJPosition(startPosition.intValue())));
    }

    //[Index: start with 0]
    public static Integer IPOSITION(String findText, String textToSearch, Integer startPosition) {
	if (findText == null || textToSearch == null || startPosition == null) {
	    return null;
	}
	return toJPosition(POSITION(findText, textToSearch, toHPosition(startPosition)));
    }

    //[Index: start with 1]
    public static Integer NPOSITION(String findText, String textToSearch, Integer startPosition) {
	return POSITION(findText, textToSearch, startPosition);
    }
    
    
    //SUBSTR(String, Integer, Integer)

    //[Index-Number]
    public static String SUBSTR(String text, Integer startPosition, Integer endPosition) {
	if (text == null || startPosition == null || endPosition == null) {
	    return null;
	}
	return text.substring(toJPosition(startPosition), toJPosition(endPosition));
    }

    //[Index: start with 0]
    public static String ISUBSTR(String text, Integer startPosition, Integer endPosition) {
	if (text == null || startPosition == null || endPosition == null) {
	    return null;
	}
	return SUBSTR(text, toHPosition(startPosition), toHPosition(endPosition));
    }

    //[Number: start with 1]
    public static String NSUBSTR(String text, Integer startPosition, Integer endPosition) {
	return SUBSTR(text, startPosition, endPosition);
    }


    //STR(String, Integer)

    //[Index-Number]
    public static String STR(String text, Integer position) {
	if (text == null || position == null) {
	    return null;
	}
	return text.substring(toJPosition(position), toJPosition(position) + 1);
    }

    //[Index: start with 0]
    public static String ISTR(String text, Integer position) {
	if (text == null || position == null) {
	    return null;
	}
	return STR(text, toHPosition(position));
    }

    //[Index: start with 1]
    public static String NSTR(String text, Integer position) {
	if (text == null || position == null) {
	    return null;
	}
	return STR(text, position);
    }

    
    /**
     * Convert position from range 1..n+1 (Human)  to 0..n (Java) 
     * @param position
     */
    private static int toJPosition(int position) {
	return position - 1;
    }
    
    /**
     * Convert position from range 0..n (Java)  to 1..n+1 (Human)
     * @param position
     * @return
     */
    private static int toHPosition(int position) {
	return position + 1;
    }

}
