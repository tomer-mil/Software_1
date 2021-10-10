package il.ac.tau.cs.sw1.trivia;

import java.io.*;
import java.util.*;

public class QuestionsManager {

    private final ArrayList<Question> questionsDB = new ArrayList<>();
    private final Iterator<Question> questionIterator;
    private Question currentQuestion;
    private int qLeft;


    public QuestionsManager(String filePath) throws IOException {
        createDB(filePath);
        Collections.shuffle(questionsDB);
        questionIterator = questionsDB.iterator();
    }

    private void createDB(String filePath) throws IOException {
        File fileFromPath = new File(filePath);
        BufferedReader BR = new BufferedReader(new FileReader(fileFromPath));

        String line;
        while ((line = BR.readLine()) != null) {
            String[] splitLine = line.split("\t");
            List<String> splitLineList = Arrays.asList(splitLine);
            String q = splitLineList.get(0);
            Question question = new Question(q, splitLineList.subList(1, splitLine.length));
            questionsDB.add(question);
        }
        qLeft = questionsDB.size();
    }

    public boolean isEmpty() {
        return qLeft == 0;
    }

    public Question nextQuestion() {

        if (questionIterator.hasNext()) {
            Question aQuestion = questionIterator.next();
//            aQuestion.setAsAsked();
            currentQuestion = aQuestion;
            qLeft--;
            return aQuestion;
        }

        return null;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public class Question {
        private final String question;
        private final List<String> answers;
        private final String correctAnswer;
//        private boolean wasAsked = false;

        public Question(String q, List<String> a) {
            this.question = q;
            this.answers = a;
            this.correctAnswer = answers.get(0);
            Collections.shuffle(answers);
        }

        public String getQuestionTitle() {
            return question;
        }

        public List<String> getAnswersList() {
            return answers;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

//        public void setAsAsked() {
//            this.wasAsked = true;
//        }


    }

}
