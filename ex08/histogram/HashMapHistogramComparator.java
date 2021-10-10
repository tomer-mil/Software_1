package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.Comparator;
import java.util.HashMap;

public class HashMapHistogramComparator<T extends Comparable<T>> implements Comparator<T> {

    HashMapHistogram<T> hashMapHistogram;
    HashMap<T, Integer> map;

    public HashMapHistogramComparator(HashMapHistogram<T> hashMapHistogram) {
        this.hashMapHistogram = hashMapHistogram;
        this.map = hashMapHistogram.getMap();
    }

    @Override
    public int compare(T o1, T o2) {
        int o1Value = map.get(o1);
        int o2Value = map.get(o2);

        if (o1Value == o2Value) {
            return o1.compareTo(o2);
        }

        return Integer.compare(o2Value, o1Value);

    }
}
