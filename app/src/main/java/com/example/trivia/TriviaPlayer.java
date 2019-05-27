package com.example.trivia;

import java.io.Serializable;

// Class which holds a trivia players name, score, date and id.
public class TriviaPlayer implements Serializable {
    private int score;
    private String name;
    private String date;
    private int id;

    public TriviaPlayer(int score, String name, String date, int id) {
        this.score = score;
        this.name = name;
        this.date = date;
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}