package il.ac.tau.cs.sw1.hw3;
import java.util.Arrays;

public class ArrayUtils {

	//Question 1
	public static int[][] transposeMatrix(int[][] m) {
		if (m.length != 0) {
			int numOfColumns = m.length; //columns of transposed matrix
			int numOfRows = m[0].length; //rows of transposed matrix
			int[][] mTransposed = new int[numOfRows][numOfColumns];

			for (int i = 0; i < numOfRows; i++) {
				for (int j = 0; j < numOfColumns; j++) {
					mTransposed[i][j] = m[j][i]; // does the transposing
				}
			}

			return mTransposed;
		}
		else {
			return new int[0][];
		}
	}

	//Question 2
	private static boolean isValidInput(int[] arr, char dir) {
		boolean direction = (dir == 'L' || dir == 'R');
		boolean array = (arr.length != 0);
		return (direction && array);
	}
	public static int[] shiftArrayCyclic(int[] array, int move, char direction) {
		if (isValidInput(array, direction)) {

			// assuming direction == 'L'
			if (direction == 'R') {
				move *= -1;
			}
			// Stats
			int length = array.length;
			int[] shiftedArray = new int[length];
			int actualMove = move % length;

			//cycling the array
			for (int i = 0; i < length; i++) {
				int originalPointer = (actualMove + i) % length;
				if (originalPointer < 0) { //if move is negative
					originalPointer += length;
				}
				shiftedArray[i] = array[originalPointer];
			}

			System.arraycopy(shiftedArray, 0, array, 0, length);

		}
		return array;
	}

	//Question 3
	public static int alternateSum(int[] array) {
		if (array.length == 0) {
			return 0;
		}

		int maxSum = 0;

		for (int j = 0; j < array.length; j++) {
			boolean isEven = true;
			int currSum = 0;
			for (int i = j; i < array.length; i++) {
				currSum += isEven ? array[i] : -array[i];
				maxSum = Math.max(currSum, maxSum);
				isEven = !isEven;
				}
			}
		return maxSum;

	}

	//Question 4
	/**
	 * Creates a line for BFS
	 * @param a index
	 * @param b exponent
	 * @return a line of length a^b filled with -1
	 */
	private static int[] createLine(int a, int b) {
		int[] line = new int[(int) Math.pow(a, b + 4)];
		Arrays.fill(line, -1);
		return line;
	}
	public static int findPath(int[][] m, int i, int j, int k) {

		if (k == 0) {
			return 0;
		}
		//CREATE LINE
		int[] line = createLine(m.length, k);

		// INITIALIZE LINE PARAMETERS
		line[0] = i;
		int waitingInLine = 1;
		int currentNode;
		int queue = 0;
		int openIndex = 1;

		int steps = -1;

		while (waitingInLine > 0) {
			currentNode = line[queue];

			// IF FOUND
			if (currentNode == j && steps == k) {
				return 1;
			}

			// LOOKING FOR NEIGHBORS
			for (int g = 0; g < m[currentNode].length; g++) {
				if (m[currentNode][g] == 1) {
					// LOOKING FOR NEXT OPEN QUEUE
					line[openIndex] = g;
					openIndex++;
					waitingInLine++;
				}
			}
			steps++;
			queue++;
			waitingInLine--;
		}

		// NOT FOUND
		return 0;
	}

}
