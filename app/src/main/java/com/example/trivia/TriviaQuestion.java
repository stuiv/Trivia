package com.example.trivia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class TriviaQuestion implements Serializable {
    private String category;
    private String type;
    private String difficulty;
    private String correct_answer;
    private String question;
    private ArrayList<String> incorrect_answers;
    private ArrayList<String> all_answers;

    // initialize the items with all details.
    public TriviaQuestion(String c, String t, String d, String cor, String q, ArrayList<String> i) {
        category = c;
        type = t;
        difficulty = d;
        correct_answer = cor;
        question = q;
        incorrect_answers = i;
        all_answers = i;
        all_answers.add(cor);
        Collections.shuffle(all_answers);
    }

    public ArrayList<String> getAll_answers() {
        return all_answers;
    }

    public void setAll_answers(ArrayList<String> all_answers) {
        this.all_answers = all_answers;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getIncorrect_answers() {
        return incorrect_answers;
    }

    public void setIncorrect_answers(ArrayList<String> incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }
}