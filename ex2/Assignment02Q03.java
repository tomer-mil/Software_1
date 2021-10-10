package il.ac.tau.cs.sw1.ex2;

public class Assignment02Q03 {

	public static void main(String[] args) {
		int numOfEven = 0;
		int n = -1;
		// Your code goes here
		n = Integer.parseInt(args[0]);
		int[] fibNum = new int[n];

		int i = 2;
		int num1 = 1;
		int num2 = 1;
		int res;
		fibNum[0] = num1;
		fibNum[1] = num2;

		do {
			res = num1 + num2;
			fibNum[i] = res;
			num1 = num2;
			num2 = res;
			if (res % 2 == 0) {
				numOfEven++;
			}
			i++;
		}
		while (i < n);

		System.out.println("The first "+ n +" Fibonacci numbers are:");
		for (int num : fibNum) {
			if (num == fibNum[n - 1]) {
				System.out.print(num);
			}
			else {
				System.out.print(num + " ");
			}
		}
		System.out.println();
		System.out.println("The number of even numbers is: "+numOfEven);

	}

}
