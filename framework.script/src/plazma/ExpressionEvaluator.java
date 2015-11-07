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

/**
 * 
 */
package plazma;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

import org.antlr.runtime.RecognitionException;

import plazma.ast.LNode;
import plazma.lang.LValue;
import plazma.parser.PlazmaScriptLexer;
import plazma.parser.PlazmaScriptParser;
import plazma.parser.PlazmaScriptWalker;

/**
 * @author ohapon
 *
 */
public class ExpressionEvaluator {
    
    
    private Map<String, Function> globalFunctions;
    
    private GlobalScope globalScope;
    
    
    public ExpressionEvaluator() {
    }

    public ExpressionEvaluator(Map<String, Function> globalFunctions) {
	this(globalFunctions, null);
    }



    public ExpressionEvaluator(Map<String, Function> globalFunctions, GlobalScope globalScope) {
	this.globalFunctions = ScriptUtils.getSafeFunctions(globalFunctions);
	this.globalScope = globalScope;
    }

    public Object evaluate(String expression) throws EvaluateException {

	if (expression == null) {
	    return null;
	}
	
	expression = expression.trim();
	if (expression.isEmpty()) {
	    return null;
	}

	try {
	    // Lexer
	    PlazmaScriptLexer lexer = new PlazmaScriptLexer(new ANTLRStringStream(expression));

	    CommonTokenStream tokens = new CommonTokenStream(lexer);

	    // Parser
	    PlazmaScriptParser parser = new PlazmaScriptParser(tokens);

	    CommonTree tree = (CommonTree) parser.expression().getTree();

	    CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);

	    
	    
	    PlazmaScriptWalker walker = new PlazmaScriptWalker(nodes, globalFunctions, null, globalScope);

	    // Get the returned node
	    LNode returnNode = walker.expression();
	    LValue returnValue = returnNode.evaluate();
	    if (returnValue == null || returnValue == LValue.NULL || returnValue == LValue.VOID) {
		return null;
	    }
	    return returnValue.getValue();

	} catch (RecognitionException e) {
	    throw new EvaluateException(e);
	}
    }

    public static Map<String, Function> loadLibraryFunctions() throws EvaluateException {
	InputStream is = null;
	is = ExpressionEvaluator.class.getResourceAsStream("library.script");
	if (is == null) {
	    return null;
	}
	return loadFunctions(is);
    }
    
    public static Map<String, Function> loadFunctions(String sourceCode) throws EvaluateException {
	PlazmaScriptLexer lexer = new PlazmaScriptLexer(new ANTLRStringStream(sourceCode));
	return loadFunctions(lexer);

    }

    public static Map<String, Function> loadFunctions(InputStream is) throws EvaluateException {
	PlazmaScriptLexer lexer = null;
	try {
	    lexer = new PlazmaScriptLexer(new ANTLRInputStream(is));
	} catch (IOException e) {
	    throw new EvaluateException(e);
	}
	return loadFunctions(lexer);
    }
    
    public static Map<String, Function> loadFunctionsFromFile(String fileName) throws EvaluateException {
	PlazmaScriptLexer lexer = null;
	try {
	    lexer = new PlazmaScriptLexer(new ANTLRFileStream(fileName));
	} catch (IOException e) {
	    throw new EvaluateException(e);
	}
	return loadFunctions(lexer);
    }
    
    public static Map<String, Function> loadFunctions(PlazmaScriptLexer lexer) throws EvaluateException {
	
	// Tokens
	CommonTokenStream tokens = new CommonTokenStream(lexer);

	// Parser
	PlazmaScriptParser parser = new PlazmaScriptParser(tokens);

	return loadFunctions(parser);

    }

    public static Map<String, Function> loadFunctions(PlazmaScriptParser parser) throws EvaluateException {
	try {

	    // Get tree. IMPORTANT string
	    CommonTree tree = (CommonTree) parser.parse().getTree();

	    return parser.functions;

	} catch (RecognitionException e) {
	    throw new EvaluateException(e);
	}
    }    

}
