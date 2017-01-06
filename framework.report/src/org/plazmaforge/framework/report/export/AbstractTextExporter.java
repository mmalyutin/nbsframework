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

package org.plazmaforge.framework.report.export;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;


public abstract class AbstractTextExporter extends AbstractBaseExporter {

    protected Writer writer;
    
    protected abstract void writeHeader() throws IOException;
    
    protected abstract void writeFooter() throws IOException;
    
    /////////////////////////////////////////////////////////////////
    
    
    protected String getEncoding( ){
	//TODO
	return "UTF-8";
    }
    
    protected void write(String str) throws IOException {
	writer.write(str);
    }
    
    protected OutputStream createOutputStream(String fileName)  throws IOException {
	return new FileOutputStream(fileName);
    }

    protected Writer createWriter(String fileName, String fileEncoding) throws IOException {
	return createWriter(fileName, fileEncoding, true);
    }
	
    protected Writer createWriter(String fileName, String fileEncoding, boolean buf) throws IOException {
	OutputStream os = createOutputStream(fileName);
	return createWriter(os, fileEncoding, buf);
    }
    
    protected Writer createWriter(OutputStream os, String fileEncoding, boolean buf) throws IOException {
	if (fileEncoding == null) {
	    return createWriter(new OutputStreamWriter(os), buf);
	}
	try {
	    return createWriter(new OutputStreamWriter(os, fileEncoding), buf);
	} catch (UnsupportedEncodingException ex) {
	    throw new IOException(ex);
	}
    }

    protected Writer createWriter(Writer writer, boolean buf) throws IOException {
	if (buf) {
	    return new BufferedWriter(writer);
	}
	return writer;
    }
           
}
