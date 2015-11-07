
package script.system;

import org.junit.Test;

import junit.framework.TestCase;

public class TestCaller extends TestCase {

    @Test
    public void testCaller() throws Exception {

	
	System.out.println("increment: begin");

	// script: x = 10;
	final __ValueRef<Integer> __x = new __ValueRef<Integer>(10);

	__Caller<Void> __inc = new __Caller<Void>() {

	    public Void call() {
		inc(__x);
		return null;
	    }

	};
	
	// script: System.out.println(x);
	System.out.println("x=" + __x.getValue());

	// script: inc(x)
	__inc.call();
	
	System.out.println("increment: end");
	// script: System.out.println(x);
	System.out.println("x=" + __x.getValue());
	
	
	System.out.println("swap: begin");

	// script: a = 100;
	// script: b = 200;
	final __ValueRef<Integer> __a = new __ValueRef<Integer>(100);
	final __ValueRef<Integer> __b = new __ValueRef<Integer>(200);
	
	// script: System.out.println(a);
	// script: System.out.println(b);
	System.out.println("a="+ __a.getValue());
	System.out.println("b="+ __b.getValue());
	

	__Caller<Void> __swap = new __Caller<Void>() {

	    public Void call() {
		swap(__a, __b);
		return null;
	    }

	};


	// script: swap(a, b)
	__swap.call();
	
	System.out.println("swap: end");
	
	// script: System.out.println(a);
	// script: System.out.println(b);
	System.out.println("a="+ __a.getValue());
	System.out.println("b="+ __b.getValue());
	
	float x = 300.402821e20f;
	float y = -300.402826e20f;
	
	System.out.println(" " + x);
	System.out.println(y);
	
    }

    // script: private static void inc(&Integer x) {
    private void inc(__ValueRef<Integer> __x) {
	__x.setValue(__x.getValue() + 1);
    }
    
    private void swap(__ValueRef<Integer> __x, __ValueRef<Integer> __y) {
	Integer value = __x.getValue();
	__x.setValue(__y.getValue());
	__y.setValue(value);
    }
    
}
