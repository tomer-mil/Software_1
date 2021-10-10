package riddles;

public class C extends B {
	
	private int i;
	private int j;

	public C(int i, int j) {
		super(i,j);
	}

	@Override
	public int compareTo(A other) {
		if (this.i == other.i) {
			return Integer.compare(this.j, other.j);
		}
		return Integer.compare(this.i, other.i);
	}



}