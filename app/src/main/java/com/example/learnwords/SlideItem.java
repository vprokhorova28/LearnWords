package com.example.learnwords;

import android.widget.TextView;

public class SlideItem {

    private String term, desc;

    SlideItem(String term, String desc){
        this.term = term;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
