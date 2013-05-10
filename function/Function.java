package function;
/**
 * Abstract class for the implementation of different single-variate function types.
 * @author 	Kushal Ranjan
 * @version	050613
 */
public abstract class Function {
	
	double scalar = 1; //Scalar multiplier for this function
	
	/**
	 * eval() takes in a double as a parameter and outputs the double to which this function maps
	 * the parameter.
	 * @param param	input to the Function
	 * @return		the Function evaluated at the value of the parameter.
	 */
	public abstract double eval(double param);
	
	/**
	 * Determines and returns the derivative of this function.
	 * @return	the unique function that is the derivative of this function
	 */
	public abstract Function derivative();
}
