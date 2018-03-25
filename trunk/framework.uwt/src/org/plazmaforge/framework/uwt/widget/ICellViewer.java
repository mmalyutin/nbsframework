package org.plazmaforge.framework.uwt.widget;

/**
 * 
 * Viewer with cell:
 *  - display property
 *  - display format
 * 
 * @author ohapon
 *
 */
public interface ICellViewer<T> extends IViewer<T> {

    String getDisplayProperty();
    
    void setDisplayProperty(String displayProperty);
    
    String getDisplayFormat();

    void setDisplayFormat(String displayFormat);
    
}
