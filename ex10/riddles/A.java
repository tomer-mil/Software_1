package riddles;


import java.util.Objects;

public class A implements Comparable<A> {

	protected int i;
	protected int j;

	public A(int i, int j) {
		this.i = i;
		this.j = j;
	}

	@Override
	public int hashCode() {
		return Objects.hash(i);
	}

	@Override
	public boolean equals(Object obj) {
		int comparison;

		if (obj instanceof B) {
			comparison = Integer.compare(this.j, ((B) obj).j);
			return comparison == 0;
		}

		if (obj instanceof A) {
			comparison = Integer.compare(this.i, ((A) obj).i);
			return comparison == 0;
		}

		return true;
	}

	@Override
	public int compareTo(A o) {
		if (this.i == o.i) {
			return Integer.compare(this.j, o.j);
		}
		return Integer.compare(this.i, o.j);
	}
	
	
	public String toString() {
		return "("+this.i+" "+this.j+")";
	}



}
