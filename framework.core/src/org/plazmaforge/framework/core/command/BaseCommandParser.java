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
package org.plazmaforge.framework.core.command;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.util.StringTokenizer;
import org.plazmaforge.framework.util.StringUtils;

/**
 * @author ohapon
 *
 */
public class BaseCommandParser implements CommandParser {

    @Override
    public CommandInfo parseCommand(String command) {
	String commandLine = normalizeCommand(command);
	if (commandLine == null) {
	    return null;
	}

	CommandInfo commandInfo = new CommandInfo();
	commandInfo.setLine(commandLine);
	
	// TODO: Must use real command parser
	int index = commandLine.indexOf(" ");
	if (index == -1) {
	    commandInfo.setName(commandLine);
	    return commandInfo;
	}
	
	String name = commandLine.substring(0, index); // COMMAND NAME
	String parametersLine = null;
	if (index < commandLine.length() - 1 ) {
	    parametersLine = StringUtils.nullIfEmpty(commandLine.substring(index + 1), true);
	}
	String[] parameters = getParametersFromString(parametersLine); // COMMAND PARAMETERS
	
	commandInfo.setName(name);
	commandInfo.setParameters(parameters);
	
	return commandInfo;	
    }
	
    protected String normalizeCommand(String command) {
	if (command == null) {
	    return null;
	}
	command = command.trim();
	if (command.isEmpty()) {
	    return null;
	}
	if (command.startsWith(COMMAND_MARKER)) {
	    command = command.length() == 1 ? null : command.substring(1);
	}
	return command;
    }
    
    protected String[] getParametersFromString(String parametersLine) {
	if (parametersLine == null) {
	    return null;
	}
	StringTokenizer t = new StringTokenizer(parametersLine, PARAMETER_SEPARATORS);
	List<String> parameters = new ArrayList<String>();
	while (t.hasMoreTokens()) {
	    parameters.add(t.nextToken());
	}
	return parameters.isEmpty() ? null : parameters.toArray(new String[0]);
    }

}
