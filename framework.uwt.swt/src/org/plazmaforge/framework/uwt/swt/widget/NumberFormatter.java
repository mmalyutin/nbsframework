package org.plazmaforge.framework.uwt.swt.widget;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Hashtable;
import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.plazmaforge.framework.uwt.util.NumberUtils;


//[WD]
public class NumberFormatter extends AbstractTextFormatter {

    
    private static final char P_DIGIT = '#';
    
    private static final char P_ZERODIGIT = '0';
    
    private static final char P_DECIMAL_SEP = '.';
    
    private static final char P_GROUP_SEP = ',';
    
    private static final char P_MINUS = '-';

    /** Cache of number patterns by locales */
    private static Hashtable<Locale, String> cachedPatterns = new Hashtable<Locale, String>(3);

    /** Number formatter for display */
    protected DecimalFormat nfDisplay;
    
    /** Number formatter for display edit */
    protected DecimalFormat nfEdit;
    
    /** Buffer for the edit value */
    protected StringBuilder editValue;
    
    
    /** Number containing the current value */
    protected Number value;
    
    
    protected Class valueClass;
    
    
    /** Edit pattern */
    protected String editPattern;
    /** Length of groups (0 if no group separator) */
    protected int groupLen = 0;
    /** Current number of digits of the int part */
    protected int intCount = 0;
    /** Maximum number of digits of the int part */
    protected int intLen = 0;
    /** Maximum number of digits of the decimal part */
    protected int decimalLen = 0;
    /** Minimum number of digits of the int part (complement by 0) */
    protected int zeroIntLen = 0;
    /** Minimum number of digits of the decimal part (complement by 0) */
    protected int zeroDecimalLen = 0;
    /** Flag for display of the negative sign */
    protected boolean minus = false;
    /** Symboles used to format numbers */
    protected DecimalFormatSymbols symbols;
    /** Flag for dispplay of the decimal separator */
    protected boolean alwaysShowDec;
    /** Flag indicating that the buffer is modified and the Number value must be computed */
    protected boolean modified;
    /** Flag indicating that the int part has a fixed length */
    protected boolean fixedInt = true;
    /** Flag indicating that the decimal part has a fixed length */
    protected boolean fixedDec = true;

    /**
     * Constructs a new instance with all defaults :
     * <ul>
     *   <li>edit mask from NumberPatterns for the default locale</li>
     *   <li>display mask identical to the edit mask</li>
     *   <li>default locale</li>
     * </ul>
     */
    public NumberFormatter() {
      this(null, null, Locale.getDefault());
    }

    /**
     * Constructs a new instance with default edit and display masks for the given
     * locale.
     * 
     * @param loc locale
     */
    public NumberFormatter(Locale loc) {
      this(null, null, loc);
    }

    /**
     * Constructs a new instance with the given edit mask. Display mask is
     * identical to the edit mask, and locale is the default one.
     * 
     * @param editPattern edit mask
     */
    public NumberFormatter(String editPattern) {
      this(editPattern, null, Locale.getDefault());
    }

    /**
     * Constructs a new instance with the given edit mask and locale. Display mask
     * is identical to the edit mask.
     * 
     * @param editPattern edit mask
     * @param loc locale
     */
    public NumberFormatter(String editPattern, Locale loc) {
      this(editPattern, null, loc);
    }

    /**
     * Constructs a new instance with the given edit and display masks. Uses the
     * default locale.
     * 
     * @param editPattern edit mask
     * @param displayPattern display mask
     */
    public NumberFormatter(String editPattern, String displayPattern) {
      this(editPattern, displayPattern, Locale.getDefault());
    }

    /**
     * Constructs a new instance with the given masks and locale.
     * 
     * @param editPattern edit mask
     * @param displayPattern display mask
     * @param loc locale
     */
    public NumberFormatter(String editPattern, String displayPattern, Locale loc) {
      if ( editPattern == null ) {
        editPattern = cachedPatterns.get(loc);
        if ( editPattern == null ) {
            
          // TODO
          // Java 1.6 doesn't have class sun.text.resources.LocaleData !!!
            
          /*  
          ResourceBundle r = LocaleData.getLocaleElements(loc);
          String[] ps = r.getStringArray("NumberPatterns"); //$NON_NLS_1$
          editPattern = ps[0];
          int i = editPattern.indexOf(';');
          if ( i >= 0 ) {
            editPattern = editPattern.substring(0, i);
          }
          cachedPatterns.put(loc, editPattern);
          */
        }
      }

      editValue     = new StringBuilder();
      alwaysShowDec = false;
      setPatterns(editPattern, displayPattern, loc);
    }

    /**
     * Clear a part of the edition cache.
     * 
     * @param start beginning index
     * @param len length of portion to clear
     */
    protected void clearText(int start, int len) {
    	int d = editValue.indexOf(EMPTY + symbols.getDecimalSeparator());
    	boolean decimal = d >= start && d < start + len;
    	for (int i = 0; i < len; i++) {
    		char c = editValue.charAt(start + i);
    		if ( c >= '0' && c <= '9' ) {
    			if ( d < 0 || start + i < d ) {
    				intCount--;
    			}
    		}
    	}
    	editValue.delete(start, start + len);
    	if ( decimal ) {
    		editValue.insert(start, symbols.getDecimalSeparator());
    	}
    }

    /**
     * Formats the edit buffer. Inserts group separators to the right places,
     * deletes excess decimal digits and add 0 to complete to the minimal length
     * of int and decimal parts. The position of the cursor is preserved.
     * 
     * @param curseur Current position of the cursor
     * @return New position of the cursor
     */
    protected int format(int curseur) {
    	// Inserts zeros in the int part
    	int i = editValue.length() > 0
    					&& editValue.charAt(0) == symbols.getDecimalSeparator()
    				  ? 1 : 0;
    	while ( intCount < zeroIntLen ) {
    		editValue.insert(i, '0');
    		intCount++;
    		curseur++;
    	}
    	while ( intCount > zeroIntLen ) {
    		if ( editValue.charAt(0) == '0' ) {
      		intCount--;
    		} else if ( editValue.charAt(0) != symbols.getGroupingSeparator() ) {
    			break;
    		}
    		editValue.deleteCharAt(0);
    		if ( curseur > 0 ) curseur--;
    	}

    	// Recreates the groups in the int part
    	
    	//int n = intCount > groupLen ? groupLen - intCount % groupLen : 0;
    	int n = intCount > groupLen && groupLen > 0 ? groupLen - intCount % groupLen : 0; //OHA
    	
    	char c;
    	if ( n == groupLen ) {
    		n = 0;
    	}
    	
    	// oha: fixed add (groupLen > 0)
    	for (i = 0; groupLen > 0 && i < editValue.length(); i++) {
    		c = editValue.charAt(i);
    		if ( c >= '0' && c <= '9' ) {
    			if ( n == groupLen ) {
    				editValue.insert(i, symbols.getGroupingSeparator());
    				if ( curseur >= i ) {
    					curseur++;
    				}
    				n = 0;
    			} else {
    				n++;
    			}
    		} else if ( c == symbols.getGroupingSeparator() ) {
    			if ( n != groupLen ) {
    				editValue.deleteCharAt(i);
    				if ( curseur >= i ) {
    					curseur--;
    				}
    				i--;
    			} else {
    				n = 0;
    			}
    		} else if ( c == symbols.getDecimalSeparator() ) {
    			break;
    		}
    	}

    	// Truncates / completes by zeros the decimal part
    	i = editValue.indexOf(EMPTY + symbols.getDecimalSeparator());
    	if ( i >= 0 ) {
    	  for (int j = i + 1; j < editValue.length();) {
          c = editValue.charAt(j);
          if ( c < '0' || c > '9' ) {
            editValue.delete(j, j + 1);
          } else {
            j++;
          }
        }

        if ( editValue.length() > i + decimalLen + 1 ) {
    			editValue.setLength(i + decimalLen + 1);
    		}

    		i = editValue.length() - i - 1;
    		while ( i < zeroDecimalLen ) {
    			editValue.append('0');
    			i++;
    		}
    	}

    	return curseur;
    }

    /**
     * Returns the current value formatted for display.
     * This method is called by <code>FormattedText</code> when the <code>Text</code>
     * widget looses focus.
     * The displayed value is the result of formatting on the <code>Number</code>
     * with a <code>DecimalFormat<code> for the display pattern passed in
     * constructor.
     * 
     * @return display string if valid, empty string else
     */
    public String getDisplayString() {
      return getValue() != null ? nfDisplay.format(value) : EMPTY;
    }

    /**
     * Returns the current value formatted for editing.
     * This method is called by <code>FormattedText</code> when the <code>Text</code>
     * widget gains focus.
     * The value returned is the content of the StringBuilder <code>editValue</code>
     * used as cache.
     * 
     * @return edit string
     */
    public String getEditString() {
      return editValue.toString();
    }

    /**
     * Returns the current value of the text control if it is a valid <code>Number</code>.
     * If the buffer is flaged as modified, the value is recalculated by parsing
     * with the <code>nfEdit</code> initialized with the edit pattern. If the
     * number is not valid, returns <code>null</code>.
     * 
     * @return current number value if valid, <code>null</code> else
     */
    public Object getValue() {
    	if ( modified ) {
        try {
            value = nfEdit.parse(editValue.toString());
    	} catch (ParseException e1) {
    	    value = null;
    	}
    	modified = false;
    	}
      return convertNumber(value);
    }
    
    
    protected Number convertNumber(Number value) {
	return NumberUtils.convertNumber(value, valueClass);
   }
    

    /**
     * Returns <code>true</code> if current edited value is valid, else returns
     * <code>false</code>. An empty value is considered as invalid.
     * 
     * @return true if valid, else false
     */
    public boolean isValid() {
      return (getValue() != null);
    }

    /**
     * Sets the flag to always display the decimal separator, even if the decimal
     * part is empty.
     * 
     * @param show true / false
     */
    public void setDecimalSeparatorAlwaysShown(boolean show) {
      alwaysShowDec = show;
      int i = editValue.lastIndexOf(EMPTY + symbols.getDecimalSeparator());
      if ( alwaysShowDec ) {
        if ( i == -1 ) {
          editValue.append(symbols.getDecimalSeparator());
        }
      } else {
        if ( i == editValue.length() - 1 ) {
          editValue.deleteCharAt(i);
        }
      }
    }

    /**
     * Sets the fixed length flags. By default, int and decimal part of the
     * pattern have a fixed length.
     * 
     * @param fixedInt flag for int part
     * @param fixedDec flag for decimal part
     */
    public void setFixedLengths(boolean fixedInt, boolean fixedDec) {
    	this.fixedInt = fixedInt;
    	this.fixedDec = fixedDec;
    }

    protected boolean isNormalGroupingSeparator;
    
    /**
     * Sets the patterns and initializes the technical attributes used to manage
     * the operations.
     * 
     * @param edit edit pattern
     * @param display display pattern
     * @param loc Locale to use
     * @throws IllegalArgumentException if a pattern is invalid
     */
    protected void setPatterns(String edit, String display, Locale loc) {
	
      // HACK
      //minus = true;
	
      symbols = new DecimalFormatSymbols(loc);
      //symbols = format.getDecimalFormatSymbols();
      
      symbols.setGroupingSeparator(' '); // OHA
      if (symbols.getGroupingSeparator() != P_DECIMAL_SEP) {
	  isNormalGroupingSeparator = true;
      }
	  

      // Analyse pattern
      boolean grouping = false;
      boolean decimal = false;
      groupLen = intLen = decimalLen = zeroIntLen = zeroDecimalLen = 0;
      minus = false;
      int i;
      for (i = 0; i < edit.length(); i++) {
        switch ( edit.charAt(i) ) {
        	case P_MINUS :
        		if ( i != 0 ) {
              throw new IllegalArgumentException(INVALID_PATTERN + edit);
        		}
        		minus = true;
        		break;
          case P_GROUP_SEP :
            if ( ! decimal ) {
              grouping = true;
              groupLen = 0;
            } else {
              throw new IllegalArgumentException(INVALID_PATTERN + edit);
            }
            break;
          case P_DECIMAL_SEP :
            grouping = false;
            decimal  = true;
            break;
          case P_ZERODIGIT :
            if ( decimal ) {
              zeroDecimalLen++;
            } else {
              zeroIntLen++;
            }
            // Continue on P_DIGIT...
          case P_DIGIT :
            if ( decimal ) {
              decimalLen++;
            } else {
              intLen++;
              if ( grouping ) {
                groupLen++;
              }
            }
            break;
          default :
          	throw new IllegalArgumentException(INVALID_PATTERN + edit);
        }
      }
      editPattern = edit;
      nfEdit = new DecimalFormat(minus ? editPattern.substring(1) : editPattern, symbols);

      // Create format
      nfDisplay = display != null ? new DecimalFormat(display) : nfEdit;

      // Initialize
      intCount = 0;
      for (i = 0; i < zeroIntLen; i++) {
        editValue.append('0');
        intCount++;
      }
      if ( alwaysShowDec || zeroDecimalLen > 0 ) {
        editValue.append(symbols.getDecimalSeparator());
      }
      for (i = 0; i < zeroDecimalLen; i++) {
        editValue.append('0');
      }
      value = 0L;
    }

    /**
     * Sets the value to edit. The value provided must be a <code>Number</code>.
     * 
     * @param value number value
     * @throws IllegalArgumentException if not a number
     */
    public void setValue(Object value) {
      boolean decimal = false;
      if ( value instanceof Number ) {
        this.value = (Number) value;
        
        if (this.valueClass == null && value != null) {
            this.valueClass = value.getClass();
        }
        
        editValue.setLength(0);
        editValue.append(nfEdit.format(this.value));
        intCount = 0;
        for (int i = 0; i < editValue.length(); i++) {
        	char c = editValue.charAt(i);
        	if ( c == symbols.getDecimalSeparator() ) {
        		decimal = true;
        	} else if ( c >= '0' && c <= '9' ) {
        		if ( ! decimal ) {
        			intCount++;
        		}
        	}
        }
        modified = false;
      } else if ( value == null ) {
      	clearText(0, editValue.length());
        updateText(editValue.toString(), format(0));
      } else {
        throw new IllegalArgumentException("Invalid number value");
      }
    }

    /**
     * Handles a <code>VerifyEvent</code> sent when the text is about to be modified.
     * This method is the entry point of all operations of formatting.
     * 
     * @see org.eclipse.swt.events.VerifyListener#verifyText(org.eclipse.swt.events.VerifyEvent)
     */
    public void verifyText(VerifyEvent e) {
      if ( ignore ) {
        return;
      }
      int p;
      e.doit = false;
      if ( e.keyCode == SWT.BS || e.keyCode == SWT.DEL ) {
      	clearText(e.start, (e.end > e.start) ? e.end - e.start : 1);
      	p = e.start;
      } else {
      	if ( e.end > e.start ) {
      		clearText(e.start, e.end - e.start);
      	}
      	p = e.start;

      	int d = editValue.indexOf(EMPTY + symbols.getDecimalSeparator());
      	for (int i = 0; i < e.text.length(); i++) {
      	    
      		char c = e.text.charAt(i);
      		boolean isEmulateDecimalSeparator = isNormalGroupingSeparator && c == P_DECIMAL_SEP;
      		
      		if ( c >= '0' && c <= '9' ) {
      			// Check number
      			if ( d >= 0 && p > d ) {
      				if ( fixedDec && p > d + decimalLen ) {
      					beep();
      					break;
      				}
      			} else {
      				if ( fixedInt && intCount >= intLen ) {
      					beep();
      					break;
      				}
      				d++;
      				intCount++;
      			}
      			// Insert char
      			editValue.insert(p++, c);
      		} else if ( c == symbols.getGroupingSeparator() ) {
      			if ( d >= 0 && p > d ) {
      				beep();
    					break;
      			}
      			editValue.insert(p++, c);
      			d++;
      		} else if ( c == symbols.getMinusSign() ) {
      			if ( p != 0 || ! minus ) {
      				beep();
      				break;
      			}
      			if ( editValue.charAt(0) != c ) {
        			editValue.insert(p++, c);
        			d++;
      			} else {
      				editValue.deleteCharAt(0);
      				d--;
      			}
      		} else if ( c == symbols.getDecimalSeparator() || isEmulateDecimalSeparator ) {
      		        if (isEmulateDecimalSeparator) {
      		            c = symbols.getDecimalSeparator();
      		        }
      			if ( d >= 0 && d < p ) {
      				beep();
      				break;
      			}
            d = p;
            editValue.insert(p++, c);
            intCount = 0;
            for (int j = 0; j < d; j++) {
              char c1 = editValue.charAt(j);
              if ( c1 >= '0' && c1 <= '9' ) {
                intCount++;
              }
            }
      		}
      	}
      }
      p = format(p);
      
      // oha: Fixed bug (add '0' before '.' if the place is empty)
      if (editValue.length() > 0 && editValue.charAt(0) == symbols.getDecimalSeparator()) {
	  editValue.insert(0, '0');
      }
      
      updateText(editValue.toString(), p);
      modified = true;
    }
 
    
    public Class getValueClass() {
        return valueClass;
    }

    public void setValueClass(Class valueClass) {
        this.valueClass = valueClass;
    }

}
