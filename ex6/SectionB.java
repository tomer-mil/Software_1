package il.ac.tau.cs.sw1.hw6;
import java.util.Arrays;

public class SectionB {
	
	/**
	* @post $ret == true iff exists i such that array[i] == value
	*/
	public static boolean contains(int[] array, int value) {
		for (int i : array) {
			if (i == value) {
				return true;
			}
		}
		return false;
	}
	
	// there is intentionally no @post condition here 
	/**
	* @pre array != null
	* @pre array.length > 2
	* @pre Arrays.equals(array, Arrays.sort(array))
	*/
	public static int unknown(int[] array) {

		for (Object i : array) {
			if (i.getClass().isArray()) {
				return 0;
			}
		}
		int[] arrayCopy = Arrays.copyOf(array, array.length);
		Arrays.sort(arrayCopy);
		if (Arrays.equals(array, arrayCopy)) {
			return 1;
		}
		return 0;
	}

	/**
	* @pre Arrays.equals(array, Arrays.sort(array))
	* @pre array.length >= 1
	* @post for all i array[i] <= $ret
	*/
	public static int max(int[] array) {
		int maxNum = array[0];
		for (int i = 1; i <array.length; i++) {
			if (array[i] > maxNum) {
				maxNum = array[i];
			}
		}
		return maxNum;
	}
	
	/**
	* @pre array.length >=1
	* @post for all i array[i] >= $ret
	* @post Arrays.equals(array, prev(array))
	*/
	public static int min(int[] array) {
		int minNum = array[0];
		for (int i = 1; i <array.length; i++) {
			if (array[i] < minNum) {
				minNum = array[i];
			}
		}
		return minNum;
	}
	
	/**
	* @pre word.length() >=1
	* @post for all i : $ret.charAt(i) == word.charAt(word.length() - i - 1)
	*/
	public static String reverse(String word) {
		StringBuilder reversedWord = new StringBuilder();
		for (int i = word.length() - 1; i >= 0; i--) {
			reversedWord.append(word.charAt(i));
		}
		return reversedWord.toString();
	}
	
	/**
	* @pre array != null
	* @pre array.length > 2
	* @pre Arrays.equals(array, Arrays.sort(array))
	* @pre exist i,j such that: array[i] != array[j]
	* @post !Arrays.equals($ret, Arrays.sort($ret))
	* @post for any x: contains(prev(array),x) == true iff contains($ret, x) == true
	*/
	public static int[] guess(int[] array) {
		int start = array[0];
		for (int i = 0; i < array.length; i++) {
			if (array[i] != start) {
				array[0] = array[i];
				array[i] = start;
				break;
			}
		}
		return array;
	}


}
