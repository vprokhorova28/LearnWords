package com.example.learnwords;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        terms = termAdapter.getTerms();
        descs = termAdapter.getDescs();

        addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int termNumber = termAdapter.getTerms().size();
                DatabaseHelper db = new DatabaseHelper(AddActivity.this);
                if (termNumber >= 2){
                    for (int i = 0; i < termNumber; i++){
                        db.addTerm(terms.get(i), descs.get(i),
                                moduleNameInput.getText().toString().trim());
                    }
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    warnDialog();
                }
            }
        });

        addTermBtn = findViewById(R.id.addTerm_btn);
        addTermBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                termAdapter.getTerms().add("");
                termAdapter.getDescs().add("");
                termAdapter.notifyDataSetChanged();
            }
        });
    }

    private void warnDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setMessage("You must add at least two terms to save your module.");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }
}