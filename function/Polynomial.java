package function;
import list.*;
import plot.*;
import matrixmath.*;
/**
 * Class for the manipulation and evaluation of polynomials.
 * 
 * @author 	Kushal Ranjan
 * @version	050613
 */
public class Polynomial extends Function {

	private LinkedList<Double> coeffs; //List of coefficients, starting with the constant term
	
	/**
	 * Test code.
	 */
	public static void main(String[] args) {
		double[][] points = {{-1,0}, {0,-1}, {1, 0}, {2,0}};
		Polynomial p = Polynomial.construct(points);
		System.out.println(p);
		System.out.println(p.derivative());
	}
	
	/**
	 * Constructs a polynomial from a preset list of coefficients.
	 */
	public Polynomial(LinkedList<Double> coeffs) {
		this.coeffs = coeffs;
	}
	
	/**
	 * Constructs a polynomial from an array of coefficients.
	 */
	public Polynomial(double[] coeffs) {
		this.coeffs = new LinkedList<Double>();
		for(double d: coeffs) {
			this.coeffs.add(new Double(d));
		}
	}
	
	/**
	 * Returns the degree of this polynomial.
	 */
	public int degree() {
		return coeffs.size() - 1;
	}
	
	/**
	 * Evaluates the polynomial at a specified value.
	 */
	public double eval(double param) {
		Iterator<Double> coeffIt = coeffs.iterator();
		double out = 0;
		for(int i = 0; i < coeffs.size(); i++) {
			out += coeffIt.next() * Math.pow(param, i);
		}
		return scalar * out;
	}
	
	/**
	 * Uses a system of linear equations to construct a Polynomial from a set of points
	 * @param points	a double[2][n] of n points {x, y}
	 * @return			the unique polynomial of degree n-1 passing through all points in points
	 */
	public static Polynomial construct(double[][] points) {
		double[][] coeffs = new double[points.length][points.length];
		double[] rhs = new double[points.length];
		for(int j = 0; j < coeffs.length; j++) {
			double[] point = points[j];
			rhs[j] = point[1];
			for(int i = 0; i < coeffs.length; i++) {
				coeffs[i][j] = Math.pow(point[0], i);
			}
		}
		try {
			Matrix solution = MatrixCalc.solve(new Matrix(coeffs), new Matrix(rhs));
			return new Polynomial(solution.getCol(0));
		} catch (MatrixException e) {
			System.err.println(e);
			return null;
		}
	}
	
	/**
	 * Adds two polynomials together.
	 * @param a	first polynomial
	 * @param b	second polynomial
	 * @return	the sum of a and b
	 */
	public static Polynomial add(Polynomial a, Polynomial b) {
		Iterator<Double> aIt = a.coeffs.iterator();
		Iterator<Double> bIt = b.coeffs.iterator();
		LinkedList<Double> newCoeffs = new LinkedList<Double>();
		while(aIt.hasNext() && bIt.hasNext()) {
			newCoeffs.add(aIt.next() + bIt.next());
		}
		while(aIt.hasNext()) {
			newCoeffs.add(aIt.next());
		}
		while(bIt.hasNext()) {
			newCoeffs.add(bIt.next());
		}
		return new Polynomial(newCoeffs);
	}
	
	/**
	 * Multiplies two polynomials together.
	 * @param a	first polynomial
	 * @param b	second polynomial
	 * @return	the product of a and b
	 */
	public static Polynomial multiply(Polynomial a, Polynomial b) {
		double[] coeffs = new double[a.degree() + b.degree() + 1];
		Iterator<Double> aIt = a.coeffs.iterator();
		int aDeg = 0;
		while(aIt.hasNext()) {
			double aCoeff = aIt.next();
			Iterator<Double> bIt = b.coeffs.iterator();
			int bDeg = 0;
			while(bIt.hasNext()) {
				double bCoeff = bIt.next();
				coeffs[aDeg + bDeg] += aCoeff * bCoeff;
				bDeg++;
			}
			aDeg++;
		}
		return new Polynomial(coeffs);
	}
	
	/**
	 * Gives the derivative of this polynomial.
	 * @return	the derivative polynomial of this polynomial.
	 */
	public Polynomial derivative() {
		LinkedList<Double> newCoeffs = new LinkedList<Double>();
		int pow = 1;
		Iterator<Double> it = coeffs.iterator();
		it.next();
		while(it.hasNext()) {
			newCoeffs.add(it.next() * pow);
			pow++;
		}
		return new Polynomial(newCoeffs);
	}
	
	/**
	 * Plots the polynomial on a specified domain.
	 * @param lowerBound	lower bound of the plot
	 * @param upperBound	upper bound of the plot
	 */
	public void plot(int lowerBound, int upperBound) {
		String[] args = new String[upperBound - lowerBound + 1];
		int pos = 0;
		for(int i = lowerBound; i <= upperBound; i++) {
			String point = "[" + i + "," + (int)eval((double)i) + "]";
			args[pos] = point;
			pos++;
		}
		Plot.main(args);
	}
	
	public String toString() {
		Iterator<Double> it = coeffs.iterator();
		String out = scalar + "(" + it.next();
		for(int i = 1; i < coeffs.size(); i++) {
			if(i == 1)
				out += " + " + it.next() + "(x)";
			else
				out += " + " + it.next() + "(x^" + i + ")";
		}
		return out + ")";
	}

}
