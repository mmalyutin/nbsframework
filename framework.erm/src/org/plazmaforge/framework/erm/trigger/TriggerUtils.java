package org.plazmaforge.framework.erm.trigger;

import org.plazmaforge.framework.erm.Language;

public class TriggerUtils {

    
    /**
     * Return language by script lines
     * @param scriptLines
     * @return
     */
    public static String getLanguageByScript(String[] scriptLines) {
	if (scriptLines == null || scriptLines.length == 0) {
	    return null;
	}
	// Get first line and trim all
	String script = scriptLines[0].trim();
	return getLanguageByScript(script);
    }

    
    /**
     * Return language by script (analyze first command) 
     * @param script
     * @return
     */
    
    public static String getLanguageByScript(String script) {
	if (script == null || script.isEmpty()) {
	    return null;
	}
	
	// TODO: Must refactore
	
	// SET
	// INSERT
	// UPDATE
	// DELETE
	
	int position = script.length();
	int maxCommandLen = 7; // max len + space
	if (position > maxCommandLen) {
	    position = maxCommandLen;
	}
	String firstCommand = script.substring(0, position).toUpperCase();
	if (firstCommand.startsWith("SET ") || firstCommand.startsWith("CALL ")) {
	    return Language.MACROS;
	}
	
	if (firstCommand.startsWith("INSERT ") || firstCommand.startsWith("UPDATE ") || firstCommand.startsWith("DELETE ")) {
	    return Language.SQL;
	}
	
	return null;

    }
    
    

    /**
     * Create trigger by language
     * @param language
     * @return
     */
    public static AbstractTrigger createTrigger(String language) {
	if (language == null) {
	    return null;
	}
	// TODO: Use trigger factory
	if (Language.SQL.equalsIgnoreCase(language)) {
	    return new SQLTrigger();
	} else if (Language.MACROS.equalsIgnoreCase(language)) {
	    return new MacrosTrigger();
	}
	return null;

    }
    
}
