package org.plazmaforge.framework.erm.storage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.jdom.Document;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Writer data to XML
 * @author ohapon
 *
 */
public class XMLWriter implements XMLInfo {

    
    
    protected void writeDocument(Document doc, String fileName) throws IOException {
        writeDocument(doc, new File(fileName));
    }

    protected void writeDocument(Document doc, File file) throws IOException {
        writeDocument(doc, new FileOutputStream(file));
    }

    protected void writeDocument(Document doc, OutputStream os) throws IOException {
        writeDocument(doc, new OutputStreamWriter(os, "UTF-8"));
    }

    protected void writeDocument(Document doc, Writer writer) throws IOException {
	Format format = Format.getPrettyFormat();
        format.setEncoding("UTF-8");
	XMLOutputter outputter = new XMLOutputter(format);
        outputter.output(doc, writer);
        writer.flush();
    }
    
    ////
}
