package il.ac.tau.cs.sw1.ex4;

import java.util.Arrays;



public class MyWordPuzzleTester {

    public static void main(String[] args) {
        boolean[] template = {true, false, true, true, false};
        String word = "look";
        char[] puzzle = WordPuzzle.createPuzzleFromTemplate(word, template);
        boolean isLegal = WordPuzzle.checkLegalTemplate(word, template);
        System.out.print(puzzle);
        System.out.println();
        System.out.print(isLegal);
        System.out.println();
        int n = 8;
        int k = 5;

        System.out.println(WordPuzzle.factorial(1));
        System.out.println(WordPuzzle.nCk(n, k));

        System.out.println(Arrays.deepToString(WordPuzzle.getAllLegalTemplates(word, 1)));
        char[] secondPuzzle = {'w', '_', '_', '_', 'p', 's'};
        System.out.println(WordPuzzle.applyGuess('g', "wheeps", secondPuzzle));
        System.out.print(Arrays.toString(secondPuzzle));

    }


}
