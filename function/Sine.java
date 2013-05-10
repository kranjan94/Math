package function;
/**
 * Represents a sine function with a phase shift and a specified frequency.
 * @author	Kushal Ranjan
 * @version	050913
 */
public class Sine extends Sinusoid{

	/**
	 * Constructor for a sine function with no arguments.
	 */
	public Sine() {
		super();
	}
	
	/**
	 * Constructor with only a frequency.
	 */
	public Sine(double f) {
		super(f);
	}
	
	/**
	 * Constructor with both a frequency and a phase shift.
	 */
	public Sine(double f, double ph) {
		super(f, ph);
	}

	/**
	 * Evaluates this sine function at the specified point.
	 */
	public double eval(double param) {
		double shiftedInput = angFreq * param + phase;
		return Math.sin(shiftedInput);
	}
	
	public Cosine derivative() {
		Cosine ret = new Cosine(angFreq, phase);
		ret.scalar = angFreq;
		return ret;
	}
}
