package il.ac.tau.cs.sw1.ex8.wordsRank;

import il.ac.tau.cs.sw1.ex8.histogram.HashMapHistogram;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class FileIndex {
	
	public static final int UNRANKED_CONST = 30;

	//Maps
	/**
	 * Respectively:
	 * String(file) -> HashMapHistogram
	 * String(word) -> RankedWord
	 * String(word) -> HashMap<String(file), Integer(Rank)>
	 */
	private HashMap<String, HashMapHistogram<String>> fileToHistogram;
	private HashMap<String, RankedWord> wordToRank;
	private HashMap<String, HashMap<String, Integer>> wordToFileMap;

	//Constants
	private String folderPath;
	private String[] filesArr;
	private final String NOT_FOUND = "File Not Found In Directory 404";



	/*
	 * @pre: the directory is no empty, and contains only readable text files
	 */

  	public void indexDirectory(String folderPath) {
  		initMaps();
  		this.folderPath = folderPath;
		File folder = new File(folderPath);
		File[] listFiles = folder.listFiles();
		filesArr = folder.list();
		for (File file : listFiles) {
			if (file.isFile()) {
				String filename = file.getName();
				initFileToHistogram(filename);
				initWordToFileMap(filename);
			}
		}
		fillAbsentWords();
		initWordToRank();
	}

	//Initialization Methods
	private void initFileToHistogram(String filename) {
		HashMapHistogram<String> histogram = new HashMapHistogram<String>();
		String filePath = folderPath + filename;
		File file = new File(filePath);
		try {
			List<String> tokens = FileUtils.readAllTokens(file);
			histogram.addAll(tokens);
		} catch (IOException e) {
			e.printStackTrace();
		}
		fileToHistogram.put(filename, histogram);
	}
	private void initWordToRank() {
		Set<String> wordsSet = wordToFileMap.keySet();
		for (String word : wordsSet) {
			wordToRank.put(word, new RankedWord(word, wordToFileMap.get(word)));
		}
	}
	private void initWordToFileMap(String filename) {
		HashMapHistogram<String> histogram = fileToHistogram.get(filename);
		Iterator<String> iterator = histogram.iterator();
		Set<String> valuesSet = wordToFileMap.keySet();
		int rank = 1;
		while (iterator.hasNext()) {
			String word = iterator.next();
			if (!valuesSet.contains(word)) {
				wordToFileMap.put(word, new HashMap<String, Integer>());
			}

			wordToFileMap.get(word).put(filename, rank);
			rank++;
		}
	}
	private HashMap<String, Integer> initWordsCountMap() {
		HashMap<String, Integer> wordsCount = new HashMap<>();
		for (String file : filesArr) {
			int wordsInFile = fileToHistogram.get(file).getItemsSet().size();
			wordsCount.put(file, wordsInFile);
		}
		return wordsCount;
	}
	private void fillAbsentWords() {
		Set<String> allWords = wordToFileMap.keySet();
		HashMap<String, Integer> wordsCount = initWordsCountMap();

		for (String word : allWords) {
			Set<String> foundFiles = new HashSet<>(wordToFileMap.get(word).keySet());
			if (foundFiles.size() != filesArr.length) {
				HashSet<String> filesArrCopySet = new HashSet<String>(Arrays.asList(Arrays.copyOf(filesArr, filesArr.length)));
				filesArrCopySet.removeAll(foundFiles);
				for (String file : filesArrCopySet) {
					int numberOfWordsInFile = wordsCount.get(file);
					wordToFileMap.get(word).put(file, numberOfWordsInFile + UNRANKED_CONST);
				}
			}
		}
	}
	private void initMaps() {
		this.fileToHistogram = new HashMap<>();
		this.wordToRank = new HashMap<>();
		this.wordToFileMap = new HashMap<>();
	}


	/*
	 * @pre: the index is initialized
	 * @pre filename is a name of a valid file
	 * @pre word is not null
	 */
	public int getCountInFile(String filename, String word) throws FileIndexException {
		if (fileNotInDirectory(filename)) {
			throw new FileIndexException(NOT_FOUND);
		}

		word = word.toLowerCase();
		return fileToHistogram.get(filename).getCountForItem(word);
	}

	private boolean fileNotInDirectory(String filename) {
		File folder = new File(folderPath);
		if (folder.list().length == 0) {
			return true;
		}
		List<String> filesNames = Arrays.asList(folder.list());
		return !filesNames.contains(filename);
	}
	/*
	 * @pre: the index is initialized
	 * @pre filename is a name of a valid file
	 * @pre word is not null
	 */
	public int getRankForWordInFile(String filename, String word) throws FileIndexException {
		word = word.toLowerCase();
		if (fileNotInDirectory(filename)) {
			throw new FileIndexException(NOT_FOUND);
		}
		if (!wordToFileMap.containsKey(word) ||
				!wordToFileMap.get(word).containsKey(filename)) {
			return fileToHistogram.get(filename).getItemsSet().size() + UNRANKED_CONST;
		}
		return wordToFileMap.get(word).get(filename);
	}

	private int calcTotalAverage() {
		if (fileToHistogram.isEmpty()) {
			return 0;
		}
		int sum = 0;
		for (String file : filesArr) {
			HashMap<String, Integer> wordsCountMap = initWordsCountMap();
			int wordsInFile = wordsCountMap.get(file);
			sum += wordsInFile + UNRANKED_CONST;
		}


		return (int)Math.round(((double)sum) / filesArr.length);
	}

	/*
	 * @pre: the index is initialized
	 * @pre word is not null
	 */
	public int getAverageRankForWord(String word) {
		word = word.toLowerCase();
		int sum = 0;

		Set<String> allWords = wordToRank.keySet();
		if (!allWords.contains(word)) {
			return calcTotalAverage();
		}

		Map<String, Integer> wordRanks = wordToRank.get(word).getRanksForFile();

		for (String file : filesArr) {
			sum += wordRanks.get(file);
		}

		return wordToRank.get(word).getRankByType(RankedWord.rankType.average);

	}

	private List<String> getWordsWithRankTypeSmallerThanK(RankedWord.rankType rType, int k) {
		List<String> wordsList = new ArrayList<String>();
		RankedWordComparator comparator = new RankedWordComparator(rType);

		List<RankedWord> toSort = new ArrayList<>();
		for (RankedWord x : wordToRank.values()) {
			if ((x.getRankByType(rType) < k)) {
				toSort.add(x);
			}
		}
		toSort.sort(comparator);
		for (RankedWord x : toSort) {
			wordsList.add(x.getWord());
		}

		return wordsList;
	}

	public List<String> getWordsWithAverageRankSmallerThanK(int k){

		return getWordsWithRankTypeSmallerThanK(RankedWord.rankType.average, k);
	}
	public List<String> getWordsWithMinRankSmallerThanK(int k){
		return getWordsWithRankTypeSmallerThanK(RankedWord.rankType.min, k);
	}
	public List<String> getWordsWithMaxRankSmallerThanK(int k){
		return getWordsWithRankTypeSmallerThanK(RankedWord.rankType.max, k);
	}
	public HashSet<RankedWord> getRankedWordList(Set<String> words) {
		HashSet<RankedWord> RankedWordsSet = new HashSet<>();
		for (String word : words) {
			RankedWordsSet.add(wordToRank.get(word));
		}
		return RankedWordsSet;

	}

}
