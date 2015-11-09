package org.plazmaforge.framework.uwt.swt.widget;

import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Text;

public interface TextFormatter extends VerifyListener {

    /**
     * Called when the formatter is replaced by an other one in the <code>FormattedText</code>
     * control. Allow to release resources like additional listeners.
     */
    public void detach();
    

    /**
     * Returns the current value formatted for display.
     * This method is called by <code>FormattedText</code> when the <code>Text</code>
     * widget looses focus.
     * 
     * @return display string
    */
    public String getDisplayString();
    

    /**
     * Returns the current value formatted for editing.
     * This method is called by <code>FormattedText</code> when the <code>Text</code>
     * widget gains focus.
     * 
     * @return edit string
     */
    public String getEditString();


    /**
     * Returns <code>true</code> if current edited value is valid, else returns
     * <code>false</code>.
     * 
     * @return true if valid, else false
    */
    public boolean isValid();


    /**
     * Returns the current value of the text control. If the current value is
     * invalid for its type (ex. Date missing parts), returns <code>null</code>.
     * 
     * @return current value
    */
    Object getValue();
    

    /**
     * Sets the value to edit.
     * 
     * @param value value
     */
    void setValue(Object value);
    

    /**
     * Sets the <code>Text</code> widget that will be managed by this formatter.
     * 
     * @param text Text widget
     */
    void setText(Text text);
    
    /**
     * Specify whether or not <code>VerifyEvent</code> events must be processed.
     * Those events are the base of all formatters, allowing  on-the-fly
     * processing of each text change in the Text widget.
     * In some situations (e.g. when focus change), the <code>FormattedText</code>
     * must change the text in the widget without formatting.
     * 
     * @param ignore when true, VerifyEvent events are processed.
     */
    void setIgnore(boolean ignore);
    
    
    /**
     * Sets the value class
     * @param valueClass
     */
    void setValueClass(Class valueClass);
    
    /**
     * Gets the value class
     * @return
     */
    Class getValueClass();

}
