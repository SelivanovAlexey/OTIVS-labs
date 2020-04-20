package ru.nn.nntu.vst.otivs.lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ru.nn.nntu.vst.otivs.model.QuestionEntity;

public class ComplexES {

    public static void main(String[] args) throws IOException {

        KnowledgeBase tree = new KnowledgeBase();
        tree.init();

        startInteract(tree);
    }

    public static void startInteract(KnowledgeBase tree) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nСистема диагностики неисправности персонального компьютера." +
                "\nСистема состоит из 11 вопросов. Введите 'Y' для ответа 'Да' или 'N' для ответа 'Нет'.\n");
        int counter = 1;
        boolean next = true;
        String currentQuestion = tree.getDecisionTree().get(0).getQuestionText();

        while (next) {
            QuestionEntity question = tree.getQuestionByText(currentQuestion);
            String answer;

            System.out.println(counter++ + ". " + question.getQuestionText());
            question.setAsked(true);
            tree.update(question);

            do {
                answer = reader.readLine();
            } while ((!answer.toLowerCase().equals("y")) && (!answer.toLowerCase().equals("n")));

            switch (answer) {
                case "y":
                case "Y":
                    next = getNextQuestion(tree, question.getAnswPositive());
                    currentQuestion = question.getAnswPositive();
                    break;
                case "n":
                case "N":
                    next = getNextQuestion(tree, question.getAnswNegative());
                    currentQuestion = question.getAnswNegative();
                    break;
                default:
                    next = false;
                    System.out.println("Для ответа введите Y или N");
                    break;
            }
        }

        System.out.println("Результат: " + currentQuestion);
        System.out.println("Диагностика завершена");
    }

    private static boolean getNextQuestion(KnowledgeBase tree, String question) {
        boolean next = false;
        for (QuestionEntity q : tree.getDecisionTree()) {
            if (question.equals(q.getQuestionText()) && !q.isAsked()) {
                next = true;
                break;
            }
        }
        return next;
    }
}
