package org.plazmaforge.framework.uwt.widget;

import org.plazmaforge.framework.core.data.Notifier;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.event.TypedListener;
import org.plazmaforge.framework.uwt.event.WindowEvent;
import org.plazmaforge.framework.uwt.event.WindowListener;
import org.plazmaforge.framework.uwt.graphics.Image;

/**
 * Base Window class
 * 
 * @author ohapon
 *
 */
public class Window extends Composite implements IDecorator {


    public static final String PROPERTY_MODAL = "modal";
    
    public static final String PROPERTY_PACK = "pack";
    
    public static final String PROPERTY_CENTER = "center";
    
    public static final String PROPERTY_UNDECORATED = "undecorated";
    
    public static final String PROPERTY_DISPOSE_WHEN_CLOSE = "disposeWhenClose";
    
    
    public static final String METHOD_OPEN = "open";
    
    public static final String METHOD_CLOSE = "close";
    
    public static final String METHOD_PACK = "pack";
    
    public static final String METHOD_CENTER = "center";
    
    public static final String METHOD_ACTIVATE = "activate";
    
    public static final String METHOD_DEACTIVATE = "deactivate";
    
    public static final String METHOD_MAXIMIZE = "maximize";
    
    public static final String METHOD_MINIMIZE = "minimize";
    
    
    
    private String title;
    
    private Image icon;
    
    
   
    /**
     * True if window has modal mode
     * NON_DELEGATE_PROPERTY
     */
    private boolean modal;
    
    /**
     * True if window has pack mode.
     * NON_DELEGATE_PROPERTY
     */
    private boolean pack;
    
    /**
     * True if window has center mode
     * NON_DELEGATE_PROPERTY
     */
    private boolean center;
    

    /**
     * Notifier of window
     */
    private Notifier notifier;
    
    /**
     * Resizable mode
     */
    private boolean resizable;
    
    /**
     * Closable mode (close button)
     */
    private boolean closable;
    
    /**
     * Minimizable mode (iconified button)
     */
    private boolean minimizable;
    
    /**
     * Maximizable mode (min/max button)
     */
    private boolean maximizable;
    
    /**
     * 
     */
    private boolean undecorated;
    
    /**
     * True if need dispose after close 
     */
    private boolean disposeWhenClose;

    
    public Window() {
	super();
	resizable = true;	// By default resize mode
	closable = true;	// By default close button
	minimizable = false;
	maximizable = false;
	undecorated = false;
	disposeWhenClose = true;
	
	setSize(300, 200); //TODO
    }

    protected void configure() {
	configureLocation();
	configureSize();
    }

    protected void configureLocation() {

    }

    protected void configureSize() {
	
    }
    
       
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        fireChangeProperty(PROPERTY_TITLE, title);
    }


    
    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
        fireChangeProperty(PROPERTY_ICON, icon);
    }
    
    public void setIcon(String path) {
	doGetIcon().setPath(path);
	fireChangeProperty(PROPERTY_ICON_PATH, path);
    }
    
    protected Image doGetIcon() {
	if (icon == null) {
	    icon = new Image();
	}
	return icon;
    }

    public void open() {
	activateUI();
	invokeDelegate(this, METHOD_OPEN, null);
    }
    
    public void close() {
	invokeDelegate(this, METHOD_CLOSE, null);
	//deactivateUI();
    }
    
    public void pack() {
	invokeDelegate(this, METHOD_PACK, null);
    }
    
    public void center() {
	invokeDelegate(this, METHOD_CENTER, null);
    }

    public void activate() {
	invokeDelegate(this, METHOD_ACTIVATE, null);
    }

    public void deactivate() {
	invokeDelegate(this, METHOD_DEACTIVATE, null);
    }

    public void maximize() {
	invokeDelegate(this, METHOD_MAXIMIZE, null);
    }

    public void minimize() {
	invokeDelegate(this, METHOD_MINIMIZE, null);
    }
    
    

    @Override
    protected boolean isInitProperty(String name) {
	// Block use 'visible' property
	if (PROPERTY_VISIBLE.equals(name)) {
	    return false;
	}
	return super.isInitProperty(name);
    }
    
    /**
     * Return true if window has modal mode
     * @return
     */
    public boolean isModal() {
        return modal;
    }

    /**
     * Set modal flag
     * 
     * @param modal
     */
    public void setModal(boolean modal) {
        this.modal = modal;
    }

    /**
     * Return true if window has pack mode
     * @return
     */
    public boolean isPack() {
        return pack;
    }

    /**
     * Set pack flag.
     * If flag is true the method doesn't pack window in display.
     * To center window use center() method.
     * @param pack
     */
    public void setPack(boolean pack) {
        this.pack = pack;
    }

    /**
     * Return true if window has center mode
     * @return
     */
    public boolean isCenter() {
        return center;
    }

    /**
     * Set center flag.
     * If flag is true the method doesn't center window in display.
     * To center window use center() method.
     * @param center
     */
    public void setCenter(boolean center) {
        this.center = center;
    }

   
    @Override
    public Notifier getNotifier() {
	return notifier;
    }

    @Override
    public void setNotifier(Notifier notifier) {
	this.notifier = notifier;
    }

    public boolean isResizable() {
        return resizable;
    }

    public void setResizable(boolean resizable) {
	checkNonRenderedProperty(PROPERTY_RESIZABLE);
        this.resizable = resizable;
    }

    public boolean isClosable() {
        return closable;
    }

    public void setClosable(boolean closable) {
	checkNonRenderedProperty(PROPERTY_CLOSABLE);
        this.closable = closable;
    }

    public boolean isMinimizable() {
        return minimizable;
    }

    public void setMinimizable(boolean minimizable) {
	checkNonRenderedProperty(PROPERTY_MINIMIZABLE);
        this.minimizable = minimizable;
    }

    public boolean isMaximizable() {
        return maximizable;
    }

    public void setMaximizable(boolean maximizable) {
	checkNonRenderedProperty(PROPERTY_MAXIMIZABLE);
        this.maximizable = maximizable;
    }

    public boolean isUndecorated() {
        return undecorated;
    }

    public void setUndecorated(boolean undecorated) {
	checkNonRenderedProperty(PROPERTY_UNDECORATED);
        this.undecorated = undecorated;
    }

    public boolean isDisposeWhenClose() {
        return disposeWhenClose;
    }

    public void setDisposeWhenClose(boolean disposeWhenClose) {
        this.disposeWhenClose = disposeWhenClose;
    }


    
    /**
     * Add <code>WindowListener</code> to <code>Window</code>
     * @param listener
     */
    protected void addWindowListener(final WindowListener listener) {
   	addListener(Events.Open, new TypedListener(listener) {
   	    public  void handleEvent(Event event) {
   		listener.openWindow(createWindowEvent(event));
   	    }
   	});
   	
   	addListener(Events.Close, new TypedListener(listener) {
   	    public  void handleEvent(Event event) {
   		listener.closeWindow(createWindowEvent(event));
   	    }
   	});
   	
   	addListener(Events.Activate, new TypedListener(listener) {
   	    public  void handleEvent(Event event) {
   		listener.activateWindow(createWindowEvent(event));
   	    }
   	});
   	
   	addListener(Events.Deactivate, new TypedListener(listener) {
   	    public  void handleEvent(Event event) {
   		listener.deactivateWindow(createWindowEvent(event));
   	    }
   	});
   	
   	addListener(Events.Iconify, new TypedListener(listener) {
   	    public  void handleEvent(Event event) {
   		listener.iconifyWindow(createWindowEvent(event));
   	    }
   	});

   	addListener(Events.Deiconify, new TypedListener(listener) {
   	    public  void handleEvent(Event event) {
   		listener.deiconifyWindow(createWindowEvent(event));
   	    }
   	});

   	
    }
    
    /**
     * Remove <code>WindowListener</code>from <code>Window</code>
     * @param listener
     */
    protected void removeWindowListener(WindowListener listener) {
   	removeListener(Events.Open, listener);
   	removeListener(Events.Close, listener);
   	removeListener(Events.Activate, listener);
   	removeListener(Events.Deactivate, listener);
   	removeListener(Events.Iconify, listener);
   	removeListener(Events.Deiconify, listener);
    }
    
    

    protected WindowEvent createWindowEvent(Event event) {
	WindowEvent e = new WindowEvent(getEventSource());
	populateEvent(e, event);
	return e;
    }

}
