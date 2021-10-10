package il.ac.tau.cs.sw1.ex5;

import java.io.IOException;
import java.util.Arrays;


public class MyBiagramTester {

    public static void main(String[] args) throws IOException {
        String[] test = new String[4];
        test[0] = "hi";
        String allUNeedPath = "/Users/tomermildworth/Documents/University/Semester_b/Software1/Assignments/hw5-mildworth/resources/hw5/emma.txt";
        BigramModel testBiagram = new BigramModel();
        String[] voc = {};

        try {
            voc = testBiagram.buildVocabularyIndex(allUNeedPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("voc: " + Arrays.toString(voc));
        System.out.println("length: " + voc.length);

        int[][] countsArray = testBiagram.buildCountsArray(allUNeedPath, voc);
//        System.out.println(Arrays.deepToString(countsArray));

//        testBiagram.saveModel(allUNeedPath);
        testBiagram.initModel(allUNeedPath);
//        testBiagram.writeVocFile(allUNeedPath);
        System.out.println(testBiagram.mVocabulary.length);


    }





}
