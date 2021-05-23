package com.example.learnwords;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LearnActivity extends AppCompatActivity {

    ViewPager2 termViewPager;
    ModuleAdapter adapter;
    TextView title;
    String moduleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        termViewPager = findViewById(R.id.termViewPager);
        title = findViewById(R.id.moduleName_tv);

        getData();
        setData();

        ArrayList<String> terms = new ArrayList<>();
        terms.add("a");
        terms.add("b");
        terms.add("c");
        terms.add("d");

        adapter = new ModuleAdapter(LearnActivity.this, terms);
        termViewPager.setAdapter(adapter);
        termViewPager.setClipChildren(false);
        termViewPager.setOffscreenPageLimit(3);
        termViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
    }

    private void getData(){
        if(getIntent().hasExtra("moduleName")){
            moduleName = getIntent().getStringExtra("moduleName");
        }
        else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        title.setText(moduleName);
    }
}