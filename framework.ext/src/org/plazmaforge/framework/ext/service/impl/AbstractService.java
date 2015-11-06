package org.plazmaforge.framework.ext.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.plazmaforge.framework.core.exception.DAOException;
import org.plazmaforge.framework.core.logging.Level;
import org.plazmaforge.framework.core.logging.Logger;
import org.plazmaforge.framework.erm.EntityManager;
import org.plazmaforge.framework.erm.sql.SQLEntityManager;
import org.plazmaforge.framework.sql.ConnectionHolder;
import org.plazmaforge.framework.util.StringUtils;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class AbstractService extends JdbcDaoSupport {

    private EntityManager entityManager;
    
    private Logger logger;
    
    public EntityManager getEntityManager() {
	if (entityManager == null) {
	    entityManager = new SQLEntityManager(new ERMConnectionHolder());
	}
        return entityManager;
    }
    
    private Connection getThisConnection() {
	return getConnection();
    }
    
    class ERMConnectionHolder implements ConnectionHolder {
	public Connection getConnection() throws SQLException {
	    return getThisConnection();
	}
    }

    protected boolean isEmpty(String str) {
	return StringUtils.isEmpty(str);
    }
    
    protected Logger getLogger() {
	if (logger == null) {
	    logger = Logger.getLogger(getClass().getName());
	    logger.setSoftLevel(Logger.DEFAULT_SOFT_LEVEL); // TODO: Must configure 'service.soft.level'
	}
	return logger;
    }

    // log-soft-level
    
    protected void log(String message) {
	getLogger().log(message);
    }

    // log-level
    
    protected void log(Level level, String message, Throwable error) {
	getLogger().log(level, message, error);
    }

    // log-info
    
    protected void logInfo(String message) {
	getLogger().info(message);
    }

    protected void logInfo(String message, Throwable error) {
	getLogger().info(message, error);
    }

    protected void logInfo(Throwable error) {
	getLogger().info(error);
    }

    // log-debug
    
    protected void logDebug(String message) {
	getLogger().debug(message);
    }

    protected void logDebug(String message, Throwable error) {
	getLogger().debug(message, error);
    }

    protected void logDebug(Throwable error) {
	getLogger().debug(error);
    }

    // log-warn
    
    protected void logWarn(String message) {
	getLogger().warn(message);
    }

    protected void logWarn(String message, Throwable error) {
	getLogger().warn(message, error);
    }

    protected void logWarn(Throwable error) {
	getLogger().warn(error);
    }
    
    // log-error
    
    protected void logError(String message) {
	getLogger().error(message);
    }

    protected void logError(String message, Throwable error) {
	getLogger().error(message, error);
    }

    protected void logError(Throwable error) {
	getLogger().error(error);
    }

    protected void handleError(Throwable ex)  throws DAOException {
	logError("Service error", ex);
	throw new DAOException(ex); // TODO: use ServiceException
    }

}
