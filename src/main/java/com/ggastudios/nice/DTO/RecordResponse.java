package com.ggastudios.nice.DTO;

public class RecordResponse {

    private String name;

    private int score;

    private int position;

    public RecordResponse(Record record) {
        this.name = record.getName();
        this.score = record.getScore();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
