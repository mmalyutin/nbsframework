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

package org.plazmaforge.framework.core.sql;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import org.plazmaforge.framework.util.ConverterUtils;

/**
 * 
 * @author ohapon
 *
 */
public class AbstractSQLWorker {

    protected String toString(Object value) {
	return ConverterUtils.toString(value);
    }
    
    protected Byte toByte(Object value) {
	return ConverterUtils.toByte(value);
    }

    protected Short toShort(Object value) {
	return ConverterUtils.toShort(value);
    }
    
    protected Integer toInteger(Object value) {
	return ConverterUtils.toInteger(value);
    }

    protected Long toLong(Object value) {
	return ConverterUtils.toLong(value);
    }

    protected Float toFloat(Object value) {
	return ConverterUtils.toFloat(value);
    }

    protected Double toDouble(Object value) {
	return ConverterUtils.toDouble(value);	
    }

    protected BigInteger toBigInteger(Object value) {
	return ConverterUtils.toBigInteger(value);
    }

    protected BigDecimal toBigDecimal(Object value) {
	return ConverterUtils.toBigDecimal(value);
    }

    protected Date toDate(Object value) {
	return ConverterUtils.toDate(value);
    }
    
    protected java.sql.Date toSQLDate(Object value) {
	return ConverterUtils.toSQLDate(value);
    }

    protected Timestamp toSQLTimestamp(Object value) {
	return ConverterUtils.toSQLTimestamp(value);
    }

    protected Time toSQLTime(Object value) {
	return ConverterUtils.toSQLTime(value);
    }

    protected Blob toSQLBlob(Object value) {
	return ConverterUtils.toSQLBlob(value);
    }

    protected Clob toSQLClob(Object value) {
	return ConverterUtils.toSQLClob(value);
    }
}
