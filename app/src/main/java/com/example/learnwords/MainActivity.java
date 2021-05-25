package com.example.learnwords;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView moduleRecyclerView;
    ModuleAdapter moduleAdapter;
    ArrayList<String> moduleNames;
    FloatingActionButton addBtn;
    DatabaseHelper db;
    HashMap<String, ArrayList<Term>> modulesData;

    ImageView imageView;
    TextView noData;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);

        imageView = findViewById(R.id.imageView);
        moduleRecyclerView = findViewById(R.id.moduleRecyclerView);
        noData = findViewById(R.id.noData);

        db = new DatabaseHelper(MainActivity.this);

        moduleNames = new ArrayList<>();
        storeModules();

        modulesData = new HashMap<>();
        for (String module: moduleNames){
            ArrayList<Term> moduleData = new ArrayList<>();
            modulesData.put(module, moduleData);
            storeModuleData(module, moduleData);
            modulesData.put(module, moduleData);
        }
        ArrayList<Module> modules = new ArrayList<>();
        for (Map.Entry entry : modulesData.entrySet()) {
            String moduleName = entry.getKey().toString();
            ArrayList<Term> moduleData = (ArrayList<Term>) entry.getValue();
            int termNumber = moduleData.size();
            modules.add(new Module(moduleName, termNumber));
        }

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

    void storeModules(){
        Cursor cursor = db.readAllModules();
        if (cursor.getCount() == 0 ){
            imageView.setVisibility(View.VISIBLE);
            noData.setVisibility(View.VISIBLE);
        }
        else {
            while (cursor.moveToNext()){
                moduleNames.add(cursor.getString(0));
                imageView.setVisibility(View.GONE);
                noData.setVisibility(View.GONE);
            }
        }
    }

     void storeModuleData(String module, ArrayList<Term> moduleData){
        Cursor cursor = db.readAllModuleData(module);
        if (cursor.getCount() == 0 ){
        }
        else {
            while (cursor.moveToNext()){
                String termId = cursor.getString(0);
                String termName = cursor.getString(1);
                String termDesc = cursor.getString(2);
                Term term = new Term(termId, termName, termDesc);
                moduleData.add(term);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.deleteAll) {
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper db = new DatabaseHelper(MainActivity.this);
                db.deleteAllData();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }
}