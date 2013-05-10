package matrixmath;
/**
 * Represents a column vector in R^n.
 * 
 * @author KushalRanjan
 * @version	100112
 */
public class Vector extends Matrix
{	
	/**
	 * Constructors
	 */
	public Vector(double[][] vals)
	{	super(vals[0]);	}
	public Vector(double[] vals) 
	{	super(vals);	}
	public Vector(double val)
	{	super(val);	}
	public Vector(int rows, int cols) 
	{	super(rows, 1);	}
	/**
	 * Constructs a matrix out of the vectors in v
	 * 
	 * @param v	the array of vectors to be used
	 * @return	a matrix consisting of the vectors in v
	 * @throws Exception	if the vectors in v are of varying dimensions
	 */
	public static Matrix vectorArray(Vector[] v) throws Exception
	{
		double[][] vals = new double[v.length][v[0].length()];
		for(int i = 0; i<v.length; i++)
		{
			double[] entries = v[i].getEntries();
			if(entries.length != vals[0].length)
				throw new Exception("Vectors are not all of same length.");
			vals[i] = entries;
		}
		return new Matrix(vals);
	}

	/**
	 * Creates a vector of length entries
	 * 
	 * @param length	the number of entries in the vector
	 */
	public Vector(int length)
	{	super(length, 1);	}
	
	/**
	 * Selectors
	 */
	
	/**
	 * Returns the entries of the vector
	 * 
	 * @return	the entries of the vector
	 */
	public double[] getEntries()
	{	return super.getCol(0);	}
	
	/**
	 * Returns the (n+1)th component of the vector
	 * 
	 * @param n	the index of the component to be returned
	 * @return	the (n+1)th component of the vector
	 */
	public double getComp(int n)
	{
		double[] entries = getEntries();
		return entries[n];
	}
	
	/**
	 * Properties
	 */
	
	/**
	 * Returns the Euclidian norm of the vector
	 * 
	 * @return	the Euclidian norm of the vector
	 */
	public double norm()
	{
		double sum = 0;
		for(double d: getEntries())
		{
			sum += Math.pow(d,2);
		}
		return Math.sqrt(sum);
	}
	
	/**
	 * Returns the number of entries in the vector
	 * 
	 * @return	number of entries of the vector
	 */
	public int length()
	{	return getEntries().length;	}
}
