package il.ac.tau.cs.sw1.hw3;

public class StringTesting {

    public static void main(String[] args) {
//        boolean answer = StringUtils.isLex("hello", "helln");
//        System.out.println(answer);
        boolean answer = StringUtils.isEditDistanceOne("godi", "god");
        System.out.println(answer);

    }
}
