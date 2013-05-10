package function;
/**
 * Represents a cosine function with a phase shift and a specified frequency.
 * @author	Kushal Ranjan
 * @version	050913
 */
public class Cosine extends Sinusoid{

	/**
	 * Constructor for a cosine function with no arguments.
	 */
	public Cosine() {
		super();
	}
	
	/**
	 * Constructor with only a frequency.
	 */
	public Cosine(double f) {
		super(f);
	}
	
	/**
	 * Constructor with both a frequency and a phase shift.
	 */
	public Cosine(double f, double ph) {
		super(f, ph);
	}
	
	/**
	 * Evaluates this sine function at the specified point.
	 */
	public double eval(double param) {
		double shiftedInput = angFreq * param + phase;
		return scalar * Math.cos(shiftedInput);
	}
	
	/**
	 * Gives the derivative of this cosine function, which is a sine function.
	 */
	public Sine derivative() {
		Sine ret = new Sine(angFreq, phase);
		ret.scalar = angFreq;
		return ret;
	}
}
