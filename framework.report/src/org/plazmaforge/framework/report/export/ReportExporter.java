/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
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
package org.plazmaforge.framework.report.export;

import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.model.document.Document;
import org.plazmaforge.framework.report.model.document.Page;

/**
 * @author ohapon
 *
 */
public interface ReportExporter {

    // input
    String PROPERTY_INPUT_TYPE = "inputType";
    
    String PROPERTY_INPUT_FILE = "inputFile";
    
    String PROPERTY_INPUT_FILE_NAME = "inputFileName";
    
    String PROPERTY_INPUT_STREAM = "inputStream";
    
    String PROPERTY_READER = "reader";
    
    // output
    String PROPERTY_OUTPUT_TYPE = "outputType";
    
    String PROPERTY_OUTPUT_FILE = "outputFile";
    
    String PROPERTY_OUTPUT_FILE_NAME = "outputFileName";
    
    String PROPERTY_OUTPUT_STREAM = "outputStream";
    
    String PROPERTY_WRITER = "writer";
    

    String IO_TYPE_FILE_NAME = "fileName";
    
    String IO_TYPE_FILE = "file";
    
    String IO_TYPE_STREAM = "stream";

    
    String INPUT_TYPE_FILE_NAME = IO_TYPE_FILE_NAME;
    
    String INPUT_TYPE_FILE = IO_TYPE_FILE;
    
    String INPUT_TYPE_STREAM = IO_TYPE_STREAM;
    
    String INPUT_TYPE_READER = "reader";


    String OUTPUT_TYPE_FILE_NAME = IO_TYPE_FILE_NAME;
    
    String OUTPUT_TYPE_FILE = IO_TYPE_FILE;
    
    String OUTPUT_TYPE_STREAM = IO_TYPE_STREAM;
    
    String OUTPUT_TYPE_WRITER = "writer";
    
    

    /**
     * Set data to exporter
     * @param name
     * @param data
     */
    void setData(String name, Object data);
    
    /**
     * Get data from exporter
     * @param name
     * @return
     */
    Object getData(String name);
    
    /**
     * Export document to other format
     * 
     * @param document
     * @throws RTException
     */
    void exportDocument(Document document) throws RTException;
    
    /**
     * Export one page to other format
     * 
     * @param page
     * @throws RTException
     */
    void exportPage(Page page) throws RTException;
    
}
