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

package org.plazmaforge.framework.uwt.swing.widget;

import java.text.DecimalFormatSymbols;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Number Document
 * 
 * @author ohapon
 *
 */
public class NumberDocument extends PlainDocument {

    private char groupingSeparator = ',';
	
    private char decimalSeparator = '.';


    public NumberDocument() {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        groupingSeparator = dfs.getGroupingSeparator();
        decimalSeparator = dfs.getDecimalSeparator();
     }

    
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

        String currentText = getText(0, getLength());
        String beforeOffset = currentText.substring(0, offs);
        String afterOffset = currentText.substring(offs, currentText.length());
        String proposedResult = beforeOffset + str + afterOffset;

        proposedResult = proposedResult.replaceAll("\\" + groupingSeparator + "", "");
        proposedResult = proposedResult.replace(decimalSeparator, '.');
        try {
            Double.parseDouble(proposedResult);
            str = str.replaceAll("\\.","" + decimalSeparator);
            super.insertString(offs, str, a);
        } catch (NumberFormatException e) {
            //
        }

    }

     public void remove(int offs, int len) throws BadLocationException {
        String currentText = getText(0, getLength());
        String beforeOffset = currentText.substring(0, offs);
        String afterOffset = currentText.substring(len + offs, currentText.length());
        String proposedResult = beforeOffset + afterOffset;

        proposedResult = proposedResult.replaceAll("\\" + groupingSeparator + "", "");
        proposedResult = proposedResult.replace(decimalSeparator, '.');

       try {
           if (proposedResult.length() != 0) {
               Double.parseDouble(proposedResult);
           }
           super.remove(offs, len);
        } catch (NumberFormatException e) {
           //
        }
    }

}
