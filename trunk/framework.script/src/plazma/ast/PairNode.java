package plazma.ast;

import plazma.lang.LValue;

public class PairNode implements LNode {

    private LNode key;
    private LNode value;

    public PairNode(LNode key, LNode value) {
	this.key = key;
	this.value = value;
    }

    @Override
    public LValue evaluate() {
	return LValue.VOID;
    }

    @Override
    public String toString() {
	return String.format("(key=%s, value=%s ", key, value);
    }

    public LNode getKey() {
	return key;
    }

    public LNode getValue() {
	return value;
    }
    
    
}
