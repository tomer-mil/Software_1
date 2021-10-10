package il.ac.tau.cs.sw1.ex8.wordsRank;
import il.ac.tau.cs.sw1.ex8.wordsRank.RankedWord.rankType;
import java.util.Comparator;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/

class RankedWordComparator implements Comparator<RankedWord>{

	private final rankType typeToCompare;

	public RankedWordComparator(rankType cType) {
		this.typeToCompare = cType;
	}
	
	@Override
	public int compare(RankedWord o1, RankedWord o2) {
		return Integer.compare(o1.getRankByType(typeToCompare), o2.getRankByType(typeToCompare));
	}	
}
