package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Comparator;

public class MyOfficerComparator implements Comparator<Officer> {

    @Override
    public int compare(Officer o1, Officer o2) {
        return o1.rank.compareTo(o2.rank);
    }
}
