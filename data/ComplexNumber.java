package data;
/**
 * Class for the representation of complex numbers using the complexification of the reals.
 * @author	Kushal Ranjan
 * @version	050913
 */
public class ComplexNumber implements Data<ComplexNumber> {
	double re; //Real component
	double co; //Imaginary component
	
	public ComplexNumber(double re, double co) {
		this.re = re;
		this.co = co;
	}
	
	/**
	 * Adds this complex number to another and returns the sum.
	 */
	public ComplexNumber add(ComplexNumber o) {
		return new ComplexNumber(re + o.re, co + o.co);
	}
	
	/**
	 * Multiplies this complex number with another and returns the product.
	 */
	public ComplexNumber multiply(ComplexNumber o) {
		return new ComplexNumber((re * o.re) - (co * o.co), (re * o.co) + (co * o.re));
	}
	
	/**
	 * Returns the norm, or "magnitude," of this complex number.
	 * @return ||this||
	 */
	public double norm() {
		return Math.sqrt(Math.pow(re, 2) + Math.pow(co, 2));
	}
	
	public String toString() {
		return re + " + " + co + "i";
	}
}
