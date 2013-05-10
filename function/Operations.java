package function;
/**
 * Class for performing numerical operations on functions.
 * @author	Kushal Ranjan
 * @version 050713
 */
public class Operations {
	
	/**
	 * Test code.
	 */
	public static void main(String[] args) {
		//Construct a polynomial and take its derivative at x = 1.
		double[][] points = {{-1,1}, {0, 0}, {1, 1}};
		Function f = Polynomial.construct(points);
		System.out.println(f);
		System.out.println(differentiate(f, 1));
	}
	
	/**
	 * Takes the product of the values of a function evaluated at specified points.
	 * @param f		function to use
	 * @param low	lower bound of calculation
	 * @param high	upper bound of calculation
	 * @param step	step size to use for calculation
	 * @return		the product of the values of f evaluated at (low + k*step) for all k while
	 * 				(low + k*step) <= high.
	 */
	public static double product(Function f, double low, double high, double step) {
		double out = 1;
		for(double i = 0; i <= high; i += step) {
			out *= f.eval(i);
		}
		return out;
	}
	
	/**
	 * Takes the product of the values of a function evaluated at specified points with a step
	 * size of 1.
	 * @param f		function to use
	 * @param low	lower bound of calculation
	 * @param high	upper bound of calculation
	 * @return		the product of the values of f evaluated at (low + k) for all k while
	 * 				(low + k) <= high.
	 */
	public static double product(Function f, int low, int high) {
		return product(f, low, high, 1);
	}
	
	/**
	 * Takes the sum of the values of a function evaluated at specified points.
	 * @param f		function to use
	 * @param low	lower bound of calculation
	 * @param high	upper bound of calculation
	 * @param step	step size to use for calculation
	 * @return		the sum of the values of f evaluated at (low + k*step) for all k while
	 * 				(low + k*step) <= high.
	 */
	public static double sum(Function f, double low, double high, double step) {
		double out = 0;
		for(double i = 0; i <= high; i += step) {
			out += f.eval(i);
		}
		return out;
	}
	
	/**
	 * Takes the sum of the values of a function evaluated at specified points with a step size
	 * of 1.
	 * @param f		function to use
	 * @param low	lower bound of calculation
	 * @param high	upper bound of calculation
	 * @return		the sum of the values of f evaluated at (low + k) for all k while
	 * 				(low + k) <= high.
	 */
	public static double sum(Function f, int low, int high) {
		return sum(f, low, high, 1);
	}
	
	/**
	 * Numerically determines the integral of a function between two points.
	 * @param f		function to integrate
	 * @param low	lower bound of integration
	 * @param high	upper bound of integration
	 * @return		the integral of f from low to high
	 */
	public static double integrate(Function f, double low, double high) {
		double prevOut = 0;
		double out = 1;
		double intervals = 100;
		double step = (high - low)/intervals;
		do {
			prevOut = out;
			out = 0;
			for(double i = low; i < high; i += step) {
				out += step * f.eval(i);
			}
			intervals *= 2;
			step = (high - low)/intervals;
		} while(((prevOut - out)/prevOut) > 0.0001);
		return out;
	}
	
	/**
	 * Numerically determines the derivative of a function at a specified point.
	 * @param f	function to differentiate
	 * @param x	point in domain at which to differentiate
	 * @return	the derivative of f at x
	 */
	public static double differentiate(Function f, double x) {
		double prevOut = 0;
		double out = 1;
		double delta = 0.01;
		do {
			prevOut = out;
			out = (f.eval(x + delta) - f.eval(x - delta)) / (2*delta);
		} while(((prevOut - out)/prevOut) > 0.0001);
		return out;
	}

}
