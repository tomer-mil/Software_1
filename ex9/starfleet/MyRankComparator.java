package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MyRankComparator implements Comparator<OfficerRank> {

    private final Map<OfficerRank, Integer> rankToInt = new HashMap<>();


    public MyRankComparator() {
        rankToInt.put(OfficerRank.Ensign, 0);
        rankToInt.put(OfficerRank.Lieutenant, 1);
        rankToInt.put(OfficerRank.LieutenantCommander, 2);
        rankToInt.put(OfficerRank.Commander, 3);
        rankToInt.put(OfficerRank.Captain, 4);
        rankToInt.put(OfficerRank.Admiral, 5);
    }

    @Override
    public int compare(OfficerRank o1, OfficerRank o2) {
        return Integer.compare(rankToInt.get(o1), rankToInt.get(o2));
    }

}
