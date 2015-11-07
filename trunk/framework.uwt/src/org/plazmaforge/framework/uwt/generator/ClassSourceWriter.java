package org.plazmaforge.framework.uwt.generator;


public class ClassSourceWriter implements SourceWriter {

    private static final String STAR_COMMENT_LINE = " * ";

    private boolean atStart;

    private String commentIndicator;

    private final GeneratorContext ctx;

    private boolean inComment;

    private int indent;
    
    private final StringBuffer writer;
    
    private boolean closed;
    

    public ClassSourceWriter(GeneratorContext ctx) {
	this.ctx = ctx;
	writer = new StringBuffer();
    }
    
    public void beginJavaDocComment() {
	println("\n/**");
	inComment = true;
	commentIndicator = STAR_COMMENT_LINE;
    }

    public void commit() {
	outdent();
	println("}");
	
	closed = true;
	
	// If generating a class on the command line, may not have a
	if (ctx != null) {
	    // ctx.commit();
	}
    }

    /**
     * End emitting a JavaDoc comment.
     */
    public void endJavaDocComment() {
	inComment = false;
	println("\n */");
    }

    public void indent() {
	++indent;
    }

    public void indentln(String s) {
	indent();
	println(s);
	outdent();
    }

    public void indentln(String s, Object... args) {
	indentln(String.format(s, args));
    }

    public void outdent() {
	--indent;
    }

    public void print(String s) {
	
	if  (closed) {
	    throw new GenerateException("Writer is commited");
	}
	
	// If we just printed a newline, print an indent.
	//
	if (atStart) {
	    for (int j = 0; j < indent; ++j) {
		writer.append("  ");
	    }
	    if (inComment) {
		writer.append(commentIndicator);
	    }
	    atStart = false;
	}
	// Now print up to the end of the string or the next newline.
	//
	String rest = null;
	int i = s.indexOf("\n");
	if (i > -1 && i < s.length() - 1) {
	    rest = s.substring(i + 1);
	    s = s.substring(0, i + 1);
	}
	writer.append(s);
	// If rest is non-null, then s ended with a newline and we recurse.
	//
	if (rest != null) {
	    atStart = true;
	    print(rest);
	}
    }

    public void print(String s, Object... args) {
	print(String.format(s, args));
    }

    public void println() {
	print("\n");
	atStart = true;
    }

    public void println(String s) {
	print(s + "\n");
	atStart = true;
    }

    public void println(String s, Object... args) {
	println(String.format(s, args));
    }

    @Override
    public String getSource() {
	return writer.toString();
    }    


}
