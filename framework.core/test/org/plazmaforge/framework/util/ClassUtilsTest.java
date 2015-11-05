package org.plazmaforge.framework.util;

import junit.framework.TestCase;

import org.junit.Test;
import org.plazmaforge.framework.util.ClassUtils;

public class ClassUtilsTest extends TestCase {

    @Test
    public void testGetBaseClassName() throws Exception {
	output(String.class.getName(), ClassUtils.getBaseClassName(String.class));
	output(java.util.Date.class.getName(), ClassUtils.getBaseClassName(java.util.Date.class));
	output(java.sql.Date.class.getName(), ClassUtils.getBaseClassName(java.sql.Date.class));
    }
    
    private void output(String input, String output) {
	System.out.println("Input name=" + input + ", Output name=" + output);
    }

}
