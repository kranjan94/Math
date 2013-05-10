package function;
/**
 * Abstract class to represent sinusoidal functions.
 * Should be extended by Sine, Cosine, Tangent, and their reciprocal functions.
 * @author 	Kushal Ranjan
 * @version	050913
 */
public abstract class Sinusoid extends Function{
	double angFreq; //Angular frequency of the sinusoid
	double phase;  //Phase shift of the sinusoid
	
	/**
	 * Constructor for a sinusoid with no arguments.
	 */
	public Sinusoid() {
		angFreq = 1;
		phase = 0;
	}
	
	/**
	 * Constructor with only a frequency.
	 */
	public Sinusoid(double f) {
		angFreq = f;
		phase = 0;
	}
	
	/**
	 * Constructor with both a frequency and a phase shift.
	 */
	public Sinusoid(double f, double ph) {
		angFreq = f;
		phase = ph;
	}
}
