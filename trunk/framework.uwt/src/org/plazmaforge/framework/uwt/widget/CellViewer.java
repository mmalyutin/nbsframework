package org.plazmaforge.framework.uwt.widget;


/**
 * Viewer with cell:
 *  - display property
 *  - display format
 *  
 * @author ohapon
 *
 * @param <T>
 */
public abstract class CellViewer<T> extends Viewer<T> implements ICellViewer<T> {

    /**
     * Display property
     */
    private String displayProperty;

    /**
     * Display format
     */
    private String displayFormat;
    
    
    @Override
    public String getDisplayProperty() {
        return displayProperty;
    }

    @Override
    public void setDisplayProperty(String displayProperty) {
        this.displayProperty = displayProperty;
        fireChangeProperty(PROPERTY_DISPLAY_PROPERTY, displayProperty);
    }
    
    @Override
    public String getDisplayFormat() {
        return displayFormat;
    }

    @Override
    public void setDisplayFormat(String displayFormat) {
        this.displayFormat = displayFormat;
        fireChangeProperty(PROPERTY_DISPLAY_FORMAT, displayFormat);
    }    

}
