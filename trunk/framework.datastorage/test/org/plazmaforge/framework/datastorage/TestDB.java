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
package org.plazmaforge.framework.datastorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;

import org.plazmaforge.framework.util.DateUtils;

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
			"  PRODUCT_GROUP VARCHAR," + 
			"  PRICE NUMERIC(15, 2)" +
			")");
	stm.close();
	
	// TEST
	stm = connection.createStatement();
	stm.executeUpdate("CREATE TABLE TEST (" +
			"  A INTEGER," +
			"  B INTEGER," +
			"  C INTEGER" +
			")");
	stm.close();

	// TEST_DATA
	stm = connection.createStatement();
	stm.executeUpdate("CREATE TABLE TEST_DATA_TYPE (" +
			"  F_STRING VARCHAR," +
			"  F_INTEGER INTEGER," +
			"  F_FLOAT FLOAT," +
			"  F_DATE DATE," +
			"  F_TIME TIME," +
			"  F_DATE_TIME DATETIME," +
			"  F_DATE_FMT VARCHAR," + // Date in formatted string
			"  F_TIME_FMT VARCHAR" +  // Time in formatted string
			")");
	stm.close();
	
    }
    
    private void populateTables(Connection connection) throws SQLException {
	
	// PRODUCT
	PreparedStatement pstm = connection.prepareStatement("INSERT INTO PRODUCT (PRODUCT_ID, PRODUCT_NAME, PRODUCT_GROUP, PRICE) VALUES (?, ?, ?, ?)");
	for (int i = 101; i <= 200; i++) {
	    
	    pstm.setInt(1, i);
	    pstm.setString(2, "Product " + i);
	    pstm.setString(3, "Group 1");
	    pstm.setFloat(4, (float)(i/100.00 + i));
	    pstm.execute();
	}
	pstm.close();
	
	
	// TEST
	pstm = connection.prepareStatement("INSERT INTO TEST (A, B, C) VALUES (?, ?, ?)");
	
	/*
	1, 2, 3
	4, 5, 6
	7, 8, 9
	*/

	// Row 1
	pstm.setInt(1, 1);
	pstm.setInt(2, 2);
	pstm.setInt(3, 3);
	pstm.execute();
	
	// Row 2
	pstm.setInt(1, 4);
	pstm.setInt(2, 5);
	pstm.setInt(3, 6);
	pstm.execute();

	// Row 3
	pstm.setInt(1, 7);
	pstm.setInt(2, 8);
	pstm.setInt(3, 9);
	pstm.execute();
	
	pstm.close();

	
	// TEST_DATA
	pstm = connection.prepareStatement("INSERT INTO TEST_DATA_TYPE (F_STRING, F_INTEGER, F_FLOAT, F_DATE, F_TIME, F_DATE_TIME, F_DATE_FMT, F_TIME_FMT) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
	
	// Row 1
	pstm.setString(1, "String 1");
	pstm.setInt(2, 100);
	pstm.setFloat(3, 123.45f);
	pstm.setDate(4,  getSQLDate(2001, 1, 11));
	pstm.setTime(5,  getSQLTime(11, 1, 21));
	pstm.setTimestamp(6,  getSQLDateTime(2011, 1, 11, 21, 1, 11));
	pstm.setString(7, "21/Jan/1951");
	pstm.setString(8, "01:21 AM");
	pstm.execute();
	
	// Row 2
	pstm.setString(1, "String 2");
	pstm.setInt(2, 200);
	pstm.setFloat(3, 234.56f);
	pstm.setDate(4,  getSQLDate(2002, 2, 12));
	pstm.setTime(5,  getSQLTime(12, 2, 22));
	pstm.setTimestamp(6,  getSQLDateTime(2012, 2, 12, 22, 2, 12));
	pstm.setString(7, "22/Feb/1952");
	pstm.setString(8, "02:22 PM");
	pstm.execute();

	// Row 3
	pstm.setString(1, "String 3");
	pstm.setInt(2, 300);
	pstm.setFloat(3, 345.67f);
	pstm.setDate(4,  getSQLDate(2003, 3, 13));
	pstm.setTime(5,  getSQLTime(13, 3, 23));
	pstm.setTimestamp(6,  getSQLDateTime(2013, 3, 13, 23, 3, 13));
	pstm.setString(7, "23/Mar/1953");
	pstm.setString(8, "03:23 AM");
	pstm.execute();
	
	pstm.close();

    }
    
    
    protected  java.sql.Date getSQLDate(int year, int month, int day) {
	return  new java.sql.Date(year - 1900, month - 1, day);
    }

    protected java.sql.Time getSQLTime(int h, int m, int s) {
	return new java.sql.Time(h, m, s);
    }

    protected java.sql.Timestamp getSQLDateTime(int year, int month, int day, int h, int m, int s) {
	return new java.sql.Timestamp(year - 1900, month - 1, day, h, m, s, 0);
    }

   
}
