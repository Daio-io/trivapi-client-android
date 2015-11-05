package io.daio.trivapiclient;

import java.util.List;

public class TrivapiResult {

    private String question;
    private String answer;
    private List<String> options;
    private TrivapiCategory category;
    private TrivapiDifficulty difficulty;

    public TrivapiResult(String question, String answer, List<String> options,
                         TrivapiCategory category, TrivapiDifficulty difficulty) {

        this.question = question;
        this.answer = answer;
        this.options = options;
        this.category = category;
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public List<String> getOptions() {
        return options;
    }

    public TrivapiCategory getCategory() {
        return category;
    }

    public TrivapiDifficulty getDifficulty() {
        return difficulty;
    }
}
