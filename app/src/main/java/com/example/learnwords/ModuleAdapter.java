package com.example.learnwords;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        String termForm = termNumber == 1 ? "term": "terms";

        holder.moduleTitle.setText(modules.get(position).getName());
        holder.termNumber.setText(String.format("%s %s", String.valueOf(termNumber), termForm));
        holder.moduleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LearnActivity.class);
                intent.putExtra("moduleName", modules.get(pos).getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView moduleTitle, termNumber;
        ConstraintLayout moduleView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            moduleTitle = itemView.findViewById(R.id.moduleTitle);
            termNumber = itemView.findViewById(R.id.termNumber);
            moduleView = itemView.findViewById(R.id.module);
        }
    }

}
