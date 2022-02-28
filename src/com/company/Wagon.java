package com.company;

public class Wagon {
    private String meaning;
    private Boolean sign;

    public Wagon(String meaning, Boolean sign) {
        this.meaning = meaning;
        this.sign = sign;
    }

    @Override
    public String toString() {
        return meaning;
    }
}
