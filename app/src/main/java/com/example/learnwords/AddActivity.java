package com.example.learnwords;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    EditText moduleNameInput;
    Button addBtn;
    RecyclerView termRecyclerView;
    ArrayList<String> terms, descs;
    DatabaseHelper db;
    FloatingActionButton addTermBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        moduleNameInput = findViewById(R.id.module_mame_input);

        final TermAdapter termAdapter = new TermAdapter(AddActivity.this);
        termRecyclerView = findViewById(R.id.termRecyclerView);
        termRecyclerView.setAdapter(termAdapter);
        termRecyclerView.setLayoutManager(new LinearLayoutManager(AddActivity.this));

        terms = termAdapter.getTerm();
        descs = termAdapter.getDesc();

        addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(AddActivity.this);
                for (int i = 0; i < termAdapter.getTerm().size(); i++){
                    db.addTerm(terms.get(i), descs.get(i),
                            moduleNameInput.getText().toString().trim());
                }
            }
        });

        addTermBtn = findViewById(R.id.addTerm_btn);
        addTermBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                termAdapter.setItemCount(termAdapter.getItemCount() + 1);
                termAdapter.notifyDataSetChanged();
            }
        });
    }
}