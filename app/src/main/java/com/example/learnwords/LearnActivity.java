package com.example.learnwords;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class LearnActivity extends AppCompatActivity {

    ViewPager2 termViewPager;
    ViewPagerAdapter adapter;
    TextView title;
    TextView termNumber;
    String moduleName;
    ArrayList<SlideItem> termCards;
    ArrayList<String> terms;
    ArrayList<String> descs;
    DatabaseHelper db;
    ArrayList<TextView> dots;
    LinearLayout dotsContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        getSupportActionBar().setElevation(0);

        termViewPager = findViewById(R.id.termViewPager);
        title = findViewById(R.id.moduleName_tv);
        termNumber = findViewById(R.id.termNumber_tv);
        dotsContainer = findViewById(R.id.dots_container);
        termCards = new ArrayList<>();
        terms = new ArrayList<>();
        descs = new ArrayList<>();

        getData();
        db = new DatabaseHelper(LearnActivity.this);
        storeModuleData(moduleName, termCards);
        title.setText(moduleName);
        String termForm = termCards.size() == 1 ? "term": "terms";
        termNumber.setText(String.format("%s %s", termCards.size(), termForm));


        for (SlideItem item: termCards){
            terms.add(item.getTerm());
            descs.add(item.getDesc());
        }

        adapter = new ViewPagerAdapter(terms);
        termViewPager.setAdapter(adapter);

        termViewPager.setClipToPadding(false);
        termViewPager.setClipChildren(false);
        termViewPager.setOffscreenPageLimit(3);
        termViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(10));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.95f + r * 0.05f);
            }
        });
        termViewPager.setPageTransformer(compositePageTransformer);

        dots = new ArrayList<>();
        dotsIndicator();
        termViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                selectedIndicator(position);
            }
        });
    }

    private void selectedIndicator(int position) {
        for (int i = 0; i < dots.size(); i++){
            if (i == position){
                dots.get(i).setTextColor(getResources().getColor(R.color.colorAccent));
            } else{
                dots.get(i).setTextColor(getResources().getColor(R.color.grey));
            }
        }

    }

    private void getData(){
        if(getIntent().hasExtra("moduleName")){
            moduleName = getIntent().getStringExtra("moduleName");
        }
        else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    void storeModuleData(String module, ArrayList<SlideItem> moduleData){
        Cursor cursor = db.readAllModuleData(module);
        if (cursor.getCount() == 0 ){
        }
        else {
            while (cursor.moveToNext()){
                String termName = cursor.getString(1);
                String termDesc = cursor.getString(2);
                SlideItem item = new SlideItem(termName, termDesc);
                moduleData.add(item);
            }
        }
    }

    void dotsIndicator(){
        for (int i = 0; i < terms.size(); i++){
            dots.add(new TextView(this));
            dots.get(i).setText(String.format("%s ", Html.fromHtml("&#9679;")));
            dots.get(i).setTextSize(18);
            dotsContainer.addView(dots.get(i));
        }
    }
}