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

package org.plazmaforge.framework;

public class NeuralNetwork {


    
    // AND LEAR DATA
    static double learnDataAND[][] = {
	{0,0, 0},
	{1,0, 0},
	{0,1, 0},
	{1,1, 1}
    };

    
    // OR LEARN DATA
    static double learnDataOR[][] = {
	{0,0, 0},
	{1,0, 1},
	{0,1, 1},
	{1,1, 1}
    };
    
    
    /**
     * @param args
     */
    public static void main(String[] args) {
	new NeuralNetwork().test();
    }
    
    
    
    
    public void test() {
	
	// AND Neuron
	Neuron neuronAND = new Neuron();
	neuronAND.initInput(2);
	neuronAND.train(learnDataAND);
	
	// AND
	double testDataAND[][] = {
		{0,0, 0},
		{1,0, 0},
		{0,1, 0},
		{1,1, 1}		
	    };
	

	System.out.println("Test AND Neuron") ;
	System.out.println("=========================================") ;
	
	processNeuron(neuronAND, testDataAND);
	
	
	
	// OR Neuron
	Neuron neuronOR = new Neuron();
	neuronOR.initInput(2);
	neuronOR.train(learnDataOR);
	
	// OR
	double testDataOR[][] = {
		{0,0, 0},
		{1,0, 1},
		{0,1, 1},
		{1,1, 1}		
	    };

	System.out.println() ;
	System.out.println("Test OR Neuron") ;
	System.out.println("=========================================") ;
	processNeuron(neuronOR, testDataOR);
	
	
    }
    
    private void processNeuron(Neuron neuron, double data[][]) {
	for (int p = 0; p < data.length; p++) {
	    neuron.setInputs(java.util.Arrays.copyOf(data[p],  data[p].length - 1));
	    neuron.summator();
	    double originalOut = data[p][2];
	    System.out.println("" + neuron.getOutput() + "  <->  " + originalOut +  (originalOut != neuron.getOutput() ? "FAIL" : "")) ;
	}
    }

    
}
