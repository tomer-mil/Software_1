package il.ac.tau.cs.sw1.hw3;
import java.util.Arrays;

public class Main{
    public static void main(String[] args) {
        /* ArrayUtils testing */

        /* Q1 transposeMatrix */

        int[][] Q1_res;

        int[][] Q1_test_1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] Q1_test_2 = {{-1, 8}, {7, -3}};
        int[][] Q1_test_3 = {{5, 3, 2}};
        int[][] Q1_test_4 = {{1, 2, 3}, {4, 5, 6}};
        int[][] Q1_test_5 = {};
        int[][] Q1_test_6 = {{},{},{}};
        int[][] Q1_test_7 = {{5}, {3}, {2}};


        int[][] Q1_expected_result_1 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int[][] Q1_expected_result_2 = {{-1, 7}, {8, -3}};
        int[][] Q1_expected_result_3 = {{5}, {3}, {2}};
        int[][] Q1_expected_result_4 = {{1, 4}, {2, 5}, {3, 6}};
        int[][] Q1_expected_result_5 = {};
        int[][] Q1_expected_result_6 = {{},{},{}};
        int[][] Q1_expected_result_7 = {{5, 3, 2}};

        Q1_res = ArrayUtils.transposeMatrix(Q1_test_1);
        if (!Arrays.deepEquals(Q1_expected_result_1, Q1_res)) {
            System.out.println("failed test 1 of question 1");
        }
        Q1_res = ArrayUtils.transposeMatrix(Q1_test_2);
        if (!Arrays.deepEquals(Q1_expected_result_2, Q1_res)) {
            System.out.println("failed test 2 of question 1");
        }
        Q1_res = ArrayUtils.transposeMatrix(Q1_test_3);
        if (!Arrays.deepEquals(Q1_expected_result_3, Q1_res)) {
            System.out.println("failed test 3 of question 1");
        }
        Q1_res = ArrayUtils.transposeMatrix(Q1_test_4);
        if (!Arrays.deepEquals(Q1_expected_result_4, Q1_res)) {
            System.out.println("failed test 4 of question 1");
        }
        Q1_res = ArrayUtils.transposeMatrix(Q1_test_5);
        if (!Arrays.deepEquals(Q1_expected_result_5, Q1_res)) {
            System.out.println("failed test 5 of question 1");
        }
        Q1_res = ArrayUtils.transposeMatrix(Q1_test_6);
        if (!Arrays.deepEquals(Q1_expected_result_6, Q1_res)) {
            System.out.println("failed test 6 of question 1");
        }
        Q1_res = ArrayUtils.transposeMatrix(Q1_test_7);
        if (!Arrays.deepEquals(Q1_expected_result_7, Q1_res)) {
            System.out.println("failed test 7 of question 1");
        }


        /* Q2 shiftArrayCyclic */
        int[] Q2_res;

        int[] Q2_test_1 = {1,2,3,4,5};
        int[] Q2_test_2 = {1,2,3,4,5};
        int[] Q2_test_3 = {1,2,3,4,5};
        int[] Q2_test_4 = {1,2,3,4,5};
        int[] Q2_test_5 = {1,2,3,4,5};
        int[] Q2_test_6 = {1,2,3,4,5};
        int[] Q2_test_7 = {0,8,9,5,6};
        int[] Q2_test_8 = {};
        int[] Q2_test_9 = {1};
        int[] Q2_test_10 = {1};
        int[] Q2_test_11 = {1};
        int[] Q2_test_12 = {11,12};
        int[] Q2_test_13 = {11,12};
        int[] Q2_test_14 = {11,12};
        int[] Q2_test_15 = {0,8,9,5,6,5};
        int[] Q2_test_16 = {0,8,9,5,6,5};

        int[] Q2_expected_result_1 = {2,3,4,5,1};
        int[] Q2_expected_result_2 = {5,1,2,3,4};
        int[] Q2_expected_result_3 = {1,2,3,4,5};
        int[] Q2_expected_result_4 = {1,2,3,4,5};
        int[] Q2_expected_result_5 = {4,5,1,2,3};
        int[] Q2_expected_result_6 = {3,4,5,1,2};
        int[] Q2_expected_result_7 = {8,9,5,6,0};
        int[] Q2_expected_result_8 = {};
        int[] Q2_expected_result_9 = {1};
        int[] Q2_expected_result_10 = {1};
        int[] Q2_expected_result_11 = {1};
        int[] Q2_expected_result_12 = {11,12};
        int[] Q2_expected_result_13 = {11,12};
        int[] Q2_expected_result_14 = {12,11};
        int[] Q2_expected_result_15 = {9,5,6,5,0,8};
        int[] Q2_expected_result_16 = {6,5,0,8,9,5};

        Q2_res = ArrayUtils.shiftArrayCyclic(Q2_test_1, -1, 'R');
        if(!Arrays.equals(Q2_expected_result_1, Q2_res)){
            System.out.println("failed test 1 of question 2");
        }
        Q2_res = ArrayUtils.shiftArrayCyclic(Q2_test_2, 1, 'R');
        if(!Arrays.equals(Q2_expected_result_2, Q2_res)){
            System.out.println("failed test 2 of question 2");
        }
        Q2_res = ArrayUtils.shiftArrayCyclic(Q2_test_3, 1, 'r');
        if(!Arrays.equals(Q2_expected_result_3, Q2_res)){
            System.out.println("failed test 3 of question 2");
        }
        Q2_res = ArrayUtils.shiftArrayCyclic(Q2_test_4, -2, 'g');
        if(!Arrays.equals(Q2_expected_result_4, Q2_res)){
            System.out.println("failed test 4 of question 2");
        }
        Q2_res = ArrayUtils.shiftArrayCyclic(Q2_test_5, 3, 'L');
        if(!Arrays.equals(Q2_expected_result_5, Q2_res)){
            System.out.println("failed test 5 of question 2");
        }
        Q2_res = ArrayUtils.shiftArrayCyclic(Q2_test_6, -3, 'L');
        if(!Arrays.equals(Q2_expected_result_6, Q2_res)){
            System.out.println("failed test 6 of question 2");
        }
        Q2_res = ArrayUtils.shiftArrayCyclic(Q2_test_7, 6, 'L');
        if(!Arrays.equals(Q2_expected_result_7, Q2_res)){
            System.out.println("failed test 7 of question 2");
        }
        Q2_res = ArrayUtils.shiftArrayCyclic(Q2_test_8, 3, 'R');
        if(!Arrays.equals(Q2_expected_result_8, Q2_res)){
            System.out.println("failed test 8 of question 2");
        }
        Q2_res = ArrayUtils.shiftArrayCyclic(Q2_test_9, 0, 'R');
        if(!Arrays.equals(Q2_expected_result_9, Q2_res)){
            System.out.println("failed test 9 of question 2");
        }
        Q2_res = ArrayUtils.shiftArrayCyclic(Q2_test_10, 1, 'R');
        if(!Arrays.equals(Q2_expected_result_10, Q2_res)){
            System.out.println("failed test 10 of question 2");
        }
        Q2_res = ArrayUtils.shiftArrayCyclic(Q2_test_11, 2, 'R');
        if(!Arrays.equals(Q2_expected_result_11, Q2_res)){
            System.out.println("failed test 11 of question 2");
        }
        Q2_res = ArrayUtils.shiftArrayCyclic(Q2_test_12, -2, 'R');
        if(!Arrays.equals(Q2_expected_result_12, Q2_res)){
            System.out.println("failed test 12 of question 2");
        }        Q2_res = ArrayUtils.shiftArrayCyclic(Q2_test_13, 2, 'R');
        if(!Arrays.equals(Q2_expected_result_13, Q2_res)){
            System.out.println("failed test 13 of question 2");
        }
        Q2_res = ArrayUtils.shiftArrayCyclic(Q2_test_14, -1, 'L');
        if(!Arrays.equals(Q2_expected_result_14, Q2_res)){
            System.out.println("failed test 14 of question 2");
        }
        Q2_res = ArrayUtils.shiftArrayCyclic(Q2_test_15, -2, 'R');
        if(!Arrays.equals(Q2_expected_result_15, Q2_res)){
            System.out.println("failed test 15 of question 2");
        }        Q2_res = ArrayUtils.shiftArrayCyclic(Q2_test_16, -4, 'R');
        if(!Arrays.equals(Q2_expected_result_16, Q2_res)){
            System.out.println("failed test 16 of question 2");
        }


        /* Q3 alternateSum */
        int Q3_res;

        int[] Q3_test_1 = {1,-2,3,4,5};
        int[] Q3_test_2 = {1,2,-3,4,5};
        int[] Q3_test_3 = {};
        int[] Q3_test_4 = {-5,-6};
        int[] Q3_test_5 = {-7,10,-11,-6,7};
        int[] Q3_test_6 = {-7,10,-4,7};
        int[] Q3_test_7 = {1,-2,23,-4,5};
        int[] Q3_test_8 = {1,2,3};
        int[] Q3_test_9 = {3,2,3};
        int[] Q3_test_10 = {1,4,3};

        int Q3_expected_result_1 = 7;
        int Q3_expected_result_2 = 9;
        int Q3_expected_result_3 = 0;
        int Q3_expected_result_4 = 1;
        int Q3_expected_result_5 = 21;
        int Q3_expected_result_6 = 21;
        int Q3_expected_result_7 = 35;
        int Q3_expected_result_8 = 3;
        int Q3_expected_result_9 = 4;
        int Q3_expected_result_10 = 4;
        Q3_res = ArrayUtils.alternateSum(Q3_test_1);
        if(Q3_expected_result_1!=Q3_res){
            System.out.println("failed test 1 of question 3");
        }
        Q3_res = ArrayUtils.alternateSum(Q3_test_2);
        if(Q3_expected_result_2!=Q3_res){
            System.out.println("failed test 2 of question 3");
        }
        Q3_res = ArrayUtils.alternateSum(Q3_test_3);
        if(Q3_expected_result_3!=Q3_res){
            System.out.println("failed test 3 of question 3");
        }
        Q3_res = ArrayUtils.alternateSum(Q3_test_4);
        if(Q3_expected_result_4!=Q3_res){
            System.out.println("failed test 4 of question 3");
        }
        Q3_res = ArrayUtils.alternateSum(Q3_test_5);
        if(Q3_expected_result_5!=Q3_res){
            System.out.println("failed test 5 of question 3");
        }
        Q3_res = ArrayUtils.alternateSum(Q3_test_6);
        if(Q3_expected_result_6!=Q3_res){
            System.out.println("failed test 6 of question 3");
        }
        Q3_res = ArrayUtils.alternateSum(Q3_test_7);
        if(Q3_expected_result_7!=Q3_res){
            System.out.println("failed test 7 of question 3");
        }
        Q3_res = ArrayUtils.alternateSum(Q3_test_8);
        if(Q3_expected_result_8!=Q3_res){
            System.out.println("failed test 8 of question 3");
        }
        Q3_res = ArrayUtils.alternateSum(Q3_test_9);
        if(Q3_expected_result_9!=Q3_res){
            System.out.println("failed test 9 of question 3");
        }
        Q3_res = ArrayUtils.alternateSum(Q3_test_10);
        if(Q3_expected_result_10!=Q3_res){
            System.out.println("failed test 10 of question 3");
        }

        /* Q4 findPath */

        int Q4_res;

        int[][] Q4_test_1 = {{1,0,0},{0,1,0},{0,0,1}};
        int[][] Q4_test_2 = {{1,0,0},{0,1,0},{0,0,1}};
        int[][] Q4_test_3 = {{1,0,0},{0,1,0},{0,0,1}};
        int[][] Q4_test_4 = {{0,1,0,0},{0,1,1,0},{0,0,1,1},{1,0,0,1}};
        int[][] Q4_test_5 = {{1,1,0,0},{0,1,1,0},{0,0,1,1},{1,0,0,1}};
        int[][] Q4_test_6 = {{0,1,0,0},{0,1,1,0},{0,0,1,1},{1,0,0,1}};
        int[][] Q4_test_7 = {{0,1,0,0},{0,0,1,0},{0,0,0,1},{1,0,0,0}};

        int Q4_expected_result_1 = 0;
        int Q4_expected_result_2 = 1;
        int Q4_expected_result_3 = 1;
        int Q4_expected_result_4 = 1;
        int Q4_expected_result_5 = 0;
        int Q4_expected_result_6 = 1;
        int Q4_expected_result_7 = 0;
        int Q4_expected_result_8 = 1;
        int Q4_expected_result_9 = 0;
        int Q4_expected_result_10 = 0;
        int Q4_expected_result_11 = 1;

//        Q4_res = ArrayUtils.findPath(Q4_test_1,1,1,0);
//        if(Q4_expected_result_1!=Q4_res){
//            System.out.println("failed test 1 of question 4");
//        }
//        Q4_res = ArrayUtils.findPath(Q4_test_2,1,1,1);
//        if(Q4_expected_result_2!=Q4_res){
//            System.out.println("failed test 2 of question 4");
//        }
//        Q4_res = ArrayUtils.findPath(Q4_test_3,1,1,2);
//        if(Q4_expected_result_3!=Q4_res){
//            System.out.println("failed test 3 of question 4");
//        }
//        Q4_res = ArrayUtils.findPath(Q4_test_4,0,2,2);
//        if(Q4_expected_result_4!=Q4_res){
//            System.out.println("failed test 4 of question 4");
//        }
//        Q4_res = ArrayUtils.findPath(Q4_test_5,0,2,1);
//        if(Q4_expected_result_5!=Q4_res){
//            System.out.println("failed test 5 of question 4");
//        }
//        Q4_res = ArrayUtils.findPath(Q4_test_6,0,3,3);
//        if(Q4_expected_result_6!=Q4_res){
//            System.out.println("failed test 6 of question 4");
//        }
//        Q4_res = ArrayUtils.findPath(Q4_test_7,0,0,7);
//        if(Q4_expected_result_7!=Q4_res){
//            System.out.println("failed test 7 of question 4");
//        }
//        Q4_res = ArrayUtils.findPath(Q4_test_7,0,0,8);
//        if(Q4_expected_result_8!=Q4_res){
//            System.out.println("failed test 8 of question 4");
//        }
//        Q4_res = ArrayUtils.findPath(Q4_test_7,0,0,9);
//        if(Q4_expected_result_9!=Q4_res){
//            System.out.println("failed test 9 of question 4");
//        }
//        Q4_res = ArrayUtils.findPath(Q4_test_7,0,0,10);
//        if(Q4_expected_result_10!=Q4_res){
//            System.out.println("failed test 10 of question 4");
//        }
//        Q4_res = ArrayUtils.findPath(Q4_test_7,0,3,11);
//        if(Q4_expected_result_11!=Q4_res){
//            System.out.println("failed test 11 of question 4");
//        }

        /* StringUtils testing */

            /* Q5 testing */

        String Q5_test_1 = "to be or not to be";
        String Q5_test_2 = "my mind is an empty zoo";
        String Q5_test_3 = "";
        String Q5_test_4 = "andy bought candy";
        String Q5_test_5 = "life is not not not fair";
        String Q5_test_6 = "art act";
        String Q5_test_7 = "act act";
        String Q5_test_8 = "act2 act act3";

        String Q5_expected_result_1 = "not to";
        String Q5_expected_result_2 = "an empty zoo";
        String Q5_expected_result_3 = "";
        String Q5_expected_result_4 = "andy bought candy";
        String Q5_expected_result_5 = "is not not not";
        String Q5_expected_result_6 = "act";
        String Q5_expected_result_7 = "act act";
        String Q5_expected_result_8 = "act act3";

//        if(!StringUtils.findSortedSequence(Q5_test_1).equals(Q5_expected_result_1)){
//            System.out.println("failed test 1 of question 5");
//        }
//        if(!StringUtils.findSortedSequence(Q5_test_2).equals(Q5_expected_result_2)){
//            System.out.println("failed test 2 of question 5");
//        }
//        if(!StringUtils.findSortedSequence(Q5_test_3).equals(Q5_expected_result_3)){
//            System.out.println("failed test 3 of question 5");
//        }
//        if(!StringUtils.findSortedSequence(Q5_test_4).equals(Q5_expected_result_4)){
//            System.out.println("failed test 4 of question 5");
//        }
//        if(!StringUtils.findSortedSequence(Q5_test_5).equals(Q5_expected_result_5)){
//            System.out.println("failed test 5 of question 5");
//        }
//        if(!StringUtils.findSortedSequence(Q5_test_6).equals(Q5_expected_result_6)){
//            System.out.println("failed test 6 of question 5");
//        }
//        if(!StringUtils.findSortedSequence(Q5_test_7).equals(Q5_expected_result_7)){
//            System.out.println("failed test 7 of question 5");
//        }
//        if(!StringUtils.findSortedSequence(Q5_test_8).equals(Q5_expected_result_8)){
//            System.out.println("failed test 8 of question 5");
//        }

            /* Q6 testing */

        String Q6_test_1_a = "mothEr in law";
        String Q6_test_1_b = "hitlEr woman";

        String Q6_test_2_a = "ListeN";
        String Q6_test_2_b = "SileNt";

        String Q6_test_3_a = "software";
        String Q6_test_3_b = "jeans";

        String Q6_test_4_a = "Funeral";
        String Q6_test_4_b = "real Fun";

        String Q6_test_5_a = "Aa";
        String Q6_test_5_b = "aA";

        String Q6_test_6_a = "";
        String Q6_test_6_b = "    ";

        String Q6_test_7_a = "A L M OST";
        String Q6_test_7_b = "A L M OS";


        if(StringUtils.isAnagram(Q6_test_1_a, Q6_test_1_b)!=true){
            System.out.println("failed test 1 of question 6");
        }
        if(StringUtils.isAnagram(Q6_test_2_a, Q6_test_2_b)!=true){
            System.out.println("failed test 2 of question 6");
        }
        if(StringUtils.isAnagram(Q6_test_3_a, Q6_test_3_b)!=false){
            System.out.println("failed test 3 of question 6");
        }
        if(StringUtils.isAnagram(Q6_test_4_a, Q6_test_4_b)!=true){
            System.out.println("failed test 4 of question 6");
        }
        if(StringUtils.isAnagram(Q6_test_5_a, Q6_test_5_b)!=true){
            System.out.println("failed test 5 of question 6");
        }
        if(StringUtils.isAnagram(Q6_test_6_a, Q6_test_6_b)!=true){
            System.out.println("failed test 6 of question 6");
        }
        if(StringUtils.isAnagram(Q6_test_7_a, Q6_test_7_b)!=false){
            System.out.println("failed test 7 of question 6");
        }
            /* Q7 testing */

        String Q7_test_1_a = "dog";
        String Q7_test_1_b = "god";
        String Q7_test_2_a = "x";
        String Q7_test_2_b = "x";
        String Q7_test_3_a = "main";
        String Q7_test_3_b = "man";
        String Q7_test_4_a = "ab";
        String Q7_test_4_b = "cab";
        String Q7_test_5_a = "dfab";
        String Q7_test_5_b = "dfcab";

        if(StringUtils.isEditDistanceOne(Q7_test_1_a, Q7_test_1_b)!=false){
            System.out.println("failed test 1 of question 7");
        }
        if(StringUtils.isEditDistanceOne(Q7_test_2_a, Q7_test_2_b)!=true){
            System.out.println("failed test 2 of question 7");
        }
        if(StringUtils.isEditDistanceOne(Q7_test_3_a, Q7_test_3_b)!=true){
            System.out.println("failed test 3 of question 7");
        }
        if(StringUtils.isEditDistanceOne(Q7_test_4_a, Q7_test_4_b)!=true){
            System.out.println("failed test 4 of question 7");
        }
        if(StringUtils.isEditDistanceOne(Q7_test_5_a, Q7_test_5_b)!=true){
            System.out.println("failed test 5 of question 7");
        }

        System.out.println("All done!");

    }





}
