package com.example.learnwords;

import android.widget.TextView;

import java.io.Serializable;

public class SlideItem implements Serializable {

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
