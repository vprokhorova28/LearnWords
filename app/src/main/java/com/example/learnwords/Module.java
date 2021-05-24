package com.example.learnwords;

public class Module {
    private String name;
    private int termNumber;

    Module(String name, int termNumber){
        this.name = name;
        this.termNumber = termNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTermNumber() {
        return termNumber;
    }

    public void setTermNumber(int termNumber) {
        this.termNumber = termNumber;
    }
}
