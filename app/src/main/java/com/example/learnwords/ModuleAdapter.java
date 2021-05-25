package com.example.learnwords;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Module> modules;

    ModuleAdapter(Context context, ArrayList<Module> modules){
        this.context = context;
        this.modules = modules;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.module_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final int pos = position;
        DatabaseHelper db = new DatabaseHelper(context);
        int termNumber = modules.get(pos).getTermNumber();
        final String moduleName = modules.get(position).getName();
        String termForm = termNumber == 1 ? "term": "terms";

        holder.moduleTitle.setText(moduleName);
        holder.termNumber.setText(String.format("%s %s", String.valueOf(termNumber), termForm));
        holder.moduleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LearnActivity.class);
                intent.putExtra("moduleName", modules.get(pos).getName());
                context.startActivity(intent);
            }
        });
        holder.editMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }

            private void showPopupMenu(View view){
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                popupMenu.inflate(R.menu.edit_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.deleteModule) {
                            deleteModule(moduleName);
                            return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();

            }
        });
    }
    private void deleteModule(String moduleName) {
        final String name = moduleName;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete Module?");
        builder.setMessage("Are you sure you want to delete module data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper db = new DatabaseHelper(context);
                db.deleteModuleData(name);

                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }

    private void editModule(String moduleName) {
        final String name = moduleName;
        Intent intent = new Intent(context, AddActivity.class);
        intent.putExtra("moduleNames", modules);
        context.startActivity(intent);

    }



    @Override
    public int getItemCount() {
        return modules.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView moduleTitle, termNumber;
        ConstraintLayout moduleView;
        ImageView editMenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            moduleTitle = itemView.findViewById(R.id.moduleTitle);
            termNumber = itemView.findViewById(R.id.termNumber);
            moduleView = itemView.findViewById(R.id.module);
            editMenu = itemView.findViewById(R.id.editMenu);
        }
    }

}
