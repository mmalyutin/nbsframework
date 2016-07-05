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

import org.plazmaforge.framework.script.Scope;
import org.plazmaforge.framework.script.ValueAdapter;
import org.plazmaforge.framework.script.ast.LNode;


/**
 * General script test.
 * 
 * @author ohapon
 *
 */
public class ScriptTest extends AbstractScriptTest {
    
    public void testScript() throws Exception {

	Scope globalScope = new Scope();
	
	Book book = new Book();
	book.setAuthor("Author-17");
	book.setName("Name-18");
	
	globalScope.assign("$book", ValueAdapter.fromNativeValue(book));
	
	runScript("test.script", globalScope);
    }
    
    public static class Book {

	private String name;
	private String author;
	
	
	public Book() {
	    super();
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public String getAuthor() {
	    return author;
	}

	public void setAuthor(String author) {
	    this.author = author;
	}
	
	
    }

}
