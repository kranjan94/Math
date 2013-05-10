package matrixmath;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

	public static void main(String[] args) throws Exception
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter size of matrix, and then each row with values space-separated.");
		int size = Integer.parseInt(in.readLine());
		double[][] coeff = new double[size][size];
		for(int i = 0; i < size; i++) {
			String input = in.readLine();
			double[] row = new double[size];
			String[] valStrings = input.split(" ");
			for(int j = 0; j < valStrings.length; j++) {
				row[j] = Double.parseDouble(valStrings[j]);
			}
			coeff[i] = row;
		}
		Matrix A = new Matrix(coeff);
		
		System.out.println("Enter number of columns, and then each row with values space-separated.");
		int newSize = Integer.parseInt(in.readLine());
		coeff = new double[size][newSize];
		for(int i = 0; i < size; i++) {
			String input = in.readLine();
			double[] row = new double[newSize];
			String[] valStrings = input.split(" ");
			for(int j = 0; j < valStrings.length; j++) {
				row[j] = Double.parseDouble(valStrings[j]);
			}
			coeff[i] = row;
		}
		Matrix B = new Matrix(coeff);
		Matrix solution = MatrixCalc.solve(A, B);
		solution.printMatrix();
	}
}
