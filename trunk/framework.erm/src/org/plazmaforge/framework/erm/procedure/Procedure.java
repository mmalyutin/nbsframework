package org.plazmaforge.framework.erm.procedure;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.plazmaforge.framework.erm.script.Script;

public interface Procedure {

    /**
     * Execute procedure by parameters
     * @param cn
     * @param parameters
     * @throws SQLException
     */
    void execute(Connection cn, Map<String, Object> parameters) throws SQLException;
    
    
    /**
     * Return procedure name
     * @return
     */
    String getName();
    
    /**
     * Return entry point
     * @return
     */
    String getEntryPoint();
    
    
    /**
     * Return script
     * @return
     */
    Script getScript();

}
