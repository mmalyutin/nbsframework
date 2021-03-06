/*
 * Copyright (C) 2012-2015 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */

/**
 * 
 */
package org.plazmaforge.framework.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Test Database
 * 
 * @author ohapon
 *
 */
public class TestDB {

    private boolean init;

    
    public boolean isInit() {
        return init;
    }

    public void init(Connection connection) throws SQLException {
	if (init) {
	    return;
	}
	init = true;
	createTables(connection);
	populateTables(connection);
    }
    
    private void createTables(Connection connection) throws SQLException {
	
	// PRODUCT
	Statement stm = connection.createStatement();
	stm.executeUpdate("CREATE TABLE PRODUCT (" +
			"  PRODUCT_ID INTEGER," +
			"  PRODUCT_NAME VARCHAR," +
			"  GROUP_NAME VARCHAR," + 
			"  PRICE NUMERIC(15, 2)," +
			"  CREATED_DATE DATE" +			
			")");
	stm.close();
	
	// BIG_PRODUCT
	stm = connection.createStatement();
	stm.executeUpdate("CREATE TABLE BIG_PRODUCT (" +
			"  PRODUCT_ID INTEGER," +
			"  PRODUCT_NAME VARCHAR," +
			"  GROUP_NAME VARCHAR," + 
			"  PRICE NUMERIC(15, 2)," +
			"  CREATED_DATE DATE" +			
			")");
	stm.close();
	
    }
    
    private void populateTables(Connection connection) throws SQLException {
	
	// PRODUCT
	PreparedStatement pstm = connection.prepareStatement("INSERT INTO PRODUCT (PRODUCT_ID, PRODUCT_NAME, GROUP_NAME, PRICE, CREATED_DATE) VALUES (?, ?, ?, ?, ?)");
	for (int i = 101; i <= 200; i++) {
	    
	    pstm.setInt(1, i);
	    pstm.setString(2, "Product " + i);
	    pstm.setString(3, "Group " + (i < 200 ? "1" : "2"));
	    pstm.setFloat(4, (float)(i/100.00 + i));
	    pstm.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
	    pstm.execute();
	}
	pstm.close();
	
	
	// BIG_PRODUCT
	pstm = connection.prepareStatement("INSERT INTO BIG_PRODUCT (PRODUCT_ID, PRODUCT_NAME, GROUP_NAME, PRICE, CREATED_DATE) VALUES (?, ?, ?, ?, ?)");
	for (int i = 101; i <= 2000; i++) {
	    
	    pstm.setInt(1, i);
	    pstm.setString(2, "Product " + i);
	    pstm.setString(3, "Group " + (i < 200 ? "1" : "2"));
	    pstm.setFloat(4, (float)(i/100.00 + i));
	    pstm.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
	    pstm.execute();
	}
	pstm.close();
	
    }
}
