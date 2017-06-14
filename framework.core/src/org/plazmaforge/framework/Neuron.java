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

    /**
     * Initialize input (count)
     * @param count
     */
    public void initInput(int count) {
	inputs = new double[count];
	weights = new double[count];
    }

    
    public void summator() {
	output = 0; // reset input
	for ( int i = 0; i < inputs.length; i++ ) {
	    output += inputs[i] * weights[i]; // calculate output
	}
	output = normalize(output);
    }
    
    protected double normalize(double value) {
	// TODO : Must implement own activation function
	if ( value > 0.1 ) return 1; else return 0;
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
  			
  		    gError += Math.abs(error); // суммируем ошибку в модуле
  			
  		    for ( int j = 0; j < inputs.length; j++ ) {
  			weights[j] += 0.1 * error * inputs[j]; // старый вес + скорость * ошибку * i-ый вход
  		    }
  				
  		}
  	} while (gError != 0); // пока gError не равно 0, выполняем код
  	
       
      }    
}
