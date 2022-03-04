package com.company;

public class Wagon {
    private String meaning;
    private int priority;

    public Wagon(String meaning, int priority) {
        this.meaning = meaning;
        this.priority = priority;

    }

    @Override
    public String toString() {
        return meaning;
    }

    public String getMeaning() {
        return meaning;
    }

    public int getPriority() {
        return priority;
    }
}
