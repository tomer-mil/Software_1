package il.ac.tau.cs.sw1.ex2;

public class Assignment02Q01 {

	public static void main(String[] args) {
		for (String word : args) {
			char letter = word.charAt(0);
			if ((int) letter % 2 == 0 && (int) letter % 3 == 0) {
				System.out.println(letter);
			}
		}
	}
}
