package il.ac.tau.cs.sw1.hw6;

import java.util.Arrays;

public class Polynomial {

	private double[] coefficients;

	/*
	 * Creates the zero-polynomial with p(x) = 0 for all x.
	 */
	public Polynomial() {
		this.coefficients = new double[] {0};
	}

	/*
	 * Creates a new polynomial with the given coefficients.
	 */
	public Polynomial(double[] coefficients) {
		int lastIndex = 0;

		for (int i = coefficients.length - 1; i >= 0 ; i--) {
			if (coefficients[i] != 0) {
				lastIndex = i;
				break;
			}
		}
		this.coefficients = Arrays.copyOf(coefficients, lastIndex + 1);

	}


	/*
	 * Adds this polynomial to the given one
	 * and returns the sum as a new polynomial.
	 */
	public Polynomial adds(Polynomial polynomial) {
		double[] newPolyCoeff = new double[Math.max(this.coefficients.length, polynomial.coefficients.length)];
		for (int i = 0; i < newPolyCoeff.length; i++) {
			newPolyCoeff[i] = this.coefficients[i] + polynomial.coefficients[i];
		}
		return new Polynomial(newPolyCoeff);
	}
	/*
	 * Multiplies a to this polynomial and returns 
	 * the result as a new polynomial.
	 */
	public Polynomial multiply(double a) {
		double[] newCoeff = new double[this.coefficients.length];

		for (int i = 0; i < this.coefficients.length; i++) {
			newCoeff[i] = this.coefficients[i] * a;
		}

		return new Polynomial(newCoeff);
		
	}
	/*
	 * Returns the degree (the largest exponent) of this polynomial.
	 */
	public int getDegree() {
		for (int i = this.coefficients.length - 1; i >= 0 ; i--) {
			if (coefficients[i] != 0) {
				return i;
			}
		}
		return 0;
	}

	/*
	 * Returns the coefficient of the variable x 
	 * with degree n in this polynomial.
	 */
	public double getCoefficient(int n)	{

		if (n <= this.coefficients.length - 1) {
			return this.coefficients[n];
		}
		return 0.0;
	}
	
	/*
	 * set the coefficient of the variable x 
	 * with degree n to c in this polynomial.
	 * If the degree of this polynomial < n, it means that that the coefficient of the variable x 
	 * with degree n was 0, and now it will change to c. 
	 */
	public void setCoefficient(int n, double c) {
		if (n < this.coefficients.length - 1) {
			this.coefficients[n] = c;
		}
		else {
			double[] newCoeff = new double[n + 1];
			newCoeff[n] = c;
			this.coefficients = newCoeff;
		}
	}
	
	/*
	 * Returns the first derivation of this polynomial.
	 *  The first derivation of a polynomial a0x0 + ...  + anxn is defined as 1 * a1x0 + ... + n anxn-1.
	 */
	public Polynomial getFirstDerivation() {
		double[] derivationCoeff = new double[this.coefficients.length - 1];
		for (int i = 0; i < derivationCoeff.length; i++) {
			derivationCoeff[i] = (i + 1) * this.coefficients[i + 1];
		}
		return new Polynomial(derivationCoeff);
	}
	
	/*
	 * given an assignment for the variable x,
	 * compute the polynomial value
	 */
	public double computePolynomial(double x) {
		double sum = this.coefficients[0];
		for (int i = 1; i < this.coefficients.length; i++) {
			sum += this.coefficients[i] * Math.pow(x, i);
		}
		return sum;
	}
	
	/*
	 * given an assignment for the variable x,
	 * return true iff x is an extrema point (local minimum or local maximum of this polynomial)
	 * x is an extrema point if and only if The value of first derivation of a polynomial at x is 0
	 * and the second derivation of a polynomial value at x is not 0.
	 */
	public boolean isExtrema(double x) {
		Polynomial firstDerivative = this.getFirstDerivation();
		Polynomial secondDerivative = firstDerivative.getFirstDerivation();
		return firstDerivative.computePolynomial(x) == 0.0
				&& secondDerivative.computePolynomial(x) != 0.0;
	}
	
	
	
	

    
    

}
