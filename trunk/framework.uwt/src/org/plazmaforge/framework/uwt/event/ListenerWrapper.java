package org.plazmaforge.framework.uwt.event;

import org.plazmaforge.framework.uwt.widget.Event;
import org.plazmaforge.framework.uwt.widget.Listener;

/**
 * Wrapper of Listener
 * 
 * @author ohapon
 *
 */
public class ListenerWrapper implements Listener {

    private Listener original;
    
    private int eventType;

    public ListenerWrapper(int eventType, Listener original) {
	this.eventType = eventType;
	this.original = original;
    }

    @Override
    public void handleEvent(Event event) {
	original.handleEvent(event);
    }

    public int getEventType() {
        return eventType;
    }

    public Listener getOriginal() {
        return original;
    }

    
    
}
