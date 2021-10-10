package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.*;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogramIterator<T extends Comparable<T>> implements Iterator<T>{

	private final Iterator<T> iterator;

//	public HashMapHistogramIterator(HashMapHistogram<T> hashMapHistogram) {
//		HashMap<T, Integer> histogramMap = hashMapHistogram.getMap();
//		ArrayList<Map.Entry<T, Integer>> entryList = new ArrayList<>(histogramMap.entrySet());
//		Comparator<Map.Entry<T, Integer>> comparator = new HashMapHistogramComparator<T>(hashMapHistogram);
//		entryList.sort(comparator);
//		this.entryIterator = entryList.iterator();
//	}

	public HashMapHistogramIterator(HashMapHistogram<T> hashMapHistogram) {
		HashMap<T, Integer> histogramMap = hashMapHistogram.getMap();
		ArrayList<T> entryList = new ArrayList<>(histogramMap.keySet());
		Comparator<T> comparator = new HashMapHistogramComparator<T>(hashMapHistogram);
		entryList.sort(comparator);
		this.iterator = entryList.iterator();
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public T next() {
		return iterator.next();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
