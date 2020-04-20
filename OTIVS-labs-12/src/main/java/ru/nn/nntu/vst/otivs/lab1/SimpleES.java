package ru.nn.nntu.vst.otivs.lab1;

import ru.nn.nntu.vst.otivs.model.Questions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleES {
    public static void main(String[] args) throws IOException {
        System.out.println("\nСистема диагностики неисправности персонального компьютера." +
                "\nСистема состоит из 11 вопросов. Введите 'Y' для ответа 'Да' или 'N' для ответа 'Нет'.\n");

        startInteract();
    }

    @SuppressWarnings("DuplicatedCode")
    private static void startInteract() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(Questions.S1);
        if (readAnswer(reader).equals("y")) {
            System.out.println(Questions.S6);
            if (readAnswer(reader).equals("y")) {
                System.out.println(Questions.S7);
                if (readAnswer(reader).equals("y")) {
                    System.out.println(Questions.S8);
                    if (readAnswer(reader).equals("y")) {
                        System.out.println(Questions.F5);
                    } else {
                        System.out.println(Questions.F6);
                    }
                } else {
                    System.out.println(Questions.F7);
                }
            } else {
                System.out.println(Questions.S9);
                if (readAnswer(reader).equals("y")) {
                    System.out.println(Questions.S11);
                    if (readAnswer(reader).equals("y")) {
                        System.out.println(Questions.F8);
                    } else {
                        System.out.println(Questions.F9);
                    }
                } else {
                    System.out.println(Questions.S10);
                    if (readAnswer(reader).equals("y")) {
                        System.out.println(Questions.F10);
                    } else {
                        System.out.println(Questions.F11);
                    }
                }
            }
        } else {
            System.out.println(Questions.S2);
            if (readAnswer(reader).equals("y")) {
                System.out.println(Questions.S3);
                if (readAnswer(reader).equals("y")) {
                    System.out.println(Questions.F2);
                } else {
                    System.out.println(Questions.S4);
                    if (readAnswer(reader).equals("y")) {
                        System.out.println(Questions.S5);
                        if (readAnswer(reader).equals("y")) {
                            System.out.println(Questions.F2);
                        } else {
                            System.out.println(Questions.F3);
                        }
                    } else {
                        System.out.println(Questions.F4);
                    }
                }
            } else {
                System.out.println(Questions.F1);
            }
        }
    }

    private static String readAnswer(BufferedReader reader) throws IOException {
        String answer;
        do {
            answer = reader.readLine();
        } while ((!answer.toLowerCase().equals("y")) && (!answer.toLowerCase().equals("n")));
        return answer;
    }

}
