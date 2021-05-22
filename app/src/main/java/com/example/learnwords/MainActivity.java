package com.example.learnwords;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView moduleRecyclerView;
    ModuleAdapter moduleAdapter;
    ArrayList<String> modules;
    FloatingActionButton addBtn;

    DatabaseHelper db;
    ArrayList<String> term_id, term, desc;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moduleRecyclerView = findViewById(R.id.moduleRecyclerView);

        db = new DatabaseHelper(MainActivity.this);

        term_id = new ArrayList<>();
        term = new ArrayList<>();
        desc = new ArrayList<>();
        modules = new ArrayList<>();

        storeDataInArrays();

        moduleAdapter = new ModuleAdapter(this, modules);
        moduleRecyclerView.setAdapter(moduleAdapter);
        moduleRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        moduleRecyclerView.setOnScrollChangeListener(new RecyclerView.OnScrollChangeListener() {

            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY - oldScrollY > 0){
                    addBtn.hide();
                }
                else{
                    addBtn.show();
                }
            }
        });

        addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }


    void storeDataInArrays(){
        Cursor cursor = db.readAllData();
        if (cursor.getCount() == 0 ){
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                term_id.add(cursor.getString(0));
                term.add(cursor.getString(1));
                desc.add(cursor.getString(2));
                modules.add(cursor.getString(3));
            }
        }
    }
}