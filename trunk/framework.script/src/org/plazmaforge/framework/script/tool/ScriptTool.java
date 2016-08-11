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

package org.plazmaforge.framework.script.tool;

import java.io.BufferedReader;
import java.util.Properties;

import org.plazmaforge.framework.script.ScriptRunner;



public class ScriptTool {

    private Properties pProperties = new Properties();

    
    public static void main(String[] arg) {

	for (int i = 0; i < arg.length; i++) {
	    String p = arg[i];
	    if (p.equals("-?")) {
		printHelp();
		System.exit(0);
	    }
	}

	ScriptTool tool = new ScriptTool();
	tool.execute(arg);
	System.exit(0);
    }

    public void execute(String[] arg) {

	for (int i = 0; i < arg.length; i++) {
	    String p = arg[i];

	    if (p.charAt(0) == '-') {
		pProperties.put(p.substring(1), arg[i + 1]);
		i++;
	    }
	}

	//BufferedReader in = null;
	Properties p = pProperties;
	String script = p.getProperty("script");
	boolean log = p.getProperty("log", "false").equalsIgnoreCase("true");

	try {
	    if (log) {
		trace("script   = " + script);
		trace("log      = " + log);
	    }
	    script = toNormalizeString(script);
	    if (script == null) {
		error("Script file name is empty");
	    }

	    // in = new BufferedReader(new FileReader(script));
	    ScriptRunner scriptRunner = new ScriptRunner();
	    scriptRunner.runScript(script);

	} catch (Exception e) {
	    error("ScriptTool.init error: " + e.getMessage());
	    //e.printStackTrace();
	}

    }

    private String toNormalizeString(String str) {
	if (str == null) {
	    return null;
	}
	str = str.trim();
	return str.isEmpty() ? null : str; 
    }

    private void trace(String s) {
	System.out.println(s);
    }

    private void error(String s) {
	System.err.println(s);
    }
    
    private static void printHelp() {

	System.out.println("Usage: java ScriptTool [-options]\n"
		+ "where options include:\n"
		+ "    -log <true/false>       write log to system out\n"
		+ "    -script <script file>   reads from script file\n");
    }

}
