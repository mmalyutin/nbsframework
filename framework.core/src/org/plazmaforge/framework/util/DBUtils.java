package org.plazmaforge.framework.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    
    public static void close(Statement stm) {
	if (stm == null) {
	    return;
	}
	try {
	    stm.close();
	} catch (SQLException ex) {

	}
    }

    public static void close(ResultSet rs) {
	if (rs == null) {
	    return;
	}
	try {
	    rs.close();
	} catch (SQLException ex) {

	}
    }
    
    
    public static List<Object[]> getList(Connection cn, String sql) throws SQLException {
	return getList(cn, sql, null);
    }
	
    public static List<Object[]> getList(Connection cn, String sql, Object[] parameters) throws SQLException {
	PreparedStatement stm = null;
	ResultSet rs = null;
	try {
	    stm = cn.prepareStatement(sql);
	    if (parameters != null && parameters.length > 0) {
		for (int i = 0; i < parameters.length; i++) {
		    stm.setObject(i + 1, parameters[i]);
		}
	    }
	    rs = stm.executeQuery();
	    ResultSetMetaData md = rs.getMetaData();
	    int column = md.getColumnCount();
	    List<Object[]> list = new ArrayList<Object[]>();
	    while (rs.next()) {
		Object[] record = new Object[column];
		for (int i = 0; i < column; i++) {
		    record[i] = rs.getObject(i + 1);
		}
		list.add(record);
	    }
	    return list;
	} finally {
	    close(rs);
	    close(stm);
	}
    }

    public static Object getValue(Connection cn, String sql) throws SQLException {
	return getValue(cn, sql, null);
    }
    
    public static Object getValue(Connection cn, String sql, Object[] parameters) throws SQLException {
	List<Object[]> rows = getList(cn, sql, parameters);
	if (rows == null || rows.isEmpty()) {
	    return null;
	}
	Object[] row = rows.get(0);
	if (row == null || row.length == 0) {
	    return null;
	}
	return row[0];
    }
    
    public static void updateList(Connection cn, String sql) throws SQLException {
	updateList(cn, sql, null);
    }
    
    public static void updateList(Connection cn, String sql, List<Object[]> list) throws SQLException {
	PreparedStatement stm = null;
	try {
	    stm = cn.prepareStatement(sql);
	    if (list != null && !list.isEmpty()) {
		int count = list.size();
		for (int i = 0; i < count; i++) {
		    Object[] record = list.get(i);
		    int k = record.length;
		    for (int j = 0; j < k; j++) {
			stm.setObject(j + 1, record[j]);
		    }
		    stm.executeUpdate();
		}
	    } else {
		stm.executeUpdate();
	    }
	} finally {
	    close(stm);
	}
    }
    
    public static void execute(Connection cn, String sql) throws SQLException {
	execute(cn, sql, null);
    }
	
    public static void execute(Connection cn, String sql, Object[] parameters) throws SQLException {
	PreparedStatement stm = null;
	try {
	    stm = cn.prepareStatement(sql);
	    if (parameters != null && parameters.length > 0) {
		for (int i = 0; i < parameters.length; i++) {
		    stm.setObject(i + 1, parameters[i]);
		}
	    }
	    stm.execute();
	} finally {
	    close(stm);
	}
    }
}
