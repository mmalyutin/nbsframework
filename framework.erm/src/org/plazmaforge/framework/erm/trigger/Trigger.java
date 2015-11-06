package org.plazmaforge.framework.erm.trigger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.plazmaforge.framework.erm.event.EventType;
import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.erm.script.Script;

/**
 * The trigger interface
 * 
 * @author ohapon
 *
 */
public interface Trigger {

    /**
     * Get parent entity
     * 
     * @return
     */
    Entity getEntity();
    
    /**
     * Set parent entity
     * 
     * @param entity
     */
    void setEntity(Entity entity);
    
    
    /**
     * Return trigger type of event
     * @return
     */
    EventType getType();
    
    /**
     * Return name of trigger (optional)
     * @return
     */
    String getName();
    
    /**
     * Execute trigger by parameters
     * @param cn
     * @param parameters
     * @throws SQLException
     */
    void execute(Connection cn, Map<String, Object> parameters) throws SQLException;
    
    /**
     * Return text of trigger script
     * @return
     */
    Script getScript();
}
