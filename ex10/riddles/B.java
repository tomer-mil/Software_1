package riddles;

import java.util.Objects;

public class B extends A{
	
	protected int i;
	protected int j;


	public B(int i, int j) {
		super(i,j);
	}


	@Override
	public int hashCode() {
		return Objects.hash(i, j);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		B that = (B) obj;
		return (this.i == that.i) && (this.j == that.j);
	}

}
