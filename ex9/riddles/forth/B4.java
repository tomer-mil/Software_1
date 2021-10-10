package il.ac.tau.cs.sw1.ex9.riddles.forth;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class B4 implements Iterator<String> {

    List<String> lst;

    public B4(String[] strings, int k) {
        lst = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < k; j++) {
                lst.add(i + strings.length * j, strings[i]);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return lst.iterator().hasNext();
    }

    @Override
    public String next() {
        return lst.iterator().next();
    }
}
