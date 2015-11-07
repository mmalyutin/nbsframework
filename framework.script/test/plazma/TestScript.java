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

package plazma;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

import plazma.ast.LNode;
import plazma.parser.PlazmaScriptLexer;
import plazma.parser.PlazmaScriptParser;
import plazma.parser.PlazmaScriptWalker;
import junit.framework.TestCase;

public class TestScript extends TestCase {
    
    public void testScript() throws Exception {

	// create an instance of the lexer
	PlazmaScriptLexer lexer = new PlazmaScriptLexer(new ANTLRFileStream("test.script"));

	// wrap a token-stream around the lexer
	CommonTokenStream tokens = new CommonTokenStream(lexer);

	// create the parser
	PlazmaScriptParser parser = new PlazmaScriptParser(tokens);

	// walk the tree
	CommonTree tree = (CommonTree) parser.parse().getTree();
	CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);

	// pass the reference to the Map of functions to the tree walker
	PlazmaScriptWalker walker = new PlazmaScriptWalker(nodes, parser.functions);

	// get the returned node
	LNode returned = walker.walk();

	System.out.println();

	System.out.println(returned == null ? "null" : returned.evaluate());

    }

}
