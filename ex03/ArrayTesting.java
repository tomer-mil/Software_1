package il.ac.tau.cs.sw1.hw3;

import java.util.Arrays;

public class ArrayTesting {

    public static void testTransposed(int[][] matrix) {
        int[][] matrixTransposed = ArrayUtils.transposeMatrix(matrix);
        for (int[] ints : matrixTransposed) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    public static void testCyclic(int[] array) {
        int[] copyArray = Arrays.copyOf(array, array.length);
        int [] cycledArray = ArrayUtils.shiftArrayCyclic(array, -10, 'L');
        System.out.println("original:" + Arrays.toString(copyArray));
        System.out.println("cycled: " + Arrays.toString(cycledArray));
    }

    public static void main(String[] args) {

        int[][] m = {{0,1,0,0}, {0,1,1,0}, {0,0,1,1}, {1,0,0,1}};
        int[][] n = {{1,0,0}, {0,1,0}, {0,0,1}};
        int answer = ArrayUtils.findPath(n, 1, 1, 2);
        System.out.println(answer);


//        ArrayUtils.splitArray(myArray);


    }
}
