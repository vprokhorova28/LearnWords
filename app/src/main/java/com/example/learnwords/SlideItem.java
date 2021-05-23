package com.example.learnwords;

import android.widget.TextView;

public class SlideItem {

    private String term;

    SlideItem(String term){
        this.term = term;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
