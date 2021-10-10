package il.ac.tau.cs.sw1.hw3;
import java.util.Arrays;

public class StringUtils {


	//Question 5
	private static boolean isAfter(String word1, String word2) {
		int w1FirstLetter = word1.charAt(0);
		int w2FirstLetter = word2.charAt(0);

		return w1FirstLetter < w2FirstLetter;
	}
	private static boolean isBefore(String word1, String word2) {
		int w1FirstLetter = word1.charAt(0);
		int w2FirstLetter = word2.charAt(0);

		return w1FirstLetter > w2FirstLetter;
	}
	private static boolean isLex(String w1, String w2) {

		if (isAfter(w1, w2) || w1.equals(w2)) {
			return true;
		}

		if (isBefore(w1, w2)) {
			return false;
		}

		return isLex(w1.substring(1), w2.substring(1));
	}
	public static String findSortedSequence(String str) {

		if (str.isBlank() || str.isEmpty()) {
			return "";
		}

		String[] splitArray = str.split(" ");
		String finalSentence = "";
		String currentSentence = splitArray[0];
		int cnt = 0;
		int maxLength = 0;

		for (int i = 0; i < splitArray.length - 1; i++) {
			if (isLex(splitArray[i], splitArray[i + 1])) {
				cnt++;
				currentSentence = String.join(" ",currentSentence, splitArray[i + 1]);
				if (cnt >= maxLength) {
					maxLength = cnt;
					finalSentence = currentSentence;
				}
			}
			else {
				cnt = 0;
				currentSentence = splitArray[i + 1];
			}

		}
		if (maxLength == 0) {
			return splitArray[splitArray.length - 1];
		}
		return finalSentence;

	}


	// Question 6
	/**
	 * Converts a String to a space-less char[]
	 * @param a String
	 * @return char[] without spaces
	 */
	private static char[] doLettersArray(String a) {
		String lower = a.toLowerCase();
		String[] wordsArray = lower.split(" ");
		String noSpaces = String.join("", wordsArray);
		return noSpaces.toCharArray();

	}
	/**
	 * Sorts a String by a lexicographic order and removes spaces
	 * @param a String (Sentence/word)
	 * @return A sorted and space-less String
	 */
	private static String toLexString(String a) {
		char[] charArray = doLettersArray(a);
		Arrays.sort(charArray);
		return Arrays.toString(charArray);
	}
	public static boolean isAnagram(String a, String b) {
		return (toLexString(a).equals(toLexString(b)));
	}


	// Question 7
	/**
	 * Checks for length difference between 2 Strings
	 * @param a First word
	 * @param b Second word
	 * @return "-1" if greater than 1; "0" if no diff; "1" if equals 1
	 */
	private static int checkLengthDiff(String a, String b) {
		if (Math.abs(a.length() - b.length()) > 1) {
			return -1;
		}

		if (a.length() == b.length()) {
			return 0;
		}

		return 1;
	}
	/**
	 * Assuming a.length == b.length; checks if the words have more than one different letter
	 * @param a First word
	 * @param b Second word
	 * @return true if only 1 difference, otherwise false.
	 */
	private static boolean isMoreThanOneDiff(String a, String b) {
		int cnt = 0;

		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) {
				cnt++;
			}

			if (cnt > 1) {
				return false;
			}
		}

		return true;
	}
	/**
	 * Assuming one of the strings is longer by 1 from the other, checks if removing one letter makes the longer string equal to the shorter one.
	 * @param a First word to check
	 * @param b Second word to check
	 * @return true if possible; false otherwise
	 */
	private static boolean canRemoveOneLetter(String a, String b) {
		int diff = a.length() - b.length();

		int i = 0;
		while (a.charAt(i) == b.charAt(i)) {
			i++;
		}

		int x = diff == 1 ? b.length() : a.length();

		for (int j = i; j < x; j++) {
			if (diff == 1) {
				if (b.charAt(j) != a.charAt(j + 1)) {
					return false;
				}
			}
			else {
				if (a.charAt(j) != b.charAt(j + 1)) {
					return false;
				}
			}
		}
		return true;
	}
	public static boolean isEditDistanceOne(String a, String b) {
		if (a.equals(b)) {
			return true;
		}

			int diff = checkLengthDiff(a, b);

			switch (diff) {
				case -1:
					return false;

				case 0:
					return isMoreThanOneDiff(a, b);

				case 1:
					if ((a.contains(b) || b.contains(a))) {
						return true;
					}
					return canRemoveOneLetter(a, b);

			}
		return false;

	}
	
}
