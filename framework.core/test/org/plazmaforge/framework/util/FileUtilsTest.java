package org.plazmaforge.framework.util;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;

public class FileUtilsTest extends TestCase {

    @Test
    public void testGetFolder() throws Exception {
	
	String folder = null; 
		
	folder = FileUtils.getFolderName(null);
	assertNull(folder);
	
	folder = FileUtils.getFolderName("");
	assertNull(folder);

	folder = FileUtils.getFolderName(" ");
	assertNull(folder);

	folder = FileUtils.getFolderName(" abc ");
	assertNull(folder);

	folder = FileUtils.getFolderName("a/b");
	assertEquals(folder, "a");

	//folder = FileUtils.getFolderName("a/b/c");
	//assertEquals(folder, "a/b");
	
    }
    
    public void testGetCanonicalPath()  throws Exception {
	String path = null;
	path = FileUtils.getCanonicalPath("/a/b/c/d", "../x/y/z");
	
	assertNotNull(path);
    }
    
    public void testPath()  throws Exception {
	File file = null;
	
	//file = new File("abc");
	//System.out.println(file.getPath() + " -> " + file.isAbsolute());
	
	printAbsoluteFlag("c:\\abc");
	printAbsoluteFlag("c:/abc");
	
	printAbsoluteFlag("abc/123");
	printAbsoluteFlag("/abc/123");
	printAbsoluteFlag("./abc/123");
	printAbsoluteFlag("../abc/123");
	printAbsoluteFlag("../../abc/123");
	printAbsoluteFlag("");
    }

    private void printAbsoluteFlag(String fileName) throws IOException{ 
	File file = new File(fileName);
	System.out.println(fileName + " -> " + file.isAbsolute() + " " + file.getCanonicalPath());
    }
    
    
}
