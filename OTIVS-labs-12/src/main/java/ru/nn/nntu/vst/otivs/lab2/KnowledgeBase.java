package ru.nn.nntu.vst.otivs.lab2;

import ru.nn.nntu.vst.otivs.model.QuestionEntity;
import ru.nn.nntu.vst.otivs.model.Questions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Продукционная база знаний. Содержит множество правил продукции,
 * которые описывают предметную область.
 *
 * Метод {@link KnowledgeBase#init} задает множество продукционных правил
 *
 * Конструктор {@link KnowledgeBase#KnowledgeBase(java.util.LinkedList)} используется для задания списка
 * продукционных правил из БД в третьей лабе.
 */

@SuppressWarnings("DuplicatedCode")
public class KnowledgeBase {
    private LinkedList<QuestionEntity> decisionTree;

    KnowledgeBase() {
        this.decisionTree = new LinkedList<>();
    }

    public KnowledgeBase(LinkedList<QuestionEntity> decisionTree) {
        this.decisionTree = decisionTree;
    }

    List<QuestionEntity> getDecisionTree() {
        return decisionTree;
    }

    void init() {
        decisionTree.add(new QuestionEntity(Questions.S1, Questions.S6, Questions.S2));
        decisionTree.add(new QuestionEntity(Questions.S2, Questions.S3, Questions.F1));
        decisionTree.add(new QuestionEntity(Questions.S3, Questions.F2, Questions.S4));
        decisionTree.add(new QuestionEntity(Questions.S4, Questions.S5, Questions.F4));
        decisionTree.add(new QuestionEntity(Questions.S5, Questions.F2, Questions.F3));
        decisionTree.add(new QuestionEntity(Questions.S6, Questions.S7, Questions.S9));
        decisionTree.add(new QuestionEntity(Questions.S7, Questions.S8, Questions.F7));
        decisionTree.add(new QuestionEntity(Questions.S8, Questions.F5, Questions.F6));
        decisionTree.add(new QuestionEntity(Questions.S9, Questions.S11, Questions.S10));
        decisionTree.add(new QuestionEntity(Questions.S10, Questions.F10, Questions.F11));
        decisionTree.add(new QuestionEntity(Questions.S11, Questions.F8, Questions.F9));
    }

    void update(QuestionEntity q) {
        QuestionEntity foundQuestion = null;
        for (QuestionEntity qq : decisionTree) {
            if (qq.getQuestionText().equals(q.getQuestionText())) {
                foundQuestion = q;
            }
        }
        if (foundQuestion != null) {
            decisionTree.remove(foundQuestion);
            decisionTree.add(q);
        }
    }

    QuestionEntity getQuestionByText(String text) {
        for (QuestionEntity qq : decisionTree) {
            if (qq.getQuestionText().equals(text)) return qq;
        }
        return null;
    }
}
