package matrixmath;
/**
 * Enables calculations on and manipulations of a matrix or multiple matrices
 * 
 * @author KushalRanjan
 * @version 092412
 */
public class MatrixCalc {
	
	/**
	 * Solves the vector equation Ax = b for each b (column in B
	 * 
	 * @param coeff	the matrix A
	 * @param B		the matrix whose columns make up the right side of the equation Ax = b.
	 * @return		the solution vector
	 */
	public static Matrix solve(Matrix coeff, Matrix B) throws MatrixException
	{
		Matrix AInv = coeff.getInverse();
		Matrix solution = multiply(AInv,B);
		return solution;
	}
	
	/**
	 * Returns the sum of matrices A and B, if applicable
	 * 
	 * @param A	the first matrix
	 * @param B	the second matrix
	 * @return	the matrix sum A+B
	 * @throws MatrixException	if A and B are of different sizes
	 */
	public static Matrix add(Matrix A, Matrix B) throws MatrixException
	{
		if(A.getNumCols() != B.getNumCols() || A.getNumRows()!=B.getNumRows())
			throw new MatrixException("Matrices are of different sizes.");
		double[][] newMat = new double[A.getNumCols()][A.getNumRows()];
		for(int c = 0; c<newMat.length; c++)
		{
			double[] columnA = A.getCol(c);
			double[] columnB = B.getCol(c);
			for(int r = 0; r<columnA.length; r++)
			{
				newMat[c][r] = columnA[r] + columnB[r];
			}
		}
		return new Matrix(newMat);
	}
	
	/**
	 * Returns the difference of matrices A and B, if applicable
	 * 
	 * @param A	the first matrix
	 * @param B	the second matrix
	 * @return	the matrix difference A-B
	 * @throws MatrixException	if A and B are of different sizes
	 */
	public static Matrix sub(Matrix A, Matrix B) throws MatrixException
	{
		return add(A, multiplyConstant(B,-1));
	}
	
	/**
	 * Returns the result of raising a matrix to a power
	 * 
	 * @param A	the matrix
	 * @param k	the exponent
	 * @return	the matrix A raised to the kth power
	 * @throws MatrixException	if A cannot be exponentiated
	 */
	public static Matrix pow(Matrix A, int k) throws MatrixException
	{
		Matrix result = A;
		for(int i = 1; i<k; i++)
		{
			result = multiply(result,A);
		}
		return result;
	}
	
	/**
	 * Returns a matrix scaled by a constant
	 * 
	 * @param A	the matrix
	 * @param k	the constant
	 * @return	the product kA
	 */
	public static Matrix multiplyConstant(Matrix A, double k)
	{
		double[][] prod = A.getArray();
		for(int c = 0; c<prod.length; c++)
		{
			for(int r = 0; r<prod.length; r++)
			{
				prod[c][r] *= k;
			}
		}
		return new Matrix(prod);
	}
	
	/**
	 * Returns the product of two matrices if it is defined.
	 * 
	 * @param A	first factor matrix
	 * @param B	second factor matrix
	 * @return	the matrix product AB
	 * @throws MatrixException	if product is undefined
	 */
	public static Matrix multiply(Matrix A, Matrix B) throws MatrixException
	{
		if(A.getNumCols() != B.getNumRows())
			throw new MatrixException("Matrix product undefined.");
		double[][] prod = new double[B.getNumCols()][A.getNumRows()];
		for(int c = 0; c<prod.length; c++)
		{
			double[] column = B.getCol(c);
			for(int r = 0; r<prod[0].length; r++)
			{
				prod[c][r] = dotProduct(A.getRow(r),column);
			}
		}
		return new Matrix(prod);
	}

	/**
	 * Returns the dot product of two vectors of equal length.
	 * 
	 * @param A	first vector
	 * @param B	second vector
	 * @return	the dot product of A and B
	 * @throws MatrixException	if the vectors are of different dimensions
	 */
	public static double dotProduct(double[] A, double[] B) throws MatrixException
	{
		if(A.length != B.length)
			throw new MatrixException("Vectors are of different dimensions.");
		double sum = 0;
		for(int i = 0; i<A.length; i++)
		{
			sum += A[i]*B[i];
		}
		return sum;
	}

	/**
	 * Displays the dimensions and entries of a matrix
	 */
	public static void printMatrix(Matrix A)
	{
		System.out.println(A.getNumRows() + " rows, " + A.getNumCols() + " columns:");
		double[][] entries = A.getArray();
		for(int row = 0; row<A.getNumRows(); row++)
		{
			for(int col = 0; col<A.getNumCols(); col++)
			{
				System.out.print(roundThreeDecimals(entries[col][row]) + "\t");
			}
			System.out.print("\n");
		}
	}
	
	/**
     * Modified from: 
     * http://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
     * Rounds the given decimal number to the nearest thousandth.
     * 
     * @param   d   the decimal number to be rounded
     */
    public static double roundThreeDecimals(double d) 
    {
        return Double.parseDouble(String.format("%.3g%n", d));
    }
    
}
