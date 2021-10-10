package il.ac.tau.cs.sw1.ex4;


import java.util.Random;
import java.util.Scanner;

public class WordPuzzle {
	public static final char HIDDEN_CHAR = '_';
	public static final int MAX_VOCABULARY_SIZE = 3000;

	/**
	 * Question 1
	 */
	/*
	 * @pre: template is legal for word
	 */
	public static char[] createPuzzleFromTemplate(String word, boolean[] template) { // Q - 1
		char[] puzzle = new char[word.length()];
		for (int i = 0; i < word.length(); i++) {
			if (template[i]) {
				puzzle[i] = HIDDEN_CHAR;
			} else {
				puzzle[i] = word.charAt(i);
			}
		}
		return puzzle;
	}

	/**
	checks if the template and the word are the same length
	 */
	private static boolean areLengthsEquals(String word, boolean[] template) {
		return word.length() == template.length;
	}

	/**
	 * checks if all same letters are hidden
	 */
	private static boolean areAllLettersHidden(String word, boolean[] template) {

		for (int i = 0; i < template.length; i++) {
			if (template[i]) { // found hidden character (true value)
				char letterToCheck = word.charAt(i); // fetches hidden letter
				for (int j = 0; j < template.length; j++) {
					if (letterToCheck == word.charAt(j) && !template[j]) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	checks if at least one letter is hidden and not all letters are hidden
	 */
	private static boolean isOneOrAllHidden(boolean[] template) {
		int cnt = 0;
		for (boolean bool : template) {
			if (bool) {
				cnt++;
			}
		}
		return (cnt != template.length && cnt != 0);
	}

	/**
	checks if the puzzle is legally hidden
	 */
	private static boolean isLegallyHidden(String word, boolean[] template) {
		return (isOneOrAllHidden(template) && areAllLettersHidden(word, template));
	}
	/**
	 * Question 2
	 */
	public static boolean checkLegalTemplate(String word, boolean[] template) { // Q - 2
		return (areLengthsEquals(word, template) && isLegallyHidden(word, template));
	}

	/*
	 * @pre: 0 < k < word.length(), word.length() <= 10
	 */

	/**
	does a factorial operation
	 */
	public static int factorial(int a) {
		int i = 1;
		int res = 1;
		while (i <= a) {
			res *= i;
			i++;
		}
		return res;
	}
	/**
	does a choose operation
	 */
	public static int nCk(int n, int k) {
		int res;

		if (k > n) {
			return 0;
		}

		res = (factorial(n)) / ((factorial(k)) * factorial(n - k));
		return res;
	}

	/**
	 * takes a single String and translates it to a boolean array
	 * @param perm String of 0 an 1
	 * @return boolean[]
	 */
	private static boolean[] convertToBoolArray(String perm) {
		boolean[] res = new boolean[perm.length()];
		for (int i = 0; i < perm.length(); i++) {
			res[i] = perm.charAt(i) == '1';
		}
		return res;
	}

	/**
	 *
	 * @param n is the length of the word
	 * @param k is the number of trues
	 * @return an array of strings containing all possible permutations, including illegal ones
	 */
	private static String[] createAllPermStringArray(int n, int k) {

		String[] allPerm = new String[nCk(n, k)];
		int cnt = 0;

		for (int i = 0; i < Math.pow(2, n); i++) {
			if (Integer.bitCount(i) != k) {
				continue;
			}

			String binary = Integer.toBinaryString(i);
			String perm;

			if (binary.length() < n) {
				perm = String.format("%0" + (n - binary.length()) + "d%s", 0, binary);
			} else {
				perm = binary;
			}

			allPerm[cnt] = perm;
			cnt++;

		}

		return allPerm;
	}

	/**
	 * Creates an array of boolean arrays of possible permutations
	 * @param allPerm All permutations in String format
	 * @param cnt Number of permutations
	 * @return An array of boolean arrays
	 */
	private static boolean[][] getBoolPerm(String[] allPerm, int cnt) {

		boolean[][] boolPerm = new boolean[cnt][];

		for (int i = 0; i < allPerm.length; i++) {
			boolPerm[i] = convertToBoolArray(allPerm[i]);
		}

		return boolPerm;
	}

	/**
	 * Envelope method for creating the boolean array
	 * @param n The length of the word
	 * @param k	Number of trues needed
	 * @return An array of boolean arrays
	 */
	private static boolean[][] createAllPermBoolArray(int n, int k) {

		String[] allPerm = createAllPermStringArray(n, k);
		int cnt = allPerm.length;

		return getBoolPerm(allPerm, cnt);
	}

	/**
	 * Counts only the valid templates from a given array
	 * @param word word to check
	 * @param boolArray array of possible templates
	 * @return Number of legal templates
	 */
	private static int numberOfLegalTemplates(String word, boolean[][] boolArray) {
		int cnt = 0;

		for (boolean[] template : boolArray) {
			if (checkLegalTemplate(word, template)) {
				cnt++;
			}
		}

		return cnt;
	}


	/**
	 * Question 3
	 */
	public static boolean[][] getAllLegalTemplates(String word, int k) {  // Q - 3

		boolean[][] boolArray = createAllPermBoolArray(word.length(), k);
		int legalBoolSize = numberOfLegalTemplates(word, boolArray);
		boolean[][] legalTemplatesArray = new boolean[legalBoolSize][word.length()];
		int cnt = 0;
		for (boolean[] booleans : boolArray) {
			if (checkLegalTemplate(word, booleans)) {
				legalTemplatesArray[cnt] = booleans;
				cnt++;
			}
		}

		return legalTemplatesArray;
	}


	private static boolean isGuessInWord(char guess, String word) {
		for (int i = 0; i < word.length(); i++) {
			if (guess == word.charAt(i)) {
				return true;
			}
		}
		return false;
	}
	private static boolean isLetterExposed(char guess, char[] puzzle) {
		for (char p : puzzle) {
			if (p == guess) {
				return true;
			}
		}
		return false;
	}

	private static void modifyPuzzle(char guess, String word, char[] puzzle) {
		for (int i = 0; i < word.length(); i++) {
			if (guess == word.charAt(i)) {
				puzzle[i] = word.charAt(i);
			}
		}
	}

	private static int numberOfChanges(char guess, String word) {
		int cnt = 0;
		for (int i = 0; i < word.length(); i++) {
			if (guess == word.charAt(i)) {
				cnt++;
			}
		}
		return cnt;
	}


	/**
	 * Question 4
	 */
	/*
	 * @pre: puzzle is a legal puzzle constructed from word, guess is in [a...z]
	 */
	public static int applyGuess(char guess, String word, char[] puzzle) { // Q - 4
		if (!isGuessInWord(guess, word) || isLetterExposed(guess, puzzle)) {
			return 0;
		}
		
		modifyPuzzle(guess, word, puzzle);
		
		return  numberOfChanges(guess, word);
	}




	/*
	 * @pre: puzzle is a legal puzzle constructed from word
	 * @pre: puzzle contains at least one hidden character. 
	 * @pre: there are at least 2 letters that don't appear in word, and the user didn't guess
	 */

	private static final char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	private static final String alphabetString = "abcdefghijklmnopqrstuvwxyz";



	/**
	 * Checks where puzzle has HIDDEN_CHAR and returns an array of letters from word matches these indexes
	 * @param word the original word
	 * @param puzzle the current state of the puzzle
	 * @return an array of char containing the hidden letters, including repetitions.
	 */
	private static char[] fetchRealLettersArray(String word, char[] puzzle) {
		int[] letterIndex = new int[word.length()];
		int cnt = 0;

		for (int i = 0; i < puzzle.length; i++) {
			if (puzzle[i] == HIDDEN_CHAR) {
				letterIndex[i] = 1;
				cnt++;
			}
		}

		char[] lettersArray = new char[cnt];
		int index = 0;
		for (int j = 0; j < letterIndex.length; j++) {
			if (letterIndex[j] == 1) {
				lettersArray[index] = word.charAt(j);
				index++;
			}
		}
		return lettersArray;
	}

	/**
	 * generates a random letter from a char[] of letters
	 * @param letters an array of given letters
	 * @return a random letter
	 */
	private static char getRandomLetter(char[] letters) {
		char chosenLetter = '?';
		Random random = new Random();
		double randNum = random.nextDouble();
		for (int i = 0; i < letters.length; i++) {
			double chance = (double) (i + 1) / letters.length;
			if (randNum < chance) {
				chosenLetter = letters[i];
				break;
			}
		}
		return chosenLetter;
	}

	/**
	 * Picks a random --correct-- letter from a legal puzzle of a word and an array of already guessed letters.
	 * @param word the original word
	 * @param puzzle a legal puzzle containing at least one HIDDEN_CHAR
	 * @return a random char of a non-guessed letter
	 */
	private static char pickRealLetter(String word, char[] puzzle) {
		char[] letters = fetchRealLettersArray(word, puzzle);
		return getRandomLetter(letters);
	}

	/**
	 * Counts how many wrong letters are yet to guessed by player
	 * @param word the word to guess
	 * @param already_guessed a boolean[] containing true in index representing an index of a guessed letter
	 * @return int of number of possible letters
	 */
	private static int countFakeLetters(String word, boolean[] already_guessed) {
		int cnt = 0;

		for (int i = 0; i < already_guessed.length; i++) {
			if (!already_guessed[i]) {
				if (!word.contains("" + alphabet[i])) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	/**
	 * fetches an array of letters of wrong guesses
	 * @param word the original word
	 * @param already_guessed boolean[] representing 26 letters, where true means guessed for all guesses
	 * @return char[] containing
	 */
	private static char[] fetchFakeLettersArray(String word, boolean[] already_guessed) {

		int numberOfFakeLetters = countFakeLetters(word, already_guessed);
		char[] fakeLettersArray = new char[numberOfFakeLetters];
		int index = 0;

		for (int i = 0; i < already_guessed.length; i++) {
			if (!already_guessed[i]) {
				char possibleHint = alphabet[i];

				if (!word.contains("" + possibleHint)) {
					fakeLettersArray[index] = possibleHint;
					index++;
					}
				}
			}
		return fakeLettersArray;
	}

	/**
	 * Randomly picks a --wrong-- letter from yet to be guessed letters
	 * @param word the word to guess
	 * @param already_guessed a boolean[] containing true in index representing an index of a guessed letter
	 * @return char of a random letter
	 */
	private static char pickFakeLetter(String word, boolean[] already_guessed) {
		char[] letters = fetchFakeLettersArray(word, already_guessed);
		return getRandomLetter(letters);

	}

	/**
	 * Question 5
	 */
	public static char[] getHint(String word, char[] puzzle, boolean[] already_guessed) { // Q - 5
		char[] hint = new char[2];

		char realLetter = pickRealLetter(word, puzzle);
		char fakeLetter = pickFakeLetter(word, already_guessed);

		if (realLetter > fakeLetter) {
			hint[0] = fakeLetter;
			hint[1] = realLetter;
			return hint;
		}

		hint[0] = realLetter;
		hint[1] = fakeLetter;

		return hint;
	}

	/**
	 * gets a random template from a given array of templates
	 * @param templates an array of boolean[] contains legal templates
	 * @return a single array of legal template
	 */
	private static boolean[] getRandomTemplate(boolean[][] templates) {
		boolean[] chosenTemplate = new boolean[templates[0].length];
		Random random = new Random();
		double randNum = random.nextDouble();
		for (int i = 0; i < templates.length; i++) {
			double chance = (double) (i + 1) / templates.length;
			if (randNum < chance) {
				chosenTemplate = templates[i];
				break;
			}

		}
		return chosenTemplate;
	}

	/**
	 * checks if the user's input is valid according to given structure (contains only "X" or "_")
	 * @param template user's input template
	 * @return true if valid; else false
	 */
	private static boolean isValidInput(String template) {
		String[] characters = template.split(",");
		for (String character : characters) {
			if (!character.equals("X") && !character.equals("_")) {
				return false;
			}
		}
		return true;
	}

	/**
	 * converts the user's input template to a boolean[]
	 * @param template user's String input
	 * @return boolean[] of true if hidden
	 */
	private static boolean[] convertToTemplate(String template) {

		String[] tArray = template.split(",");
		boolean[] puzzle = new boolean[tArray.length];

		for (int i = 0; i < tArray.length; i++) {
			puzzle[i] = tArray[i].equals("_");
		}
		return puzzle;
	}

	/**
	 * Question 6
	 */
	public static char[] mainTemplateSettings(String word, Scanner inputScanner) { // Q - 6
		printSettingsMessage();

		while (true) {
			printSelectTemplate();
			String templateOption = inputScanner.next();

			if (templateOption.equals("1")) {
				printSelectNumberOfHiddenChars();

				String numberOfLettersToHide = inputScanner.next();
				int lettersInt = Integer.parseInt(numberOfLettersToHide);
				boolean[][] allLegalTemplates = getAllLegalTemplates(word, lettersInt);

				if (allLegalTemplates.length == 0) {
					printWrongTemplateParameters();
					continue;
				}

				boolean[] randBoolTemplate = getRandomTemplate(allLegalTemplates);
				return createPuzzleFromTemplate(word, randBoolTemplate);
			}

			if (templateOption.equals("2")) {
				printEnterPuzzleTemplate();
				String userInput = inputScanner.next();
				if (isValidInput(userInput)) {
					boolean[] userTemplate = convertToTemplate(userInput);
					if (checkLegalTemplate(word, userTemplate)) {
						return createPuzzleFromTemplate(word, userTemplate);
					}
				}
				printWrongTemplateParameters();
				continue;
			}
			return null;
		}
	}

	/**
	 * sets the number of guesses the user has
	 * @param puzzle the selected puzzle
	 * @return number of guesses
	 */
	private static int setGuesses(char[] puzzle) {
		return countHidden(puzzle) + 3;
	}

	/**
	 * counts the number of hidden letters in a given puzzle
	 * @param puzzle a current puzzle
	 * @return number of hidden letters
	 */
	private static int countHidden(char[] puzzle) {
		int cnt = 0;
		for (char p : puzzle) {
			if (p == HIDDEN_CHAR) {
				cnt++;
			}
		}
		return cnt;
	}

	/**
	 * creates a boolean[] of guessed letters according to current puzzle
	 * @param puzzle a given puzzle
	 * @return boolean[] where true means exposed letter
	 */
	private static boolean[] setGuessedArray(char[] puzzle) {
		boolean[] guesses = new boolean[26];

		for (char p : puzzle) {
			if (p != HIDDEN_CHAR) {
				guesses[alphabetString.indexOf(p)] = true;
			}
		}
		return guesses;
	}

	/**
	 * Question 7
	 */
	public static void mainGame(String word, char[] puzzle, Scanner inputScanner) { // Q - 7
		boolean[] already_guessed = setGuessedArray(puzzle);
		printGameStageMessage();
		int remainingGuesses = setGuesses(puzzle);

		while (remainingGuesses > 0) {
			printPuzzle(puzzle);
			printEnterYourGuessMessage();
			String inputLetter = inputScanner.next();
			char charLetter = inputLetter.charAt(0);
			if (inputLetter.equals("H")) {
				char[] hint = getHint(word, puzzle, already_guessed);
				printHint(hint);
				continue;
			}

			if (alphabetString.contains("" + charLetter)) {

				already_guessed[alphabetString.indexOf(charLetter)] = true;
				int changes = applyGuess(charLetter, word, puzzle);
				if (countHidden(puzzle) == 0) {
					printWinMessage();
					return;
				}
				else {
					remainingGuesses--;
					if (changes == 0) {
						printWrongGuess(remainingGuesses);
					}
					else {
						printCorrectGuess(remainingGuesses);
					}
				}
			}
		}
		printGameOver();
	}



/*************************************************************/
/********************* Don't change this ********************/
/*************************************************************/

	public static void main(String[] args) throws Exception { 
		if (args.length < 1){
			throw new Exception("You must specify one argument to this program");
		}
		String wordForPuzzle = args[0].toLowerCase();
		if (wordForPuzzle.length() > 10) {
			throw new Exception("The word should not contain more than 10 characters");
		}
		Scanner inputScanner = new Scanner(System.in);
		char[] puzzle = mainTemplateSettings(wordForPuzzle, inputScanner);
		mainGame(wordForPuzzle, puzzle, inputScanner);
		inputScanner.close();
	}

	// Settings Stage prints
	public static void printSettingsMessage() {
		System.out.println("--- Settings stage ---");
	}
	public static void printEnterWord() {
		System.out.println("Enter word:");
	}
	public static void printSelectNumberOfHiddenChars(){
		System.out.println("Enter number of hidden characters:");
	}
	public static void printSelectTemplate() {
		System.out.println("Choose a (1) random or (2) manual template:");
	}
	public static void printWrongTemplateParameters() {
		System.out.println("Cannot generate puzzle, try again.");
	}
	public static void printEnterPuzzleTemplate() {
		System.out.println("Enter your puzzle template:");
	}

	// Game Stage prints
	public static void printPuzzle(char[] puzzle) {
		System.out.println(puzzle);
	}
	public static void printGameStageMessage() {
		System.out.println("--- Game stage ---");
	}
	public static void printEnterYourGuessMessage() {
		System.out.println("Enter your guess:");
	}
	public static void printHint(char[] hist){
		System.out.println(String.format("Here's a hint for you: choose either %s or %s.", hist[0] ,hist[1]));

	}
	public static void printCorrectGuess(int attemptsNum) {
		System.out.println("Correct Guess, " + attemptsNum + " guesses left.");
	}
	public static void printWrongGuess(int attemptsNum) {
		System.out.println("Wrong Guess, " + attemptsNum + " guesses left.");
	}
	public static void printWinMessage() {
		System.out.println("Congratulations! You solved the puzzle!");
	}
	public static void printGameOver() {
		System.out.println("Game over!");
	}

}
