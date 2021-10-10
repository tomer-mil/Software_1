package il.ac.tau.cs.sw1.ex1;

public class Assignment1 {
	public static void main(String[] args) {

		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		int z = Integer.parseInt(args[2]);

		if (x <= 0 || y <= 0 || z <= 0) //Checks for valid values
			System.out.println("Invalid input!");

		else if ((x + y > z) && (x + z > y) && (z + y > x)) { //Checks for a valid triangle

			int xSq = x * x;
			int ySq = y * y;
			int zSq = z * z;

			if ((xSq + ySq == zSq) || (xSq + zSq == ySq) || (ySq + zSq == xSq)) //Checks for right triangle
				System.out.println("The input ("+x+", "+y+", "+z+") defines a valid right triangle!");

			else
				System.out.println("The input ("+x+", "+y+", "+z+") defines a valid triangle!");
		}

		else
			System.out.println("The input ("+x+", "+y+", "+z+") does not define a valid triangle!");
	}
}
