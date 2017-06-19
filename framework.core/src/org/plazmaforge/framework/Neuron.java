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

public class Neuron {

    
    private double inputs[]; 	// Input
    private double output; 	// Output
    private double[] weights;	// Weights
    
    private double[] deltaWeights;	// Weights    
    
    
    public Neuron() {
	super();
    }

    public double[] getInputs() {
        return inputs;
    }

    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }

    public double getOutput() {
        return output;
    }

    public void setOutput(double output) {
        this.output = output;
    }

    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }
    
    public double[] getDeltaWeights() {
        return deltaWeights;
    }

    public void setDeltaWeights(double[] deltaWeights) {
        this.deltaWeights = deltaWeights;
    }

    /**
     * Initialize input (count)
     * @param count
     */
    public void initInput(int count) {
	inputs = new double[count];
	weights = new double[count];
	deltaWeights = new double[count];
	
//	for ( int i = 0; i < weights.length; i++ ) {
//	    weights[i] = 0.5;
//	}
    }


    public double summatorZZZ(double inputs[], double weights[]) {
	double output = 0; // reset input
	for ( int i = 0; i < inputs.length; i++ ) {
	    output += inputs[i] * weights[i]; // calculate output
	}
	output = normalizeZZZ(output);
	return output;
    }

    protected double normalizeZZZ(double value) {
	return 1 / (1 + Math.pow(2.7, (-1 * value)));
	//return 1 / (1 + Math.pow(2.7, (-4*(value-0.5))));
    }
    
    public void summator() {
	output = 0; // reset input
	for ( int i = 0; i < inputs.length; i++ ) {
	    output += inputs[i] * weights[i]; // calculate output
	}
	output = normalize(output);
	//output = Math.round(output * 100);
    }

    public void summator2() {
	output = 0; // reset input
	for ( int i = 0; i < inputs.length; i++ ) {
	    output += inputs[i] * weights[i]; // calculate output
	}
	//output = normalize(output);
    }
    
    protected double normalize(double value) {
	// TODO : Must implement own activation function

//	double result = Math.round(value * 100);
//	if (result > 1) {
//	    return 1;
//	} else {
//	    return 0;
//	}
	
	//if ( value >= 0.000001 ) return 1; else return 0;
	
	//if ( value > 0.1 ) return 1; else return 0;
	//if ( value > 0.001 && value < 0.002) return 1; else return 0;
	
	//return Math.round(value * 100);
	return value * 100;
	//return value;
	//return value * 1000000000;
	
	//return 1 / (1 + Math.pow(2.7, (-1 * value) ));
	
	/*
	if ( value > 0.001 ) {
	    if (value <= 0.002) {
		return 1;
	    }
	    return 0;
	} else {
	    return 0;
	}
	*/
    }
    
    public void train(double learnData[][]) {
  	double gError = 0; // создаём счётчик ошибок
  	int it = 0; // количество итераций
  	
  	double[] items;
  	double originalOut;

  	do {
  		gError = 0; // обнуляем счётчик
  		it++; // увеличиваем на 1 итерации
  		
  		for ( int i = 0; i < learnData.length; i++ ) {
  		    
  		    items = learnData[i];
  		    originalOut = items[2];
  		    
  		    inputs = java.util.Arrays.copyOf(items, items.length - 1); // copy only input values (without result)
  		    
  		    
  		    summator(); // суммируем
  			
  		    //double error = tableOfLearn[i][2] - out; // получаем ошибку
  		    double error = originalOut - output; // получаем ошибку
  		    double error2 = Math.abs(error);
  			
  		    gError += Math.abs(error); // суммируем ошибку в модуле
  		    //gError += error; // суммируем ошибку в модуле
  			
  		    for ( int j = 0; j < inputs.length; j++ ) {
  			//weights[j] += 0.001 * error * inputs[j]; // старый вес + скорость * ошибку * i-ый вход
  			//weights[j] += 0.0001 * error * inputs[j]; // старый вес + скорость * ошибку * i-ый вход
  			weights[j] += 0.001 * error * inputs[j]; // старый вес + скорость * ошибку * i-ый вход
  			//weights[j] += 0.00000000001 * error * inputs[j]; // старый вес + скорость * ошибку * i-ый вход
  		    }
  				
  		}
  		if (it > 10000) {
  		    System.out.print(10000);
  		}
  		
  		
  		//gError = Math.abs(gError);
  		
  	//} while (gError != 0); // пока gError не равно 0, выполняем код
  	//} while (gError <= 0.000000001); // пока gError не равно 0, выполняем код
    	} while (gError >= 0.000001); // пока gError не равно 0, выполняем код
  	//} while (gError >= 0.000000000000000000000000000000000000000000000000001); // пока gError не равно 0, выполняем код
  	
  	System.out.println("EE");
       System.out.println(gError);
      }
    
    private Neuron h1;
    private Neuron h2;
    private Neuron o1;
    
    private double E = 0.7;
    private double A = 0.3;
	    
    public double FPOH(double value) {
	return (1 - value) * value;
    }
    
    // formula
    private double grad(Neuron neuron, double delta) {
	return grad(neuron.getOutput(), delta);
    }

    private double grad(double output, double delta) {
	return output * delta;
    }
    
    // formula
    private double mop(double grad, double delta) {
	return E * grad + A * delta;
    }
    
    private void changeWeight(double grad, Neuron neuron, int index) {
	// calculate delta weight
	double deltaWeight = mop(grad, neuron.getDeltaWeights()[index]);
	
	// set new weight
	neuron.getWeights()[index] = neuron.getWeights()[index] + deltaWeight;
	
	// set new delta weight
	neuron.getDeltaWeights()[index] = deltaWeight;
    }
    
    public void calculate() {
	
	    //==============================================================================================
	    // H1
	    h1.setInputs(inputs);
	    h1.setOutput(summatorZZZ(h1.getInputs(), h1.getWeights()));

	    // H2
	    h2.setInputs(inputs);
	    h2.setOutput(summatorZZZ(h2.getInputs(), h2.getWeights()));
	    
	    // O1
	    o1.setInputs(new double[] {h1.getOutput(), h2.getOutput()});
	    o1.setOutput(summatorZZZ(o1.getInputs(), o1.getWeights()));
	    
	    output = o1.getOutput();
	
    }
    
    public void trainZZZ(double learnData[][]) {
  	double gError = 0; // создаём счётчик ошибок
  	int it = 0; // количество итераций
  	
  	double[] items;
  	double originalOut;

  	h1 = new Neuron();
  	h1.initInput(2);
  	
  	h2 = new Neuron();
  	h2.initInput(2);
  	
  	o1 = new Neuron();
  	o1.initInput(2);
  	
  	double w1 = 0.45;
  	double w2 = 0.78;
  	double w3 = -0.12;
  	double w4 = 0.13;
  	double w5 = 1.5;
  	double w6 = -2.3;
  	
  	//h1.getWeights()[0] = w1;
  	//h2.getWeights()[0] = w2;
  	
  	//h1.getWeights()[1] = w3;
  	//h2.getWeights()[1] = w4;
  	
  	//o1.getWeights()[0] = w5;
  	//o1.getWeights()[1] = w6;
  	
  	int BREAK_ITERATION = 1000000;
  	int PRINT_STEP_ITERATION = BREAK_ITERATION / 100;
  		
  	do {
  		gError = 0; // обнуляем счётчик
  		it++; // увеличиваем на 1 итерации
  		double ESUM = 0;
  		for ( int i = 0; i < learnData.length; i++ ) {
  		    
  		    items = learnData[i];
  		    originalOut = items[2];
  		    
  		    inputs = java.util.Arrays.copyOf(items, items.length - 1); // copy only input values (without result)
  		  
  		    //==============================================================================================
  		    // H1
  		    h1.setInputs(inputs);
  		    h1.setOutput(summatorZZZ(h1.getInputs(), h1.getWeights()));

  		    // H2
  		    h2.setInputs(inputs);
  		    h2.setOutput(summatorZZZ(h2.getInputs(), h2.getWeights()));
  		    
  		    // O1
  		    o1.setInputs(new double[] {h1.getOutput(), h2.getOutput()});
  		    o1.setOutput(summatorZZZ(o1.getInputs(), o1.getWeights()));

  		    
  		    //==============================================================================================
  		    // ERROR
  		    double delta = originalOut - o1.getOutput();
  		    ESUM += delta * delta;
  		    double error = ESUM / (i + 1);
  		    
  		    gError = error;
  		  
  		    //System.out.println();
  		    //System.out.println(error);
  		  
  		    //==============================================================================================
  		    double deltaO1 = delta * FPOH(o1.getOutput());
  			
  		    // H1: CALCULATE
  		    double deltaH1 =  FPOH(h1.getOutput()) * (o1.getWeights()[0] * deltaO1);
  		    double gradH1 = grad(h1, deltaO1);
  		    changeWeight(gradH1, o1, 0);
  		    
  		    // H1: CALCULATE
  		    double deltaH2 = FPOH(h2.getOutput()) * (o1.getWeights()[1] * deltaO1);
  		    double gradH2 = grad(h2, deltaO1);
  		    changeWeight(gradH2, o1, 1);
  		  
  		    double gradH1_1 = grad(inputs[0], deltaH1); // w1
  		    double gradH2_1 = grad(inputs[0], deltaH2); // w2
  		    
  		    double gradH1_2 = grad(inputs[1], deltaH1); // w3
  		    double gradH2_2 = grad(inputs[1], deltaH2); // w4
  		    
  		    changeWeight(gradH1_1, h1, 0);
  		    changeWeight(gradH2_1, h2, 0);
  		    
  		    changeWeight(gradH1_2, h1, 1);
  		    changeWeight(gradH2_2, h2, 1);

  		    
  		    
  		    
  		    /////////////////////////////////////////
  		  w1 = h1.getWeights()[0];
  		  w2 = h2.getWeights()[0];
  		  	
  		  w3 = h1.getWeights()[1];
  		  w4 = h2.getWeights()[1];
  		  	
  		  w5 = o1.getWeights()[0];
  		  w6 = o1.getWeights()[1];
  		    
  		  //System.out.println("w1=" + w1 + ", w2=" + w2 + ", w3=" + w3 + ", w4=" + w4 + ", w5=" + w5 + ", w6=" + w6);
  		  //System.out.println("output=" + o1.getOutput());
  		    
  		}
  		if (it % PRINT_STEP_ITERATION == 0) {
  		  System.out.println("itr=" + it);
  		}
  		if (it > BREAK_ITERATION) {
  		    System.out.print("BREAK: " + gError);
  		    break;
  		    //System.out.print(10000);
  		}
  		
  		
  		//gError = Math.abs(gError);
  		
  	//} while (gError != 0); // пока gError не равно 0, выполняем код
  	//} while (gError <= 0.000000001); // пока gError не равно 0, выполняем код
    	//} while (gError <= 0.0000000001); // пока gError не равно 0, выполняем код
  	//} while (gError >= 0.000000000000000001); // пока gError не равно 0, выполняем код
  	} while (gError >= 0.001); // пока gError не равно 0, выполняем код
  	//} while (gError >= 0.00000000000000000000000000000000000000000000000000000000000000001); // пока gError не равно 0, выполняем код
  	
       
      }    
    
}
