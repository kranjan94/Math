package matrixmath;
 /**
 * Stores and displays information about a matrix over R.
 * 
 * @author KushalRanjan
 * @version 092412
 */
public class Matrix {

	private double[][] entries;
	
	/**
	 * Constructors
	 */
	
	/**
	 * Create a matrix with the entries stored in vals
	 * 
	 * @param vals	the values to be used in the new matrix
	 */
	public Matrix(double[][] vals)
	{
		entries = vals;
	}
	
	/**
	 * Create 1 column matrix with the entries stored in vals
	 * 
	 * @param vals	the values to be used in the new matrix
	 */
	public Matrix(double[] vals)
	{
		entries = new double[1][vals.length];
		entries[0] = vals;
	}
	
	/**
	 * Create 1x1 matrix with the specified value
	 * 
	 * @param val	the value to be used in the new matrix
	 */
	public Matrix(double val)
	{
		entries = new double[1][1];
		entries[0][0] = val;
	}
	
	/**
	 * Create a zero matrix with a specified number of rows and columns
	 * 
	 * @param rows	number of rows in new matrix
	 * @param cols	number of columns in new matrix
	 */
	public Matrix(int rows, int cols)
	{
		entries = new double[rows][cols];
	}
	
	/**
	 * Displays the dimensions and entries of the matrix
	 */
	public void printMatrix()
	{
		System.out.println(getNumRows() + " rows, " + getNumCols() + " columns:");
		for(int row = 0; row<entries[0].length; row++)
		{
			for(int col = 0; col<entries.length; col++)
			{
				System.out.print(roundThreeDecimals(entries[col][row]) + "\t");
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * Selectors
	 */
	
	public int getNumRows() //Returns number of rows in matrix
	{	return entries[0].length;	}
	public int getNumCols() //Returns number of columns in matrix
	{	return entries.length;	}
	public double[] getRow(int num) //Returns entries in a specific row
	{
		double[] row = new double[entries.length];
		for(int i = 0; i<row.length; i++)
		{
			row[i] = entries[i][num];	
		}
		return row;	
	}
	public double[] getCol(int num) //Returns entries in a specific column
	{	return entries[num];	}
	public double[][] getArray() //Returns the entries in a 2D array
	{	return entries;	}
	
	/**
	 * Returns the determinant of the matrix if it is square.
	 * 
	 * @precondition	Matrix is square
	 * @return			double type representation of determinant
	 */
	public double determinant() throws MatrixException
	{
		if(entries.length != entries[0].length)
			throw new MatrixException("Matrix is not n x n.");
		double det = 0;
		if(entries.length == 2) // Determinant can be directly calculated
			return (entries[0][0]*entries[1][1] - entries[1][0]*entries[0][1]);
		for(int i = 0; i<entries.length; i++) //Determinant requires recursive formula
		{
			Matrix recMat = getSubmatrix(i,0);
			det += entries[i][0]*Math.pow(-1,i)*recMat.determinant();
		}
		return det;
	}
	
	/**
	 * Derived matrices
	 */
	
	/**
	 * Returns a new matrix excluding a specified row and column
	 * 
	 * @param col	the column to exclude
	 * @return		a matrix without row row and column col
	 */
	public Matrix getSubmatrix(int col, int row)
	{
		double[][] mat = new double[getNumRows()-1][getNumCols()-1];
		int colPos = 0;
		for(int c = 0; c<entries.length; c++)
		{
			if(c != col)
			{
				int rowPos = 0;
				for(int r = 0; r<entries[c].length; r++)
				{
					if(r != row)
					{
						mat[colPos][rowPos] = entries[c][r];
						rowPos++;
					}
				}
				colPos++;
			}
		}
		return new Matrix(mat);
	}
	
	/**
	 * Returns the transpose of the matrix. The transpose of A is the matrix whose
	 * rows are the columns of A and whose columns are the rows of A.
	 * 
	 * @return	the transpose of this matrix
	 */
	public Matrix getTranspose()
	{
		double[][] trans = new double[getNumRows()][getNumCols()];
		for(int c = 0; c<entries.length; c++)
		{
			for(int r = 0; r<entries[0].length; r++)
			{
				trans[r][c] = entries[c][r];
			}
		}
		return new Matrix(trans);
	}
	
	/**
	 * Returns the adjugate of the matrix. The adjugate of A is the transpose of the
	 * cofactor matrix of A.
	 * 
	 * @return	the adjugate matrix of this matrix
	 */
	public Matrix getAdjugate() throws MatrixException {
		if(entries.length != entries[0].length)
			throw new MatrixException("Matrix is not n x n.");
		double[][] cof = new double[entries.length][entries.length];
		for(int c = 0; c<entries.length; c++)
		{
			for(int r = 0; r<entries[0].length; r++)
			{
				Matrix cofactor = getSubmatrix(c,r);
				cof[c][r] = cofactor.determinant()*Math.pow(-1, c+r);
			}
		}
		Matrix adjTrans = new Matrix(cof);
		return adjTrans.getTranspose();
	}
	
	/**
	 * Returns the inverse of the matrix. The inverse of matrix A is the matrix B such that
	 * AB = I.
	 *
	 * @return	the inverse of the matrix
	 */
	public Matrix getInverse() throws MatrixException {
		double det = determinant();
		double coeff;
		if(det != 0)
			coeff = ((double)1)/det;
		else
			throw new MatrixException("Inverse does not exist.");
		Matrix adj = getAdjugate();
		double[][] invEnt = adj.getArray();
		for(int c = 0; c<invEnt.length; c++)
		{
			for(int r = 0; r<invEnt[0].length; r++)
			{
				invEnt[c][r] *= coeff;
			}
		}
		return new Matrix(invEnt);
	}
	
	/**
     * Modified from: 
     * http://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
     * Rounds the given decimal number to the nearest thousandth.
     * 
     * @param   d   the decimal number to be rounded
     */
    public static double roundThreeDecimals(double d)  {
        return Double.parseDouble(String.format("%.3g%n", d));
    }

}
