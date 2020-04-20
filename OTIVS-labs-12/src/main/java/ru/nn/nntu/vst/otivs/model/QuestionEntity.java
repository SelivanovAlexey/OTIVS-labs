package ru.nn.nntu.vst.otivs.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionEntity {
    @JsonProperty("question")
    private String questionText;

    @JsonProperty("positive")
    private String answPositive;

    @JsonProperty("negative")
    private String answNegative;

    private boolean isAsked;

    /**
     * С помощью данного конструктора можно задавать продукционное правило
     *
     * @param questionText значение переменной Si
     * @param answPositive значение переменной r1 (Вариант при положительном ответе)
     * @param answNegative значение переменной r2 (Вариант при отрицательном ответе)
     */
    public QuestionEntity(String questionText, String answPositive, String answNegative) {
        this.questionText = questionText;
        this.answPositive = answPositive;
        this.answNegative = answNegative;
        this.isAsked = false;
    }

    public QuestionEntity() {
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswPositive() {
        return answPositive;
    }

    public String getAnswNegative() {
        return answNegative;
    }

    public boolean isAsked() {
        return isAsked;
    }

    public void setAsked(boolean asked) {
        isAsked = asked;
    }
}
