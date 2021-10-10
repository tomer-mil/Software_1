package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.*;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogram<T extends Comparable<T>> implements IHistogram<T>{

	private final HashMap<T, Integer> histogramMap = new HashMap<>();

	private boolean itemNotInMap(T item) {
		return !histogramMap.containsKey(item);
	}

	@Override
	public Iterator<T> iterator() {
		return new HashMapHistogramIterator<>(this);
	}

	@Override
	public void addItem(T item) {
		if (itemNotInMap(item)) {
			histogramMap.put(item, 0);
		}
		int itemCount = histogramMap.get(item);
		histogramMap.put(item, ++itemCount);
	}

	@Override
	public void removeItem(T item) throws IllegalItemException {
		if (itemNotInMap(item) || histogramMap.get(item) == 0) {
			throw new IllegalItemException();
		}
		int itemCount = histogramMap.get(item);
		histogramMap.put(item, --itemCount);
		if (itemCount <= 0) {
			histogramMap.remove(item);
		}
	}

	@Override
	public void addItemKTimes(T item, int k) throws IllegalKValueException {
		if (k < 1) {
			throw new IllegalKValueException(k);
		}
		if (itemNotInMap(item)) {
			histogramMap.put(item, 0);
		}
		int itemCount = histogramMap.get(item);
		histogramMap.put(item, itemCount + k);
	}

	@Override
	public void removeItemKTimes(T item, int k) throws IllegalItemException, IllegalKValueException {
		if (itemNotInMap(item)) {
			throw new IllegalItemException();
		}

		if (getCountForItem(item) == 0) {
			throw new IllegalItemException();
		}

		int itemCount = histogramMap.get(item);

		if (k < 1 || k > itemCount) {
			throw new IllegalKValueException(k);
		}

		histogramMap.put(item, itemCount - k);
		
	}

	@Override
	public int getCountForItem(T item) {
		if (itemNotInMap(item)) {
			return 0;
		}
		return histogramMap.get(item);
	}

	@Override
	public void addAll(Collection<T> items) {
		if (items.isEmpty()) {
			return;
		}
		for (T item : items) {
			this.addItem(item);
		}
		
	}

	@Override
	public void clear() {
		this.histogramMap.clear();
	}

	@Override
	public Set<T> getItemsSet() {
		Set<T> itemSet = new HashSet<T>();
		Set<T> keysSet = histogramMap.keySet();

		for (T item : keysSet) {
			if (histogramMap.get(item) != 0) {
				itemSet.add(item);
			}
		}
		return itemSet;
	}

	@Override
	public void update(IHistogram<T> anotherHistogram) {
		Set<T> itemsSet = anotherHistogram.getItemsSet();

		for (T item : itemsSet) {
			int itemCount = anotherHistogram.getCountForItem(item);
			if (itemCount != 0) {
				try {
					this.addItemKTimes(item, itemCount);
				} catch (IllegalKValueException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public HashMap<T, Integer> getMap() {
		return histogramMap;
	}


	
}
