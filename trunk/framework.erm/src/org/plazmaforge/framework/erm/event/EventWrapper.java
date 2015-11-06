package org.plazmaforge.framework.erm.event;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.plazmaforge.framework.util.ClassUtils;

/**
 * Wrapper of event
 * 
 * @author ohapon
 *
 */
public class EventWrapper implements Event {

    private EventType type;
    
    private String eventClassName;
    
    private Event event;
    
    private boolean eventFail;
    
    

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    
    public String getEventClassName() {
        return eventClassName;
    }

    public void setEventClassName(String eventClassName) {
        this.eventClassName = eventClassName;
    }

    public void fire(Connection cn, Map<String, Object> parameters) throws SQLException {
	Event event = getEvent();
	if (event == null) {
	    return;
	}
	event.fire(cn, parameters);
    }

    private Event getEvent() {
	if (event == null) {
	    if (eventFail) {
		return event;
	    }
	    try {
		eventFail = false;
		event = (Event) ClassUtils.newInstance(getEventClassName());
	    } catch (Exception ex) {
		eventFail = true;
		throw new RuntimeException("Error create instance of event: " + ex.getMessage());
	    }
	}
	return event;
    }
}
