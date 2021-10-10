package il.ac.tau.cs.sw1.ex5;


import java.io.*;
import java.util.Arrays;

public class BigramModel {

	public static final int MAX_VOCABULARY_SIZE = 14500;
	public static final String VOC_FILE_SUFFIX = ".voc";
	public static final String COUNTS_FILE_SUFFIX = ".counts";
	public static final String SOME_NUM = "some_num";
	public static final int ELEMENT_NOT_FOUND = -1;

	
	String[] mVocabulary;
	int[][] mBigramCounts;

	// DO NOT CHANGE THIS !!! 
	public void initModel(String fileName) throws IOException{
		mVocabulary = buildVocabularyIndex(fileName);
		mBigramCounts = buildCountsArray(fileName, mVocabulary);
		
	}
	//// ----- Question 1 ----- ////
	/**
	 * creates a new BufferedReader object containing a file from a given path.
	 * @param filePath the path of the file
	 * @return a BufferedReader object
	 * @throws FileNotFoundException : file not found
	 */
	private BufferedReader getNewBufferedReader(String filePath) throws FileNotFoundException {
		File file = new File(filePath);
		FileReader fileReader = new FileReader(file);
		return new BufferedReader(fileReader);
	}

	/**
	 * creates a new BufferedWriter object containing a designated path
	 * @param filePath a valid file path in the system
	 * @return a BufferedWriter instance
	 * @throws IOException - FileNotFound etc.
	 */
	private BufferedWriter getNewWriterBuffer(String filePath) throws IOException {
		File file = new File(filePath);
		FileWriter toFile = new FileWriter(file);
		return new BufferedWriter(toFile);
	}

	/**
	 * Given 3 boolean parameters, determines whether the word is legal. if so, checks if it's an int or not.
	 * @param hasNumbers if the word contains numbers
	 * @param hasLetters if the word contains English letters
	 * @param hasOther if the word has other symbols like +, -, () etc.
	 * @return -1: word is invalid; 0: the word is a legal number; 1: the word is a legal normal word.
	 */
	private int getWordConditions(boolean hasNumbers, boolean hasLetters, boolean hasOther) {

		if (hasNumbers) {
			if (hasLetters) {
				return 1;
			}
			if (hasOther) {
				return -1;
			}
			return 0;
		}
		if (hasLetters) {
			return 1;
		}
		else {
			return -1;
		}
	}

	/**
	 * checks 3 conditions of the characters of a given word and defines it by them
	 * @param word the given word
	 * @return -1: word is invalid; 0: the word is a legal number; 1: the word is a legal normal word.
	 */
	private int defineWord(String word) {
		char[] wordArray = word.toCharArray();

		boolean hasNumbers = false;
		boolean hasLetters = false;
		boolean hasOther = false;

		for (char c : wordArray) {
			if (Character.isDigit(c)) {
				hasNumbers = true;
			}

			if (Character.isLetter(c)) {
				hasLetters = true;
			}
			if (!Character.isLetterOrDigit(c)) {
				hasOther = true;
			}
		}
		return getWordConditions(hasNumbers, hasLetters, hasOther);
	}

	/**
	 * checks if a given word is in a given String array. the method trims the array according to the current length
	 * @param arr String array
	 * @param myWord word to check
	 * @param length number of words in vocabulary
	 * @return boolean
	 */
	private boolean isInArray(String[] arr, String myWord, int length) {
		String[] trimmedArr = Arrays.copyOf(arr, length);
		for (String w : trimmedArr) {
			if (w.equals(myWord)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * creates the vocabulary array of only valid words and numbers from a given text
	 * @param filePath path of the text
	 * @return array containing only valid words without duplicates
	 * @throws IOException : file not found
	 */
	private String[] getVocabularyArray(String filePath) throws IOException {

		BufferedReader buffer = getNewBufferedReader(filePath);
		String[] textArray = new String[MAX_VOCABULARY_SIZE];

		StringBuilder textString = new StringBuilder();
		int currentIndex = 0;

		String line = buffer.readLine();
		while (line != null) {
			String[] lineArray = line.split("\\s+");
			for (String word : lineArray) {
				word = word.toLowerCase();
				int wordDefinition = defineWord(word);

				switch (wordDefinition) {
					case -1:
						continue;
					case 0:
						word = SOME_NUM;
						break;
					case 1:
						word = word.toLowerCase();
				}
				if (!isInArray(textArray, word, currentIndex)) {
					textArray[currentIndex] = word;
					currentIndex++;
				}
			}
			line = buffer.readLine();
		}
		buffer.close();
		return Arrays.copyOf(textArray, currentIndex);
	}

	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public String[] buildVocabularyIndex(String fileName) throws IOException { // Q 1
		return getVocabularyArray(fileName);
	}



	//// ----- Question 2 ----- ////
	/**
	 * checks if a given word is in the line
	 * @param myWord word to check
	 * @param line current line
	 * @return true if myWord is in line; else false.
	 */
	private boolean isInLine(String myWord, String[] line) {
		for (String word : line) {
			word = word.toLowerCase();
			if (word.equals(myWord)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * gets the next word in a sentence after a given index.
	 * @param line a given sentence
	 * @param startIndex a given index
	 * @return lower-cased word; empty String if the given word is the last one.
	 */
	private String getNextWord(String[] line, int startIndex) {
		if (startIndex < line.length - 1) {
			return line[startIndex +1].toLowerCase();
		}
		return "";
	}

	/**
	 * searches for the index of a given word in the vocabulary
	 * @param myWord word to search
	 * @param vocabulary a given vocabulary
	 * @return the index of myWord in vocabulary. returns -1 if word is not in the vocabulary.
	 */
	private int getWordVocabularyIndex(String myWord, String[] vocabulary) {
		for (int i = 0; i < vocabulary.length; i++) {
			String word = vocabulary[i];
			if (myWord.equals(word)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * increases by 1 the combination of word at index i and j in the vocabulary
	 * @param i first word index
	 * @param j second word index
	 * @param countsArray a given countArray
	 */
	private void setCountsArray(int i, int j, int[][] countsArray) {
		countsArray[i][j]++;
	}

	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public int[][] buildCountsArray(String fileName, String[] vocabulary) throws IOException{
		int[][] countsArray = new int[vocabulary.length][vocabulary.length];

		BufferedReader buffer = getNewBufferedReader(fileName);
		String stringLine = buffer.readLine();

		while (stringLine != null) {
			String[] line = stringLine.split(" ");

			for (int i = 0; i < vocabulary.length; i++) {

				String vocWord = vocabulary[i];

				if (isInLine(vocWord, line)) {
					for (int j = 0; j < line.length; j++) {
						String appearance = line[j].toLowerCase();
						if (appearance.equals(vocWord)) {
							String nextWord = getNextWord(line, j);
							if (!nextWord.equals("")) {
								int nextIndex = getWordVocabularyIndex(nextWord, vocabulary);
								if (nextIndex != -1) {
									setCountsArray(i, nextIndex, countsArray);
								}
							}
						}
					}
				}
			}
			stringLine = buffer.readLine();
		}
		buffer.close();
		return countsArray;
	}



	//// ----- Question 3 ----- ////
	/**
	 * creates and writes the .voc file to a desired path
	 * @param filePath a given path
	 * @throws IOException - FileNotFound or NoSuchDirectory
	 */
	private void writeVocFile(String filePath) throws IOException {
		BufferedWriter wBuffer = getNewWriterBuffer(filePath + VOC_FILE_SUFFIX);

		wBuffer.write(mVocabulary.length + " words");
		wBuffer.newLine();
		for (int j = 0; j < mVocabulary.length; j++) {
			String lineToWrite = j + "," + mVocabulary[j];
			wBuffer.write(lineToWrite);
			if (j != mVocabulary.length - 1) {
				wBuffer.newLine();
			}
		}
		wBuffer.close();
	}

	/**
	 * creates and writes the ".count" file to a desired path
	 * @param filePath a given path
	 * @throws IOException - FileNotFound or NoSuchDirectory
	 */
	private void writeCountFile(String filePath) throws IOException {
		BufferedWriter wBuffer = getNewWriterBuffer(filePath + COUNTS_FILE_SUFFIX);

		for (int i = 0; i < mBigramCounts.length; i++) {
			for (int j = 0; j < mBigramCounts.length; j++) {
				if (mBigramCounts[i][j] != 0) {
					String aLine = i + "," + j + ":" + mBigramCounts[i][j];
					wBuffer.write(aLine);
					wBuffer.newLine();
				}
			}
		}
		wBuffer.close();
	}

	/*
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: fileName is a legal file path
	 */
	public void saveModel(String fileName) throws IOException{ // Q-3
		writeVocFile(fileName);
		writeCountFile(fileName);
	}



	//// ----- Question 4 ----- ////
	/**
	 * gets the number of words in a vocabulary from a ".voc" file
	 * @param fileName directory of the ".voc" file
	 * @return number of words
	 * @throws IOException .
	 */
	private int getNumberOfWordsInVoc(String fileName) throws IOException {
		BufferedReader rBuffer = getNewBufferedReader(fileName + VOC_FILE_SUFFIX);
		String[] line = getLineArray(rBuffer.readLine(), " ");
		rBuffer.close();
		return Integer.parseInt(line[0]);
	}

	/**
	 * creates an array of String from a given line separated by a given regex
	 * @param stringLine String representation of the line
	 * @param regex a delimiter
	 * @return line separated by the regex
	 */
	private String[] getLineArray(String stringLine, String regex) {
		if (stringLine == null) {
			return null;
		}
		return stringLine.split(regex);
	}

	/**
	 * loads a ".voc" file from a given directory and sets it as the mVocabulary
	 * @param fileName directory of ".voc" file
	 * @throws IOException .
	 */
	private void loadVocFile(String fileName) throws IOException {

		int numberOfWords = getNumberOfWordsInVoc(fileName);
		String[] vocabularyArray = new String[numberOfWords];

		BufferedReader rBuffer = getNewBufferedReader(fileName + VOC_FILE_SUFFIX);
		rBuffer.readLine();
		String[] newLine = getLineArray(rBuffer.readLine(), ",");

		while (newLine != null) {
			vocabularyArray[Integer.parseInt(newLine[0])] = newLine[1];
			newLine = getLineArray(rBuffer.readLine(), ",");
		}
		rBuffer.close();
		mVocabulary = vocabularyArray;
	}

	/**
	 * loads a ".count" file from a given directory and sets it as the mBiagramCounts
	 * @param fileName directory of ".count" file
	 * @throws IOException .
	 */
	private void loadCountFile(String fileName) throws IOException {
		int numberOfWords = getNumberOfWordsInVoc(fileName);
		int[][] countArray = new int[numberOfWords][numberOfWords];

		BufferedReader rBuffer = getNewBufferedReader(fileName + COUNTS_FILE_SUFFIX);
		String[] line = getLineArray(rBuffer.readLine(), "");

		while (line != null) {
			int i = Integer.parseInt(line[0]);
			int j = Integer.parseInt(line[2]);
			int value = Integer.parseInt(line[4]);

			countArray[i][j] = value;
			line = getLineArray(rBuffer.readLine(), "");
		}
		rBuffer.close();
		mBigramCounts = countArray;
	}

	/*
	 * @pre: fileName is a legal file path
	 */
	public void loadModel(String fileName) throws IOException{ // Q - 4
		loadVocFile(fileName);
		loadCountFile(fileName);
	}


	//// ----- Question 5 ----- ////
	/*
	 * @pre: word is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: word is in lowercase
	 * @post: $ret = -1 if word is not in vocabulary, otherwise $ret = the index of word in vocabulary
	 */
	public int getWordIndex(String word){  // Q - 5
		for (int i = 0; i < mVocabulary.length; i++) {
			String v = mVocabulary[i];
			if (v.equals(word)) {
				return i;
			}
		}
		return ELEMENT_NOT_FOUND;
	}



	//// ----- Question 6 ----- ////
	/*
	 * @pre: word1, word2 are in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post: $ret = the count for the bigram <word1, word2>. if one of the words does not
	 * exist in the vocabulary, $ret = 0
	 */
	public int getBigramCount(String word1, String word2) { //  Q - 6
		int word1Index = getWordIndex(word1);
		if (word1Index == -1) {
			return 0;
		}
		int word2Index = getWordIndex(word2);
		if (word2Index == -1) {
			return 0;
		}

		return mBigramCounts[word1Index][word2Index];
	}



	//// ----- Question 7 ----- ////
	/*
	 * @pre word in lowercase, and is in mVocabulary
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post $ret = the word with the lowest vocabulary index that appears most frequently after word (if a bigram starting with
	 * word was never seen, $ret will be null
	 */
	public String getMostFrequentProceeding(String word) { //  Q - 7
		int wordIndex = getWordIndex(word);
		int max = 0;
		int index = -1;

		for (int i = 0; i < mBigramCounts[wordIndex].length ; i++) {
			if (mBigramCounts[wordIndex][i] > max) {
				max = mBigramCounts[wordIndex][i];
				index = i;
			}
		}

		if (index != -1) {
			return mVocabulary[index];
		}

		return null;
	}



	//// ----- Question 8 ----- ////
	/* @pre: sentence is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: each two words in the sentence are are separated with a single space
	 * @post: if sentence is is probable, according to the model, $ret = true, else, $ret = false
	 */
	public boolean isLegalSentence(String sentence) { //  Q - 8
		if (sentence.isEmpty()) {
			return true;
		}
		if (sentence.length() == 1) {
			return (getWordIndex(sentence) != -1);
		}

		String[] sentenceArray = sentence.toLowerCase().split(" ");

		for (int i = 0; i < sentenceArray.length - 1; i++) {
			String word1 = sentenceArray[i];
			String word2 = sentenceArray[i + 1];
			if (getBigramCount(word1, word2) == 0) {
				return false;
			}
		}
		return true;
	}



	//// ----- Question 9 ----- ////
	/**
	 * checks if the given vectors are valid vectors
	 * @param arr1 first vector
	 * @param arr2 second vector
	 * @return true if valid; else false
	 */
	private static boolean areValidVectors(int[] arr1, int[] arr2) {
		if (arr1.length != arr2.length) {
			return false;
		}
		if (arr1.length == 0) {
			return false;
		}
		return !Arrays.equals(arr1, new int[arr1.length]) && !Arrays.equals(arr2, new int[arr2.length]);
	}

	/**
	 * calculates the cosine symmetry of two word vectors
	 * @param arr1 first vector
	 * @param arr2 second vector
	 * @return a double representing the symmetry value. bigger values means closer words
	 */
	/*
	 * @pre: arr1.length = arr2.length
	 * post if arr1 or arr2 are only filled with zeros, $ret = -1, otherwise calculates CosineSim
	 */
	public static double calcCosineSim(int[] arr1, int[] arr2) { //  Q - 9
		if (!areValidVectors(arr1, arr2)) {
			return -1;
		}

		double numerator = 0.;
		double denominator;
		double aDenominator = 0.;
		double bDenominator = 0.;

		for (int i = 0; i < arr1.length; i++) {
			int A_i = arr1[i];
			int B_i = arr2[i];

			numerator += (A_i * B_i);
			aDenominator += Math.pow(A_i, 2);
			bDenominator += Math.pow(B_i, 2);
		}
		denominator = (Math.sqrt(aDenominator) * Math.sqrt(bDenominator));
		return (numerator / denominator);
	}



	//// ----- Question 10 ----- ////
	/*
	 * @pre: word is in vocabulary
	 * @pre: the method initModel was called (the language model is initialized), 
	 * @post: $ret = w implies that w is the word with the largest cosineSimilarity(vector for word, vector for w) among all the
	 * other words in vocabulary
	 */
	public String getClosestWord(String word) { //  Q - 10
		int wordIndex = getWordIndex(word);
		int[] wordVector = mBigramCounts[wordIndex];

		double maxSim = 0.;
		int index = 0;

		for (int i = 0; i < mBigramCounts.length; i++) {
			if (i == wordIndex) {
				continue;
			}

			int[] otherWordVector = mBigramCounts[i];
			double sim = calcCosineSim(wordVector, otherWordVector);

			if (sim == -1) {
				continue;
			}

			if (sim > maxSim) {
				maxSim = sim;
				index = i;
			}
		}
		return mVocabulary[index];
	}
	
}
