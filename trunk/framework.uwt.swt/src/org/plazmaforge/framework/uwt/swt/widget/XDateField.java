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

package org.plazmaforge.framework.uwt.swt.widget;

import java.util.Date;
import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;

public class XDateField extends AbstractCombo {

    protected static final String IMAGE = "/org/plazmaforge/framework/uwt/widget/DateChooser.png";

    /** Default image for the button */
    protected static Image buttonImage;

    
    static {
	buttonImage = null; //new Image(Display.getCurrent(), XDateField.class.getResourceAsStream(IMAGE));
    }
    
    /**
     * Constructs a new instance of this class given its parent and a style value
     * describing its behavior and appearance.<p>
     * 
     * The widget is initialized with a default image for the button, and a
     * default <code>DateFormatter</code>.
     * 
     * @param parent a composite control which will be the parent of the new instance (cannot be null)
     * @param style the style of control to construct
     */
    
    public XDateField(Composite parent, int style) {
	super(parent, style);
	initialize();
	setImage(buttonImage);
	pack();
    }

    private void initialize() {
	setValueClass(Date.class);
    }

    protected String getDefaultPattern() {
	 //[MUST-ENABLE]
	return "dd-MM-yyyy"; //PlatformEnvironment.getDatePattern(); 
    }

    /**
     * Called just before the popup is droppped. The selected date of the
     * 
     * calendar is setted to the current date present in the formatted text.
     * 
     * @see org.plazmaforge.framework.client.swt.controls.AbstractCombo#beforeDrop()
     */
    @Override
    protected void beforeDrop() {
        Date d = (Date) getFormatterValue();
        CCalendar cal = (CCalendar) popupContent;
        cal.setSelectedDate(d != null ? d : cal.getTodayDate());
    }

    /**
     * Manages popup content events. Extend the selection behaviour, adding the
     * selected date in the <code>data</code> attribute of the event.
     * 
     * @param event event
     * @see org.plazmaforge.framework.client.swt.controls.AbstractCombo#contentEvent(org.eclipse.swt.widgets.Event)
     */
      
    @Override
    protected void contentEvent(Event event) {
	switch (event.type) {
	    case SWT.Selection :
		
		//oha: fix bug: set value before notify super selection event
		//super.contentEvent(event);
		
                if (event.doit) {
                    setFormatterValue(event.data);
                    super.contentEvent(event);
                    dropDown(false);
                    text.setFocus();
                }
                break;
            default :
                super.contentEvent(event);
                break;
        }
    }

    
    protected void handleKeyReleased(KeyEvent e) {
	    int keyCode = e.keyCode;
	    if (keyCode == 32) {
		dropDown(true);
                text.setFocus();
                return;
	    }
	    super.handleKeyReleased(e);
    }
    
    /**
     * Creates the button widget. The default appearence with an arrow is
     * replaced by a button with an image.
     * 
     * @param style button style
     * @see org.plazmaforge.framework.uwt.swt.widget.AbstractCombo#createPopupControl(int)
     */
     protected void createPopupControl(int style) {
         //style &= ~(SWT.ARROW | SWT.DOWN);
         super.createPopupControl(style);
     }
     
     

     /**
      * Creates the popup content. The content is a <code>CCalendar</code>.
      * 
      * @see org.plazmaforge.framework.client.swt.controls.AbstractCombo#createPopupContent()
      */
     @Override
     protected void createPopupContent() {
         popupContent = new CCalendar(popup, SWT.NONE);
         
         //[MUST-ENABLE]
         //initPopupContentListener();
     }
     

     /**
      * Creates the text widget. Overrides the default implementation to create a
      * <code>FormattedText</code> with the default formatter for <code>Date</code>
      * values.
      * The formatter is provided by <code>DefaultFormatterFactory</code>. By default
      * a <code>DateFormatter</code> is returned. This can be changed by
      * registering a new formatter for Date class.
      * 
      * @param style text style
      * @see org.plazmaforge.framework.client.swt.controls.AbstractCombo#createTextControl(int)
      */
     @Override
     protected void createControl(int style) {
	 super.createControl(style);
	 setFormatter(createFormatter());
         setEditable(true);
         
         //initKeyListener();
         
         //[MUST-ENABLE]
         //initModifyListener();
     }

     
     protected TextFormatter createFormatter(String pattern) {
	 return new DateFormatter(pattern);
     }


     /**
      * Returns the current <code>Date</code> value of the widget.<p>
      * 
      * @return Current value
      */
     public Date getDate() {
	 return (Date) getFormatterValue();
	 
     }

     /**
      *  Returns true if grid is visible in the calendar popup.
      * 
      * @return Returns the grid visible status.
      */
     public boolean isGridVisible() {
	 return ((CCalendar) popupContent).isGridVisible();
     }
     
     
    /**
     * Sets the grid visible or not in the calendar popup. By default, the grid
     * is visible.
     * 
     * @param gridVisible <code>true</code> to set grid visible, else <code>false</code>
     */
    public void setGridVisible(boolean gridVisible) {
	((CCalendar) popupContent).setGridVisible(gridVisible);
	popup.pack();
    }

    private boolean isFixImage;
     
    /**
     * Sets a new image to display on the button, replacing the default one.
     * 
     * @param image new image
     */
    public void setImage(Image image) {
	if (popupControl == null || !isFixImage) {
	    return;
	}
	setToolControlImage(popupControl, image);
    }

    /**
     * Sets the locale used both by the input mask and the calendar.
     * 
     * @param loc locale
     */
    public void setLocale(Locale loc) {
        DateFormatter formatter = (DateFormatter) getFormatter();
        if (formatter != null) {
            formatter.setLocale(loc);
        }
        ((CCalendar) popupContent).setLocale(loc);
    }

    /**
     * Sets the theme to apply to the calendar popup.
     * 
     * @param theme new theme (must not be null)
     */
    public void setTheme(CalendarTheme theme) {
	((CCalendar) popupContent).setTheme(theme);
    }

    /**
     * Sets a new <code>Date</code> value.
     * 
     * @param value new date value
     */
    public void setDate(Date value) {
	//[MUST-ENABLE]
	//super.setValue(value);
	setFormatterValue(value);
    }
    
    public void setValue(Object value) {
	if (value == null) {
	    setDate(null);
	    return;
	}
	if (!(value instanceof Date)) {
	    throw new IllegalArgumentException("Value must be Date");
	}
	setDate((Date) value);
    }
    
    public Object getValue() {
	return getDate();
    }
	
    protected Control getTextParent() {
	return text;
    }
    
    public void setEditable(boolean editable) {
	super.setEditable(editable);
	setPopupControlEnabled(editable);
    }
    

    /////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Formatter methods
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected Object getFormatterValue() {
	return AbstractTextFormatter.getValue(text);
    }

    protected void setFormatterValue(Object value) {
	AbstractTextFormatter.setValue(text, value);
    }

    /**
     * Returns <code>true</code> if the current value is valid, else <code>false</code>.
     * 
     * @return <code>true</code> if valid.
     */
    public boolean isValid() {
      return getFormatter() != null ? getFormatter().isValid() : true;
    }

    
    
    public void setValueClass(Class valueClass) {
	if (getFormatter() != null && getFormatter().getValueClass() == null) {
	    getFormatter().setValueClass(valueClass);
	    //TODO
	    //getModel().setValueClass(valueClass);
	}
	
    }
    
    public Class getValueClass() {
	return getFormatter() == null ? null : getFormatter().getValueClass();
    }
    
    public void setFormatter(TextFormatter formatter) {
	AbstractTextFormatter.initFormatter(text, formatter);
    }

    public TextFormatter getFormatter() {
	return AbstractTextFormatter.getFormatter(text);
    }

    public void setPattern(String pattern) {
	TextFormatter formatter = createFormatter(pattern);
	setFormatter(formatter);
    }
    
    protected TextFormatter createFormatter() {
	return createFormatter(getDefaultPattern());
    }

   
}
