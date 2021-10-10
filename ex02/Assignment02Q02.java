package il.ac.tau.cs.sw1.ex2;

public class Assignment02Q02 {

	public static void main(String[] args) {

		double piEstimation = 0.0;
		double brackets = 0.0;

		for (int i = 0; i < Integer.parseInt(args[0]); i++) {
			double fraction = (double) 1 / ((2 * i) + 1);
			if (i % 2 == 1) {
				fraction = (-1 * fraction);
			}
			brackets += fraction;
		}

		piEstimation = 4 * brackets;

		System.out.println(piEstimation + " " + Math.PI);

	}

}
