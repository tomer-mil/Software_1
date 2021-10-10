package il.ac.tau.cs.sw1.ex2;
import java.util.Arrays;

public class Assignment02Q05 {

	public static void main(String[] args) {
		// do not change this part below
		int N = Integer.parseInt(args[0]);
		int[][] matrix = new int[N][N]; // the input matrix to be
		for(int i=0;i < N; i++){
			for(int j=0; j < N; j++){
				matrix[i][j] = Integer.parseInt(args[1+(i*N)+j]); // the value at [i][j] is the i*N+j item in args (without considering args[0])
			}
		}
		for(int i=0;i < N; i++)
			System.out.println(Arrays.toString(matrix[i]));
		System.out.println("");

		for (int i = 0; i < N; i++) { // rotating the original matrix by 90 degrees in-place!
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(args[(N*N) - ((N * (j + 1)) - (i + 1))]);
			}
		}

		// do not change this part below
		for(int i=0;i < N; i++){
			System.out.println(Arrays.toString(matrix[i])); // this would compile after you would put value in transposedMatrix
		}
	}
}
