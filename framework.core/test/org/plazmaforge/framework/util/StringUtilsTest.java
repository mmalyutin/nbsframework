package org.plazmaforge.framework.util;

import org.junit.Test;
import org.plazmaforge.framework.util.StringUtils;

import junit.framework.TestCase;

public class StringUtilsTest extends TestCase {

    @Test
    public void testSplitLines() throws Exception {
	String text = "\n  test \n  ";
	
	String[] lines = StringUtils.splitLines(text);
	assertEquals(lines.length, 3);
	println("Lines");
	println("===================================");
	printLines(lines);
	
	lines = StringUtils.splitScriptLines(text);
	assertEquals(lines.length, 1);
	assertEquals(" test ", lines[0]);
	println("\nScript lines 1");
	println("===================================");
	printLines(lines);
	
	text = "\n  test;";
	lines = StringUtils.splitScriptLines(text);
	assertEquals(lines.length, 1);
	assertEquals(" test;", lines[0]);
	println("\nScript lines 2");
	println("===================================");
	printLines(lines);

	text = "\n  test \t \t;";
	lines = StringUtils.splitScriptLines(text);
	assertEquals(lines.length, 1);
	assertEquals(" test \t \t;", lines[0]);
	println("\nScript lines 3");
	println("===================================");
	printLines(lines);

	
	text = "\n  test; \t \t";
	lines = StringUtils.splitScriptLines(text);
	assertEquals(lines.length, 1);
	assertEquals(" test; ", lines[0]);
	println("\nScript lines 4");
	println("===================================");
	printLines(lines);

	text = "test;";
	lines = StringUtils.splitScriptLines(text);
	assertEquals(lines.length, 1);
	assertEquals("test;", lines[0]);
	println("\nScript lines 5");
	println("===================================");
	printLines(lines);

    }
    
    private void printLines(String[] lines) {
	int len = lines.length;
	for (int i = 0 ; i < len; i++) {
	    System.out.println("Line " + (i + 1) + ": '" + lines[i] + "'");	    
	}
    }
    
    private void println(String str) {
	System.out.println(str);	    
    }

}
