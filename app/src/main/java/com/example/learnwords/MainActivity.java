package com.example.learnwords;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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
    ImageView imageView;
    TextView noData;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        moduleRecyclerView = findViewById(R.id.moduleRecyclerView);
        noData = findViewById(R.id.noData);

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

        storeModules();
    }

    void storeModules(){
        Cursor cursor = db.readAllModules();
        if (cursor.getCount() == 0 ){
            imageView.setVisibility(View.VISIBLE);
            noData.setVisibility(View.VISIBLE);
        }
        else {
            while (cursor.moveToNext()){
                modules.add(cursor.getString(0));
                imageView.setVisibility(View.GONE);
                noData.setVisibility(View.GONE);
            }
        }
    }


    void storeDataInArrays(){
        Cursor cursor = db.readAllData();
        if (cursor.getCount() == 0 ){

        }
        else {
            while (cursor.moveToNext()){
                term_id.add(cursor.getString(0));
                term.add(cursor.getString(1));
                desc.add(cursor.getString(2));
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